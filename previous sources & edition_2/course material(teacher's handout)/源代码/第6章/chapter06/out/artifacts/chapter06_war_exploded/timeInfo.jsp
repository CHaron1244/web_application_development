<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<html>
<head>
    <title>JSP页面---显示系统时间</title>
</head>
<body>
<%
    Date date = new Date();
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String today =df.format(date);
%>
当前时间：<%=today%>
</body>
</html>
