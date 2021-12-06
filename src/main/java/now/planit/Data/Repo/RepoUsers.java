package now.planit.Data.Repo;

import now.planit.Domain.Models.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class RepoUsers {
    DBMapper dbMapper = new DBMapper();
    User user;
    String sql;
    ArrayList<String> parameters = new ArrayList<>();
    int userId;

    //Manipulate ResultSet to other type of data
    public int getId(ResultSet rs) {
        try {
            while (rs.next()) {
                userId = rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return userId;
    }

    public User getUser(ResultSet rs) {
        try {
            user = null;
            while (rs.next()) {
                user = new User(rs.getString(1), rs.getString(2), rs.getString(3));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return user;
    }


    //Do Something to Database
    public void registerUser(String name, String email, String password) {
        sql = "insert into PlanIt.Users(username, email, password) values(?,?,?)";
        parameters.clear();
        parameters.add(name);
        parameters.add(email);
        parameters.add(password);
        dbMapper.save(sql, parameters);
        dbMapper.save(sql, parameters);

    }

    public User validateLogin(String email, String password)  {
        sql = "SELECT username, email, password FROM PlanIt.Users WHERE email = ? AND password = ?";
        parameters.clear();
        parameters.add(email);
        parameters.add(password);
        return getUser(dbMapper.load(sql, parameters));

    }


    public int getUserId(User user)  {
        sql = "Select id from PlanIt.Users where email = ? AND password = ?";
        parameters.clear();
        parameters.add(user.getEmail());
        parameters.add(user.getPassword());
        return getId(dbMapper.load(sql, parameters));

    }


    public void editName(String newName, int userId) {
        sql = "UPDATE PlanIt.Users SET username = ? WHERE id = ?";
        parameters.clear();
        parameters.add(newName);
        parameters.add(String.valueOf(userId));
        dbMapper.save(sql, parameters);

    }

    public void editEmail(String newEmail, int userId) {
        sql = "UPDATE PlanIt.Users SET email = ? WHERE id = ?";
        parameters.clear();
        parameters.add(newEmail);
        parameters.add(String.valueOf(userId));
        dbMapper.save(sql, parameters);

    }

    public void editPassword(String newPassword, int userId) {
        sql = "UPDATE PlanIt.Users SET password = ? WHERE id = ?";
        parameters.clear();
        parameters.add(newPassword);
        parameters.add(String.valueOf(userId));
        dbMapper.save(sql, parameters);

    }
}
