<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>index</title>
  <script type="text/javascript"
          src="${pageContext.request.contextPath}/jquery-3.6.0.js"></script>
  <script language="JavaScript">
    function getInfo() {
      $.get("http://localhost:8080/chapter12/getInfo.jsp?nocache="+new  Date().getTime(),function (data) {
        $("#showInfo").html(data);
      });
    }
    $(document).ready(function () {
      getInfo();//调用getInfo()方法获取公告信息
      window.setInterval("getInfo()",600000);
    })
  </script>
</head>
<body>
<section>
  <marquee id="showInfo" direction="up" scrollamount="3">
  </marquee>
</section>
</body>
</html>
