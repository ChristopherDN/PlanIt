package now.planit.Data.Repo;


import now.planit.Data.Utility.DBManager;
import now.planit.Domain.Models.Task;
import org.springframework.stereotype.Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


@Controller
public class RepoTask {
  Connection connection;
  PreparedStatement ps;
  boolean bol;
  ResultSet rs;
  ArrayList<Task> tasks = new ArrayList<>();
  int userid;

  public void query(String sqlCommand) {
    try {
      connection = DBManager.getConnection();
      ps = connection.prepareStatement(sqlCommand);
      bol = ps.execute();
    } catch (SQLException ex) {
      System.out.println(ex.getMessage());
    }
  }

  public ResultSet load(String sqlCommand)  {
    try {
      connection = DBManager.getConnection();
      ps = connection.prepareStatement(sqlCommand);
      rs = ps.executeQuery();
    } catch (SQLException ex) {
      System.out.println(ex.getMessage());
      //throw new ExceptionService(ex.getMessage());
      //Chose not to use ExceptionService, and instead catch as early as possible.
    }
    return rs;
  }

  /*public ArrayList<Task> rsToArray(ResultSet rs) {
    try {
      tasks.clear();
      while (rs.next()) {
        tasks.add(new Task(rs.getString(1)));
      }
    } catch (SQLException ex) {
      System.out.println(ex.getMessage());
    }
    return tasks;
  }
*/

  public int rsToId(ResultSet rs){
    try {
      userid = 0;
      while (rs.next()) {
        userid = rs.getInt(1);
      }
    } catch (SQLException ex) {
      System.out.println(ex.getMessage());
    }
    return userid;
  }


  /*public void createWishlist(User user, String event) {
    userid = rsToId(load("select id from wishlist.users where name = '" + user.getName() + "'"));
    query("insert into wishlist.wishlist(event, userid) values('"+ event + "' , '" + userid + "')");
  }

  public void deleteWishlist(User user, String event) {
    query("delete from wishlist.wishlist where event = '" + event + "' and userid = " + rsToId(load("select id from wishlist.users where name = '" + user.getName() + "'")));
  }

  public ArrayList<Task> loadWishlists(User user) {
    return rsToArray(load("select event from wishlist.wishlist where userid =" + rsToId(load("select id from wishlist.users where name = '" + user.getName() + "'"))));
  }

  public int setGetWishlist(User user, String event) {
    userid = rsToId(load("select id from wishlist.users where name = '" + user.getName() + "'"));
    return rsToId(load("select id from wishlist.wishlist where userid =" + userid +  " and event ='" + event + "'"));
  }*/
}


