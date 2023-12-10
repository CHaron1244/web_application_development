<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>邮件信息</title>
</head>
<body>
<jsp:useBean id="email" class="cn.itcast.Email"></jsp:useBean>
<jsp:useBean id="encoding"
             class="cn.itcast.CharactorEncoding"></jsp:useBean>
<jsp:setProperty name="email" property="*"></jsp:setProperty>
<div align="center">
    <div id="container">
        <div id="title">
            <%=encoding.toString(email.getTitle())%>
        </div>
        <hr>
        <div id="content">
            <%=encoding.toString(email.getContent())%>
        </div>
    </div>
</div>
</body>
</html>
