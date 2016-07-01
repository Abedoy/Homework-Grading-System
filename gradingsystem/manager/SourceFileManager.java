package edu.csustan.gradingsystem.manager;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import edu.csustan.gradingsystem.controller.CAGTesterGui;
import edu.csustan.gradingsystem.domain.*;
import edu.csustan.gradingsystem.util.DBConnector;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

import edu.csustan.gradingsystem.domain.SourceFile;
import edu.csustan.gradingsystem.domain.Assignment;
import edu.csustan.gradingsystem.domain.StudentSubmission;
import edu.csustan.gradingsystem.util.DBConnector;

/**
 * @author Brandon Halpin
 * Last Edited by:  Thomas Riley
 * Database Manager class for SourceFiles table
 **/

/* 
 * Database Table definitions:
 * SourceFiles
 * `SubmissionId` int(10) NOT NULL,
 * `SrcFile` blob,
 * `FileName` varchar(45),
 * PRIMARY KEY (`SubmissionId`),
 * KEY `Includes` (`SubmissionId`),
 * CONSTRAINT `Includes` FOREIGN KEY (`SubmissionId`) REFERENCES `StudentSubmission` (`SubmissionId`)
 *
 */

public class SourceFileManager {
	
	String username = "bhalpin";
	String password = "nmuy3923";
	
	/**Prints all files
	 * Edited by Joacim Soto 11/21/13
	 * 
	 */
	
