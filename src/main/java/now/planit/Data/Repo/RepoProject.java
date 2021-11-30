package now.planit.Data.Repo;



import now.planit.Data.Utility.DBManager;
import now.planit.Domain.Models.Project;
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
  String sql;
  ArrayList<String> parameters = new ArrayList<>();
  ArrayList<Project> projects = new ArrayList<>();
  Project project;
  int projectId;


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

  public int getId(ResultSet rs){
    try {
      while (rs.next()) {
        projectId = rs.getInt(1);
      }
    } catch (SQLException ex) {
      System.out.println(ex.getMessage());
    }
    return projectId;
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

    public int getProjectId(String projectName, int userId) {
    sql ="select id from PlanIt.Projects where name = ? and user_id = ?";
    parameters.clear();
    parameters.add(projectName);
    parameters.add(String.valueOf(userId));
    return getId(load(sql,parameters));
    }
  }

  public ArrayList<Project> getProjects(int userId) {

      sql = "Select id from PlanIt.Users where email = ? AND password = ?";
      parameters.clear();
      parameters.add(project.getName());
      parameters.add(project.getStart());
      parameters.add(project.getFinish());
      parameters.add(String.valueOf(project.getBudget()));
      return loadProjects(load(sql, parameters));

    }

  public ArrayList<Project> loadProjects(ResultSet rs) {
    try {
      projects.clear();
      while (rs.next()) {
        projects.add(new Project(rs.getString(1), rs.getString(2),
                rs.getString(3), rs.getInt(4)));
      }
    } catch (SQLException ex) {
      System.out.println(ex.getMessage());
    }
    return projects;
  }



}
