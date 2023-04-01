var chinaEpidemic_center_c1 = echarts.init(document.getElementById("chinaEpidemic_c1"));
var myColor = [
        "#1089E7", "#F57474", "#56D0E3", "#F8B448",
        "#8B78F6", "#ffff99", "#99ffff",
        "#9932CC", "#33ff33","#6666ff","#0099cc",
        "#ff00cc","#00BFFF","#708090",
        "#0033cc", "#ff6633","#00FA9A",
        "#ff0033","#9ACD32","#FFFF00",
        "#FFD700", "#9966ff","#86678b",
        "#cc3399","#40E0D0",
        "#660066", "#009966","#666600"
    ];
option_chinaEpidemic_center_c1 = {
  title: {
    text: '重点省份累积确诊人数'
  },
  tooltip: {
    trigger: 'axis',
    axisPointer: {
      type: 'shadow'
    }
  },
  legend: {},
  grid: {
    left: '3%',
    right: '4%',
    bottom: '3%',
    containLabel: true
  },
  xAxis: {
    type: 'value',
    boundaryGap: [0, 0.01]
  },
  yAxis: {
    inverse: true,//倒叙
    type: 'category',
    datay: []
  },
  series: [
    {
      name: '累积确诊人数',
      type: 'bar',
      data: [],
      itemStyle:{
          normal:{
              // color:'#4ad2ff'
 // barBorderRadius: 20,
                        // dataIndex 是当前柱子的索引号
                        color: function (params) {
                            return myColor[params.dataIndex];
                        }

          }
      },
      // 实现数字展示在柱状图
      label: {
          show: true,
          position:'inside',
          color:"white",
      },
    }
  ]
};

chinaEpidemic_center_c1.setOption(option_chinaEpidemic_center_c1);
