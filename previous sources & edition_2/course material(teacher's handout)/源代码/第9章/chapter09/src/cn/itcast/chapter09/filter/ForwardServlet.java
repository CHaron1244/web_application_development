package cn.itcast.chapter09.filter;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet(name = "ForwardServlet", urlPatterns = "/ForwardServlet")
public class ForwardServlet extends HttpServlet {
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/first.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
