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
  ArrayList<String> statements = new ArrayList<>(); // AddParam ikke Statements


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

  public void newQuery(String sqlCommand, ArrayList<String> list) {
    try {
      connection = DBManager.getConnection();
      ps = connection.prepareStatement(sqlCommand);
      ps.clearParameters();
      for (int i = 0; i < list.size(); i++) {
        ps.setString(i + 1, list.get(i));
      }
      ps.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public ResultSet newLoad(String sqlCommand, ArrayList<String> list) {
    try {
      Connection connection = DBManager.getConnection();
      ps = connection.prepareStatement(sqlCommand);
      //ps.clearParameters();
      for (int i = 0; i < list.size(); i++) {
        ps.setString(i + 1, list.get(i));
      }
      rs = ps.executeQuery();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return rs;
  }

  public void registerUser(String name, String email, String password) {
    sql = "insert into PlanIt.Users(username, email, password) values(?,?,?)";
    statements.clear();
    statements.add(name);
    statements.add(email);
    statements.add(password);
    newQuery(sql, statements);
  }

  public User validateLogin(String email, String password) {
    sql = "SELECT username, email, password FROM PlanIt.Users WHERE email = ? AND password = ?";
    statements.clear();
    statements.add(email);
    statements.add(password);
    return getUser(newLoad(sql, statements));
  }
}
