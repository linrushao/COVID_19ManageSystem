var chinaHistory_center_c3 = echarts.init(document.getElementById("chinaHistory_c3"));
option_chinaHistory_center_c3 = {
  title: {
    text: '2022年',
    left: '15%'
  },
  tooltip: {
    trigger: 'item',
          left: 'center'
  },

  legend: {
    orient: 'horizontal',
      left:"left",
      top:'13%'
  },

  series: [
    {
      name: '2022年',
      type: 'pie',
      radius: '70%',
        center: ['40%', '65%'],
      data: [],
        emphasis: {
        itemStyle: {
          shadowBlur: 10,
          shadowOffsetX: 0,
          shadowColor: 'rgba(0, 0, 0, 0.5)'
        }
      },
        label:{
            fontSize: 15,
          // show:true,
          position:'inner',
           formatter: '{b}\n {d}% '
        }
    }
  ]
};

chinaHistory_center_c3.setOption(option_chinaHistory_center_c3);
