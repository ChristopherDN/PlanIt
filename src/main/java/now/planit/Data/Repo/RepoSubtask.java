package now.planit.Data.Repo;

import now.planit.Domain.Models.Subtask;
import now.planit.Domain.Models.Task;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class RepoSubtask {
    DBMapper dbMapper = new DBMapper();
    ArrayList<String> parameters = new ArrayList<>();
    String sql;
    ArrayList<Subtask> subtasks = new ArrayList<>();
    int subtaskId;

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

    private int getId(ResultSet rs) {
        try {
            while (rs.next()) {
                subtaskId = rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return subtaskId;
    }

    //Db Do something. TODO FIX DEN HER, TASKID som den modtager er altid 5
    public ArrayList<Subtask> getSubtasks(int taskId){
        System.out.println(taskId + " This is TaskID Repo");
        sql ="select name, start, finish, cost from PlanIt.Subtasks where task_id = ?";
        parameters.clear();
        parameters.add(String.valueOf(taskId));
        return loadSubtasks(dbMapper.load(sql,parameters));
    }


    public void createSubtask(String subtaskName, String startDate, String finishDate, int cost, int taskId) {
        sql="insert into PlanIt.Subtasks (task_id, name, start, finish, cost) values (?, ?, ?, ?, ?)";
        parameters.clear();
        parameters.add(String.valueOf(taskId));
        parameters.add(subtaskName);
        parameters.add(String.valueOf(startDate));
        parameters.add(String.valueOf(finishDate));
        parameters.add(String.valueOf(cost));
        dbMapper.save(sql,parameters);
    }
    public int getSubtaskId(String subtaskName, int taskId) {
        sql ="select id from PlanIt.Subtasks where name = ? and task_id = ?";
        parameters.clear();
        parameters.add(subtaskName);
        parameters.add(String.valueOf(taskId));
        return getId(dbMapper.load(sql,parameters));
    }

    public void deleteSubtask(int subtaskId, int taskId) {
        System.out.println(subtaskId + " This  is TaskID");
        System.out.println(taskId + " This  is ProjectId");
        sql = "delete from PlanIt.subtasks where id = ? and task_id = ?";
        parameters.clear();
        parameters.add(String.valueOf(subtaskId));
        parameters.add(String.valueOf(taskId));
        dbMapper.save(sql, parameters);
    }


}