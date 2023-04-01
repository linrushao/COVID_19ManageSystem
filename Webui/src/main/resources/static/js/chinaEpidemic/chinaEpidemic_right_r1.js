var chinaEpidemic_right_r1 = echarts.init(document.getElementById("chinaEpidemic_r1"));
option_chinaEpidemic_right_r1 = {
  title: {
    text: '累积确诊人数区域分布',
    left: 'center'
  },
  tooltip: {
    trigger: 'item',
          left: 'center'
  },

  legend: {
    orient: 'horizontal',
      left:"right",
      top:'5%'
  },

  series: [
    {
      name: '累积确诊人数',
        color:'white',
      type: 'pie',
      radius: '50%',
        center: ['50%', '50%'],
      data: [],
      itemStyle: {
                      normal: {
                      color: function(params) {
                      //自定义颜色
                      var colorList = [
                          "#ffff99","#000066","#ccff66","#99ffff","#99ff66","#DA70D6","#8B008B","#9932CC",
                        "#33ff33","#6666ff","#0099cc","#003366","#ff00cc","#00BFFF","#708090","#0033cc",
                        "#ff6633","#2F4F4F","#00FA9A","#333366","#ff0033","#9ACD32","#FFFF00","#FFD700",
                        "#9966ff","#800000","#86678b","#cc3399","#660000","#40E0D0","#339966","#660066",
                        "#009966","#666600","#cc0099"
                      ];
                  return colorList[params.dataIndex]
                  }
              }
          },
        label:{
            fontSize: 10
        }
    }
  ]
};

chinaEpidemic_right_r1.setOption(option_chinaEpidemic_right_r1);
