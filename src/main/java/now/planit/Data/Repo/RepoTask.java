package now.planit.Data.Repo;


import now.planit.Data.Utility.DBManager;
import now.planit.Domain.Models.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



public class RepoTask implements RepoInterface{
  Connection connection;
  PreparedStatement ps;
  boolean bol;
  ResultSet rs;
  ArrayList<String> parameters = new ArrayList<>();
  String sql;
  ArrayList<Task> tasks = new ArrayList<>();


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
  public void save(String sqlCommand, ArrayList<String> parameters) {
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

  public ArrayList<Task> loadTasks(ResultSet rs){
    try {
      tasks.clear();
      while (rs.next()) {
        tasks.add(new Task(rs.getString(1), rs.getString(2),
                rs.getString(3), rs.getString(4)));

      }
    } catch (SQLException ex) {
      System.out.println(ex.getMessage());
    }
    return tasks;
  }

  public ArrayList<Task> getTasks(int projectId){
    sql ="select name, start, finish, budget from PlanIt.Tasks where project_Id = ?";
    parameters.clear();
    parameters.add(String.valueOf(projectId));
    return loadTasks(load(sql,parameters));
  }
}


