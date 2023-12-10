import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(name = "AJAXServlet", urlPatterns = "/AJAXServlet")
public class AJAXServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean flag = false;
        Map<String, String[]> parameterMap = request.getParameterMap();
        if ((request.getParameter("userName")).equals("itcast")
                && request.getParameter("password").equals("123")) {
            flag = true;            //登录成功标志
        } else {
            flag = false;
        }
        response.setContentType("text/html;charset=utf-8");
        //使用PrintWriter方法打印登录结果
        PrintWriter out = response.getWriter();
        out.print(flag);
        out.flush();
        out.close();
    }
}
