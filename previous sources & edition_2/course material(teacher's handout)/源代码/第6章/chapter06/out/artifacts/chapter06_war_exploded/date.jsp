<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<html>
<head><title>date</title>
</head>
<body>
当前时间是：
<%
    Date date = new Date();
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String today =df.format(date);
    out.println(today);
%>
</body>
</html>
