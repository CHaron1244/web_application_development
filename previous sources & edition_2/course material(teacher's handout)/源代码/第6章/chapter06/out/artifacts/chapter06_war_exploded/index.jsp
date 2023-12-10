<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>应用Java程序片段动态生成表格</title>
</head>
<body>
<%
  String[] bookName = {"JavaWeb程序开发入门","JavaWeb程序开发进阶","Java基础案例教程"};
%>
<table border="1" align="center" style="font-size:25px;">
  <tr>
    <td align="center">编号</td>
    <td align="center">书名</td>
  </tr>
  <%
    for (int i=0;i<bookName.length;i++){
  %>
  <tr>
    <td align="center"><%=i %></td>
    <td align="center"><%=bookName[i]%></td>
  </tr>
  <%}%>
</table>
</body>
</html>
