<%@ page import="java.util.List" %>
<%@ page import="model.domain.Task" %>
<%@ page import="model.domain.User" %>
<%@ page import="model.controllers.Controller" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 12.09.2017
  Time: 22:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page pageEncoding="UTF-8" %>
<html>
<head>
    <title>ToDoList</title>
</head>
<body>
<%!
    Controller controller = Controller.getController();
%>
<div align="center" >
    <%
        User user = (User) request.getAttribute("user");
    %>
    <table  border="1">
        <caption align="center">TODO list</caption>
        <tr>
            <th>Task id</th>
            <th>Begin date</th>
            <th>End date</th>
            <th>Title</th>
            <th>Description</th>
            <th>Comments</th>
            <th>Priority</th>
            <th>Status</th>
            <th>
                <form align="right" name="create_task" method="get" action="/create">
                    <input type="hidden" name="userName" value="<%=user.getUserName()%>">
                    <input type="hidden" name="userId" value="<%=user.getId()%>">
                    <input type="submit" value="Create new task">
                </form>
            </th>
        </tr>

            <%
                List<Task> taskList = controller.getTaskListByUser(new User(user.getUserName()));
                for (Task task : taskList) { %>
        <tr>
            <td>
                <%= task.getId()%>
            </td>
            <td>
                <%= new SimpleDateFormat("yyyy.MM.dd HH:mm").format(task.getDateOfStart())%>
            </td>
            <td>
                <%= new SimpleDateFormat("yyyy.MM.dd HH:mm").format(task.getDateOfEnd())%>
            </td>
            <td>
                <%= task.getTitle()%>
            </td>
            <td>
                <%= task.getDescription()%>
            </td>
            <td>
                <%= task.getComments()%>
            </td>
            <td>
                <%= task.getPriority()%>
            </td>
            <td>
                <%= task.getStatus()%>
            </td>
            <td>
                <form align="center" name="create_task" method="get" action="/edit">
                    <input type="hidden" name="userId" value="<%=user.getId()%>">
                    <input type="hidden" name="taskId" value="<%=task.getId()%>">
                    <input type="submit" value="Edit task">
                </form>
            </td>
        </tr>
                <%
                }
            %>



    </table>
</div>

</body>
</html>
