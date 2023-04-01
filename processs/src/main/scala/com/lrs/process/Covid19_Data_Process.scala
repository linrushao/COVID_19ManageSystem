package com.lrs.process

import java.sql.PreparedStatement
import java.util
import com.alibaba.fastjson.JSON
import com.lrs.bean.{CovidBean, StatisticsDataBean}
import com.lrs.util.BaseJdbcSink
import org.apache.spark.SparkContext
import org.apache.spark.sql.functions.sum
import org.apache.spark.sql.streaming.Trigger
import org.apache.spark.sql.{DataFrame, Dataset, ForeachWriter, Row, SparkSession}

import scala.collection.immutable.StringOps
import scala.collection.mutable

/**
 * @Author LRS
 * @Date 2022/7/12 16:12
 *       Desc  全国各省市疫情数据实时处理统计分析
 */
object Covid19_Data_Process {
  def main(args: Array[String]): Unit = {
    //1.创建StructuredStreaming执行环境
    //structuredStreaming支持使用SQL来处理实时流数据，数据抽象和SparkSQL一样，也是DataFrame和DataSet
    //所以这里创建structuredStreaming执行环境就直接创建SparkSession即可
    val spark: SparkSession = SparkSession.builder().master("local[*]").appName("Covid19_Data_Process").getOrCreate()
    val sc: SparkContext = spark.sparkContext
    sc.setLogLevel("WARN")
    //导入隐式转换方便后续使用
    import spark.implicits._
    import org.apache.spark.sql.functions.to_date
    import scala.collection.JavaConversions._

    //2.连接Kafka
    //从Kafka接收消息
    val kafkaDF: DataFrame = spark.readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", "hadoop201:9092,hadoop202:9092,hadoop203:9092")
      .option("subscribe", "covid19")
      .load()

    //取出消息中的value
    val jsonStrDS: Dataset[String] = kafkaDF.selectExpr("CAST(value AS STRING)").as[String]
    //    jsonStrDS.writeStream
    //      .format("console") //输出目的地
    //      .outputMode("append") //输出模式，默认就是append表示显示新增行
    //      .trigger(Trigger.ProcessingTime(0)) //触发间隔，0表示尽可能快的执行
    //      .option("truncate", false) //表示如果列名过长不进行截断
    //      .start()
    //      .awaitTermination()


    //3.处理数据
    //将jsonStr转为样例类
    val covidBeanDS: Dataset[CovidBean] = jsonStrDS.map(jsonStr => {
      //注意：Scala中获取class对象使用classOf[类名]
      //java中使用类名，class/Class.forName(全类路径)/对象.getClass()
      JSON.parseObject(jsonStr, classOf[CovidBean])

    })

    //分离出省份数据
    val provinceDS: Dataset[CovidBean] = covidBeanDS.filter(_.statisticsData != null)

    //分离出城市数据
    val cityDS: Dataset[CovidBean] = covidBeanDS.filter(_.statisticsData == null)

    //分理出各省份每一天的统计数据
    val statisticsDataDS: Dataset[StatisticsDataBean] = provinceDS.flatMap(p => {
      val jsonStr: StringOps = p.statisticsData //获取到的是该省份每一天的统计数据组成的jsonStr数组
      val list: mutable.Buffer[StatisticsDataBean] = JSON.parseArray(jsonStr, classOf[StatisticsDataBean])
      list.map(s => {
        s.provinceShortName = p.provinceShortName
        s.locationId = p.locationId
        s
      })
    })

    //    statisticsDataDS.writeStream
    //          .format("console") //输出目的地
    //          .outputMode("append") //输出模式，默认就是append表示显示新增行
    //          .trigger(Trigger.ProcessingTime(0)) //触发间隔，0表示尽可能快的执行
    //          .option("truncate", false) //表示如果列名过长不进行截断
    //          .start()
    //          .awaitTermination()
    /*
+--------+-----------------+----------+--------------+---------------------+--------------------+-------------+----------+---------+------------------+--------------+---------+--------+
|dateId  |provinceShortName|locationId|confirmedCount|currentConfirmedCount|currentConfirmedIncr|confirmedIncr|curedCount|curedIncr|suspectedCountIncr|suspectedCount|deadCount|deadIncr|
+--------+-----------------+----------+--------------+---------------------+--------------------+-------------+----------+---------+------------------+--------------+---------+--------+
|20200122|台湾               |710000    |1             |1                    |1                   |1            |0         |0        |0                 |0             |0        |0       |
|20200123|台湾               |710000    |1             |1                    |0                   |0            |0         |0        |0                 |0             |0        |0       |
|20200124|台湾               |710000    |3             |3                    |2                   |2            |0         |0        |0                 |0             |0        |0       |

     */

    //4.统计分析
    //4.1.全国疫情汇总信息：现有确证，累积确诊，现有疑似，累积治愈，累积死亡--注意，按照日期分组统计
    val result1: DataFrame = provinceDS.groupBy("datetime")
      .agg(sum('currentConfirmedCount) as "currentConfirmedCount", //现有确诊
        sum('confirmedCount) as "confirmedCount", //累计确诊
        sum('suspectedCount) as "suspectedCount", //现有疑似
        sum('curedCount) as "curedCount", //累计治愈
        sum('deadCount) as "deadCount" //累计死亡
      )

    //4.2.全国各省份累积确诊数地图--注意：按照日期-省份分组
    //    cityDS.groupBy('datetime,'provinceShortName)
    //      .agg(sum('confirmedCount) as "confirmedCount" )
    val result2: DataFrame = provinceDS.select('datetime, 'locationId, 'provinceShortName, 'currentConfirmedCount, 'confirmedCount, 'suspectedCount, 'curedCount, 'deadCount)

    //4.3.全国疫情趋势--注意：按照日期分组聚合
    val result3: DataFrame = statisticsDataDS.groupBy('dateId)
      .agg(
        sum('confirmedIncr) as "confirmedIncr", //新增确诊
        sum('confirmedCount) as "confirmedCount", //累计确诊
        sum('suspectedCount) as "suspectedCount", //累计疑似
        sum('curedCount) as "curedCount", //累计治愈
        sum('deadCount) as "deadCount" //累计死亡
      )

    //4.4.境外输入排行--注意：按照城市分组聚合
    val result4: Dataset[Row] = cityDS.filter(_.cityName.contains("境外输入"))
      .groupBy('datetime, 'provinceShortName, 'pid)
      .agg(sum('confirmedCount) as "confirmedCount")
      .sort('confirmedCount.desc)

    //4.5.统计北京市的累积确诊地图
    val result5: DataFrame = cityDS.filter(_.provinceShortName.equals("北京")).select('datetime, 'locationId, 'provinceShortName, 'cityName, 'currentConfirmedCount, 'confirmedCount, 'suspectedCount, 'curedCount, 'deadCount)
    //.....

    //5.结果输出--先输出到控制台观察,最终输出到MySQL
    result1.writeStream
      .format("console")
      //输出模式:
      //1.append:默认的,表示只输出新增的数据,只支持简单的查询,不支持聚合
      //2.complete:表示完整模式,所有数据都会输出,必须包含聚合操作
      //3.update:表示更新模式,只输出有变化的数据,不支持排序
      .outputMode("complete")
      .trigger(Trigger.ProcessingTime(0))
      .option("truncate", false)
      .start()
    //    .awaitTermination()
    /*
    +----------+---------------------+--------------+--------------+----------+---------+
    |datetime  |currentConfirmedCount|confirmedCount|suspectedCount|curedCount|deadCount|
    +----------+---------------------+--------------+--------------+----------+---------+
    |2020-05-28|111                  |84547         |1476          |79791     |4645     |
    +----------+---------------------+--------------+--------------+----------+---------+
    CREATE TABLE `covid19_1` (
      `datetime` varchar(20) NOT NULL DEFAULT '',
      `currentConfirmedCount` bigint(20) DEFAULT '0',
      `confirmedCount` bigint(20) DEFAULT '0',
      `suspectedCount` bigint(20) DEFAULT '0',
      `curedCount` bigint(20) DEFAULT '0',
      `deadCount` bigint(20) DEFAULT '0',
      PRIMARY KEY (`datetime`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

*/
    result1.writeStream
      .foreach(new BaseJdbcSink("replace into covid19_1 (datetime,currentConfirmedCount,confirmedCount,suspectedCount,curedCount,deadCount) values(?,?,?,?,?,?)") {
        override def realProcess(sql: String, row: Row): Unit = {
          //取出row中的数据
          val datetime: String = row.getAs[String]("datetime")
          val currentConfirmedCount: Long = row.getAs[Long]("currentConfirmedCount")
          val confirmedCount: Long = row.getAs[Long]("confirmedCount")
          val suspectedCount: Long = row.getAs[Long]("suspectedCount")
          val curedCount: Long = row.getAs[Long]("curedCount")
          val deadCount: Long = row.getAs[Long]("deadCount")
          //获取预编译语句对象
          ps = conn.prepareStatement(sql)
          //给sql设置参数值
          ps.setString(1, datetime)
          ps.setLong(2, currentConfirmedCount)
          ps.setLong(3, confirmedCount)
          ps.setLong(4, suspectedCount)
          ps.setLong(5, curedCount)
          ps.setLong(6, deadCount)
          ps.executeUpdate()
        }
      })
      .outputMode("complete")
      .trigger(Trigger.ProcessingTime(0))
      .option("truncate", false)
      .start()


    result2.writeStream
      .format("console")
      .outputMode("append")
      .trigger(Trigger.ProcessingTime(0))
      .option("truncate", false)
      .start()
    //.awaitTermination()
    /*
+----------+----------+-----------------+---------------------+--------------+--------------+----------+---------+
|datetime  |locationId|provinceShortName|currentConfirmedCount|confirmedCount|suspectedCount|curedCount|deadCount|
+----------+----------+-----------------+---------------------+--------------+--------------+----------+---------+
|2020-05-28|810000    |香港               |27                   |1066          |47            |1035      |4        |
|2020-05-28|630000    |青海               |0                    |18            |0             |18        |0        |
|2020-05-28|540000    |西藏               |0                    |1             |0             |1         |0        |
|2020-05-28|820000    |澳门               |0                    |45            |9             |45        |0        |
  ...
CREATE TABLE `covid19_2` (
  `datetime` varchar(20) NOT NULL DEFAULT '',
  `locationId` int(11) NOT NULL DEFAULT '0',
  `provinceShortName` varchar(20) DEFAULT '',
  `cityName` varchar(20) DEFAULT '',
  `currentConfirmedCount` int(11) DEFAULT '0',
  `confirmedCount` int(11) DEFAULT '0',
  `suspectedCount` int(11) DEFAULT '0',
  `curedCount` int(11) DEFAULT '0',
  `deadCount` int(11) DEFAULT '0',
  `pid` int(11) DEFAULT '0',
  PRIMARY KEY (`datetime`,`locationId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
     */
    result2.writeStream
      .foreach(new BaseJdbcSink("replace into covid19_2 (datetime,locationId,provinceShortName,currentConfirmedCount,confirmedCount,suspectedCount,curedCount,deadCount) values(?,?,?,?,?,?,?,?)") {
        override def realProcess(sql: String, row: Row): Unit = {
          val datetime: String = row.getAs[String]("datetime")
          val locationId: Int = row.getAs[Int]("locationId")
          val provinceShortName: String = row.getAs[String]("provinceShortName")
          val currentConfirmedCount: Int = row.getAs[Int]("currentConfirmedCount")
          val confirmedCount: Int = row.getAs[Int]("confirmedCount")
          val suspectedCount: Int = row.getAs[Int]("suspectedCount")
          val curedCount: Int = row.getAs[Int]("curedCount")
          val deadCount: Int = row.getAs[Int]("deadCount")
          ps = conn.prepareStatement(sql)
          ps.setString(1, datetime)
          ps.setInt(2, locationId)
          ps.setString(3, provinceShortName)
          ps.setInt(4, currentConfirmedCount)
          ps.setInt(5, confirmedCount)
          ps.setInt(6, suspectedCount)
          ps.setInt(7, curedCount)
          ps.setInt(8, deadCount)
          ps.executeUpdate()
        }
      })
      .outputMode("append")
      .trigger(Trigger.ProcessingTime(0))
      .option("truncate", false)
      .start()

    result3.writeStream
      .format("console")
      .outputMode("complete")
      .trigger(Trigger.ProcessingTime(0))
      .option("truncate", false)
      .start()
    //.awaitTermination()
    /*
  +--------+-------------+--------------+--------------+----------+---------+
|dateId  |confirmedIncr|confirmedCount|suspectedCount|curedCount|deadCount|
+--------+-------------+--------------+--------------+----------+---------+
|20200413|99           |83696         |415           |78262     |3351     |
|20200218|1749         |74277         |0             |14378     |2006     |
|20200314|27           |81048         |0             |67022     |3204     |
|20200311|25           |80980         |0             |62874     |3173     |
|20200202|2830         |17238         |0             |478       |361      |
|20200217|1896         |72528         |0             |12557     |1870     |
  ...
CREATE TABLE `covid19_3` (
  `dateId` varchar(20) NOT NULL DEFAULT '',
  `confirmedIncr` bigint(20) DEFAULT '0',
  `confirmedCount` bigint(20) DEFAULT '0',
  `suspectedCount` bigint(20) DEFAULT '0',
  `curedCount` bigint(20) DEFAULT '0',
  `deadCount` bigint(20) DEFAULT '0',
  PRIMARY KEY (`dateId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
     */
    result3.writeStream
      .foreach(new BaseJdbcSink("replace into covid19_3 (dateId,confirmedIncr,confirmedCount,suspectedCount,curedCount,deadCount) values(?,?,?,?,?,?)") {
        override def realProcess(sql: String, row: Row): Unit = {
          //取出row中的数据
          val dateId: String = row.getAs[String]("dateId")
          val confirmedIncr: Long = row.getAs[Long]("confirmedIncr")
          val confirmedCount: Long = row.getAs[Long]("confirmedCount")
          val suspectedCount: Long = row.getAs[Long]("suspectedCount")
          val curedCount: Long = row.getAs[Long]("curedCount")
          val deadCount: Long = row.getAs[Long]("deadCount")
          //获取预编译语句对象
          ps = conn.prepareStatement(sql)
          //给sql设置参数值
          ps.setString(1, dateId)
          ps.setLong(2, confirmedIncr)
          ps.setLong(3, confirmedCount)
          ps.setLong(4, suspectedCount)
          ps.setLong(5, curedCount)
          ps.setLong(6, deadCount)
          ps.executeUpdate()
        }
      })
      .outputMode("complete")
      .trigger(Trigger.ProcessingTime(0))
      .option("truncate", false)
      .start()

    result4.writeStream
      .format("console")
      .outputMode("complete")
      .trigger(Trigger.ProcessingTime(0))
      .option("truncate", false)
      .start()
    //.awaitTermination()
    /*
  +----------+-----------------+------+--------------+
|datetime  |provinceShortName|pid   |confirmedCount|
+----------+-----------------+------+--------------+
|2020-05-28|黑龙江              |230000|386           |
|2020-05-28|上海               |310000|330           |
|2020-05-28|北京               |110000|174           |
|2020-05-28|内蒙古              |150000|155           |
|2020-05-28|山西               |140000|64            |
|2020-05-28|陕西               |610000|63            |
  ....
CREATE TABLE `covid19_4` (
  `datetime` varchar(20) NOT NULL DEFAULT '',
  `provinceShortName` varchar(20) NOT NULL DEFAULT '',
  `pid` int(11) DEFAULT '0',
  `confirmedCount` bigint(20) DEFAULT '0',
  PRIMARY KEY (`datetime`,`provinceShortName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
     */
    result4.writeStream
      .foreach(new BaseJdbcSink("replace into covid19_4 (datetime,provinceShortName,pid,confirmedCount) values(?,?,?,?)") {
        override def realProcess(sql: String, row: Row): Unit = {
          //取出row中的数据
          val datetime: String = row.getAs[String]("datetime")
          val provinceShortName: String = row.getAs[String]("provinceShortName")
          val pid: Int = row.getAs[Int]("pid")
          val confirmedCount: Long = row.getAs[Long]("confirmedCount")
          //获取预编译语句对象
          ps = conn.prepareStatement(sql)
          //给sql设置参数值
          ps.setString(1, datetime)
          ps.setString(2, provinceShortName)
          ps.setInt(3, pid)
          ps.setLong(4, confirmedCount)
          ps.executeUpdate()
        }
      })
      .outputMode("complete")
      .trigger(Trigger.ProcessingTime(0))
      .option("truncate", false)
      .start()


    result5.writeStream
      .format("console")
      .outputMode("append")
      .trigger(Trigger.ProcessingTime(0))
      .option("truncate", false)
      .start()
    //.awaitTermination()
    /*
 +----------+----------+-----------------+--------+---------------------+--------------+--------------+----------+---------+
|datetime  |locationId|provinceShortName|cityName|currentConfirmedCount|confirmedCount|suspectedCount|curedCount|deadCount|
+----------+----------+-----------------+--------+---------------------+--------------+--------------+----------+---------+
|2020-05-28|110105    |北京               |朝阳区     |75                   |75            |0             |0         |0        |
|2020-05-28|110108    |北京               |海淀区     |64                   |64            |0             |0         |0        |
|2020-05-28|110102    |北京               |西城区     |53                   |53            |0             |0         |0        |
|2020-05-28|110106    |北京               |丰台区     |40                   |43            |0             |3         |0        |
|2020-05-28|110115    |北京               |大兴区     |23                   |39            |0             |16        |0        |
  ....
CREATE TABLE `covid19_5` (
  `datetime` varchar(20) NOT NULL DEFAULT '',
  `locationId` int(11) NOT NULL DEFAULT '0',
  `provinceShortName` varchar(20) DEFAULT '',
  `cityName` varchar(20) DEFAULT '',
  `currentConfirmedCount` int(11) DEFAULT '0',
  `confirmedCount` int(11) DEFAULT '0',
  `suspectedCount` int(11) DEFAULT '0',
  `curedCount` int(11) DEFAULT '0',
  `deadCount` int(11) DEFAULT '0',
  `pid` int(11) DEFAULT '0',
  PRIMARY KEY (`datetime`,`locationId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
     */
    result5.writeStream
      .foreach(new BaseJdbcSink("replace into covid19_5 (datetime,locationId,provinceShortName,cityName,currentConfirmedCount,confirmedCount,suspectedCount,curedCount,deadCount) values(?,?,?,?,?,?,?,?,?)") {
        override def realProcess(sql: String, row: Row): Unit = {
          val datetime: String = row.getAs[String]("datetime")
          val locationId: Int = row.getAs[Int]("locationId")
          val provinceShortName: String = row.getAs[String]("provinceShortName")
          val cityName: String = row.getAs[String]("cityName")
          val currentConfirmedCount: Int = row.getAs[Int]("currentConfirmedCount")
          val confirmedCount: Int = row.getAs[Int]("confirmedCount")
          val suspectedCount: Int = row.getAs[Int]("suspectedCount")
          val curedCount: Int = row.getAs[Int]("curedCount")
          val deadCount: Int = row.getAs[Int]("deadCount")
          ps = conn.prepareStatement(sql)
          ps.setString(1, datetime)
          ps.setInt(2, locationId)
          ps.setString(3, provinceShortName)
          ps.setString(4, cityName)
          ps.setInt(5, currentConfirmedCount)
          ps.setInt(6, confirmedCount)
          ps.setInt(7, suspectedCount)
          ps.setInt(8, curedCount)
          ps.setInt(9, deadCount)
          ps.executeUpdate()
        }
      })
      .outputMode("append")
      .trigger(Trigger.ProcessingTime(0))
      .option("truncate", false)
      .start()
      .awaitTermination()
  }
}
