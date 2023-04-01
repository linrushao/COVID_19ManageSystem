var globeVaccine_center_c2 = echarts.init(document.getElementById("globeVaccine_c2"));
option_globeVaccine_center_c2 = {
      title: {
    text: '每百人接种人数',
    left: 'center',
          		textStyle: {
			color: 'white'
		},
  },
 tooltip: {
    trigger: 'item'
  },
  legend: {
    top: '10%',
    left: 'center'
  },
  series: [
    {
      type: 'pie',
        top:"15%",
      radius: ['40%', '70%'],
        center: ['50%','63%'],
      avoidLabelOverlap: false,
      itemStyle: {
        borderRadius: 15,
        borderColor: '#fff',
        borderWidth: 3
      },
      label: {
        show: false,
        position: 'center'
      },
      emphasis: {
        label: {
          show: true,
          fontSize: '40',
          fontWeight: 'bold'
        }
      },
      labelLine: {
        show: false
      },
      data: []
    }
  ]
};

globeVaccine_center_c2.setOption(option_globeVaccine_center_c2);
