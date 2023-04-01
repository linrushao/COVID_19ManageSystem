var globeEpidemic_right_r1 = echarts.init(document.getElementById("globeEpidemic_r1"),"white");

var option_globeEpidemic_right_r1 = {
	// backgroundColor: '#FFF0F5',
	title: {
		text: '全球历史累计治愈趋势',
		// x: 'center',
		textStyle: {
			color: 'white'
		},
		left: 'center'
	},

	legend: {
		data: ['中国', '美国','俄罗斯','日本','印度','巴西','法国','韩国'],
		top:"7%",
		left: 'center',
		color: '#4bcc74'  // 图例文字颜色
	},

	//  图表距边框的距离,可选值：'百分比'¦ {number}（单位px）
	grid: {
		top: 50, // 等价于 y: '16%'
		left: '4%',
		right: '6%',
		bottom: '4%',
		containLabel: true
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
	xAxis: {
		name: '时间',
		type: 'category',
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
		name: '治愈人数',
		type: 'value',

		axisLine: {
			show: true
		},
		axisLabel: {
			show: true,
			color: 'white',
			fontSize: 12,
			formatter: function(value) {
				if (value >= 1000) {
					value = value / 1000 + 'k';
				}
				return value;
			}
		},
		splitLine: {
			show: true,
			lineStyle: {
				color: '#172738',
				width: 1,
				type: 'solid'
			}
		}
	},

	series: [{
		name: '中国',
		data: [],
		type: 'line',
		smooth: true
	},

		{
			name: '美国',
			data: [],
			type: 'line',
			smooth: true
		},
		{
			name: '俄罗斯',
			data: [],
			type: 'line',
			smooth: true
		},
		{
			name: '日本',
			data: [],
			type: 'line',
			smooth: true
		},
		{
			name: '印度',
			data: [],
			type: 'line',
			smooth: true
		},
		{
			name: '巴西',
			data: [],
			type: 'line',
			smooth: true
		},
		{
			name: '法国',
			data: [],
			type: 'line',
			smooth: true
		},
		{
			name: '韩国',
			data: [],
			type: 'line',
			smooth: true
		}
	],
};

globeEpidemic_right_r1.setOption(option_globeEpidemic_right_r1);
