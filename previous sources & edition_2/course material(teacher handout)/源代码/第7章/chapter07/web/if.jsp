<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<fieldset>
    <c:if test="${param.action=='mon'}">
        周一了：工作的第一天，加油！
    </c:if>
    <c:if test="${param.action=='tues'}">
        周二了：工作的第二天，加油！
    </c:if>
    <c:if test="${param.action=='wed'}">
        周三了：工作的第三天，加油！
    </c:if>
    <c:if test="${param.action=='thu'}">
        周四了：工作的第四天，加油！
    </c:if>
    <c:if test="${param.action=='fri'}">
        周五了：工作的第五天，加油！
    </c:if>
    <c:if test="${param.action=='sat'}">
        周六了：休息的第一天！
    </c:if>
    <c:if test="${param.action=='sun'}">
        周日了：休息的第二天！
    </c:if>
</fieldset>
</body>
</html>
