function to() {
    var val = document.getElementById("search").value;
        if(val.length === 0){
                alert('搜索为空，请输入内容');
                return false;
        }else{
                var open_url = "https://www.baidu.com/s?ie=utf-8&wd=" + val;
                window.open(open_url);
        }
}
document.onkeydown = function (e) {
    var theEvent = window.event || e;
    var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
    if (code == 13) {
        to();
    }
}



function get_c1_data() {
	$.ajax({
		url: "/c1",
		success: function(data) {
			$(".num h1").eq(0).html(data.confirm)
			$(".num h1").eq(1).html(data.heal)
		},
		error: function(xhr, type, errorThrown) {
		}
	})
}


function get_r1_data() {
	$.ajax({
		url: "/r1",
		success: function(data) {
			$(".nu h1").eq(0).html(data.vaccine_sj)
			$(".nu h1").eq(1).html(data.vaccine_zg)
		},
		error: function(xhr, type, errorThrown) {
		}
	})
}

$(function(){
	$("#button").on("click",function(){
	   jump1();
	});
});
function jump1(){
	url = "home.html";//此处拼接内容
	window.location.href = url;
};

get_c1_data()
get_r1_data()


