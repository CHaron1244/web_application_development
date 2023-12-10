<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSP Scriptlets</title>
</head>
<%--<body>
<%
    int a = 1, b = 2; //定义两个变量a,b
    out.println(a+b);
%>
</body>--%>

<%!
int a = 1, b = 2; //定义两个变量a,b
%>
<body>
<%=a+b %><br />
</body>

</html>
