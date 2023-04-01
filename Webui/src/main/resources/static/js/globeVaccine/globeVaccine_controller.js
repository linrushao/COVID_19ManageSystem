
var ydata = [];//y轴
$.getJSON('http://localhost:8080/covid/getCovidVaccines_l4_l1', function (data) {
	var arr = data.data
	for (var i = 0; i < arr.length; i++) {
		ydata.push(arr[i].name)
	}
	globeVaccine_left_l1.setOption({
		series: {
			data: data.data
		},
		yAxis: {
			data: ydata
		}
	})
})

$.getJSON('http://localhost:8080/covid/getCovidVaccines_l4_c1', function (data) {
	globeVaccine_center_c1.setOption({
		series: {
			data: data.data
		}
	})
})


$.getJSON('http://localhost:8080/covid/getCovidVaccines_l4_r1', function (data) {
	globeVaccine_right_r1.setOption({
		series: {
			data: data.data
		}
	})
})

$.getJSON('http://localhost:8080/covid/getCovidVaccines_l4_c2', function (data) {
	globeVaccine_center_c2.setOption({
		series: {
			data: data.data
		}
	})
})

