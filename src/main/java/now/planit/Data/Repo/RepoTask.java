package now.planit.Data.Repo;

import now.planit.Domain.Models.Task;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



public class RepoTask {
  DBMapper dbMapper = new DBMapper();
  ArrayList<String> parameters = new ArrayList<>();
  String sql;
  ArrayList<Task> tasks = new ArrayList<>();

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
    return loadTasks(dbMapper.load(sql,parameters));
  }
}


