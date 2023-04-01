package com.lrs.bean

/**
 * @Author LRS
 * @Date 2022/7/12 18:09
 *       Desc
 */
case class CovidBean(
                      provinceName: String, //省份
                      provinceShortName: String, //省份短名
                      cityName: String, //城市名称
                      currentConfirmedCount: Integer, //当前确诊人数
                      confirmedCount: Integer, //累积确诊人数
                      suspectedCount: Integer, //疑似病例人数
                      curedCount: Integer, //治愈人数
                      deadCount: Integer, //死亡人数
                      locationId: Integer, //位置ID
                      pid: Integer,
                      statisticsData: String, //每一天的统计数据
                      cities: String, //下属城市
                      datetime: String //时间
                    )
