package now.planit.Domain.Models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author roed
 */
class SubtaskTest {
Subtask subtask = new Subtask("Test", 10, 500);
String expected;
int expectedInt;

  @Test
  void getSubtaskName() {
    expected = "Test";
    assertEquals(expected,subtask.getSubtaskName());
  }
  @Test
  void wrongGetSubtaskName() {
    expected = "est";
    assertNotEquals(expected,subtask.getSubtaskName());
  }




  @Test
  void getHours() {
  }

  @Test
  void getCost() {
  }
}