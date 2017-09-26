<%@ page import="model.controllers.Controller" %>
<%@ page import="model.domain.Task" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.ZoneId" %>
<%@ page import="java.time.temporal.TemporalUnit" %>
<%@ page import="java.time.temporal.ChronoUnit" %>
<%@ page import="model.domain.Status" %>
<%@ page import="model.domain.Priority" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 25.09.2017
  Time: 21:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8" %>
<html>
<head>
    <title>Edit the task</title>
</head>
<body>
<%!
    Controller controller = Controller.getController();
%>
<%

    Task task = (Task) request.getAttribute("task");
%>
<p align="centre">Edit the task</p>
<div align="left">

    <form id="edit_task" name="edit_task" method="post" action="/edit">
        <table>
            <tr>
                <th>
                    Task Id
                </th>
                <td>
                    <input type="hidden" name="id" value="<%=task.getId()%>">
                    <%=task.getId()%>
                </td>
            </tr>
            <tr>
                <th>
                    Begin date
                </th>
                <td>
                    <input type="datetime-local"
                           name="begin_datetime"
                           value="<%=LocalDateTime
               .ofInstant(task.getDateOfStart().toInstant(), ZoneId.systemDefault())
                .truncatedTo(ChronoUnit.MINUTES)%>"
                    >
                </td>
            </tr>
            <tr>
                <th>
                    End date
                </th>
                <td>
                    <input type="datetime-local"
                           name="end_datetime"
                           value="<%=LocalDateTime
               .ofInstant(task.getDateOfEnd().toInstant(), ZoneId.systemDefault())
                .truncatedTo(ChronoUnit.MINUTES)%>"
                    >
                </td>
            </tr>
            <tr>
                <th>
                    Title
                </th>
                <td>
                    <textarea name="title" rows="5" cols="25">
                        <%=task.getTitle()%>
                    </textarea>
                </td>
            </tr>
            <tr>
                <th>
                    Description
                </th>
                <td>
                    <textarea name="description" rows="5" cols="25">
                        <%=task.getDescription()%>
                    </textarea>
                </td>
            </tr>
            <tr>
                <th>
                    Comments
                </th>
                <td>
                    <textarea name="comments" rows="5" cols="25">
                        <%=task.getComments()%>
                    </textarea>
                </td>
            </tr>
            <tr>
                <th>
                    Priority
                </th>
                <td>
                    <select name="priority">
                        <% for (Priority priority : Priority.values()) {
                        %>
                        <option
                                <%if (priority.equals(task.getPriority())) {
                                    %>selected<%
                                }%>>
                            <%=priority.name()%>
                        </option>
                        <%
                            } %>
                    </select>
                </td>
            </tr>
            <tr>
                <th>
                    Status
                </th>
                <td>
                    <select name="status">
                        <% for (Status status : Status.values()) {
                            %>
                        <option
                                    <%if (status.equals(task.getStatus())) {
                                    %>selected<%}%>
                        >
                        <%=status.name()%></option>
                        <%}%>
                    </select>
                </td>
            </tr>
        </table>

        <input type="submit" value="Save">
    </form>
    <form method="get" action="/todo">
        <input type="submit" value="Cancel" />
    </form>
</div>
</body>
</html>
