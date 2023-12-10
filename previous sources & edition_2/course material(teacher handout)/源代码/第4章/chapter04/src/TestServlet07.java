import javax.servlet.annotation.WebServlet;
import java.io.*;
import java.util.Properties;
import javax.servlet.*;
import javax.servlet.http.*;

@WebServlet(name = "TestServlet07", urlPatterns = "/TestServlet07")
public class TestServlet07 extends HttpServlet {
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        ServletContext context = this.getServletContext();
        PrintWriter out = response.getWriter();
        //获取相对路径中的输入流对象
        InputStream in = context
                .getResourceAsStream("/WEB-INF/classes/itcast.properties");
        Properties pros = new Properties();
        pros.load(in);
        out.println("Company=" + pros.getProperty("Company") + "<br />");
        out.println("Address=" + pros.getProperty("Address") + "<br />");
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
