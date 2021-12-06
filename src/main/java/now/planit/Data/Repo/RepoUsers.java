package now.planit.Data.Repo;

import now.planit.Domain.Models.User;
import now.planit.Exceptions.QueryDataFailException;
import now.planit.Exceptions.QueryDomainViewFailedException;

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
  public void registerUser(String name, String email, String password) throws QueryDomainViewFailedException {
    sql = "insert into PlanIt.Users(username, email, password) values(?,?,?)";
    parameters.clear();
    parameters.add(name);
    parameters.add(email);
    parameters.add(password);
    try{
      dbMapper.save(sql, parameters);
    }catch(QueryDataFailException d){

    try{
      dbMapper.save(sql, parameters);
    }catch(QueryDataFailException e){
      throw new QueryDomainViewFailedException("Query in domain layer failed");
    }
    }

  }

  public User validateLogin(String email, String password) throws QueryDomainViewFailedException {
    sql = "SELECT username, email, password FROM PlanIt.Users WHERE email = ? AND password = ?";
    parameters.clear();
    parameters.add(email);
    parameters.add(password);
    try {
      return getUser(dbMapper.load(sql, parameters));
    } catch(QueryDataFailException d){
      System.out.println("Could not process request" + d.getMessage());
    }
    try {
      return getUser(dbMapper.load(sql, parameters));
    } catch(QueryDataFailException d){
      throw new QueryDomainViewFailedException("Query in domain layer failed");
    }
  }


  public int getUserId(User user) throws QueryDomainViewFailedException {
    sql = "Select id from PlanIt.Users where email = ? AND password = ?";
    parameters.clear();
    parameters.add(user.getEmail());
    parameters.add(user.getPassword());
    try {
      return getId(dbMapper.load(sql, parameters));
    }catch(QueryDataFailException d){
      System.out.println("Could not process request" + d.getMessage());
    }
    try {
      return getId(dbMapper.load(sql, parameters));
    }catch(QueryDataFailException d){
      throw new QueryDomainViewFailedException("Query in domain layer failed");
    }

    }


  public void editName(String newName, int userId) throws QueryDomainViewFailedException {
    sql = "UPDATE PlanIt.Users SET username = ? WHERE id = ?";
    parameters.clear();
    parameters.add(newName);
    parameters.add(String.valueOf(userId));
    try{
      dbMapper.save(sql, parameters);
    }catch(QueryDataFailException d){

      try{
        dbMapper.save(sql, parameters);
      }catch(QueryDataFailException e){
        throw new QueryDomainViewFailedException("Query in domain layer failed");
      }
    }
  }

  public void editEmail(String newEmail, int userId) throws QueryDomainViewFailedException {
    sql = "UPDATE PlanIt.Users SET email = ? WHERE id = ?";
    parameters.clear();
    parameters.add(newEmail);
    parameters.add(String.valueOf(userId));
    try{
      dbMapper.save(sql, parameters);
    }catch(QueryDataFailException d){

      try{
        dbMapper.save(sql, parameters);
      }catch(QueryDataFailException e){
        throw new QueryDomainViewFailedException("Query in domain layer failed");
      }
    }
  }

  public void editPassword(String newPassword, int userId) throws QueryDomainViewFailedException {
    sql = "UPDATE PlanIt.Users SET password = ? WHERE id = ?";
    parameters.clear();
    parameters.add(newPassword);
    parameters.add(String.valueOf(userId));
    try{
      dbMapper.save(sql, parameters);
    }catch(QueryDataFailException d){

      try{
        dbMapper.save(sql, parameters);
      }catch(QueryDataFailException e){
        throw new QueryDomainViewFailedException("Query in domain layer failed");
      }
    }
  }
}
