var globeEpidemic_center_c1 = echarts.init(document.getElementById("globeEpidemic_c1"), "white");

option_globeEpidemic_center_c1 = {
    title: {
    text: '全球各洲累积确诊排行',
    left: 'center',
		textStyle: {
			color: 'white'
		},
  },
  legend: {
    top: 'bottom'
  },
tooltip: {//提示框组件
    trigger: 'item', //item数据项图形触发，主要在散点图，饼图等无类目轴的图表中使用。
    axisPointer: {
        // 坐标轴指示器，坐标轴触发有效
        type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
    },
    formatter: '{b} : {c} <br/>百分比 : {d}%' //{a}（系列名称），{b}（数据项名称），{c}（数值）, {d}（百分比）
},


  series: [
    {
      type: 'pie',
      radius: [50, 150],
      roseType: 'area',
      // itemStyle: {
      //   borderRadius: 8
      // },
      data: []
    }
  ]
};
globeEpidemic_center_c1.setOption(option_globeEpidemic_center_c1);
