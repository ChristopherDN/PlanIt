package now.planit.Data.Repo;

import now.planit.Domain.Models.Project;
import java.sql.ResultSet;
import java.util.ArrayList;

public interface InterfaceProjectRepo {

  int getInt(ResultSet rs);
  ArrayList<Project> getProjects(ResultSet rs);
  String getString(ResultSet rs);
  ArrayList<Project> loadProjects(int userId);
  void createProject(String projectName, String start, String finish, int budget, int userId);
  int getProjectId(String projectName, int userId);
  void deleteProject(int projectId, int userId);
  String getProjectName(String taskName, int userId);
  void addActualHours(int hours, int projectId);
  void addActualCost(int cost, int projectId);
  void subtractHours(int hours, int projectId);
  void subtractCost(int cost, int projectId);
}
