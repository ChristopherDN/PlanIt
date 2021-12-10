package now.planit.Data.Repo;

import now.planit.Domain.Models.Project;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



public class ProjectRepo {
  MapperDB mapperDB;
  String query;
  ArrayList<String> parameters = new ArrayList<>();
  ArrayList<Project> projects = new ArrayList<>();
  int projectId;
  String projectName;

  //Dependency injection constructor.
  public ProjectRepo(MapperDB mapperDB) {
    this.mapperDB = mapperDB;
  }

//Manipulate ResultSet to data we can use.
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

  public ArrayList<Project> loadProjects(ResultSet rs) {
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

  private String getProjectName(ResultSet rs) {
    try {
      while (rs.next()) {
        projectName = rs.getString(1);
      }
    } catch (SQLException ex) {
      System.out.println(ex.getMessage());
    }
    return projectName;
  }

  //DB do something
  public ArrayList<Project> getProjects(int userId) {
    query = "select name, start, finish, actual_cost, budget, actual_hours from PlanIt.Projects where User_id = ?";
    parameters.clear();
    parameters.add(String.valueOf(userId));
    return loadProjects(mapperDB.load(query, parameters));
  }

  public void createProject(String projectName, String start, String finish, int budget, int userId) {
    query = "insert into PlanIt.Projects(name, start, finish, budget, User_id) values(?,?,?,?,?)";
    parameters.clear();
    parameters.add(projectName);
    parameters.add(start);
    parameters.add(finish);
    parameters.add(String.valueOf(budget));
    parameters.add(String.valueOf(userId));
    mapperDB.save(query, parameters);
  }

  public int getProjectId(String projectName, int userId) {
    query ="select id from PlanIt.Projects where name = ? and user_id = ?";
    parameters.clear();
    parameters.add(projectName);
    parameters.add(String.valueOf(userId));
    return getId(mapperDB.load(query,parameters));
  }

  public void deleteProject(int projectId, int userId) {
    query = "delete from PlanIt.Projects where id = ? and User_id = ?";
    parameters.clear();
    parameters.add(String.valueOf(projectId));
    parameters.add(String.valueOf(userId));
    mapperDB.save(query, parameters);
  }

  public int getProjectId2(int taskId, int userId) {
    query ="select id from PlanIt.Projects where id = ? and user_id = ?";
    parameters.clear();
    parameters.add(String.valueOf(taskId));
    parameters.add(String.valueOf(userId));
    return getId(mapperDB.load(query,parameters));
  }

  public String getProjectName(String taskName) {
    query = "SELECT planit.Projects.name from PlanIt.Projects JOIN planit.Tasks ON planit.Projects.id=planit.Tasks.project_id where planit.Tasks.name = ?";
    parameters.clear();
    parameters.add(taskName);
    return getProjectName(mapperDB.load(query, parameters));
  }

  public void addActualHours(int hours, int projectId) {
    query = "update PlanIt.Projects set actual_hours = Projects.actual_hours + ?  where Projects.id = ?";
    parameters.clear();
    parameters.add(String.valueOf(hours));
    parameters.add(String.valueOf(projectId));
    mapperDB.save(query,parameters);
  }

  public void addActualCost(int cost, int projectId){
    query = "UPDATE PlanIt.Projects SET actual_cost = Projects.actual_cost + ?  WHERE Projects.id = ?";
    parameters.clear();
    parameters.add(String.valueOf(cost));
    parameters.add(String.valueOf(projectId));
    mapperDB.save(query,parameters);
  }
  public void subtractHours(int hours, int projectId) {
    query = "UPDATE PlanIt.projects SET actual_hours = actual_hours - ? WHERE id = ?";
    parameters.clear();
    parameters.add(String.valueOf(hours));
    parameters.add(String.valueOf(projectId));
    mapperDB.save(query, parameters);
  }

  public void subtractCost(int cost, int projectId) {
    query = "UPDATE PlanIt.projects SET actual_cost = actual_cost - ?  WHERE id = ?";
    parameters.clear();
    parameters.add(String.valueOf(cost));
    parameters.add(String.valueOf(projectId));
    mapperDB.save(query, parameters);
  }
}
