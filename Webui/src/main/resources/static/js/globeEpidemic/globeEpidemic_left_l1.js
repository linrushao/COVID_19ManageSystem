var globeEpidemic_left_l1 = echarts.init(document.getElementById("globeEpidemic_l1"),"white");

option_globeEpidemic_l1 = {
	title: {
		text: '全球各国确诊人数最新排名',
		textStyle: {
			color: 'white'
		},
		left: 'center'
	},
      tooltip: {
    trigger: 'axis',
          transitionDuration:0,
    axisPointer: {
      type: 'shadow'
    }
  },
	 xAxis: {
    type: 'category',
    data: [],
         axisLabel : {
            showMaxLabel: true,
             interval: 0,
             rotate: 35
         }
  },
  yAxis: {
	    containLabel: true,
      type: "value",
      axisLabel: {
        color: "#444343",
        formatter: function (value, index) {
          // value大于1000时除以1000并拼接k，小于1000按原格式显示
          if (value >= 1000) {
            value = value / 1000 + "k";
          }else if(value < 1000){
            value;
          }
          return value;
        }
      },
  },
  grid: {
            top: '10%',
            left: '5%',//原来是10%，修改为20%
            right: '2%',
            bottom: '24%',
          },

  series: [
    {
      data: [],
      type: 'bar'
    },
  ]

};
globeEpidemic_left_l1.setOption(option_globeEpidemic_l1)
