<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>get</title>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/jquery-3.6.0.js"></script>
</head>
<body>
<button id="btn">加载数据</button>
<div id="box"></div>
<script>
    $('#btn').click(function() {
        $.get('http://localhost:8080/chapter12/target.jsp', function(data) {
            $('#box').html(data);
        }, 'html');
    });
</script>
</body>
</html>
