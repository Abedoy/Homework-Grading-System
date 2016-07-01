package edu.csustan.gradingsystem.manager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import edu.csustan.gradingsystem.domain.Person;
import edu.csustan.gradingsystem.util.DBConnector;

/**
 * Author: Brandon Halpin
 * This class manages database interactions for Person objects. 
 * 
 * Person fields from database:
 * `PersonId`  int(10) NOT NULL,
 * `FirstName` varchar(50) NOT NULL,
 * `MInitial`  char(2) DEFAULT NULL,
 * `LastName`  varchar(50) NOT NULL,
 * `Password`  varchar(10) NOT NULL,
 */


public class PersonManager {
	private String db_username = "bhalpin";
	private String db_password = "nmuy3923";

	public static void main(String[] args){
		PersonManager personManager = new PersonManager();
		System.out.println(personManager.checkForPerson(123456789));
		Person p = personManager.getPersonByID(0);
		if(p != null){
			System.out.println(p);
		}else{
			System.out.println("Null");
		}
		
	}
	/**
	 * Retrieves all persons from database and prints all attributes, with the exception of the UnitTestFile
	 * 
	 */
	public void printAllPersons(){
		Connection connection = new DBConnector(db_username, db_password).getConnection();
		PreparedStatement sqlStatement = null;
		try{
			sqlStatement = connection.prepareStatement("SELECT * FROM person");

			System.out.println("Printing all persons...");
			ResultSet resultSet = sqlStatement.executeQuery();
			//Extract data from result set
			while(resultSet.next()){
				//Retrieve by column name
				//Display values
				System.out.print("PersonID: " + resultSet.getInt("PersonID"));
				System.out.print(", First: " + resultSet.getString("firstName"));
				System.out.print(", MI: " + resultSet.getString("mInitial"));
				System.out.print(", Last: " + resultSet.getString("lastName"));
				System.out.println(", Password: " + resultSet.getString("password"));

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
	}
	
	public boolean validatePerson(int ID, String password){
		boolean success = false;
		Connection connection = new DBConnector(db_username, db_password).getConnection();
		PreparedStatement sqlStatement = null;
		try{
			sqlStatement = connection.prepareStatement("SELECT password FROM person WHERE PersonID = ?");
			sqlStatement.setInt(1, ID);
			//System.out.println(sqlStatement);
			ResultSet resultSet = sqlStatement.executeQuery();

			// Checks if there are results in the resultset
			if (resultSet.isBeforeFirst() ) {    
				resultSet.next();
				success = password.equals(resultSet.getString("password"));
				
			}else{
				success = false;
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
		return success;
	}
	
	public boolean validatePerson(String email, String password){
		boolean success = false;
		Connection connection = new DBConnector(db_username, db_password).getConnection();
		PreparedStatement sqlStatement = null;
		try{
			sqlStatement = connection.prepareStatement("SELECT password FROM person WHERE email = ?");
			sqlStatement.setString(1, email);
			//System.out.println(sqlStatement);
			ResultSet resultSet = sqlStatement.executeQuery();

			// Checks if there are results in the resultset
			if (resultSet.isBeforeFirst() ) {    
				resultSet.next();
				success = password.equals(resultSet.getString("password"));
				
			}else{
				success = false;
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
		return success;
	}
	
	/**
	 * Checks if a person exists in the database
	 * @param ID
	 * @return
	 */
	public boolean checkForPerson(int ID){
		boolean exists = false;
		Connection connection = new DBConnector(db_username, db_password).getConnection();
		PreparedStatement sqlStatement = null;
		try{
			sqlStatement = connection.prepareStatement("SELECT * FROM person WHERE PersonID = ?");
			sqlStatement.setInt(1, ID);
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
	
	/**
	 * 
	 * @param email
	 * @return
	 */
	public boolean checkForPerson(String email) {
		boolean exists = false;
		Connection connection = new DBConnector(db_username, db_password).getConnection();
		PreparedStatement sqlStatement = null;
		try{
			sqlStatement = connection.prepareStatement("SELECT * FROM person WHERE email = ?");
			sqlStatement.setString(1, email);
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

	/**
	 * Executes query to retrieve an Person by its unique ID
	 * ID = PersonID(Database Column)
	 * If no match is found returns null.
	 * 
	 * @param ID
	 * @return Person
	 */
	public Person getPersonByID(int ID){
		Person person = null;
		String firstName, mInitial, lastName, password, email;
		Connection connection = new DBConnector(db_username, db_password).getConnection();
		PreparedStatement sqlStatement = null;
		try{
			sqlStatement = connection.prepareStatement("SELECT * FROM person WHERE PersonID = ?");
			sqlStatement.setInt(1, ID);
			//System.out.println(sqlStatement);
			ResultSet resultSet = sqlStatement.executeQuery();

			// Checks if there are results in the resultset
			if (resultSet.isBeforeFirst() ) {    
				resultSet.next();
				firstName = resultSet.getString("firstName");
				mInitial = resultSet.getString("mInitial");
				lastName = resultSet.getString("lastName");
				email = resultSet.getString("email");
				//password = resultSet.getString("password");
				person = new Person(ID, firstName, mInitial, lastName, email, "");
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
		return person;
	}


	/**
	 * Inserts a new Person object into the database.
	 * Prereq: All attributes of the person must be initialized, with the exception of UnitTestFile
	 * @param person
	 */
	public void insertPerson(Person person){
		Connection connection = new DBConnector(db_username, db_password).getConnection();
		PreparedStatement sql = null;
		try{
			sql = connection.prepareStatement("INSERT INTO person " +
					"(PersonID, firstName, mInitial, lastName, email, password) " +
					"VALUES (?,?,?,?,?,?);");

			sql.setInt(1, person.getID());
			sql.setString(2, person.getFirstName());
			sql.setString(3, person.getmInitial());
			sql.setString(4, person.getLastName());
			sql.setString(5, person.getEmail());
			sql.setString(6, person.getPassword());

			//System.out.println(sql);
			sql.execute();
		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}finally{
			//finally block used to close resources
			try{
				if(sql!=null)
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

	}

	/**
	 * Deletes a person from the database whose ID matches ID.
	 * @param ID
	 */
	public void deletePersonByID(int ID){
		Connection connection = new DBConnector(db_username, db_password).getConnection();
		PreparedStatement sqlStatement = null;
		try{
			sqlStatement = connection.prepareStatement("DELETE FROM person WHERE PersonID = ?");
			sqlStatement.setInt(1, ID);
			//System.out.println(sqlStatement);
			sqlStatement.execute();
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
	}
	
	/**
	 * Retrieves ID of person with matching email. If no match is found returns -1.
	 * @param email
	 * @return
	 */
	public int getID(String email) { //TODO Test this method
		int id = -1;
		Connection connection = new DBConnector(db_username, db_password).getConnection();
		PreparedStatement sqlStatement = null;
		try{
			sqlStatement = connection.prepareStatement("SELECT * FROM person WHERE PersonID = ?");
			sqlStatement.setInt(1, id);
			//System.out.println(sqlStatement);
			ResultSet resultSet = sqlStatement.executeQuery();

			// Checks if there are results in the resultset
			if (resultSet.isBeforeFirst() ) {    
				resultSet.next();
				id = resultSet.getInt("PersonID");
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
		return id;
	}
	
	
}
