<%@ page import="model.controllers.Controller" %>
<%@ page import="model.domain.User" %>
<%@ page import="model.domain.Priority" %>
<%@ page import="model.domain.Status" %>
<%@ page import="java.time.LocalDate" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 26.09.2017
  Time: 18:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create task</title>
</head>
<body>
    <%
        User user = (User) request.getAttribute("user");
    %>

    <p align="centre">Edit the task</p>
    <div align="left">

        <form id="edit_task" name="edit_task" method="post" action="/create">
            <table>
                <tr>
                    <th>
                        Begin date
                    </th>
                    <td>
                        <input type="datetime-local"
                               name="begin_datetime"
                               value="<%=LocalDate.now()%>T00:00"
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
                               value="<%=LocalDate.now().plusDays(1)%>T00:00"
                        >
                    </td>
                </tr>
                <tr>
                    <th>
                        Title
                    </th>
                    <td>
                    <textarea name="title" rows="5" cols="25">
                    </textarea>
                    </td>
                </tr>
                <tr>
                    <th>
                        Description
                    </th>
                    <td>
                    <textarea name="description" rows="5" cols="25">

                    </textarea>
                    </td>
                </tr>
                <tr>
                    <th>
                        Comments
                    </th>
                    <td>
                    <textarea name="comments" rows="5" cols="25">

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
                                        <%if (priority.equals(Priority.REGULAR)) {
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
                                        <%if (status.equals(Status.OPEN)) {
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
