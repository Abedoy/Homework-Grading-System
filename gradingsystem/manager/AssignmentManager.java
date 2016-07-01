package edu.csustan.gradingsystem.manager;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import edu.csustan.gradingsystem.domain.Assignment;
import edu.csustan.gradingsystem.util.DBConnector;

/*
 * Author: Brandon Halpin
 * Modified by: Thomas Falasco
 * 
 * This class manages database interactions for Assignment objects. 
 * 
 * Assignment fields from database:
 * `AssignmentNo`,
 * `Title`,
 * `DueDate`,
 * 'TimeDue',
 * `MaximumScore`,
 * 'FcnScore',
 * 'StyleScore',
 * `IsAvailable`,
 * `AssignDesc`,
 * `FacultyId`,
 * `CourseId`,
 * `CourseSec`,
 * `Term`,
 * `StudentId`
 */


public class AssignmentManager {
	String username = "bhalpin";
	String password = "nmuy3923";
	/**
	 * Retrieves all assignments from database and prints all attributes, with the exception of the UnitTestFile
	 * 
	 */
	public void printAllAssignments(){
		Connection connection = new DBConnector(username, password).getConnection();
		PreparedStatement sqlStatement = null;
		try{
			sqlStatement = connection.prepareStatement("SELECT * FROM assignment");

			System.out.println("Printing all assignments... \n");
			ResultSet resultSet = sqlStatement.executeQuery();
			//Extract data from result set
			while(resultSet.next()){
				//Retrieve by column name
				//Display values
				System.out.print("AssignmentNo: " + resultSet.getInt("AssignmentNo"));
				System.out.print(", Title: " + resultSet.getString("Title"));
				System.out.print(", Term: " + resultSet.getString("Term"));
				System.out.print(", DueDate: " + resultSet.getDate("DueDate"));
				System.out.print(", TimeDue: " + resultSet.getTime("TimeDue"));
				System.out.print(", IsAvailable: " + resultSet.getInt("IsAvailable"));
				System.out.print(", AssignDesc: " + resultSet.getString("AssignDesc"));
				System.out.print(", MaximumScore: " + resultSet.getInt("MaximumScore"));
				System.out.print(", FcnScore: " + resultSet.getInt("FcnScore"));
				System.out.print(", StyleScore: " + resultSet.getInt("StyleScore"));
				System.out.print(", FacultyID: " + resultSet.getInt("FacultyID"));
				//System.out.print(", StudentID: " + resultSet.getInt("StudentID"));
				System.out.print(", CourseSec: " + resultSet.getInt("CourseSec"));
				System.out.println(", CourseID: " + resultSet.getString("CourseID"));

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

	/**
	 * Executes query to retrieve an Assignment by its unique ID
	 * ID = AssignmentNo(Database Column)
	 * 
	 * @param ID
	 * @return Assignment
	 */

	public Assignment getAssignmentByID(int ID){
		Assignment assignment = new Assignment();
		Connection connection = new DBConnector(username, password).getConnection();
		PreparedStatement sqlStatement = null;
		try{
			sqlStatement = connection.prepareStatement("SELECT FROM assignment WHERE AssignmentNo = ?");
			sqlStatement.setInt(1, ID);
			//System.out.println(sqlStatement);
			ResultSet resultSet = sqlStatement.executeQuery();
			//Extract data from result set
			resultSet.next();
			// Assign values to new assignment 
			assignment.setAssignmentNo(resultSet.getInt("AssignmentNo"));
			assignment.setTitle(resultSet.getString("Title"));
			assignment.setTerm(resultSet.getString("Term"));
			assignment.setDueDate(resultSet.getDate("DueDate"));
			assignment.setTimeDue(resultSet.getTime("TimeDue"));
			assignment.setIsAvailable(resultSet.getBoolean("IsAvailable"));
			assignment.setAssignDesc(resultSet.getString("AssignDesc"));
			assignment.setMaximumScore(resultSet.getInt("MaximumScore"));
			assignment.setFcnScore(resultSet.getInt("FcnScore"));
			assignment.setStyleScore(resultSet.getInt("StyleScore"));
			assignment.setFacultyID(resultSet.getInt("FacultyID"));
			//assignment.setStudentID(resultSet.getInt("StudentID"));
			assignment.setCourseSec(resultSet.getInt("CourseSec"));
			assignment.setCourseID(resultSet.getString("CourseID"));

			//TODO Move to UnitTestFileManager
			//Convert blob to file
			/* No longer a field of Assignment
			File unitTestFile = new File("UnitTestFile");
			Blob blob = resultSet.getBlob("UnitTestFile");
			InputStream in = blob.getBinaryStream();
			OutputStream out = new FileOutputStream(unitTestFile);
			byte[] buff = blob.getBytes(1,(int)blob.length());
			out.write(buff);
			out.close(); 
			assignment.setUnitTest(unitTestFile);
			*/
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
		return assignment;
	}

	/**
	 * Executes SQL query to retrieve Assignment whose title matches parameter.
	 * If there are multiple matches this method will return the match that was inserted most recently.
	 * 
	 * @param title
	 * @return Assignment
	 */
	public Assignment getAssignmentByTitle(String title){
		Assignment assignment = new Assignment();
		Connection connection = new DBConnector(username, password).getConnection();
		PreparedStatement sqlStatement = null;
		try{
			sqlStatement = connection.prepareStatement("SELECT * FROM assignment WHERE Title = ?");
			sqlStatement.setString(1, title);
			//System.out.println(sqlStatement);
			ResultSet resultSet = sqlStatement.executeQuery();
			//Extract data from result set
            //System.out.println(resultSet);
			while(resultSet.next()){
				// Assign values to new assignment 
				assignment.setAssignmentNo(resultSet.getInt("AssignmentNo"));
				assignment.setTitle(resultSet.getString("Title"));
				assignment.setTerm(resultSet.getString("Term"));
				assignment.setDueDate(resultSet.getDate("DueDate"));
				assignment.setTimeDue(resultSet.getTime("TimeDue"));
				assignment.setIsAvailable(resultSet.getBoolean("IsAvailable"));
				assignment.setAssignDesc(resultSet.getString("AssignDesc"));
				assignment.setMaximumScore(resultSet.getInt("MaximumScore"));
				assignment.setFcnScore(resultSet.getInt("FcnScore"));
				assignment.setStyleScore(resultSet.getInt("StyleScore"));
				assignment.setFacultyID(resultSet.getInt("FacultyID"));
				//assignment.setStudentID(resultSet.getInt("StudentID"));
				assignment.setCourseSec(resultSet.getInt("CourseSec"));
				assignment.setCourseID(resultSet.getString("CourseID"));
                //System.out.println(assignment);

				//TODO Move to UnitTestFileManager
				//Convert blob to file
				/* No longer a field of Assignment
				File unitTestFile = new File("UnitTestFile");
				Blob blob = resultSet.getBlob("UnitTestFile");
				InputStream in = blob.getBinaryStream();
				OutputStream out = new FileOutputStream(unitTestFile);
				byte[] buff = blob.getBytes(1,(int)blob.length());
				out.write(buff);
				out.close(); 
				assignment.setUnitTest(unitTestFile);
				*/
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
		return assignment;
	}

	/**
	 * Inserts a new Assignment object into the database.
	 * Prereq: All attributes of the assignment must be initialized, with the exception of UnitTestFile
	 * @param assignment
	 */
	public void insertAssignment(Assignment assignment){
		Connection connection = new DBConnector(username, password).getConnection();
		PreparedStatement sql = null;
		try{
			sql = connection.prepareStatement("INSERT INTO assignment " +
					"(Title, Term, DueDate, TimeDue, IsAvailable, AssignDesc, MaximumScore, FcnScore, StyleScore, FacultyID, CourseSec, CourseID) " +
					"VALUES (?,?,?,?,?,?,?,?,?,?,?,?);");
			 
			sql.setString(1, assignment.getTitle());
			sql.setString(2, assignment.getTerm());
			sql.setDate(3, assignment.getDueDate());
			sql.setTime(4, assignment.getTimeDue());
			sql.setBoolean(5, assignment.isAvailable());
			sql.setString(6, assignment.getAssignDesc());
			sql.setFloat(7, (float)assignment.getMaximumScore());
			sql.setFloat(8, (float)assignment.getFcnScore());
			sql.setFloat(9, (float)assignment.getStyleScore());
			sql.setInt(10, assignment.getFacultyID());
			sql.setInt(11, assignment.getCourseSec());
			sql.setString(12, assignment.getCourseID());
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
	 * Deletes an assignment from the database whose AssignmentNo matches assignmentID.
	 * @param assignmentID
	 */
	public void deleteAssignmentByID(int assignmentID){
		Connection connection = new DBConnector(username, password).getConnection();
		PreparedStatement sqlStatement = null;
		try{
			sqlStatement = connection.prepareStatement("DELETE FROM assignment WHERE AssignmentNo = ?");
			sqlStatement.setInt(1, assignmentID);
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
	 * Deletes ALL assignments whose titles match the title parameter.
	 * @param title
	 */
	public void deleteAssignmentByTitle(String title) {
		Connection connection = new DBConnector(username, password).getConnection();
		PreparedStatement sqlStatement = null;
		try{
			sqlStatement = connection.prepareStatement("DELETE FROM assignment WHERE title = ?");
			sqlStatement.setString(1, title);
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
}

