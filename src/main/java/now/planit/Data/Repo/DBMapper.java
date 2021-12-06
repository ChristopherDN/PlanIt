package now.planit.Data.Repo;

import now.planit.Data.Utility.DBManager;
import now.planit.Exceptions.DBConnFailedException;
import now.planit.Exceptions.QueryDataFailException;

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
      e.printStackTrace();
    } try {
      connection = DBManager.getConnection();
      ps = connection.prepareStatement(sqlCommand);
    }
    catch (SQLException e) {
      e.printStackTrace();
      throw new DBConnFailedException("Database unavaiable!\n" + "Status for connection= " + e.getMessage());

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
      System.out.println("Database unavaiable!");
      e.printStackTrace();
    } catch(NullPointerException d){
      System.out.println(d.getMessage());
    }
    return ps;
  }

  //Used to do something on the Database, Save or delete.
  public void save(String sqlCommand, ArrayList<String> parameters) throws QueryDataFailException {
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
  public ResultSet load(String sqlCommand, ArrayList<String> parameters) throws QueryDataFailException {
    try {
      ps = checkConnection(sqlCommand);
      rs = setParameters(parameters).executeQuery();
    } catch (DBConnFailedException | SQLException e) {
      //Her bliver vores exception fanget i login, hvis databsen ikke har forbindelse
      System.out.println("Database unavaiable.. " + e.getMessage());
    } catch(NullPointerException d){
      System.out.println("The request could not be processed" + d.getMessage());
    }
    return rs;
  }
}