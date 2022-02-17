package servlet;

import manager.TaskManager;
import model.User;
import model.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet(urlPatterns = "/changeTaskStatus")
public class ChangeTaskStatusServlet extends HttpServlet {

    private TaskManager taskManager = new TaskManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        int taskId = Integer.parseInt(req.getParameter("taskId"));
        String taskStatus = req.getParameter("task_status");

        taskManager.update(taskId, taskStatus);
        if (user.getType() == UserType.MANAGER) {
            resp.sendRedirect("/managerHome");
        } else {
            resp.sendRedirect("/userHome");
        }
    }
}
