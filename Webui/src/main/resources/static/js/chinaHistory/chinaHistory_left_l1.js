var chinaHistory_left_l1 = echarts.init(document.getElementById("chinaHistory_l1"));
option_chinaHistory_left_l1 = {
  title: {
    text: '2020年',
    left: '15%'
  },
  tooltip: {
    trigger: 'item',
          left: 'center'
  },

  legend: {
    // orient: 'horizontal',
    orirnt:'vertical',
      left:"left",
      top:'13%'
  },

  series: [
    {
      name: '2020年',
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

chinaHistory_left_l1.setOption(option_chinaHistory_left_l1);
