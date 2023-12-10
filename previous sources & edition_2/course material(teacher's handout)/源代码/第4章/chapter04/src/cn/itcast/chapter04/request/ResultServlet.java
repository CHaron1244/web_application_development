package cn.itcast.chapter04.request;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ResultServlet", urlPatterns = "/ResultServlet")
public class ResultServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        String username = (String) request.getAttribute("username");
        if (username != null) {
            out.println("用户名：" + username + "<br/>");
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        doGet(request, response);
    }
}
