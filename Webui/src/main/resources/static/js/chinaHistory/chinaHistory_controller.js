var xdata = [];//x轴
$.getJSON('http://localhost:8080/covid/getCovidCountry_l3_c1', function (data) {
	var arr = data.data
	for (var i = 0; i < arr.length; i++) {
		xdata.push(arr[i].history_time)
	}
	chinaHistory_center_c1.setOption({
		dataset: {
			source: data.data
		},
		xAxis: {
			data: xdata
		}
	})
})


$.getJSON('http://localhost:8080/covid/getCovidCountry_l3_l1', function (data) {
	chinaHistory_left_l1.setOption({
		series: {
			data: data.data
		}
	})
})
$.getJSON('http://localhost:8080/covid/getCovidCountry_l3_c2', function (data) {
	chinaHistory_center_c2.setOption({
		series: {
			data: data.data
		}
	})
})

$.getJSON('http://localhost:8080/covid/getCovidCountry_l3_c3', function (data) {
	chinaHistory_center_c3.setOption({
		series: {
			data: data.data
		}
	})
})


setTimeout(function () {
	// 异步加载json格式数据
	$.getJSON('http://localhost:8080/covid/getNationalMapData', function (data) {
		chinaHistory_right_l1.setOption({
			series: [{
				data: data.data
			}]
		});
	});
}, 1000)
