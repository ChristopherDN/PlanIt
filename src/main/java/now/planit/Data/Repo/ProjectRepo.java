package now.planit.Data.Repo;

import now.planit.Domain.Models.Project;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProjectRepo implements InterfaceProjectRepo {
  MapperDB mapperDB;
  String query;
  ArrayList<String> parameters = new ArrayList<>();
  ArrayList<Project> projects = new ArrayList<>();
  int projectId;
  String string;

  //Dependency injection constructor.
  public ProjectRepo(MapperDB mapperDB) {
    this.mapperDB = mapperDB;
  }

//Manipulate ResultSet to data we can use.
@Override
  public int getInt(ResultSet rs){
    try {
      while (rs.next()) {
        projectId = rs.getInt(1);
      }
    } catch (SQLException ex) {
      System.out.println(ex.getMessage());
    }
    return projectId;
  }

  @Override
  public String getString(ResultSet rs) {
    try {
      if (rs.next()) {
        string = rs.getString(1);
      }
    } catch (SQLException ex) {
      System.out.println(ex.getMessage());
    }
    return string;
  }

  @Override
  public ArrayList<Project> getProjects(ResultSet rs) {
    try {
      projects.clear();
      while (rs.next()) {
        projects.add(new Project(rs.getString(1), rs.getString(2),
            rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6)));
      }
    } catch (SQLException ex) {
      System.out.println(ex.getMessage());
    }
    return projects;
  }

  @Override
  public ArrayList<Project> loadProjects(int userId) {
    query = "SELECT name, start, finish, actual_cost, budget, actual_hours FROM planit.projects WHERE User_id = ?";
    parameters.clear();
    parameters.add(String.valueOf(userId));
    return getProjects(mapperDB.select(query, parameters));
  }

  @Override
  public void createProject(String projectName, String start, String finish, int budget, int userId) {
    query = "INSERT INTO planit.projects(name, start, finish, budget, User_id) VALUES(?,?,?,?,?)";
    parameters.clear();
    parameters.add(projectName);
    parameters.add(start);
    parameters.add(finish);
    parameters.add(String.valueOf(budget));
    parameters.add(String.valueOf(userId));
    mapperDB.insert(query, parameters);
  }

  @Override
  public int getProjectId(String projectName, int userId) {
    query ="SELECT id FROM planit.projects WHERE name = ? AND user_id = ?";
    parameters.clear();
    parameters.add(projectName);
    parameters.add(String.valueOf(userId));
    return getInt(mapperDB.select(query,parameters));
  }

  @Override
  public void deleteProject(int projectId, int userId) {
    query = "DELETE FROM planit.projects WHERE id = ? AND User_id = ?";
    parameters.clear();
    parameters.add(String.valueOf(projectId));
    parameters.add(String.valueOf(userId));
    mapperDB.insert(query, parameters);
  }

  @Override
  public String getProjectName(String taskName, int userID) {
    //Validates even tho there are many tasks with same name.
    query = "SELECT planit.projects.name from planit.projects JOIN planit.tasks ON planit.projects.id=planit.tasks.project_id WHERE planit.tasks.name = ? and  planit.projects.user_id = ?";
    parameters.clear();
    parameters.add(taskName);
    parameters.add(String.valueOf(userID));
    return getString(mapperDB.select(query, parameters));
  }

  @Override
  public void addActualHours(int hours, int projectId) {
    query = "UPDATE planit.projects SET actual_hours = projects.actual_hours + ?  WHERE projects.id = ?";
    parameters.clear();
    parameters.add(String.valueOf(hours));
    parameters.add(String.valueOf(projectId));
    mapperDB.insert(query,parameters);
  }

  @Override
  public void addActualCost(int cost, int projectId){
    query = "UPDATE planit.projects SET actual_cost = projects.actual_cost + ?  WHERE projects.id = ?";
    parameters.clear();
    parameters.add(String.valueOf(cost));
    parameters.add(String.valueOf(projectId));
    mapperDB.insert(query,parameters);
  }
  @Override
  public void subtractHours(int hours, int projectId) {
    query = "UPDATE planit.projects SET actual_hours = actual_hours - ? WHERE id = ?";
    parameters.clear();
    parameters.add(String.valueOf(hours));
    parameters.add(String.valueOf(projectId));
    mapperDB.insert(query, parameters);
  }

  @Override
  public void subtractCost(int cost, int projectId) {
    query = "UPDATE planit.projects SET actual_cost = actual_cost - ?  WHERE id = ?";
    parameters.clear();
    parameters.add(String.valueOf(cost));
    parameters.add(String.valueOf(projectId));
    mapperDB.insert(query, parameters);
  }
}
