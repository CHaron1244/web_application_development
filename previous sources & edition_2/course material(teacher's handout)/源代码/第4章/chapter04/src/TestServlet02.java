import javax.servlet.annotation.WebServlet;
import javax.servlet.*;

@WebServlet(name = "TestServlet02", urlPatterns = "/TestServlet02")
public class TestServlet02 extends GenericServlet {
    public void init(ServletConfig config) throws ServletException {
        System.out.println("init method is called");
    }

    public void service(ServletRequest request, ServletResponse response)
            throws ServletException {
        System.out.println("Hello World");
    }

    public void destroy() {
        System.out.println("destroy method is called");
    }
}