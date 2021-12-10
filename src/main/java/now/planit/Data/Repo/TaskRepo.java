package now.planit.Data.Repo;

import now.planit.Domain.Models.Task;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class TaskRepo {
  ArrayList<String> parameters = new ArrayList<>();
  String sql;
  ArrayList<Task> tasks = new ArrayList<>();
  int getInt;
  MapperDB mapperDB;

  public TaskRepo(MapperDB mapperDB) {
    this.mapperDB = mapperDB;
  }


  //Manipulate Resultset to data we can use
  public ArrayList<Task> loadTasks(ResultSet rs) {
    try {
      tasks.clear();
      while (rs.next()) {
        tasks.add(new Task(rs.getString(1), rs.getString(2),
            rs.getString(3), rs.getInt(4), rs.getInt(5)));

      }
    } catch (SQLException ex) {
      System.out.println(ex.getMessage());
    }
    return tasks;
  }

  public int getInt(ResultSet rs) {
    try {
      while (rs.next()) {
        getInt = rs.getInt(1);
      }
    } catch (SQLException ex) {
      System.out.println(ex.getMessage());
    }
    return getInt;
  }

  //Db Do something.
  public ArrayList<Task> getTasks(int projectId) {
    sql = "select name, start, finish, hours, cost from PlanIt.Tasks where project_Id = ?";
    parameters.clear();
    parameters.add(String.valueOf(projectId));
    tasks = loadTasks(mapperDB.load(sql, parameters));
    System.out.println("This is size of Tasks in each Project: " + tasks.size());
    return tasks;
  }


  public void createTask(String taskName, String startDate, String finishDate, int projectId) {
    sql = " insert into PlanIt.Tasks ( name, start, finish, project_id ) values (?, ?, ?, ?) ";
    parameters.clear();
    parameters.add(taskName);
    parameters.add(startDate);
    parameters.add(finishDate);
    parameters.add(String.valueOf(projectId));
    mapperDB.save(sql, parameters);
  }

  public int getTaskId(String taskName, int projectId) {
    sql = "select id from PlanIt.Tasks where name = ? and project_id = ?";
    parameters.clear();
    parameters.add(taskName);
    parameters.add(String.valueOf(projectId));
    return getInt(mapperDB.load(sql, parameters));
  }

  public void deleteTask(int taskId, int projectId) {
    sql = "delete from PlanIt.Tasks where id = ? and project_id = ?";
    parameters.clear();
    parameters.add(String.valueOf(taskId));
    parameters.add(String.valueOf(projectId));
    mapperDB.save(sql, parameters);
  }

  public int getTaskId2(int subtaskId, int projectId) {
    sql = "select id from PlanIt.Tasks where id = ? and project_id = ?";
    parameters.clear();
    parameters.add(String.valueOf(subtaskId));
    parameters.add(String.valueOf(projectId));
    return getInt(mapperDB.load(sql, parameters));
  }

  public int getProjectID(String taskName) {
    sql = "select project_id from PlanIt.Tasks where name = ?";
    parameters.clear();
    parameters.add(taskName);
    return getInt(mapperDB.load(sql, parameters));
  }

  public int getHours(int taskId, int projectId){
    sql = "select hours from PlanIt.tasks where id = ? and project_id = ?";
    parameters.clear();
    parameters.add(String.valueOf(taskId));
    parameters.add(String.valueOf(projectId));
    return getInt(mapperDB.load(sql,parameters));
  }

  public int getCost(int taskId, int projectId){
    sql = "select cost from PlanIt.tasks where id = ? and project_id = ?";
    parameters.clear();
    parameters.add(String.valueOf(taskId));
    parameters.add(String.valueOf(projectId));
    return getInt(mapperDB.load(sql,parameters));
  }

  public void addHours(int hours, String taskName, int projectId) {
    sql = "update PlanIt.tasks set Tasks.hours = Tasks.hours + ? where name = ? and project_id = ?";
    parameters.clear();
    parameters.add(String.valueOf(hours));
    parameters.add(taskName);
    parameters.add(String.valueOf(projectId));
    mapperDB.save(sql, parameters);
  }

  public void addActualCost(int cost, String taskName, int projectId) {
    sql = "update PlanIt.Tasks set Tasks.cost = Tasks.cost + ?  where Tasks.name = ? and Tasks.Project_id = ? ";
    parameters.clear();
    parameters.add(String.valueOf(cost));
    parameters.add(taskName);
    parameters.add(String.valueOf(projectId));
    mapperDB.save(sql, parameters);
  }

  public void subtractHours(int hours, String taskName, int projectId) {
    sql = "update PlanIt.tasks set Tasks.hours = Tasks.hours - ? where name = ? and Tasks.project_id = ?";
    parameters.clear();
    parameters.add(String.valueOf(hours));
    parameters.add(taskName);
    parameters.add(String.valueOf(projectId));
    mapperDB.save(sql, parameters);
  }

  public void subtractCost(int cost, String taskName, int projectId) {
    sql = "update PlanIt.Tasks set cost = Tasks.cost - ?  where Tasks.name = ? and Tasks.Project_id = ?";
    parameters.clear();
    parameters.add(String.valueOf(cost));
    parameters.add(taskName);
    parameters.add(String.valueOf(projectId));
    mapperDB.save(sql, parameters);
  }
}



