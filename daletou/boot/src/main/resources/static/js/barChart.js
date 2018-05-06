
$(function(){
    ballPeriodCount();
})

function ballPeriodCount() {
    var begin = $("#begin").val();
    var end = $("#end").val();
    if( isIntNum(begin)==isIntNum(end) && isIntNum(begin)){
        $.ajax({
            type: "GET",
            url: "./countPeriod",
            data: {
                begin:begin,
                end:end
            },
            dataType: "json",
            success: function(data){
                if(null != data && data != ''){
                    var redName = new Array();
                    var redData = new Array();
                    var blueName = new Array();
                    var blueData = new Array();
                    for(var key in data){
                        if(key.indexOf("red") > -1){
                            var nameKey = key.substring(4);
                            redName.push(nameKey);
                            redData.push(data[key]);
                        }
                        if(key.indexOf("blue") > -1){
                            var nameKey = key.substring(5);
                            blueName.push(nameKey);
                            blueData.push(data[key]);
                        }
                    }
                    initBar(redName,redData,"红球分布","chart1","#CD3333");
                    initBar(blueName,blueData,"蓝球分布","chart2","#5CACEE");

                }
            }
        });
    }else{
        alert("请输入正确的期数");
    }


}

function initBar(name,data,title,div,color){
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById(div));

    // 指定图表的配置项和数据
    var option = {
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        color: color,
        title: {
            text: title,

        },

        xAxis: {
            data: name,
            axisLabel:{
                interval:0,
                rotate:0
            }
        },
        yAxis: {},
        series: [{
            name: '次数',
            type: 'bar',
            label: {
                normal: {
                    show: true,
                    position: 'top'
                }
            },
            data: data,
            markLine: {
                lineStyle:{
                    color:'#5B5B5B',
                },
                data: [
                    {type: 'average', name: '平均值'},
                    [{
                        symbol: 'none',
                        x: '90%',
                        yAxis: 'max'
                    }, {
                        symbol: 'circle',
                        label: {
                            normal: {
                                position: 'start',
                                formatter: '最大值'
                            }
                        },
                        type: 'max',
                        name: '最高点'
                    }]
                ]
            }
        }]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}

//校验数字
function isIntNum(obj){
    var reg = /^[0-9]*$/;
    return reg.test(obj);
}