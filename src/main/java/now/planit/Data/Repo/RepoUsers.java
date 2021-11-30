package now.planit.Data.Repo;

import now.planit.Data.Utility.DBManager;
import now.planit.Domain.Models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class RepoUsers implements RepoInterface {
  Connection connection;
  PreparedStatement ps;
  ResultSet rs;
  User user;
  String sql;
  ArrayList<String> parameters = new ArrayList<>(); // AddParam ikke Statements

  @Override
  public PreparedStatement checkConnection(String sqlCommand) {
    try {
      connection = DBManager.getConnection();
      ps = connection.prepareStatement(sqlCommand);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return ps;
  }

  @Override
  public PreparedStatement setParameters(ArrayList<String> parameters) {
    try {
      for (int i = 0; i < parameters.size(); i++) {
        ps.setString(i + 1, parameters.get(i));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return ps;
  }

  @Override
  public void query(String sqlCommand, ArrayList<String> parameters) {
    try {
      ps = checkConnection(sqlCommand);
      setParameters(parameters).execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public ResultSet load(String sqlCommand, ArrayList<String> parameters) {
    try {
      ps = checkConnection(sqlCommand);
      rs = setParameters(parameters).executeQuery();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return rs;
  }

  @Override
  public void dbInput(String a, String b, String c, String d, String e) {

  }


  public User getUser(ResultSet rs) {
    try {
      user = null;
      while (rs.next()) {
        user = new User(rs.getString(1), rs.getString(2), rs.getString(3));
        System.out.println("får vi oprettet en user??" + user);
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
      //ps.clearParameters(); Er det her nødvendigt?
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
      //ps.clearParameters(); Er det her nødvendigt?
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
    parameters.clear();
    parameters.add(name);
    parameters.add(email);
    parameters.add(password);
    query(sql, parameters);
  }

  public User validateLogin(String email, String password) {
    sql = "SELECT username, email, password FROM PlanIt.Users WHERE email = ? AND password = ?";
    parameters.clear();
    parameters.add(email);
    parameters.add(password);
   return getUser(load(sql, parameters));
  }


}
