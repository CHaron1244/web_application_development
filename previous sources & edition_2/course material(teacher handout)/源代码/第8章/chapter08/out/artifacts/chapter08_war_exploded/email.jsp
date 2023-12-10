<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>邮件发送</title>
</head>
<body>
<form action="release.jsp" method="post">
    <table align="center" width="450" height="260" border="1">
        <tr>
            <td align="center" colspan="2" height="40"><b>邮件发送</b></td>
        </tr>
        <tr>
            <td align="left">
                标题：<input type="text" name="title">
            </td>
        </tr>
        <tr>
            <td align="left">
                内容：<textarea name="content" rows="8" cols="40"></textarea>
            </td>
        </tr>
        <tr>
            <td align="center" colspan="2">
                <input type="submit" value="发送">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
