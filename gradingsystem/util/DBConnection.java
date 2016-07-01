/**
 * @author Brandon Halpin
 * Co-Author: Thomas Riley
 * 
 * @since 2013-10-28
 *
 * Credit: This code is modified from an example on: 
 * http://www.cbcb.umd.edu/confcour/CMSC424/Simple%20tutorial%20for%20using%20JDBC.pdf   
 * 
 * Requires:
 * 1. MySQL server running in background @localhost:3306 containing schema named "grading_system"
 * 2. mysql jdbc connector included in class path. 
 * 
 * If you run the main in this class it will test connection to the server.
 * 
 * To implement this abstract class, just append "extends DBConnectorAbstract".
 * 
 */

package edu.csustan.gradingsystem.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBConnection
{
	private Connection connection = null;
	private String driverName = "com.mysql.jdbc.Driver"; //driver for MySql
	private String ipAddress = "//hopper.csustan.edu"; // Defined the ip address of the database server
	private String portNumber = "3306"; //The port used for the database server
	private String sid = "gradingsystem"; // Database schema name
	private String url ="jdbc:mysql:" + ipAddress + ":" + portNumber + "/" + sid; //server ip or address:port/database name; 
	private String username; // Username for MySQL server *Needs to be changed in final version
	private String password; // Password for MySQL server
	
	
	/** Constructor for the Database Connection
	 * <p>
	 * To use this constructor (required), use a constructor such as:
	 * 
	 * public YourClassNameHere(String username, String password)
	 * {
	 * 		super(username, password);
	 * }
         * <p>
         * @param username MySQL Username
	 * @param password MySQL Password
	 */
	public DBConnection(String username, String password)
	{
		this.username = username;
		this.password = password;
	}
	
	/**
	 * Creates the connection
	 * 
	 * @return object of type Connection
	 */
	public Connection getConnection() {
		try {
			// Load the JDBC driver
			Class.forName(driverName);
			// Create a connection to the database
			connection = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			// Could not find the database driver
			System.out.println("ClassNotFoundException : "+e.getMessage());
		} catch (SQLException e) {
			// Could not connect to the database
			System.out.println(e.getMessage());
		}
		return connection;
	}
	
	/**
	 * Creates the connection
	 * 
	 * @return True if connection succeeds, false if connection fails
	 */ 
	public boolean doConnection()
	{
		try
		{
			// Load the JDBC driver
			Class.forName(driverName);
			// Create a connection to the database
			connection = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e)
		{
			// Could not find the database driver
			System.out.println("ClassNotFoundException : "+e.getMessage());
			return false;
		} catch (SQLException e)
		{
			// Could not connect to the database
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}	
}
