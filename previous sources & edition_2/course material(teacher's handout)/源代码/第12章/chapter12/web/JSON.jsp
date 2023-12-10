<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSON</title>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/jquery-3.6.0.js"></script>
</head>
<body>
<button id="btn">加载数据</button>
<table id="dataTable" border="1" cellpadding="0" cellspacing="0">
    <tr>
        <th>作者</th>
        <th>书名</th>
        <th>价格</th>
    </tr>
</table>
<script type="text/javascript">
    $('#btn').click(function() {
        $.getJSON('http://localhost:8080/chapter12/JSONServlet',
            function(data) {
                var html = '';
                for (var Book in data) {
                    html += '<tr>';
                    for (var key in data[Book]) {
                        html += '<td>' + data[Book][key] + '</td>';
                    }
                    html += '</tr>';
                }
                $('#dataTable').append(html);
            });
    });
</script>
</body>
</html>
