package now.planit.Data.Repo;

import now.planit.Domain.Models.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class UsersRepo {
  MapperDB mapperDB;
  User user;
  String query;
  ArrayList<String> parameters = new ArrayList<>();
  int getInt;

  //Dependency injection constructor.
  public UsersRepo(MapperDB mapperDB) {
    this.mapperDB = mapperDB;
  }

  //Manipulate ResultSet to other type of data
  public int getInt(ResultSet rs) {
    try {
      while (rs.next()) {
        getInt = rs.getInt(1);
      }
    } catch (SQLException ex) {
      System.out.println(ex.getMessage());
    }
    return getInt;
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

  public int registerUser(String name, String email, String password) {
    //Makes sure there is no shadow data in the ResultSet.
    mapperDB = new MapperDB();

    query = "INSERT INTO planit.users(name, email, password) VALUES(?,?,?)";
    parameters.clear();
    parameters.add(name);
    parameters.add(email);
    parameters.add(password);
    return mapperDB.insertUpdate(query, parameters);
  }

  public User validateLogin(String email, String password) {
    query = "SELECT name, email, password FROM planit.users WHERE email = ? AND password = ?";
    parameters.clear();
    parameters.add(email);
    parameters.add(password);
    return getUser(mapperDB.select(query, parameters));
  }

  public int getUserId(User user) {
    query = "SELECT id FROM planit.users WHERE email = ? AND password = ?";
    parameters.clear();
    parameters.add(user.getEmail());
    parameters.add(user.getPassword());
    return getInt(mapperDB.select(query, parameters));
  }

  public void updateName(String newName, int userId) {
    query = "UPDATE planit.users SET name = ? WHERE id = ?";
    parameters.clear();
    parameters.add(newName);
    parameters.add(String.valueOf(userId));
    mapperDB.insert(query, parameters);
  }

  public void updateEmail(String newEmail, int userId) {
    query = "UPDATE planit.users SET email = ? WHERE id = ?";
    parameters.clear();
    parameters.add(newEmail);
    parameters.add(String.valueOf(userId));
    mapperDB.insert(query, parameters);
  }

  public void updatePassword(String newPassword, int userId) {
    query = "UPDATE planit.users SET password = ? WHERE id = ?";
    parameters.clear();
    parameters.add(newPassword);
    parameters.add(String.valueOf(userId));
    mapperDB.insert(query, parameters);
  }

  public void deleteUser(String email, String password) {
    query = "DELETE FROM planit.users WHERE email = ? AND password = ?";
    parameters.clear();
    parameters.add(email);
    parameters.add(password);
    mapperDB.insert(query, parameters);
  }
}
