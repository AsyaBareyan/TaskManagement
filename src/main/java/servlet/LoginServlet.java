package servlet;

import manager.TaskManager;
import manager.UserManager;
import model.Task;
import model.User;
import model.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    private final UserManager userManager = new UserManager();
    private TaskManager taskManager = new TaskManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = userManager.getByEmail(email);

//        if (user != null && user.getPassword().equals(password)) {
        if (user == null) {
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        } else {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            if (user.getType().equals(UserType.MANAGER)) {
                resp.sendRedirect("/managerHome");

//            List<Task> tasks = taskManager.getAllTasksByUser(user.getId());
//            req.setAttribute("tasks", tasks);
//            req.getRequestDispatcher("/WEB-INF/users.jsp").forward(req, resp);
            } else {
//        if (user.getType().equals(UserType.MANAGER)) {
                resp.sendRedirect("/userHome");

//            List<User> allUsers = userManager.getAllUsers();
//            req.setAttribute("allUsers", allUsers);
//            req.getRequestDispatcher("/WEB-INF/manager.jsp").forward(req, resp);

            }
        }
    }
}


//        UserType userType = UserType.valueOf(req.getParameter("userType"));
//        if (userManager.getByEmail(email) != null && user.getPassword().equals(password)) {
//            if (user.getType() == UserType.MANAGER) {
//
//                user.setName(name);
//                user.setSurname(surname);
//                user.setEmail(email);
//                user.setPassword(password);
//                user.setType(userType);
//
//                userManager.add(user);
//                resp.sendRedirect("/WEB-INF/manager.jsp");
//            } else if (user.getType() == UserType.USER) {
////                UserType userType=UserType.valueOf(req.getParameter("userType"));
//
//                user.setName(name);
//                user.setSurname(surname);
//                user.setEmail(email);
//                user.setPassword(password);
//                user.setType(userType);
//
//                userManager.add(user);
//                resp.sendRedirect("WEB-INF/users.jsp");
//            }
//        }


