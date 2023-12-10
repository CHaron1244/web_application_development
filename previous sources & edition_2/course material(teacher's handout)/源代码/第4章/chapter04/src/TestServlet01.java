/**** 文件4-1 ****/
/*
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(name = "TestServlet01")
public class TestServlet01 extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
    }


}*/

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "TestServlet01", urlPatterns = "/TestServlet01")
public class TestServlet01 extends HttpServlet {
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.print("Hello Servlet");
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}