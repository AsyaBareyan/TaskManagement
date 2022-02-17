package servlet;

import manager.TaskManager;
import manager.UserManager;
import model.Task;
import model.TaskStatus;
import model.User;
import model.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet(urlPatterns = "/managerHome")
public class ManagerHomeServlet extends HttpServlet {
    private UserManager userManager = new UserManager();
    private TaskManager taskManager = new TaskManager();
    private User user = new User();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Task> allTasks = taskManager.getAllTasks();
        List<User> allUsers = userManager.getAllUsers();
        req.setAttribute("tasks", allTasks);
        req.setAttribute("users", allUsers);
        req.getRequestDispatcher("/WEB-INF/manager.jsp").forward(req, resp);
    }
}

//        String name = req.getParameter("name");
//        String description = req.getParameter("description");
//        String deadline = req.getParameter("deadline");
//        TaskStatus task_status = TaskStatus.valueOf(req.getParameter("task_status"));
//         Task task = new Task();
//        task.setName(name);
//        task.setDescription(description);
//        try {
//            task.setDeadline(sdf.parse(deadline));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        task.setTaskStatus(task_status);
//        task.setUser(user);
//
//        taskManager.create(task);
//        req.getRequestDispatcher("/WEB-INF/managerHOME.jsp").forward(req, resp);
//
//    }
