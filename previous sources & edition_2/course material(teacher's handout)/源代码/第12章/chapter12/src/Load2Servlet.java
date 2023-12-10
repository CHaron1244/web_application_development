import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(name = "Load2Servlet",urlPatterns = "/Load2Servlet")
public class Load2Servlet extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        //获取load2.jsp页面的username与password值
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        response.getWriter().println("注册成功！<br/>用户名："+username+"<br/>密码："+password);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        doGet(request,response);
    }
}
