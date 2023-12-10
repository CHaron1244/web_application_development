<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>load2</title>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/jquery-3.6.0.js"></script>
</head>
<body>
<button id="btn">加载数据</button>
<div id="box"></div>
<script>
    $('#btn').click(function() {
/*        $('#box').load('http://localhost:8080/chapter12/Load2Servlet',
            {username: 'itcast', password: 123});*/
        $('#box').load('http://localhost:8080/chapter12/Load2Servlet',
            {username: 'itcast', password: 123},
            function(responseData, status, xhr){
            console.log(responseData);	// 输出请求的数据
            console.log(status);		// 输出请求状态
            console.log(xhr);			// 输出XMLHttpRequest对象
        })
    });
</script>
</body>
</html>
