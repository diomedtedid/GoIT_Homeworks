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

@WebServlet (urlPatterns = "/module")
public class SimpleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Path achieved!");
//        String massage = "Hello fucking World!";
//        req.setAttribute("massage", massage);
//        req.getRequestDispatcher("WEB-INF/jsp/SimpleJsp.jsp").forward(req, resp);
        req.getRequestDispatcher("WEB-INF/html/index.html").forward(req, resp);

    }
}
