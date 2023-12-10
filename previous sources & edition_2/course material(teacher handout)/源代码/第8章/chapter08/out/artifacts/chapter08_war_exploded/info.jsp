<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>图书信息</title>
</head>
<body>
<%request.setCharacterEncoding("UTF-8");%>
<jsp:useBean id="book" class="cn.itcast.Book" scope="page">
    <jsp:setProperty name="book" property="*"></jsp:setProperty>
</jsp:useBean>
<table align="center" width="400">
    <tr>
        <td align="center">名称：
            <jsp:getProperty property="bookName" name="book"/>
        </td>
    </tr>
    </tr>
    <td align="center">价格：
        <jsp:getProperty property="price" name="book"/>
    </td>
    </tr>
    </tr>
    <td align="center">作者：
        <jsp:getProperty property="author" name="book"/>
    </td>
    </tr>
</table>
</body>
</html>
