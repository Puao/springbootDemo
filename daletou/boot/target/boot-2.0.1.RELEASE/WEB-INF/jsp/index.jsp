<%--
  Created by IntelliJ IDEA.
  User: Puao
  Date: 2018/5/4
  Time: 14:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <meta charset="utf-8"/>
    <title>大乐透</title>
    <link rel="stylesheet" href="<%=basePath%>css/index.css" type="text/css">
</head>
<body>
    <div class="fd-content">
        <div class="fd-period">
            开始期数：<input type="text" id="begin" value="7001" />
            结束期数：<input type="text" id="end" value="${endPeriod}" />
            <input type="button" onclick="ballPeriodCount();"  value="确定"/>
        </div>
        <div class="fd-data" >
            <%--<div class="fd-head"><span class="fd-title">第一个</span></div>--%>
            <div id="chart1"></div>
            <div id="chart2"></div>
        </div>
    </div>


</body>
<script type="text/javascript" src="<%=basePath%>js/echarts.min.js" ></script>
<script type="text/javascript" src="<%=basePath%>js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/barChart.js" ></script>
</html>
