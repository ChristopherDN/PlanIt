package now.planit.Data.Repo;

import now.planit.Domain.Models.Subtask;
import now.planit.Domain.Models.Task;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Christopher
 */
public class RepoSubtask {
    DBMapper dbMapper = new DBMapper();
    ArrayList<String> parameters = new ArrayList<>();
    String sql;
    ArrayList<Subtask> subtasks = new ArrayList<>();

    //Manipulate Resultset to data we can use
    public ArrayList<Subtask> loadSubtasks(ResultSet rs){
        try {
            subtasks.clear();
            while (rs.next()) {
                subtasks.add(new Subtask(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getInt(4)));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return subtasks;
    }

    //Db Do something.
    public ArrayList<Subtask> getSubtasks(int taskId){
        sql ="select name, start, finish, cost from PlanIt.Subtasks where id = ?";
        parameters.clear();
        parameters.add(String.valueOf(taskId));
        return loadSubtasks(dbMapper.load(sql,parameters));
    }


    public void createSubtask(String subtaskName, String startDate, String finishDate, int cost, int taskId) {
        sql=" insert into PlanIt.Subtasks (name, start, finish, cost, task_id ) values (?, ?, ?, ?, ?) ";
        parameters.clear();
        parameters.add(subtaskName);
        parameters.add(startDate);
        parameters.add(finishDate);
        parameters.add(String.valueOf(cost));
        parameters.add(String.valueOf(taskId));
        dbMapper.save(sql,parameters);
    }
}