package manager;

import dp.DBConnectionProvider;
import model.Task;
import model.TaskStatus;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    Connection connection = DBConnectionProvider.getInstance().getConnection();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private UserManager userManager = new UserManager();

    public void create(Task task) {
        String sql = "INSERT INTO task(name,description,deadline,task_status,user_id) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, task.getName());
            statement.setString(2, task.getDescription());
            if (task.getDeadline() != null) {
                statement.setString(3, sdf.format(task.getDeadline()));
            } else {
                statement.setString(3, null);

            }
            statement.setString(4, task.getTaskStatus().name());
            statement.setInt(5, task.getUserId());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                task.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Task getById(int id) {
        String sql = "SELECT *FROM task WHERE id = " + id;
        try {
            Statement statment = connection.createStatement();
            ResultSet resultSet = statment.executeQuery(sql);

            if (resultSet.next()) {

                return getTaskFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(int taskId, String newStatus) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE task SET task_status=? where id=?");
            preparedStatement.setString(1,newStatus);
            preparedStatement.setInt(2,taskId);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean delete(int id) {
        String sql = "DELETE FROM task WHERE id= " + id;
        try {
            Statement statment = connection.createStatement();
            statment.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Task getTaskFromResultSet(ResultSet resultSet) {
        try {
            return Task.builder()
                    .id(resultSet.getInt(1))
                    .name(resultSet.getString(2))
                    .description(resultSet.getString(3))
                    .deadline(resultSet.getString(4) == null ? null : sdf.parse(resultSet.getString(4)))
                    .taskStatus(TaskStatus.valueOf(resultSet.getString(5)))
                    .user(userManager.getById(resultSet.getInt(6)))
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;

        }
    }

    public List<Task> getAllTasksByUser(int userID) {
        List<Task> result = new ArrayList<>();
        String sql = "SELECT *FROM task WHERE user_id =  " + userID;
        try {
            PreparedStatement statment = connection.prepareStatement(sql);
            ResultSet resultSet = statment.executeQuery();
            while (resultSet.next()) {
                result.add(getTaskFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Task> getAllTasks() {
        String sql = "select * from task";
        List<Task> result = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Task task = new Task();
                task.setId(resultSet.getInt(1));
                task.setName(resultSet.getString(2));
                task.setDescription(resultSet.getString(3));
                task.setDeadline(sdf.parse(resultSet.getString(4)));
                task.setTaskStatus(TaskStatus.valueOf(resultSet.getString(5)));
                task.setUserId(resultSet.getInt(6));
                task.setUser(userManager.getById(task.getUserId()));
                result.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}

