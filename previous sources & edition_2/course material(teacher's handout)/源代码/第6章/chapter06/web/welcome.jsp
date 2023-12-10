<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>welcome page</title>
</head>
<body>
你好，欢迎进入首页，当前访问时间是：
<%
    out.print(new java.util.Date());
%>
</body>
</html>
