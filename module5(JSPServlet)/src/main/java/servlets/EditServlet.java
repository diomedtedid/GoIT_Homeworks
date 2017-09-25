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

/**
 * Created by User on 25.09.2017.
 */
@WebServlet(name = "EditServlet", urlPatterns = "/edit")
public class EditServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        String param = request.getParameter("taskId");
        Long taskId = Long.parseLong(param);
        Task task = Controller.getController().getTask(taskId);
        request.setAttribute("task", task);
        request.getRequestDispatcher("WEB-INF/jsp/Edit.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        System.out.println("Method POST /edit is achived");
        Task task = Controller.getController().getTask(Long.parseLong(req.getParameter("id")));
        System.out.println(task);
        task.setTitle(req.getParameter("title"));
        task.setDescription(req.getParameter("description"));
        task.setComments(req.getParameter("comments"));
        task.setPriority(Priority.valueOf(req.getParameter("priority")));
        task.setStatus(Status.valueOf(req.getParameter("status")));
        Controller.getController().updateTask(task);

        req.getRequestDispatcher("/todo").forward(req, resp);

    }
}
