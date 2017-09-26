package servlets;

import model.controllers.Controller;
import model.domain.Priority;
import model.domain.Status;
import model.domain.Task;
import model.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by User on 26.09.2017.
 */
@WebServlet(name = "CreateServlet", urlPatterns = "/create")
public class CreateServlet extends HttpServlet {
    private Controller controller = Controller.getController();
    private User user;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("/create POST");
        Date dateOfBegin = Date.from(LocalDateTime.parse(request.getParameter ("begin_datetime")).atZone(ZoneId.systemDefault()).toInstant());
        Date dateOfEnd = Date.from(LocalDateTime.parse(request.getParameter ("end_datetime")).atZone(ZoneId.systemDefault()).toInstant());
        String title = request.getParameter("title");
        String decription = request.getParameter("description");
        String comments = request.getParameter("comments");
        Status status = Status.valueOf(request.getParameter("status"));
        Priority priority = Priority.valueOf(request.getParameter("priority"));

        Task task = new Task(dateOfBegin, dateOfEnd, user);
        task.setTitle(title.trim());
        task.setDescription(decription.trim());
        task.setComments(comments.trim());
        task.setStatus(status);
        task.setPriority(priority);
        controller.saveTask(task);

        response.sendRedirect("/todo");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        Long userId = Long.parseLong(request.getParameter("userId"));

        user = controller.getUserByName(userName);

        System.out.println(user);
        request.setAttribute("user", user);
        request.getRequestDispatcher("WEB-INF/jsp/Create.jsp").forward(request, response);

    }
}
