package now.planit.Domain.Services;

import now.planit.Data.Repo.FacadeMySQL;
import now.planit.Data.Repo.MapperDB;
import now.planit.Data.Repo.ProjectRepo;
import now.planit.Domain.Models.Project;
import now.planit.Domain.Models.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author roed
 */
class ProjectServiceTest {
  ProjectService projectService = new ProjectService(new FacadeMySQL(new ProjectRepo(new MapperDB())));
  User user = new User("UserForTests","test@test.com","test");
Project project = new Project("Test Project", "2021-12-10", "2021-12-17", 20000);
ArrayList<Project> actual = projectService.getProjects(user);

  @Test
  void createProject() {
    //Expected
    projectService.facadeMySQL.createProject("Test Project","2021-12-10", "2021-12-17", 20000, user);
    for (int i = 0; i < actual.size(); i++) {
      if (actual.get(i).equals(project)){
        assertEquals(project, actual.get(i));
      }
    }
    projectService.deleteProject(project.getName(), user);
  }

  @Test
  void deleteProject() {
    projectService.facadeMySQL.createProject("Test Project","2021-12-10", "2021-12-17", 20000, user);
    projectService.deleteProject(project.getName(), user);
  }
}