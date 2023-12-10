import net.sf.json.JSONArray;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
@WebServlet(name = "JSONServlet",urlPatterns = "/JSONServlet")
public class JSONServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        //创建list集合
        List<Book> Books= new ArrayList<Book>();
        Book  b =new Book();
        b.setName("Java基础");
        b.setAuther("黑马程序员");
        b.setPrice(78.20);
        Books.add(b);
        Book  b1 =new Book();
        b1.setName("Java进阶");
        b1.setAuther("itcast");
        b1.setPrice(68.20);
        Books.add(b1);
        //创建JSONArray对象
        JSONArray jsonArray=JSONArray.fromObject(Books);
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print(jsonArray);
        out.flush();
        out.close();
    }
    public void doPost(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        doGet(request,response);
    }
}
