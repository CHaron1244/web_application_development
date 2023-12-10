<%@ page language="java" contentType="text/html; charset=utf-8"
         import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head></head>
<body>
使用绝对路径构造URL:<br />
<c:url var="myURL"
       value="http://localhost:8080/chapter07/c_out1.jsp">
    <c:param name="username" value="张三" />
</c:url>
<a href="${myURL}">c_out1.jsp</a><br />
使用相对路径构造URL:<br />
<c:url var="myURL"
       value="c_out1.jsp?username=Tom" />
<a href="${myURL}">c_out1.jsp</a>
</body>
</html>
