package now.planit.Domain.Services;

import now.planit.Data.Repo.FacadeMySQL;
import now.planit.Data.Repo.MapperDB;
import now.planit.Data.Repo.SubtaskRepo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author roed
 */
class SubtaskServiceTest {
  FacadeMySQL facadeMySQL = new FacadeMySQL(new SubtaskRepo(new MapperDB()));

  @Test
  void createSubtask() {
  }

  @Test
  void deleteSubtask() {
  }
}