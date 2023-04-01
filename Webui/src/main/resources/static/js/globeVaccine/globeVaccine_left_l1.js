var globeVaccine_left_l1 = echarts.init(document.getElementById("globeVaccine_l1"));
var myColor = [
        "#ff0033","#8B78F6", "#ffff99", "#99ffff",
        "#1089E7", "#F57474", "#56D0E3", "#F8B448",
        "#ff00cc","#00BFFF","#708090",
        "#0033cc", "#ff6633","#00FA9A",
        "#9ACD32","#FFFF00",
        "#9932CC", "#33ff33","#6666ff","#0099cc",
        "#FFD700", "#9966ff","#86678b",
        "#cc3399","#40E0D0",
        "#660066", "#009966","#666600"
    ];
option_globeVaccine_left_l1= {
  title: {
    text: '全球疫苗接种人数排行榜',
      left:"center",
      		textStyle: {
			color: 'white'
		},
  },
  legend: {},
  grid: {
      top:'4%',
    left: '2%',
    // right: '2%',
    bottom: '1%',
    containLabel: true
  },
  xAxis: {
    // type: 'value',
    // boundaryGap: [0, 0.01]
        type: 'value',
        boundaryGap: true,
        //x 轴
        axisLabel: {
            interval: 6,
            rotate: 35
        }
  },
  yAxis: {
    inverse: true,//倒叙
    type: 'category',
    data: []
  },
  series: [
    {
      // name: '疫苗接种人数',
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
          color:"black",
      },
    }
  ]
};

globeVaccine_left_l1.setOption(option_globeVaccine_left_l1);
