package edu.csustan.gradingsystem.manager;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.interfaces.RSAKey;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import edu.csustan.gradingsystem.controller.CAGTesterGui;
import edu.csustan.gradingsystem.domain.*;
import edu.csustan.gradingsystem.util.DBConnector;

/**
 * @Author: Joacim Soto
 * @Description: This class manages database interactions for StudentSubmission objects. 
 * 
 *  StudentSubmission fields from database:
 * `SubmissionId`,
 * `SubmissionNo`,
 * `SubmsDate`,
 * `Feedback`,
 * `StudentId`,
 * `FacultyId`,
 * `AssignmentNo`
 **/


public class StudentSubmissionManager {
	
	static String username = "bhalpin";
	static String password = "nmuy3923";

	/**
	 * Prints the current StudentSubmission Table at the time its called. The Feedback attribute is
	 * not yet shown.
	 */
	 
	public void printAllSubmissions(){
		Connection connection = new DBConnector(username, password).getConnection();
		PreparedStatement sqlStatement = null;
		try{
			sqlStatement = connection.prepareStatement("SELECT * FROM StudentSubmission;");

			System.out.println("Student Submissions List: \n" + sqlStatement);
			ResultSet resultSet = sqlStatement.executeQuery();
			
			//Used for printing Feedback file name, if file not present, it should print null
			File feedbackFile = new File("FeedbackFile");
			Blob blob = resultSet.getBlob("Feedback");
			InputStream in = blob.getBinaryStream();
			OutputStream out = new FileOutputStream(feedbackFile);
			byte[] buff = blob.getBytes(1,(int)blob.length());
			out.write(buff);
			out.close();
			
			//Extract data from result set
			while(resultSet.next()){
				//Retrieve by column name
				//Display values
				System.out.print(" Submission Id: " + resultSet.getInt("SubmissionId"));
				System.out.print(", Submission Count: " + resultSet.getInt("SubmissionNo"));
				System.out.println(", Submission Date: " + resultSet.getDate("SubmsDate"));
				System.out.println(",Feedback File:"+ feedbackFile);
			    System.out.print(", StudentID: " + resultSet.getInt("StudentID"));
				System.out.print(", FacultyID: " + resultSet.getInt("FacultyID"));
				System.out.print(", AssignmentNo: " + resultSet.getInt("AssignmentNo"));
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
	
	}//end method

	/** Returns the StudentSubmission ID correspondent to the StudentSubmission associated with an specific student,
	 * faculty member and an assignment.
	 * @param StudentId, FacultyId, AssignmentNo
	 * @return SubId
	 */
	
	public int checkforSubmissionId(int StudentId, int FacultyId, int AssignmentNo){
		int SubId = 0;
		Connection connection = new DBConnector(username, password).getConnection();
		PreparedStatement sqlStatement = null;
		try{
			sqlStatement = connection.prepareStatement("SELECT SubmissionId FROM StudentSubmission"+
		    " WHERE StudentId = ? AND FacultyId = ? AND AssignmentNo= ? ");
			
			sqlStatement.setInt(1, StudentId);
			sqlStatement.setInt(2, FacultyId);
            sqlStatement.setInt(3, AssignmentNo);			
			System.out.println(sqlStatement);
			ResultSet resultSet = sqlStatement.executeQuery();
			//Extract data from result set
				if(resultSet.next()){
					SubId = resultSet.getInt("SubmissionId");
					System.out.println(SubId);
				}
			// Assign values to new Student Submission 		
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
	return SubId;	
	}
	
	/** Checks if a StudentSubmission is already submitted based on the following parameters 
	 *  If a Submission with those parameters already exist, a SubmissionId is retrieved, otherwise a negative
	 *  value is returned.
	 * @param StudentId, FacultyId, AssignmentNo
	 * @return SubId
	 */
	
	public int checkforSubmissionCount(int StudentId, int FacultyId, int AssignmentNo){
		int SubCount = 0;
		Connection connection = new DBConnector(username, password).getConnection();
		PreparedStatement sqlStatement = null;
		try{
			sqlStatement = connection.prepareStatement("SELECT SubmissionNo FROM StudentSubmission"+
		    " WHERE StudentId = ? AND FacultyId = ? AND AssignmentNo= ? ");
			
			sqlStatement.setInt(1, StudentId);
			sqlStatement.setInt(2, FacultyId);
            sqlStatement.setInt(3, AssignmentNo);			
			System.out.println(sqlStatement);
			ResultSet resultSet = sqlStatement.executeQuery();
			//Extract data from result set
				if(resultSet.next()){
					SubCount = resultSet.getInt("SubmissionNo");
					System.out.println(SubCount);
				}
			// Assign values to new Student Submission 		
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
	return SubCount;	
	}
	
	
	
	/**
	 * Executes query to retrieve a StudentSubmission by its unique ID
	 *
	 * 
	 * @param SubmissionId
	 * @return StudentSubmission
	 */

	public StudentSubmission getSubmissionByID(int ID){
		StudentSubmission submission1 = new StudentSubmission();
		Connection connection = new DBConnector(username, password).getConnection();
		PreparedStatement sqlStatement = null;
		try{
			sqlStatement = connection.prepareStatement("SELECT FROM StudentSubmission WHERE SubmissionId = ?");
			sqlStatement.setInt(1, ID);        
			System.out.println(sqlStatement);
			ResultSet resultSet = sqlStatement.executeQuery();
			//Extract data from result set
			resultSet.next();
			
			//Converts Blob to File
			File sourceCodeFile = new File("FeedbackFile");
			Blob blob = resultSet.getBlob("Feedback");
			InputStream in = blob.getBinaryStream();
			OutputStream out = new FileOutputStream(sourceCodeFile);
			byte[] buff = blob.getBytes(1,(int)blob.length());
			out.write(buff);
			out.close();
			
			// Assign values to new Student Submission 
			submission1.setSubmissionID(resultSet.getInt("SubmissionId"));
			submission1.setSubmissionCount(resultSet.getInt("SubmissionNo"));
			submission1.setSubmissionDate(resultSet.getDate("SubmsDate"));
			submission1.setInstructorFeedback(sourceCodeFile);
			submission1.setStudentID(resultSet.getInt("StudentId"));
			submission1.setFacultyID(resultSet.getInt("FacultyId"));
			submission1.setAssignmentNo(resultSet.getInt("AssignmentNo"));
			
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
		return submission1;
	}

	/**
	 * Executes SQL query to retrieve the StudentSubmission(s) by StudentId.
	 *
	 * 
	 * @param StudentId
	 * @return StudentSubmission
	 */
	public StudentSubmission getStudentSubmissionByStudentID(int sid,int aid ){
		StudentSubmission submission2 = new StudentSubmission();
		Connection connection = new DBConnector(username, password).getConnection();
		PreparedStatement sqlStatement = null;
		try{
			sqlStatement = connection.prepareStatement("SELECT * FROM StudentSubmission WHERE StudentId = ? AND AssignmentNo = ?");
			sqlStatement.setInt(1, sid);
			sqlStatement.setInt(2, aid);
			System.out.println(sqlStatement);
			ResultSet resultSet = sqlStatement.executeQuery();
			//Extract data from result set
			if(resultSet.isBeforeFirst()){
				
				resultSet.next();
				
				//Converts Blob to File
				File sourceCodeFile = new File("FeedbackFile");
				Blob blob = resultSet.getBlob("Feedback");
				InputStream in = blob.getBinaryStream();
				OutputStream out = new FileOutputStream(sourceCodeFile);
				byte[] buff = blob.getBytes(1,(int)blob.length());
				out.write(buff);
				out.close();
				
				// Assign values to new Student Submission 
				submission2.setSubmissionID(resultSet.getInt("SubmissionId"));
				submission2.setSubmissionCount(resultSet.getInt("SubmissionNo"));
				submission2.setSubmissionDate(resultSet.getDate("SubmsDate")); 
				submission2.setInstructorFeedback(sourceCodeFile);
				submission2.setStudentID(resultSet.getInt("StudentId"));
				submission2.setFacultyID(resultSet.getInt("FacultyId"));
				submission2.setAssignmentNo(resultSet.getInt("AssignmentNo"));

			}
			else{
				submission2 = null;
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
		return submission2;
	}

	/**
	 * Inserts a new StudentSubmission object into the database. 
	 * Prereq: All attributes of the StudentSubmission object must be initialized. An Assignment, a Student and a
	 * Faculty member must be associated to an Assignment and created before the StudentSubmission is inserted.
	 * Before calling this method, a call to check for submission must be made.
	 * @param StuId, FacId, AssignID
	 */
	public void insertStudentSubmission(StudentSubmission Sub){
		
		StudentSubmission submission3 = new StudentSubmission();
		Connection connection = new DBConnector(username, password).getConnection();
		PreparedStatement sqlStatement = null;
		
		try{
			sqlStatement = connection.prepareStatement("INSERT INTO StudentSubmission"+ 
			"( SubmissionNo, SubmsDate, Feedback, StudentId, FacultyId, AssignmentNo)"+
			" VALUES ( ?, ?,?, ?,?,?);");
				
			//SubmissionId: No need to set the ID as the DB assigns an auto-incremented value and is unique
				
			//Need to verify the number of submissions
		
			int Count =checkforSubmissionCount(Sub.getStudentID(),Sub.getFacultyID(),Sub.getAssignmentNo());
		
			sqlStatement.setInt(1,Count+1);
		
			
			//Date by using Java.util.Date
			java.util.Date utilDate = new java.util.Date(); 
			sqlStatement.setDate(2, new Date(utilDate.getTime())); 
			
			//Converts File to Blob
			InputStream is = new FileInputStream(submission3.getInstructorFeedback());
			sqlStatement.setBlob(3, is);
			
			//Sets the associated Student, Faculty and AssignmentNo
			sqlStatement.setInt(4, submission3.getStudentID());
			sqlStatement.setInt(5, submission3.getFacultyID());
			sqlStatement.setInt(6, submission3.getAssignmentNo());
				
			System.out.println(sqlStatement);
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
	 * Deletes a StudentSubmission from the database by SubmissionId
	 * @param SubmissionID
	 */
	
	public void deleteStudentSubmissionByID(int SubmissionId){
		Connection connection = new DBConnector(username, password).getConnection();
		PreparedStatement sqlStatement = null;
		try{
			sqlStatement = connection.prepareStatement("DELETE FROM StudentSubmission WHERE SubmissionId = ?");
			sqlStatement.setInt(1, SubmissionId);
			System.out.println(sqlStatement);
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
	 * Deletes StudentSubmission by StudentId
	 * @param StudentId
	 */
	public void deleteStudentSubmissionByStudentId(int  StudentId) {
		Connection connection = new DBConnector(username, password).getConnection();
		PreparedStatement sqlStatement = null;
		try{
			sqlStatement = connection.prepareStatement("DELETE FROM StudentSubmission WHERE StudentId = ?");
			sqlStatement.setInt(1, StudentId);
			System.out.println(sqlStatement);
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
	
	//Methods for the Download Student Submission Team

	/**Gets the feedback file content in the form of a String
	 * 
	 * @param submsId
	 * @return
	 */

public String getFeedback(int submsId){
		
		Connection connection = new DBConnector(username, password).getConnection();
		PreparedStatement sqlStatement = null;
		String s = "";
		try{
			
			sqlStatement = connection.prepareStatement("SELECT Feedback "+
		    " FROM  StudentSubmission "+" WHERE SubmissionId = ? ");
			
			sqlStatement.setInt(1, submsId);
			ResultSet resultSet = sqlStatement.executeQuery();
			Blob blob = resultSet.getBlob("Feedback");
			byte[] bdata = blob.getBytes(1, (int) blob.length());
			s = new String(bdata);
			
			
			System.out.println(sqlStatement);
			sqlStatement.execute();
			
			// Assign values to new Student Submission 		
			}catch(SQLException se){
				//Handle errors for JDBC
				se.printStackTrace();
			}catch(Exception e){
				//Handle errors for Class.forName
				e.printStackTrace();
			}
			finally{
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
		return s;
	}

	/**Updates the content of a Feedback file
	 * 
	 * @param submsId
	 * @param feedback
	 */

	public void updateFeedback(int submsId, String feedback){
		
		Connection connection = new DBConnector(username, password).getConnection();
		PreparedStatement sqlStatement = null;
		try{
			
			sqlStatement = connection.prepareStatement("UPDATE StudentSubmission"+
		    " SET Feedback = ? "+" WHERE SubmissionId = ? ");
			
			
			sqlStatement.setBytes(1, feedback.getBytes());
			sqlStatement.setInt(2, submsId);
			System.out.println(sqlStatement);
			sqlStatement.execute();
	
			// Assign values to new Student Submission 		
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
	
	//Test Section
	public static void main(String[] args)
	{
		//StudentSubmission submsn = new StudentSubmission(2,1,1);
		StudentSubmissionManager SSM = new StudentSubmissionManager();
		//SSM.insertStudentSubmission(submsn);
		SSM.updateFeedback(1,"This is the new feedback.");

		//SSM.deleteStudentSubmissionByID(20);
		//System.out.println(SSM.getStudentSubmissionByStudentID(2,1));
	}
}
