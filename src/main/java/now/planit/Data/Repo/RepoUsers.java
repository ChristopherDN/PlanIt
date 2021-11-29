package now.planit.Data.Repo;

import now.planit.Data.Utility.DBManager;
import now.planit.Domain.Models.User;
import org.springframework.stereotype.Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Controller
public class RepoUsers {
  Connection connection;
  PreparedStatement ps;
  boolean bol;
  ResultSet rs;
  User user;
  String sql;
  ArrayList<String> statements = new ArrayList<>();




  public void query(String sqlCommand) {
    try {
      connection = DBManager.getConnection();
      ps = connection.prepareStatement(sqlCommand);

      bol = ps.execute();
    } catch (SQLException ex) {
      System.out.println(ex.getMessage());
    }
  }

  public ResultSet load(String sqlCommand) {
    try {
      connection = DBManager.getConnection();
      ps = connection.prepareStatement(sqlCommand);
      rs = ps.executeQuery();
    } catch (SQLException ex) {
      System.out.println(ex.getMessage());
//throw new ExceptionService(ex.getMessage())
      //Chose not to use ExceptionService, and instead catch as early as possible.
    }
    return rs;
  }

  public User rsToUser(ResultSet rs) {
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

  public User validateLogin(String n, String p) {
    return rsToUser(load("SELECT name, email, password FROM wishlist.users WHERE name = '" + n + "' AND password = '" + p + "'"));
  }

  public void newQuery(String sqlCommand, ArrayList<String> list){
    try {
      connection = DBManager.getConnection();
      PreparedStatement ps = connection.prepareStatement(sqlCommand);
      System.out.println(sqlCommand);
      for (int i = 0; i < list.size(); i++) {
        System.out.println(i+1);
        ps.setString(i+1, list.get(i));
        System.out.println(list.get(i));
      }
      ps.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void newLoad(String sqlCommand, ArrayList<String> list){
    try {
      Connection connection = DBManager.getConnection();
      PreparedStatement ps = connection.prepareStatement(sqlCommand);
      for (int i = 0; i < list.size(); i++) {
        ps.setString(i+1, list.get(i));
      }
      rs = ps.executeQuery(sqlCommand);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void registerUser(String name, String email, String password) {
    String sql = "insert into PlanIt.Users(username, email, password) values(?,?,?)";
    statements.clear();
    statements.add(name);
    statements.add(email);
    statements.add(password);
    newQuery(sql, statements);
  }
}
