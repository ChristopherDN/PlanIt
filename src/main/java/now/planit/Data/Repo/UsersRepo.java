package now.planit.Data.Repo;

import now.planit.Domain.Models.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class UsersRepo {
  User user;
  String sql;
  ArrayList<String> parameters = new ArrayList<>();
  String userEmail;
  int userId;
  int i = 0;
  MapperDB mapperDB;

  public UsersRepo(MapperDB mapperDB) {
    this.mapperDB = mapperDB;
  }


  //Manipulate ResultSet to other type of data
  public int getId(ResultSet rs){
    try {
      while (rs.next()) {
        userId = rs.getInt(1);
      }
    } catch (SQLException ex) {
      System.out.println(ex.getMessage());
    }
    return userId;
  }

  //Builder pattern, hvis der er mange attributter i en konstruktør.
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

  public String getEmail(ResultSet rs){
    try {
      if (rs.next()) {
        userEmail = rs.getString(1);
      }
    } catch (SQLException ex) {
      System.out.println(ex.getMessage());
    }

    return userEmail;
  }



  public int registerUser(String name, String email, String password) {
    //Makes sure there is no shadow data in the ResultSet.
    mapperDB = new MapperDB();


    sql = "insert into PlanIt.Users(username, email, password) values(?,?,?)";
    parameters.clear();
    parameters.add(name);
    parameters.add(email);
    parameters.add(password);
    int test = mapperDB.saveUpdate(sql, parameters);
    System.out.println(test);
    return test;
  }

  public User validateLogin(String email, String password) {
    sql = "SELECT username, email, password FROM PlanIt.Users WHERE email = ? AND password = ?";
    parameters.clear();
    parameters.add(email);
    parameters.add(password);
   return getUser(mapperDB.load(sql, parameters));
  }


  public int getUserId(User user) {
    sql = "Select id from PlanIt.Users where email = ? AND password = ?";
    parameters.clear();
    parameters.add(user.getEmail());
    parameters.add(user.getPassword());
    return getId(mapperDB.load(sql, parameters));
  }

  public void editName(String newName, int userId) {
    sql = "UPDATE PlanIt.Users SET username = ? WHERE id = ?";
    parameters.clear();
    parameters.add(newName);
    parameters.add(String.valueOf(userId));
    mapperDB.save(sql, parameters);
  }

  public void editEmail(String newEmail, int userId) {
    sql = "UPDATE PlanIt.Users SET email = ? WHERE id = ?";
    parameters.clear();
    parameters.add(newEmail);
    parameters.add(String.valueOf(userId));
    mapperDB.save(sql, parameters);
  }

  public void editPassword(String newPassword, int userId) {
    sql = "UPDATE PlanIt.Users SET password = ? WHERE id = ?";
    parameters.clear();
    parameters.add(newPassword);
    parameters.add(String.valueOf(userId));
    mapperDB.save(sql, parameters);
  }

  public void deleteUser(String email, String password) {
    sql = "delete from PlanIt.Users where email = ? and password = ?";
    parameters.clear();
    parameters.add(email);
    parameters.add(password);
    mapperDB.save(sql, parameters);
  }
}
