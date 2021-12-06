package now.planit.Data.Repo;

import now.planit.Data.Utility.DBManager;
import now.planit.Exceptions.DBConnFailedException;
import now.planit.Exceptions.ResultsetFailException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author roed
 */
public class DBMapper {
  Connection connection;
  PreparedStatement ps;
  ResultSet rs;

  //Check connection with DBManager
  public PreparedStatement checkConnection(String sqlCommand) throws DBConnFailedException {
    try {
      connection = DBManager.getConnection();
      ps = connection.prepareStatement(sqlCommand);
    } catch (SQLException e) {
      System.out.println("Connection to database unavaiable!");
    e.printStackTrace();
    }
    return ps;
  }

  //Loads Parameters into Prepared statement
  public PreparedStatement setParameters(ArrayList<String> parameters) {
  //Should we use ResultsetFailException here
    try {
      for (int i = 0; i < parameters.size(); i++) {
        ps.setString(i + 1, parameters.get(i));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return ps;
  }

  //Used to do something on the Database, Save or delete.
  public void save(String sqlCommand, ArrayList<String> parameters) {
    try {
      ps = checkConnection(sqlCommand);
      setParameters(parameters).execute();
    } catch (DBConnFailedException | SQLException e) {
      //Eksempel : throw new exceptionsService("Database unavailable");
      System.out.println(e.getMessage());
      e.printStackTrace();
    } catch(NullPointerException d){
      System.out.println(d.getMessage());
    }
  }

  //Used to recieve something from the database, will always return ResultSet.
  public ResultSet load(String sqlCommand, ArrayList<String> parameters) {
    try {
      ps = checkConnection(sqlCommand);
      rs = setParameters(parameters).executeQuery();
    } catch (DBConnFailedException | SQLException e) {
      e.printStackTrace();
    } catch(NullPointerException d){
      //throw ned ResultsetFailException here
      // Exception must go to RepoUsers, DFFacade, UserService, Usercontroller.
    }
    return rs;
  }
}