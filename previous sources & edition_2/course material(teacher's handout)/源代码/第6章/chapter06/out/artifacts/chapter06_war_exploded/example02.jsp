<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSP声明语句</title>
</head>
<%!
    public int print() { //定义print方法
        int a = 1, b = 2; //定义两个变量a,b
        return a+b;
    }
%>
<body>
<br />
<%
    out.println(print());//调用print()方法，输出其返回值
%>
</body>
</html>
