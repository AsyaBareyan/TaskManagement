<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
<%@ page import="model.Task" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manager page</title>
</head>
<body>
<% List<User> users = (List<User>) request.getAttribute("users");%>
<% List<Task> tasks = (List<Task>) request.getAttribute("tasks");%>
<a href="/logout">logout</a><br>
Register user:<br>
<form action="/userRegister" method="post">
    Name: <input type="text" name="name"/><br>
    Surname: <input type="text" name="surname"/><br>
    email: <input type="email" name="email"/><br>
    password: <input type="password" name="password"/><br>
    user Type: <select name="userType">
    <option value="USER">USER</option>
    <option value="MANAGER">MANAGER</option>
</select><br>
    <input type="submit" value="add user">
</form>

Add task:
<form action="/addTask" method="post">
    Name: <input type="text" name="name"/><br>
    Description: <textarea name="description"/></textarea><br>
    deadLine: <input type="date" name="deadline"/><br>
    taskStatus: <select name="task_status">
    <option value="NEW">NEW</option>
    <option value="IN_PROCESS">IN_PROCESS</option>
    <option value="FINISHED">FINISHED</option>
</select><br>
    User_id: <select name="user_id">
    <%
        for (User user : users) {%>
    <option value="<%=user.getId()%>"><%=user.getName()%><%=user.getSurname()%>
    </option>

    <%

        }
    %>
</select><br>
    <input type="submit" value="add task">
</form>
<div>
    All users:<br>
    <table border="1">
        <tr>
            <th>name</th>
            <th>surname</th>
            <th>email</th>
            <th>type</th>
            <th>action</th>

        </tr>
        <%
            for (User user : users) {%>
        <tr>
            <td><%=user.getName()%>
            </td>
            <td><%=user.getSurname()%>
            </td>
            <td><%=user.getEmail()%>
            </td>
            <td><%=user.getType().name()%>
            </td>
        </tr>
        <tr>
            <td><%=user.getId()%></td>
            <td><%=user.getName()%></td>
            <td><%=user.getSurname()%></td>
            <td><%=user.getEmail()%></td>
            <td><%=user.getType().name()%></td>
            <td><a href="/deleteUser?id=<%=user.getId()%>"> Delete</a>/<a href="/editUser?id=<%=user.getId()%>" >Edit</td>
        </tr>
        <%
            }
        %>

    </table>
</div>
<div>
    All tasks:
    <table border="1">
        <tr>
            <th>name</th>
            <th>description</th>
            <th>deadline</th>
            <th>task_status</th>
            <th>user</th>
        </tr>
            <%
            for (Task task : tasks) { %>
        <tr>
            <td><%=task.getName()%>
            </td>
            <td><%=task.getDescription()%>
            </td>
            <td><%=task.getDeadline()%>
            </td>
            <td><%=task.getTaskStatus().name()%>
            </td>
            <td><%=task.getUser().getName() + " " + task.getUser().getSurname()%>
            </td>
        </tr>
            <%
            }
%>

</div>
</body>
</html>