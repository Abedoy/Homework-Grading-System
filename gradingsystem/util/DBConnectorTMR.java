/*
 * Author: Brandon Halpin
 * Co-Author: Thomas Riley
 * 
 * UPDATED October 21
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
 */

package edu.csustan.gradingsystem.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBConnectorTMR{
	private Connection connection = null;
	private String driverName = "com.mysql.jdbc.Driver"; //driver for MySql
	private String ipAddress = "//hopper.csustan.edu"; // Defined the ip address of the database server
	private String portNumber = "3306"; //The port used for the database server
	private String sid = "gradingsystem"; // Database schema name
	private String url ="jdbc:mysql:" + ipAddress + ":" + portNumber + "/" + sid; //server ip or address:port/database name; 
	private String username = "bhalpin"; // Username for MySQL server *Needs to be changed in final version
	private String password = "nmuy3923"; // Password for MySQL server
	
	public DBConnectorTMR()
	{
		
	}

	/**
	 *  Provides a connection that may be utilized by other classes for database access.
	 *  This method must be called using a static call ex: DBConnector.getConnection();
	 *  
	 * @return java.sql.Connection
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
	 *  Attempts to connect to database.
	 *  @returns boolean
	 *  true: if connection is successful
	 *  false: if connection failed, error message is printed
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

	// Sets the name of the JDBC driver used
	public void setDriverName(String driverName)
	{
		this.driverName = driverName;
	}

	public String getIpAddress()
	{
		return ipAddress;
	}

	public void setIpAddress(String ipAddress)
	{
		this.ipAddress = ipAddress;
	}

	public String getPortNumber()
	{
		return portNumber;
	}

	// Set port number used in connecting
	public void setPortNumber(String portNumber)
	{
		this.portNumber = portNumber;
	}
	
	// Set name of database
	public void setSid(String sid)
	{
		this.sid = sid;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public ArrayList<String[]> query(String query)
	{
		doConnection();
		ArrayList<String[]> ar = new ArrayList<String[]>();
		
		try
		{
			Statement stmt = connection.createStatement();
			if(query.toLowerCase().contains("select"))
			{
				ResultSet rs = stmt.executeQuery(query);
				
				ResultSetMetaData meta = rs.getMetaData();
		        int cols = meta.getColumnCount();
		       
		        
				while(rs.next())
				{
					String[] column = new String[cols];
					for(int i = 0; i < column.length; i++)
					{
						column[i] = rs.getString(i+1);
					}
					
					ar.add(column);
				}
				
				rs.close();
				
			}
			
			else if(query.toLowerCase().contains("insert") || query.toLowerCase().contains("update"))
			{
				int rs = stmt.executeUpdate(query);
				if(rs > 0)
				{
					String[] returnTrue = new String[1];
					returnTrue[0] = "true";
					ar.add(returnTrue);
				}
				
			}
			
			
			connection.close();
			stmt.close();
			
			return ar;
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} 
		
	}
	
	public static void main(String arg[])
	{
		DBConnectorTMR con = new DBConnectorTMR();
	//	con.query("INSERT INTO Person (PersonId, FirstName, MInitial, LastName, Password) VALUES (5, 'Thomas', 'M', 'Riley', 'hunter2')");
		ArrayList<String[]> list = con.query("Select * FROM Course");
		
		String[] row = list.get(0);
		for(int i = 0; i < row.length; i++)
		{
			System.out.println(row[i]);
		}
		
	}
}
