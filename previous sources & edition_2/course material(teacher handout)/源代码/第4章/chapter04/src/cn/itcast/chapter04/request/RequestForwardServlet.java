package cn.itcast.chapter04.request;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "RequestForwardServlet", urlPatterns = "/RequestForwardServlet")
public class RequestForwardServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setAttribute("username", "张三");// 将数据存储到request对象中
        RequestDispatcher dispatcher = request.getRequestDispatcher("/ResultServlet").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        doGet(request, response);
    }
}
