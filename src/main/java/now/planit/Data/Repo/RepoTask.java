package now.planit.Data.Repo;

import now.planit.Domain.Models.Task;
import now.planit.Exceptions.QueryDataFailException;
import now.planit.Exceptions.QueryDomainViewFailedException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



public class RepoTask {
  DBMapper dbMapper = new DBMapper();
  ArrayList<String> parameters = new ArrayList<>();
  String sql;
  ArrayList<Task> tasks = new ArrayList<>();
  int taskId;
  int hours;


  //Manipulate Resultset to data we can use
  public ArrayList<Task> loadTasks(ResultSet rs){
    try {
      tasks.clear();
      while (rs.next()) {
        tasks.add(new Task(rs.getString(1), rs.getString(2),
                rs.getString(3),rs.getInt(4) , rs.getInt(5)));

      }
    } catch (SQLException ex) {
      System.out.println(ex.getMessage());
    }
    return tasks;
  }

    public int getId(ResultSet rs){
        try {
            while (rs.next()) {
                taskId = rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return taskId;
    }

  //Db Do something.
  public ArrayList<Task> getTasks(int projectId) throws QueryDomainViewFailedException {
    sql ="select name, start, finish, hours, cost from PlanIt.Tasks where project_Id = ?";
    parameters.clear();
    parameters.add(String.valueOf(projectId));
    try {
        return loadTasks(dbMapper.load(sql, parameters));
    } catch(QueryDataFailException d){
        System.out.println("Could not process request" + d.getMessage());
    }
    try{
        return loadTasks(dbMapper.load(sql, parameters));
    }catch(QueryDataFailException d){
        throw new QueryDomainViewFailedException("Query in domain layer failed");
    }
  }


  public void createTask(String taskName, String startDate, String finishDate, int cost, int projectId) throws QueryDomainViewFailedException {
      sql=" insert into PlanIt.Tasks ( name, start, finish, hours, cost, project_id ) values (?, ?, ?, ?, ?, ?) ";
      parameters.clear();
      parameters.add(taskName);
      parameters.add(startDate);
      parameters.add(finishDate);
      parameters.add("0");
      parameters.add(String.valueOf(cost));
      parameters.add(String.valueOf(projectId));
      try {
          dbMapper.save(sql, parameters);
      }catch(QueryDataFailException d){
          System.out.println("Could not process request" + d.getMessage());
      }
      try{
          dbMapper.save(sql, parameters);
      }catch(QueryDataFailException d){
          throw new QueryDomainViewFailedException("Query in domain layer failed");
      }
  }

  //TODO Den her retunere så vidt jeg kan se altid 5.
    public int getTaskId(String taskName, int projectId) throws QueryDomainViewFailedException {
        sql ="select id from PlanIt.Tasks where name = ? and project_id = ?";
        parameters.clear();
        parameters.add(taskName);
        parameters.add(String.valueOf(projectId));
        try {
            return getId(dbMapper.load(sql, parameters));
        }catch(QueryDataFailException d){
            System.out.println("Could not process request" + d.getMessage());
        }
        try{
            return getId(dbMapper.load(sql, parameters));
        }catch(QueryDataFailException d){
            throw new QueryDomainViewFailedException("Query in domain layer failed");
        }
    }

  public void deleteTask(int taskId, int projectId) throws QueryDomainViewFailedException {
    sql = "delete from PlanIt.Tasks where id = ? and project_id = ?";
    parameters.clear();
    parameters.add(String.valueOf(taskId));
    parameters.add(String.valueOf(projectId));

    try {
        dbMapper.save(sql, parameters);
    }catch(QueryDataFailException d){
        System.out.println("Could not process request" + d.getMessage());
    }
    try{
        dbMapper.save(sql, parameters);
    }catch(QueryDataFailException d){
        throw new QueryDomainViewFailedException("Query in domain layer failed");
    }
  }

  public int getTaskId2(int subtaskId, int projectId) throws QueryDomainViewFailedException {
    sql ="select id from PlanIt.Tasks where id = ? and project_id = ?";
    parameters.clear();
    parameters.add(String.valueOf(subtaskId));
    parameters.add(String.valueOf(projectId));
    try {
        return getId(dbMapper.load(sql, parameters));
    } catch(QueryDataFailException d){
        System.out.println("Could not process request" + d.getMessage());
    }
    try{
        return getId(dbMapper.load(sql, parameters));
    } catch(QueryDataFailException d){
        throw new QueryDomainViewFailedException("Query in domain layer failed");
    }
  }

  public int getProjectID(String taskName) throws QueryDomainViewFailedException {
    sql ="select project_id from PlanIt.Tasks where name = ?";
    parameters.clear();
    parameters.add(taskName);
    try {
        return getId(dbMapper.load(sql, parameters));
    } catch(QueryDataFailException d){
        System.out.println("Could not process request" + d.getMessage());
    }
    try{
        return getId(dbMapper.load(sql, parameters));
    } catch(QueryDataFailException d){
        throw new QueryDomainViewFailedException("Query in domain layer failed");
    }
  }





    public void addHours(String hours, String taskName, int projectId) throws QueryDomainViewFailedException {
    sql = "update PlanIt.tasks set hours = hours + ? where name = ? and project_id = ?";
    parameters.clear();
    parameters.add(hours);
    parameters.add(taskName);
    parameters.add(String.valueOf(projectId));
    try {
        dbMapper.save(sql, parameters);
    }catch(QueryDataFailException d){
        System.out.println("Could not process request" + d.getMessage());
    }
    try{
        dbMapper.save(sql, parameters);
    } catch(QueryDataFailException d){
        throw new QueryDomainViewFailedException("Query in domain layer failed");
    }
    }
}