	public void printAllSourceFiles(){
		Connection connection = new DBConnector(username, password).getConnection();
		PreparedStatement sqlStatement = null;
		try{
			sqlStatement = connection.prepareStatement("SELECT * FROM SourceFiles");

			System.out.println("Printing all SourceFiles... \n");
			ResultSet resultSet = sqlStatement.executeQuery();
			
			//Used for printing Source file name, if file not present, it should print null
			File SourceFile = new File("SourceFile");
			Blob blob = resultSet.getBlob("SrcFile");
			InputStream in = blob.getBinaryStream();
			OutputStream out = new FileOutputStream(SourceFile);
			byte[] buff = blob.getBytes(1,(int)blob.length());
			out.write(buff);
			out.close();
			
			//Extract data from result set
			while(resultSet.next()){
				//Retrieve by column name and display values
				System.out.print("SourceFileId: "+ resultSet.getInt("SourceFileId"));
				System.out.print(",Submission: " + resultSet.getInt("SubmissionId"));
				System.out.println(",Source File:"+ SourceFile);
				System.out.print(",File Name: " + resultSet.getString("FileName"));
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
	 * Inserts a single SourceFile, if there is a SourceFile in the database that
	 * matches the SubmissionID,the SrcFile field will be updated.
	 * @param srcFile
	 */
/*	
	public void insertSourceFile(int subId, File srcFile, String fName){
		
		SourceFile src = new SourceFile(subId, fName, srcFile);

		Connection connection = new DBConnector(username, password).getConnection();
		PreparedStatement sqlStatement = null;
		
		try{
			sqlStatement = connection.prepareStatement("INSERT INTO SourceFile"+ 
			"( SubmissionId, SrcFile, FileName)"+
			" VALUES (?,?,?);");

			//Sets SubmissionId
			sqlStatement.setInt(1,src.getSubmissionID());
			
			//Sets the Blob as the SourceFile
			InputStream is = new FileInputStream(src.getSourceFile());
			sqlStatement.setBlob(2, is);
	
			//Sets the name of the file
			sqlStatement.setString(3, src.getFileName());
			
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
	*/
	
	/**Inserts multiple SourceFiles associated with a particular StudentSubmission
	 * @param StudentSubmission, List
	
	public void insertSourceFiles(StudentSubmission s, ArrayList<SourceFile> list){
		
		StudentSubmission Submission = new StudentSubmission();
		
		int Count = checkforSubmissionCount(s.getStudentID(), s.getFacultyID(), s.getAssignmentNo()); //This requires that checkForSubmission sorts results in descending order by SubmissionNo and only returns the first result
		if(Count == -1){ //assuming checkForSubmission returns 0 if no Submission is found
			StudentSubmission lastSub = getStudentSubmission(ID); 
			deleteSourceFiles(ID); //delete all SourceFiles associated with previous submission
			s.setSubmissionNo(lastSub.getSubmissionNo() + 1); // set this Submission's count to previous submission count + 1
		}else{ // there were no previous submissions found
			s.setSubmissionNo(1);
		}
		
		execute insertion for s;
		int submittedID = checkforSubmission(s.getStudentID(), s.getFacultyID(), s.getAssignmentNo()); //retrieves ID of newly inserted submission
		//Option 1: Create a method that will take in an ArrayList<SourceFile>(with no values for their ID) and a SubmissionID and insert them into the DB
		insertSourceFiles(submittedID, list); 
		//End Option 1
		//Option 2: iterate on the SourceFile list and set the SubmissionID for each sourcefile before insertion
		for(SourceFile sf : list){
			sf.setSubmissionID(submittedID);
		}
		insertSourceFiles(list);
		//End Option 2
		
	}**/
	
	/**
	 * Retrieves all SourceFiles associated with a particular 

StudentSubmission.
	 * @param SubmissionID
	 * @return java.util.ArrayList
	 */
	public ArrayList<SourceFile> getSourceFiles(int submissionID){
		return blobToFile(submissionID);
	}
	
	/**
	 * Deletes all SourceFiles associated with a particular StudentSubmission
	 * @param SubmissionID
	 */
	public void deleteSourceFiles(int SubmissionID){
		//TODO implement multiple file delete
	}
	
	/**
	 * Deletes a single SourceFile
	 * @param submissionID
	 * @param fileName
	 */
	public void deleteSourceFile(int submissionID, String fileName){
		//TODO implement single file delete
	}
	
	/**
	 * Returns a single source file from the database, depending on the index and file name
	 * @param SubmissionID
	 * @param filename
	 * @return java.io.File
	 */
	
	public File getFile(int submissionID, String fileName)
	{
		String username = "bhalpin";
		String password = "nmuy3923";
		
		Connection connection = new DBConnector(username, password).getConnection();
		PreparedStatement sqlStatement = null;
		File src = null;
		try{
			
			sqlStatement = connection.prepareStatement("SELECT srcFile FROM SourceFiles "
					+ "WHERE SubmissionID = ? AND filename = ?");
			sqlStatement.setInt(1, submissionID);
			sqlStatement.setString(2, fileName);
			ResultSet resultSet = sqlStatement.executeQuery();
			
			
			if(resultSet.isBeforeFirst())
			{
				src = new File(fileName);
				Blob blob = resultSet.getBlob("SrcFile");
				InputStream in = blob.getBinaryStream();
				OutputStream out = new FileOutputStream(src);
				byte[] buff = new byte[4096];  // how much of the blob to read/write at a time
				int len = 0;

				while ((len = in.read(buff)) != -1) {
				    out.write(buff, 0, len);
				}
				out.close();	
			}
			
			resultSet.close();
			connection.close();
			
		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}
			return src;
	}
	
	private ArrayList<SourceFile> blobToFile(int submissionID)

	{
		String username = "bhalpin";
		String password = "nmuy3923";
		
		Connection connection = new DBConnector(username, password).getConnection();
		PreparedStatement sqlStatement = null;
		
		try{
			
			sqlStatement = connection.prepareStatement("SELECT * FROM SourceFiles WHERE SubmissionID = " + submissionID);
			ResultSet resultSet = sqlStatement.executeQuery();
			resultSet.beforeFirst();
			
			ArrayList<SourceFile> srcList = new ArrayList<SourceFile>();
			
			while(resultSet.next())
			{
				
				int submissionIDF = resultSet.getInt("SubmissionID");
				String fileName = resultSet.getString("FileName");
				File src = new File(fileName);
				Blob blob = resultSet.getBlob("SrcFile");
				InputStream in = blob.getBinaryStream();
				OutputStream out = new FileOutputStream(src);
				byte[] buff = new byte[4096];  // how much of the blob to read/write at a time
				int len = 0;

				while ((len = in.read(buff)) != -1) {
				    out.write(buff, 0, len);
				}
				
				out.close();
				SourceFile sourceFile = new SourceFile(submissionIDF, src, fileName);
				srcList.add(sourceFile);
			}
			
			resultSet.close();
			connection.close();
			return srcList;
			
		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}
			return null;
	}
	
	/**
	 * Adds a file to the database
	 * @param SourceFile to be uploaded
	 * @return true if success, false if fail
	 */

	public boolean submitFile(ArrayList<SourceFile> srcFiles)
	{
		for(int i = 0; i < srcFiles.size(); i++)
		{
			boolean test = fileToBlob(srcFiles.get(i));
			if(test == false)
			{
				return false;
			}
		}
		return true;
	}
	
	private boolean fileToBlob(SourceFile inFile)
	{
		String username = "bhalpin";
		String password = "nmuy3923";
		
        String name = inFile.getFileName();
        int submissionID = inFile.getSubmissionID();
        File sourceFile = inFile.getSourceFile();
		
		Connection connection = new DBConnector(username, password).getConnection();
		try {
			FileInputStream io = new FileInputStream(sourceFile);
			
			PreparedStatement remove = (PreparedStatement) connection.prepareStatement("DELETE FROM SourceFiles WHERE FileName = ?, SubmissionId = ?");
			
			remove.setString(1, name);
			remove.setInt(2, submissionID);
			remove.executeUpdate();
			
			PreparedStatement insert = (PreparedStatement) connection.prepareStatement("INSERT  INTO SourceFiles (SubmissionId,SrcFile, FileName) VALUES  (?,?,?)");   //con is java.sql.Connection object
        	
			insert.setInt(1, submissionID);
        	insert.setBinaryStream(2, (InputStream)io,(int)sourceFile.length());
        	insert.setString(3, name);
        	insert.executeUpdate();
        	
        	connection.close();
        	
        	return true;
        	
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
        
        catch(SQLException e)
        {
        	e.printStackTrace();
        	return false;
        }
	}
}