var globeVaccine_right_r1 = echarts.init(document.getElementById("globeVaccine_r1"), "white");
var option_globeVaccine_right_r1 = {
	title: {
		text: "疫苗种类分析",
		textStyle: {
			color: 'white'
		},
		left: 'center'
	},
	tooltip: {
		show: false
	},

	series: [{
		type: 'wordCloud',
		gridSize: 1,
		sizeRange: [15, 80], //文字范围
		//文本旋转范围，文本将通过rotationStep45在[-90,90]范围内随机旋转
		rotationRange: [-45, 0, 45, 90],
		// rotationStep: 45,
		// textRotation: [0, 45, 90, -45],
		// //形状
		shape: 'diamond',
		// maskImage:maskImage,
		textStyle: {
			normal: {
				color: function() { //文字颜色的随机色
					// return 'rgb(' + [
					// 	Math.round(Math.random() * 250),
					// 	Math.round(Math.random() * 250),
					// 	Math.round(Math.random() * 250)
					// ].join(',') + ')';
					return 'rgb(' +
						Math.round(Math.random() * 255) +
						',' + Math.round(Math.random() * 255) +
						',' + Math.round(Math.random() * 255) + ')'
				}
			}
		},
		right: null,
		bottom: null,
		data: []
	}]
};
//使用制定的配置项和数据显示图表
globeVaccine_right_r1.setOption(option_globeVaccine_right_r1);
