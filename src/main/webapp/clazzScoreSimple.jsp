<!DOCTYPE html>
<html style="height: 100%">
<head>
    <meta charset="utf-8">
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="/mui/js/echarts.min.js"></script>
</head>
<body style="height: 100%; margin: 0">
<div id="container" style="height: 100%"></div>
<script type="text/javascript">
    var dom = document.getElementById("container");
    var myChart = echarts.init(dom);
    var option;

    var clazzNames = [];
    var clazzCounts = [];

    $.get("/mui/clazz?methodName=clazzStudentCount", function (data) {
        data = $.parseJSON(data)
        for (var i = 0; i < data.length; i++) {
            clazzNames.push(data[i].cname)
            clazzCounts.push(data[i].count)
        }

        option = {
            xAxis: {
                type: 'category',
                data: clazzNames
            },
            yAxis: {
                type: 'value'
            },
            series: [{
                data: clazzCounts,
                type: 'bar'
            }]
        };

        if (option && typeof option === 'object') {
            myChart.setOption(option);
        }
    })
</script>
</body>
</html>
    