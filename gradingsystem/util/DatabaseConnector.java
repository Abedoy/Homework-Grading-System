package edu.csustan.gradingsystem.util;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/*
 * Author: Jacob Gasaway
 * This is the jdbc for the submit assignment use case
 * Be sure to have jdbc resources installed before using this program.
 * Date Added: Sept. 26, 2013
 */
public class DatabaseConnector {
	//used for the creation of the link between the database and Java program
	private String databasePath, username, password;
	
	/*
	 * 
	 * The format for the database path is as follows:
	 * jdbc:mysql://[host:port]/[database]
	 */
	public DatabaseConnector(String path, String name, String pass){
		databasePath = path;
		username = name;
		password = pass;
	}
	
	/*
	 * 
	 * Prereq: Requires a valid database name(see above), username and password 
	 * to be set in the constructor.
	 * 
	 * 
	 */
	public ResultSet getTable(String table) throws SQLException{
		
		//gets the driver. Needed if jdbc is going to be used.
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println("Class Not Found");
		}
		Connection con = DriverManager.getConnection(databasePath, username, password);
		
		//Creates and executes a sequel command and stores the results.
		
		PreparedStatement statement = con.prepareStatement("select * from " + table);
		ResultSet result = statement.executeQuery();
		
		printResultSet(result);
		
		return result;
	}
	
	//Self explanatory 
	public void printResultSet(ResultSet result) throws SQLException{
		//Obtains metadata of the result. This is used to get the # of columns in the 
		//table.
		ResultSetMetaData rsmd = result.getMetaData(); 
		while(result.next()){
			for(int i = 1; i != rsmd.getColumnCount(); i++)
			{
				System.out.print(result.getString(i) + "     ");	
			}
			System.out.println("");
		}
	}
	//TODO Make mySQL commands for all database query functions needed.
	//TODO Research sanitize methods within Java or create own methods.
	public void getStudent(){
		
	}
	public void getAssignments(){
		
	}
	public void addScore(){
		
	}
}
