<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@page import="java.sql.*" %>
<jsp:useBean id="conn" class="cn.itcast.dao.ConnDB"
             scope="page"></jsp:useBean>
<ul>
    <%
        ResultSet rs=conn.executeQuery("select title from info order by id desc");
        if(rs.next()){
            do{
                out.print("<li>"+rs.getString(1)+"</li>");
            }while (rs.next());
        }else{
            out.print("<li>暂无公告信息！</li>");
        }
    %>
</ul>
