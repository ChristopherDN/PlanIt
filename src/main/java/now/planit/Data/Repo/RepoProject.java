package now.planit.Data.Repo;



import now.planit.Data.Utility.DBManager;
import now.planit.Domain.Models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



public class RepoProject implements RepoInterface{
  Connection connection;
  PreparedStatement ps;
  ResultSet rs;
  User user;
  String sql;
  ArrayList<String> parameters = new ArrayList<>();


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
  public PreparedStatement setString(ArrayList<String> parameters) {
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
      setString(parameters).execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public ResultSet load(String sqlCommand, ArrayList<String> parameters) {
    try {
     ps = checkConnection(sqlCommand);
      rs = setString(parameters).executeQuery();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return rs;
  }

  @Override
  public void dbInput(String a, String b, String c, String d, String e) {
  }
}
