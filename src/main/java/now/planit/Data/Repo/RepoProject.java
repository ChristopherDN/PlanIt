package now.planit.Data.Repo;



import now.planit.Data.Utility.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



public class RepoProject implements RepoInterface{
  Connection connection;
  PreparedStatement ps;
  ResultSet rs;
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

  public void createProject(String projectName, String start, String finish, int budget, int userId) {

    sql = "insert into PlanIt.Projects(name, start, finish, budget, User_id) values(?,?,?,?,?) ";
    parameters.clear();
    parameters.add(projectName);
    parameters.add(start);
    parameters.add(finish);
    parameters.add(String.valueOf(budget));
    parameters.add(String.valueOf(userId));
    query(sql, parameters);

  }
}
