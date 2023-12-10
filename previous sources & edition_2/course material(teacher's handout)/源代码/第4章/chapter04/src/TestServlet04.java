import javax.servlet.annotation.WebServlet;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

@WebServlet(name = "TestServlet04", urlPatterns = "/TestServlet04")
public class TestServlet04 extends HttpServlet {
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        // 得到ServletContext对象
        ServletContext context = this.getServletContext();
        // 得到包含所有初始化参数名的Enumeration对象
        Enumeration<String> paramNames = context.getInitParameterNames();
        out.println("all the paramName and paramValue are following:");
        // 遍历所有的初始化参数名，得到相应的参数值并打印
        while (paramNames.hasMoreElements()) {
            String name = paramNames.nextElement();
            String value = context.getInitParameter(name);
            out.println(name + ":" + value);
            out.println("<br />");
        }
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
