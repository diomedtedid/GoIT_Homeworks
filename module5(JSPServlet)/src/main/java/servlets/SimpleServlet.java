package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by User on 06.09.2017.
 */

@WebServlet (name = "SimpleServlet", urlPatterns = "/module")
public class SimpleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OutputStream outputStream = resp.getOutputStream();
        Writer writer = new PrintWriter(outputStream);
        resp.setStatus(200);
        writer.write("TEST");
        writer.flush();
    }
}
