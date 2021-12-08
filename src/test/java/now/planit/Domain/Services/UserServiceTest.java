package now.planit.Domain.Services;

import now.planit.Domain.Models.User;
import now.planit.Exceptions.UserAllreadyExistException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author roed
 */
class UserServiceTest {
  UserService userService = new UserService();
  User user = userService.validateLogin("test@test.com", "test");
  User wrongUser = userService.validateLogin("test@test.com", "21321");
  String expected;

  @Test
  void ValidateCorrectLogin() {
    assertNotNull(user);
  }

  @Test
  void ValidateFailedLogin() {
    assertNull(wrongUser);
  }

  @Test
  void validateLoginEmail() {
    expected = user.getEmail();
    assertEquals(expected, userService.validateLogin("test@test.com", "test").getEmail());
  }

  @Test
  void validateLoginPassword() {
    expected = user.getPassword();
    assertEquals(expected, userService.validateLogin("test@test.com", "test").getPassword());
  }


  @Test
  void registerUser() throws UserAllreadyExistException {
    userService.deleteUser("user@testing.com", "testing");
    userService.registerUser("Jens", "user@testing.com", "testing");
    assertNotNull(userService.validateLogin("user@testing.com", "testing"));
  }



}