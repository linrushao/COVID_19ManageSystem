var chinaHistory_center_c2 = echarts.init(document.getElementById("chinaHistory_c2"));
option_chinaHistory_center_c2 = {
  title: {
    text: '2021年',
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
      name: '2021年',
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

chinaHistory_center_c2.setOption(option_chinaHistory_center_c2);
