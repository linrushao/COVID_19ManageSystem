/*--------------------时间趋势折线图-----------------------------*/
var chinaHistory_center_c1 = echarts.init(document.getElementById("chinaHistory_c1"));
chinaHistory_center_c1.setOption({
	// backgroundColor: '#FFF0F5',
	title: {
		text: '全国历史累计趋势',
		// x: 'center',
		textStyle: {
			color: 'white'
		},
		left: 'center'
	},

	// 提示框
	tooltip: {
		trigger: 'axis',
		axisPointer: {
			type: 'line',
			lineStyle: {
				color: '#7171C6'
			}
		}
	},
	legend: {
		// orient 设置布局方式，默认水平布局，可选值：'horizontal'（水平） ¦ 'vertical'（垂直）
		// orient: 'horizontal',
		// x 设置水平安放位置，默认全图居中，可选值：'center' ¦ 'left' ¦ 'right' ¦ {number}（x坐标，单位px）
		// x: 'left',
		// y 设置垂直安放位置，默认全图顶端，可选值：'top' ¦ 'bottom' ¦ 'center' ¦ {number}（y坐标，单位px）
		// y: 'top',
		data: ['累计确诊', '累计治愈','累计死亡'],
		top:"7%",
		left: 'center',
		color: '#4bcc74',// 图例文字颜色
		// left: 'right',
	},
	dataset: {
		// 这里指定了维度名的顺序，从而可以利用默认的维度到坐标轴的映射。
		dimensions: ['history_time', '累计确诊', '累计治愈','累计死亡'],
		source: []
	},
	//  图表距边框的距离,可选值：'百分比'¦ {number}（单位px）
	grid: {
		top: 50, // 等价于 y: '16%'
		left: '4%',
		right: '6%',
		bottom: '4%',
		containLabel: true
	},
	toolbox: {
		feature: {
			saveAsImage: {}
		}
	},
	xAxis: {
		name: '时间',
		type: 'category',
		// axisLine: {
		// 	lineStyle: {
		// 		// 设置x轴颜色
		// 		color: '#912CEE'
		// 	}
		// },
		// // 设置X轴数据旋转倾斜
		// axisLabel: {
		// 	rotate: 30, // 旋转角度
		// 	interval: 0 //设置X轴数据间隔几个显示一个，为0表示都显示
		// },
		// // boundaryGap值为false的时候，折线第一个点在y轴上
		// boundaryGap: false,
		axisLabel: {
			color: "#ccc" //刻度线标签颜色
		},
		axisLine: {
			color: "#ccc" //刻度线标签颜色
		},
		axisTick: {
			color: "#ccc" //刻度线标签颜色
		},
		data: []
	},

	yAxis: {
		name: '人数',
		type: 'value',
		// min: 0, // 设置y轴刻度的最小值
		// max: 1800, // 设置y轴刻度的最大值
		// splitNumber: 9, // 设置y轴刻度间隔个数
		axisLine: {
			show: true
			// lineStyle: {
			// 	// 设置y轴颜色
			// 	color: '#87CEFA'
			// }
		},
		// axisLabel: {
		// 	show: true,
		// 	color: 'white',
		// 	fontSize: 12,
		// 	formatter: function(value) {
		// 		if (value >= 1000) {
		// 			value = value / 1000 + 'k';
		// 		}
		// 		return value;
		// 	}
		// },
		splitLine: {
			show: true,
			lineStyle: {
				color: '#172738',
				width: 1,
				type: 'solid'
			}
		}
	},

	series: [
		{type: 'line'},
		{type: 'line'},
		{type: 'line'}
	],

	color: ['#ee2b2e', '#00ee00', '#FFD700']
})


