package servlet;

import manager.TaskManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeTaskStatusServlet extends HttpServlet {

    private TaskManager taskManager=new TaskManager();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int taskId=Integer.parseInt(req.getParameter("taskId"));
        String taskStatus=req.getParameter("task_status");

        taskManager.update(taskId,taskStatus);
    }
}
