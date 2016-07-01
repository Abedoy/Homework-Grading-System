package edu.csustan.gradingsystem.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Author: Brandon Halpin
 * Credit: This code is modified from an example on: 
 * http://www.cbcb.umd.edu/confcour/CMSC424/Simple%20tutorial%20for%20using%20JDBC.pdf   
 * 
 * Requires:
 * 1. MySQL server running in background @localhost:3306 containing schema named "grading_system"
 * 2. mysql jdbc connector included in class path. 
 * 
 * If you run the main in this class it will test connection to the server.
 * 
 */
public class DBConnector{
	private  Connection connection = null;
	private  String driverName = "com.mysql.jdbc.Driver"; //driver for MySql
	private  String ipAddress = "//hopper.csustan.edu"; // Defined the ip address of the database server
	//TODO undo above comment
	//private  String ipAddress = "//localhost"; // Defined the ip address of the database server
	private  String portNumber = "3306"; //The port used for the database server
	private  String sid = "gradingsystem"; // Database schema name
	private  String url ="jdbc:mysql:" + ipAddress + ":" + portNumber + "/" + sid; //server ip or address:port/database name; 
	private  String username; // Username for MySQL server *Needs to be changed in final version
	private  String password; // Password for MySQL server 

	public static void main(String arg[]){
		DBConnector con =new DBConnector("bhalpin", "nmuy3923");
		System.out.println("Connection : " +con.doConnection());
	}
	
	/**
	 * 
	 * @param username
	 * @param password
	 */
	public DBConnector(String username, String password)
	{
		this.username = username;
		this.password = password;
	}

	/**
	 *  Provides a connection that may be utilized by other classes for database access.
	 *  This method must be called using a static call ex: DBConnector.getConnection();
	 *  
	 * @return java.sql.Connection
	 */
	public  Connection getConnection() {
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
	 *  Attempts to connect to database.
	 *  @returns boolean
	 *  true: if connection is successful
	 *  false: if connection failed, error message is printed
	 */
	public boolean doConnection(){
		try {
			// Load the JDBC driver
			Class.forName(driverName);
			// Create a connection to the database
			connection = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			// Could not find the database driver
			System.out.println("ClassNotFoundException : "+e.getMessage());
			return false;
		} catch (SQLException e) {
			// Could not connect to the database
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

	public  String getDriverName() {
		return driverName;
	}

	public  void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public  String getIpAddress() {
		return ipAddress;
	}

	public  void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public  String getPortNumber() {
		return portNumber;
	}

	public  void setPortNumber(String portNumber) {
		this.portNumber = portNumber;
	}

	public  String getSid() {
		return sid;
	}

	public  void setSid(String sid) {
		this.sid = sid;
	}

	public  String getUsername() {
		return username;
	}

	public  void setUsername(String username) {
		this.username = username;
	}

	public  void setPassword(String password) {
		this.password = password;
	}



}
