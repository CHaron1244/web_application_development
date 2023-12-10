<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ajax</title>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/jquery-3.6.0.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){
            $("button").click(function(){
                $.ajax({
                    type:"post",				//提交方式
                    url:'http://localhost:8080/chapter12/AJAXServlet',
                    data:{
                        userName:  $("#userName").val(),
                        password:  $("#password").val()
                    },              			//data中的内容会自动的添加到url后面
                    dataType: "text",    		//返回数据的格式
                    success:function(a){      	//请求成功后执行的函数
                        if(a=="true"){
                            $('#suss').html("登录成功!")
                        }else{
                            $('#suss').html("登录失败!")
                        }
                    },
                    error :function () {      	//请求失败后执行的函数
                        alert("请求失败");
                    },
                });
            });
        });
    </script>
</head>
<body>
<div>
    <div>
        <ul>
            <li>用户名：</li>
            <li><input id="userName" name="userName" type="text" /></li>
        </ul>
    </div>
    <div>
        <ul>
            <li>密码：</li>
            <li><input id="password" name="password" type="password"/></li>
        </ul>
    </div>
    <div>
        <ul>
            <li><button>登录</button></li>
        </ul>
    </div>
    <div id="suss"></div>
</div>
</body>
</html>
