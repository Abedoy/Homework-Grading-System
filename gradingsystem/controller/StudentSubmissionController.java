package edu.csustan.gradingsystem.controller;

/*StudentSubmissionView Controller
Author: Shawn Cole
*/



import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.*;


import javax.swing.JOptionPane;

import edu.csustan.gradingsystem.domain.SourceFile;
import edu.csustan.gradingsystem.manager.SourceFileManager;
import edu.csustan.gradingsystem.domain.StudentSubmission;
import edu.csustan.gradingsystem.manager.StudentSubmissionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javafx.collections.*;


/**
*
* @author Shawn Cole
*/
public class StudentSubmissionController implements Initializable {
  
  List<String> list = new ArrayList<String>();
  String listFile;
	
  private int submissionID = -1;
  //private ApplicationSecurityManager asm = new ApplicationSecurityManager();
  //private Person person;
  
  private static Window stage;
  @FXML
  private Label label;
  @FXML
  private TextField studentName;
  @FXML
  private TextField studentEmail;
  @FXML
  private TextField assign;
  @FXML
  private TextField subj;
  @FXML
  private ListView<String> listBoxMain = new ListView<String>();
  //Will be implemented to add file to clickable list item
  final ObservableList<String> listItems = FXCollections.observableArrayList();
  
  //TextFields will need to auto-generate based on SessionID from database
  //Text fields currently set text when clicked on
  /**@FXML
  private void Name(MouseEvent event)
  {
      studentName.setText("Test Student");
  }
  @FXML
  private void Email(MouseEvent event)
  {
      studentEmail.setText("test@csustan.edu");
  }
  @FXML
   private void Assignment(MouseEvent event){
      assign.setText("Program 1");
 } 
   @FXML
   private void Subject(MouseEvent event){
      subj.setText("CS4800");
 } 
 */
   //Jacob Gasaway
   //Tests files for type and adapts them to be 
   //acceptable in java (changes \ to /).
  @FXML
  private void addAssignmentButton(ActionEvent event)
  {    
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Add");
    List<File> files = fileChooser.showOpenMultipleDialog(stage);
    
    for(File file : files)
    {
    	if(file != null){
    		listFile = file.getAbsolutePath();
        
    			if (listFile.substring(listFile.length() - 5, listFile.length()).equalsIgnoreCase(".java")
					|| listFile.substring(listFile.length() - 4, listFile.length()).equalsIgnoreCase(".cpp")
					|| listFile.substring(listFile.length() - 4, listFile.length()).equalsIgnoreCase(".zip")
					|| listFile.substring(listFile.length() - 4, listFile.length()).equalsIgnoreCase(".tar")
					|| listFile.substring(listFile.length() - 4, listFile.length()).equalsIgnoreCase(".rar")
					|| listFile.substring(listFile.length() - 3, listFile.length()).equalsIgnoreCase(".7z")
					|| listFile.substring(listFile.length() - 7, listFile.length()).equalsIgnoreCase(".tar.gz"))
    			{
    				listFile = listFile.replace("\\", "/");
    				list.add(listFile);
    			}
			
    			// If it's not an allowed file type, don't accept the file
    			else
    			{
    				JOptionPane.showMessageDialog(null, "You must select either a .cpp or .java file");
    				listFile = null;		
    			}
    		}
    }
    System.out.println(list);
    //assign.setText(list);
  }
  
  @FXML
  private void removeButton(ActionEvent event)
  {
      //code to remove submission from list
  }
  
  //Jacob Gasaway, last edited by Joacim Soto on 12/05/13
  //extracts, grades, and uploads a student submission.
  @FXML
  private void uploadButton(ActionEvent event)
  {
      
      CompileAndGrade cag;
      
      //TODO make sure you have the location of the unit test file or remove
      //second string to extract without a test file. 
      //For now, it will only be given an array of strings without extracting the unit test file.
      
      cag = new CompileAndGrade(list);
      

      //TODO Need to convert the array of strings called "list" to and array of files so SourceFile Manager can insert files
      // into the database.
      
      
      
      //For when the log in screen is working. This will obtain user information
      //using the security manager.
      //person = asm.getActivePerson();
		//int userID = person.getID();
      int userID = 1; //Integer.parseInt(studentName.getText());
      int FacultyID = 1;
      int AssignmentNum = 1;//Integer.parseInt(assign.getText());
      int SubmissionId =2;
      
      File blobName = null;
      String fname = null;
		
      
      StudentSubmissionManager ssm = new StudentSubmissionManager();
      SourceFileManager sfm = new SourceFileManager();
      
      //StudentSubmission constructor with parameters
      StudentSubmission submission = new StudentSubmission(userID, FacultyID, AssignmentNum);
      SourceFile srcFile = new SourceFile(SubmissionId,blobName,fname);
		
      File feedback = new File("feedback.txt");
      try {
		feedback.createNewFile();
      } catch (IOException e) {
    	  // TODO Auto-generated catch block
    	  e.printStackTrace();
      }
      try {
    	 PrintWriter output = new PrintWriter("feedback.txt");
    	 output.println(cag.checkForErrors());
    	 output.close();
      } catch (FileNotFoundException e) {
    	  // TODO Auto-generated catch block
    	  e.printStackTrace();
      }
      submission.setInstructorFeedback(feedback);
      //Checks for a student submission and if it exists update it, else make
      //a new one.
      submissionID = ssm.checkforSubmissionId(userID, FacultyID, AssignmentNum);
      if( submissionID != -1)
      {
    	  ssm.insertStudentSubmission(submission);
    	  
      }
      else{
    	  //TODO - Needs to update submission if one already exists.
      }
      
  }
  @Override
  public void initialize(URL url, ResourceBundle rb) {
      // TODO
      
      //listBoxMain.setItems(listItems);
  }    
  
}
