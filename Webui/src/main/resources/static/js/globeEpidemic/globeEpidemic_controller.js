var xdata = [];//x轴
$.getJSON("http://localhost:8080/covid/getCovidGlobal_l2_l1", function (data) {
	var arr = data.data
	for (var i = 0; i < arr.length; i++) {
		xdata.push(arr[i].name)
	}
	globeEpidemic_left_l1.setOption({
		series: {
			data: data.data
		},
		xAxis: {
			data: xdata
		}
	})
})
$.getJSON('http://localhost:8080/covid/getCovidGlobal_l2_c1', function (data) {
	globeEpidemic_center_c1.setOption({
		series: [{
			data: data.data
		}]
	})
})
var xdata2 = [];//x轴
$.getJSON('http://localhost:8080/covid/getCovidGlobal_l2_r1', function (data) {
	var arr = data.data[0]
	for (var i = 0; i < arr.length; i++) {
		xdata2.push(arr[i].name)
	}
	globeEpidemic_right_r1.setOption({

		xAxis: {
			data: xdata2
		},
		series: [{
			name: '中国',
			data: data.data[0],
			type: 'line',
			smooth: true
		},

			{
				name: '美国',
				data: data.data[1],
				type: 'line',
				smooth: true
			},
			{
				name: '俄罗斯',
				data: data.data[2],
				type: 'line',
				smooth: true
			},
			{
				name: '日本',
				data: data.data[3],
				type: 'line',
				smooth: true
			},
			{
				name: '印度',
				data: data.data[4],
				type: 'line',
				smooth: true
			},
			{
				name: '巴西',
				data: data.data[5],
				type: 'line',
				smooth: true
			},
			{
				name: '法国',
				data: data.data[6],
				type: 'line',
				smooth: true
			},
			{
				name: '韩国',
				data: data.data[7],
				type: 'line',
				smooth: true
			}
		]
	})
})
