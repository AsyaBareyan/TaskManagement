<%@ page import="java.util.List" %>
<%@ page import="model.Task" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users page</title>
</head>
<body>
<%
    List<Task> allTasks = (List<Task>) request.getAttribute("tasks");
%>
My all tasks:
<table border="1">

    <tr>
        <th>name</th>
        <th>description</th>
        <th>deadLine</th>
        <th>task status</th>
        <th>update status</th>
    </tr>
    <%
        for (Task task : allTasks) { %>
    <tr>
        <td><%=task.getName()%>
        </td>
        <td><%=task.getDescription()%>
        </td>
        <td><%=task.getDeadline()%>
        </td>
        <td><%=task.getTaskStatus().name()%></td>
        <td>
            <form action="/changeTaskStatus" method="post">
                <input type="hidden" name="taskId" value="<%=task.getId()%>">
                <select name="task_status">
                    <option value="NEW">NEW</option>
                    <option value="IN_PROCESS">IN_PROCESS</option>
                    <option value="FINISHED">FINISHED</option>
                </select><input type="submit" value="OK">
            </form>
        </td>
    </tr>
    <% }

    %>


</table>
</body>
</html>
