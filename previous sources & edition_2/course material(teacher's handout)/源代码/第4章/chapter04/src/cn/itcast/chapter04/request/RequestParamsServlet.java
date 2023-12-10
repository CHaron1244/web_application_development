package cn.itcast.chapter04.request;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
@WebServlet(name = "RequestParamsServlet",urlPatterns = "/RequestParamsServlet")
public class RequestParamsServlet extends HttpServlet {
    public void doGet(HttpServletRequest request,
         HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String name = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("用户名:" + name);
        System.out.println("密  码:" + password);
        // 获取参数名为“hobby”的值
        String[] hobbys = request.getParameterValues("hobby");
        System.out.print("爱好:");
        for (int i = 0; i < hobbys.length; i++) {
            System.out.print(hobbys[i] + ", ");
        }
    }
    public void doPost(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
