//初始化echarts实例
var globeVaccine_center_c1 = echarts.init(document.getElementById("globeVaccine_c1"));
var mydata = []
var optionMap_globeVaccine_center_c1 = {
		title: {
			text: '',
			subtext: '',
			x: 'left',
		},
		tooltip: {
			// show:true,
			 transitionDuration:0,
			trigger: 'item'
		},
		//左侧小导航图标
		visualMap: {
			show: true,
			x: 'left',
			y: 'bottom',
			textStyle: {
				fontSize: 8
			},
			top:"55%",
			left:"40",
			splitList: [{
					end: 100000,
				},
				{
					start: 100000,
					end: 1000000
				},
				{
					start: 1000000,
					end: 10000000
				},
				{
					start: 10000000,
					end: 100000000
				},
				{
					start: 1000000000
				}
			],
			color: ['#2E8B57','#3dc679','#00DB00', '#79FF79', '#CEFFCE'],
			
		},
			//配置属性
			series: [{
			    name: '疫苗接种总数',
				type: 'map',
				mapType: 'world',
				roam: false,
				data: mydata //数据
			}]
		};

//使用制定的配置项和数据显示图表
globeVaccine_center_c1.setOption(optionMap_globeVaccine_center_c1);
