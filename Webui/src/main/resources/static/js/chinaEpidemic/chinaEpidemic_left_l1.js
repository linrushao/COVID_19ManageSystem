var chinaEpidemic_left_l1 = echarts.init(document.getElementById("chinaEpidemic_l1"));
option_chinaEpidemic_left_l1= {
  title: {
    text: '重点省份累积治愈人数'
  },
  tooltip: {
    trigger: 'axis',
    axisPointer: {
      type: 'shadow'
    }
  },
  legend: {
    left: '50%',
  },
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
    data: []
  },
  series: [
    {
      name: '累积治愈人数',
      type: 'bar',
      data: [{value: 235, name: '视频广告'},
        {value: 274, name: '联盟广告'},
        {value: 310, name: '邮件营销'},
        {value: 335, name: '直接访问'},
        {value: 400, name: '搜索引擎'}],
      itemStyle:{
          normal:{
              color:'#4ad2ff'
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

chinaEpidemic_left_l1.setOption(option_chinaEpidemic_left_l1);
