package now.planit.Data.Repo;

import now.planit.Domain.Models.Project;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



public class RepoProject {
  DBMapper dbMapper = new DBMapper();
  String sql;
  ArrayList<String> parameters = new ArrayList<>();
  ArrayList<Project> projects = new ArrayList<>();
  int projectId;

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
            rs.getString(3), rs.getInt(4)));
      }
    } catch (SQLException ex) {
      System.out.println(ex.getMessage());
    }
    return projects;
  }


  //DB do something
  public void createProject(String projectName, String start, String finish, int budget, int userId) {
    sql = "insert into PlanIt.Projects(name, start, finish, budget, User_id) values(?,?,?,?,?) ";
    parameters.clear();
    parameters.add(projectName);
    parameters.add(start);
    parameters.add(finish);
    parameters.add(String.valueOf(budget));
    parameters.add(String.valueOf(userId));
    dbMapper.save(sql, parameters);
  }

    public int getProjectId(String projectName, int userId) {
    sql ="select id from PlanIt.Projects where name = ? and user_id = ?";
    parameters.clear();
    parameters.add(projectName);
    parameters.add(String.valueOf(userId));
    return getId(dbMapper.load(sql,parameters));
    }

  public ArrayList<Project> getProjects(int userId) {
      sql = "select name, start, finish, budget from PlanIt.Projects where User_id = ? ";
      parameters.clear();
      parameters.add(String.valueOf(userId));
      return loadProjects(dbMapper.load(sql, parameters));
    }


  public void deleteProject(int projectId, int userId) {
    sql = "delete from PlanIt.Projects where id = ? and User_id = ?";
    parameters.clear();
    parameters.add(String.valueOf(projectId));
    parameters.add(String.valueOf(userId));
    dbMapper.save(sql, parameters);
  }

  public int getProjectId2(int taskId, int userId) {
    sql ="select id from PlanIt.Projects where id = ? and user_id = ?";
    parameters.clear();
    parameters.add(String.valueOf(taskId));
    parameters.add(String.valueOf(userId));
    return getId(dbMapper.load(sql,parameters));
  }
}
