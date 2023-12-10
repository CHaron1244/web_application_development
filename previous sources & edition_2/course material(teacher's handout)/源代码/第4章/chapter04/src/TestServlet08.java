import javax.servlet.annotation.WebServlet;
import java.io.*;
import java.util.Properties;
import javax.servlet.*;
import javax.servlet.http.*;

@WebServlet(name = "TestServlet08", urlPatterns = "/TestServlet08")
public class TestServlet08 extends HttpServlet {
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        ServletContext context = this.getServletContext();
        //获取文件绝对路径
        String path = context
                .getRealPath("/WEB-INF/classes/itcast.properties");
        FileInputStream in = new FileInputStream(path);
        Properties pros = new Properties();
        pros.load(in);
        out.println("Company=" + pros.getProperty("Company"));
        out.println("Address=" + pros.getProperty("Address"));
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
