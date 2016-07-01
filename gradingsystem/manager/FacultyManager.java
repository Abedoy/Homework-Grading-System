package edu.csustan.gradingsystem.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.csustan.gradingsystem.util.DBConnector;

public class FacultyManager {

	private String db_username = "bhalpin";
	private String db_password = "nmuy3923";

	/**
	 * 
	 * @param ID
	 * @return
	 */
	public boolean isFaculty(int id){
		boolean exists = false;
		Connection connection = new DBConnector(db_username, db_password).getConnection();
		PreparedStatement sqlStatement = null;
		try{
			sqlStatement = connection.prepareStatement("SELECT * FROM Faculty WHERE FacultyID = ?");
			sqlStatement.setInt(1, id);
			//System.out.println(sqlStatement);
			ResultSet resultSet = sqlStatement.executeQuery();

			// Checks if there are results in the resultset
			if (resultSet.isBeforeFirst() ) {    
				exists = true;
			}else{
				exists = false;
			}

			resultSet.close();
		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}finally{
			//finally block used to close resources
			try{
				if(sqlStatement!=null)
					connection.close();
			}catch(SQLException se){
			}// do nothing
			try{
				if(connection!=null)
					connection.close();
			}catch(SQLException se){
				se.printStackTrace();
			}//end finally try
		}//end try
		return exists;
	}

}
