<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册页面</title>
    <style type="text/css">
        * {
            margin: 0;
            padding: 0;
        }
        form {
            display: block;
            height: auto;
            width: 450px;
            margin: 100px auto;
        }
        form table tr {
            height: 40px;
        }
        form table tr td {
            height: 40px;
            width: 280px;
            line-height: 40px;
        }
        form table tr td input {
            height: 32px;
            border: 1px solid #BABABA;
            border-radius: 6px;
        }
        .alignRight {
            text-align: right;
            line-height: 40px;
            font-size: 16px;
            font-family: "Monaco";
            width: 200px;
        }
        .radioMid {
            vertical-align:middle;
        }
        .submit {
            display: block;
            height: 40px;
            width: 250px;
            color: white;
            font-weight: bold;
            font-size: 18px;
            background-color: #98ECAC;
            border-radius: 8px;
            margin: 15px auto;
        }
    </style>
</head>
<body>
<form action="RegistServlet" method="post">
    <table>
        <tr>
            <td class="alignRight">
                用户名:
            </td>
            <td>
                <input type="text" name="username" />
            </td>
        </tr>
        <tr>
            <td class="alignRight">
                密码:
            </td>
            <td>
                <input type="password" name="password" />
            </td>
        </tr>
        <tr>
            <td class="alignRight">
                确认密码:
            </td>
            <td>
                <input type="password" name="repeatPsd" />
            </td>
        </tr>
        <tr>
            <td class="alignRight">
                姓名:
            </td>
            <td>
                <input type="text" name="truename" />
            </td>
        </tr>
        <tr>
            <td class="alignRight">
                性别:
            </td>
            <td>
                男&nbsp;&nbsp;<input type="radio" name="gender" value="male"
                                    class="radioMid" />
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                女&nbsp;&nbsp;<input type="radio" name="gender"
                                    value="Female" class="radioMid"  />
            </td>
        </tr>
        <tr>
            <td class="alignRight">
                电话号码:
            </td>
            <td>
                <input type="text" name="phone" />
            </td>
        </tr>
        <tr>
            <td class="alignRight">
                身份证号码:
            </td>
            <td>
                <input type="text" name="indetity">
            </td>
        </tr>
    </table>
    <input type="submit" value="注册" class="submit" />
</form>
</body>
</html>
