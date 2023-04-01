/*--------------------全国统计数据-----------------------------*/
$.getJSON("http://localhost:8080/covid/getNationalData_l1", function (data) {
	var map = data.data
	$("#cofirm").html(map.cofirm)
	$("#cofirm_now").html(map.cofirm_now)
	$("#cofirm_add").html(map.cofirm_add)
	$("#suspected").html(map.suspected)
	$("#cured").html(map.cured)
	$("#dead").html(map.dead)
})

var ydata_1 = [];//y轴
$.getJSON("http://localhost:8080/covid/getNationalData_l1_l1", function (data) {
	var arr = data.data
	for (var i = 0; i < arr.length; i++) {
		ydata_1.push(arr[i].name)
	}
	chinaEpidemic_left_l1.setOption({
		yAxis: {
			data: ydata_1
		},
		series:{
			data:data.data
		}
	})
})

var ydata_2 = [];//y轴
$.getJSON("http://localhost:8080/covid/getNationalData_l1_c1", function (data) {
	var arr = data.data
	for (var i = 0; i < arr.length; i++) {
		ydata_2.push(arr[i].name)
	}
	chinaEpidemic_center_c1.setOption({
		yAxis: {
			data: ydata_2
		},
		series:{
			data:data.data
		}
	})
})

$.getJSON("http://localhost:8080/covid/getNationalData_l1_r1", function (data) {
	chinaEpidemic_right_r1.setOption({
		series:{
			data:data.data
		}
	})
})
