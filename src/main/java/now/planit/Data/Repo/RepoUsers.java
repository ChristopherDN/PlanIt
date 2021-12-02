package now.planit.Data.Repo;

import now.planit.Domain.Models.User;
import now.planit.Domain.Services.ExceptionService;

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
  public void registerUser(String name, String email, String password) throws ExceptionService {
    sql = "insert into PlanIt.Users(username, email, password) values(?,?,?)";
    parameters.clear();
    parameters.add(name);
    parameters.add(email);
    parameters.add(password);
    dbMapper.save(sql, parameters);
    /* Eksempel på Exception
    try{
      dbMapper.save(sql, parameters);
    }catch(ExceptionService e){

      try{
        dbMapper.save(sql, parameters);
      }catch(ExceptionService e){
        throw new Exception("Database unavailable");

      }
    }

     */

  }

  public User validateLogin(String email, String password) {
    sql = "SELECT username, email, password FROM PlanIt.Users WHERE email = ? AND password = ?";
    parameters.clear();
    parameters.add(email);
    parameters.add(password);
   return getUser(dbMapper.load(sql, parameters));
  }


  public int getUserId(User user) {
    sql = "Select id from PlanIt.Users where email = ? AND password = ?";
    parameters.clear();
    parameters.add(user.getEmail());
    parameters.add(user.getPassword());
    return getId(dbMapper.load(sql, parameters));
  }
}
