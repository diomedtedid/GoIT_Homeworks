package servlets;

import model.controllers.Controller;
import model.dao.inmemorydaoimpl.TaskMemoryDaoImpl;
import model.dao.inmemorydaoimpl.UserMemoryDaoImpl;
import model.domain.User;
import temp.FillTasks;

import javax.jws.soap.SOAPBinding;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Created by User on 10.09.2017.
 */

@WebServlet (name = "TodoServlet", urlPatterns = "/todo")
public class TodoServlet extends HttpServlet {
    private Controller controller;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        User user;
        String userName = null;
        System.out.println("Path achived");

        //Получаем объект данной сессии
        HttpSession httpSession = req.getSession(true);

        //Получаем все куки с браузера
        Cookie[] cookies = req.getCookies();

        //TODO: разобраться, почему с первого захода нихрена кукисов нету, а только после второго захода
        //Если на браузере куков нету, то создаем кук user=sessionId и отправляем его клиенту
        if (req.getCookies() == null) {
            System.out.println("Cookeis is absent");
            userName = httpSession.getId();
            resp.addCookie(new Cookie("user", userName));
            resp.flushBuffer();
            cookies = req.getCookies();
        }

        //Если куки на браузере есть, ищем среди них кук user
        long count = Arrays.asList(cookies).stream()
                .filter(cookie -> cookie.getName().equalsIgnoreCase("user")).count();

        //Если кука user нету, то создаем его. И создаем нового Юхера у нас в БД
        if (count == 0 ) {
            user = new User(userName);
            resp.addCookie(new Cookie("user", userName));
            controller.saveUser(user);
        } else { //если кук user существует, вытагиваем его значение (имя юзера)
            userName = Arrays.asList(cookies).stream()
                    .filter(cookie -> cookie.getName().equalsIgnoreCase("user"))
                    .findFirst()
                    .get()
                    .getValue();
            user = new User(userName);
        }

        //Передаем объект User дальше на JSP страницу
        req.setAttribute("user", user);

        req.getRequestDispatcher("WEB-INF/jsp/Todo.jsp").forward(req, resp);
    }


    //Собераем все зависимости БД и Инициализируем доступ сервлета к БД при первом обращении к сервлету
    @Override
    public void init() throws ServletException {
        System.out.println("Inicialization of Servlet");
        this.controller = Controller.getController();
        this.controller.setUserDao(new UserMemoryDaoImpl());
        this.controller.setTaskDao(new TaskMemoryDaoImpl());

        //Заполняем БД тестовыми данными
        FillTasks.fillTask();
    }
}
