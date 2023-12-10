import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.WebInitParam;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

@WebServlet(name = "TestServlet03", urlPatterns = "/TestServlet03",
        initParams = {@WebInitParam(name = "encoding", value = "UFT-8"),})
public class TestServlet03 extends HttpServlet {
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        // 获得ServletConfig对象
        ServletConfig config = this.getServletConfig();
        // 获得参数名为encoding对应的参数值
        String param = config.getInitParameter("encoding");
        out.println("encoding=" + param);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}