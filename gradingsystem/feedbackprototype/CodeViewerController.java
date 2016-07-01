package edu.csustan.gradingsystem.feedbackprototype;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;

import edu.csustan.gradingsystem.domain.StudentSubmission;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.stage.DirectoryChooser;
import javafx.stage.Window;

public class CodeViewerController {

    private static ProtoFacultyManager pFM = new ProtoFacultyManager();
    private static ProtoStudentManager pSM = new ProtoStudentManager();
    private static ProtoSubmissionsManager pSSM = new ProtoSubmissionsManager();
    private static ProtoAssignmentsManager pAM = new ProtoAssignmentsManager();
    private static ProtoSourceFileManager pSFM = new ProtoSourceFileManager();
    private Window primaryStage;
        
    
	public String fileToText(File text) 
	{
		File file = text;
		{
		   //the string where the text will be stored
		   String content = null;
		  // File file = new File(filename); //for ex foo.txt
		   try 
		   {
			   FileReader reader = new FileReader(file);
			   char[] chars = new char[(int) file.length()];
			   reader.read(chars);
			   content = new String(chars);
		   } 
		   catch (IOException e) 
		   {
			   System.out.println("NO FILE FOUND NAMED" + text);
		   }
		   
		   return content;
		}
	}
    
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField assignmentName;

    @FXML
    private Button button;

    @FXML
    private TextField instructorName;

    @FXML
    private Label label;
    
    @FXML
    private TextArea source1Area;

    @FXML
    private TextArea source2Area;

    @FXML
    private TextField studentID;

    @FXML
    private Tab tab1ID;

    @FXML
    private Tab tab2ID;
    
    @FXML
    public void setAssignmentName(String text){
         assignmentName.setText(text);
    }
    
    @FXML
    public void setStudentID(String text){
         studentID.setText(text);
    }
    
    @FXML
    public void setInstructorName(String text){
         instructorName.setText(text);
    }

    @FXML
    public void setTab1ID(String text){
         tab1ID.setText(text);
    }
    
    @FXML
    public void setTab2ID(String text){
         tab2ID.setText(text);
    }
    
    @FXML
    public void setSource1Area(String text){
        source1Area.setText(text);
    } 
    
    @FXML
    void handleButtonAction(ActionEvent event) {
    }
    
    @FXML
    void handledownloadButtonAction(ActionEvent event)    {
        
        DirectoryChooser directoryChooser = new DirectoryChooser();
  
              //Set extension filter
              //FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
              //fileChooser.getExtensionFilters().add(extFilter);
              
              //Show save file dialog
              File file = directoryChooser.showDialog(primaryStage);
              
             // Replace this section with getSourceFile from database********
              File srcFile = new File("source.java");
              FileWriter fileWriter;
			try {
				fileWriter = new FileWriter(srcFile);
			
              fileWriter.write("This is some code.");
              fileWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// ****************************************************************
              if(file != null){
                  //replace downloadString to the actual source code file to save
            	  file = new File(file.getAbsolutePath() + "\\" + srcFile.getName());
            	  //System.out.println(file.getPath());
                  SaveFile(primaryStage, file, srcFile);
                  // take another argument for file name and dont use file  becasue we want it to automatically have the name of the file being downloaded
              }
    }

        private void SaveFile(Window primaryStage, File file, File srcFile) {
        try {
            FileWriter fileWriter = null;
             
            //fileWriter = new FileWriter(file);
            //fileWriter.write("hello");
            OutputStream out = new FileOutputStream(file.getPath());
            // how does it give you the name and then decide where to save 
            byte[] data = Files.readAllBytes(srcFile.toPath());
            out.write(data);
            out.close();
           // fileWriter.close();
        } catch (IOException Exception) {
          // Logger.getLogger(null);
            Exception.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        assert assignmentName != null : "fx:id=\"assignmentName\" was not injected: check your FXML file 'CodeViewer.fxml'.";
        assert button != null : "fx:id=\"button\" was not injected: check your FXML file 'CodeViewer.fxml'.";
        assert instructorName != null : "fx:id=\"instructorName\" was not injected: check your FXML file 'CodeViewer.fxml'.";
        assert label != null : "fx:id=\"label\" was not injected: check your FXML file 'CodeViewer.fxml'.";
        assert studentID != null : "fx:id=\"studentID\" was not injected: check your FXML file 'CodeViewer.fxml'.";
        assert tab1ID != null : "fx:id=\"tab1ID\" was not injected: check your FXML file 'CodeViewer.fxml'.";
        assert tab2ID != null : "fx:id=\"tab2ID\" was not injected: check your FXML file 'CodeViewer.fxml'.";

        loadData();
        StudentSubmission currentSubmission;
        currentSubmission = pSSM.getSubmissionByID(2);
        setAssignmentName((pAM.getAssignmentByID(currentSubmission.getAssignmentNo())).getTitle());
        setInstructorName(pFM.getFacultyByID(currentSubmission.getFacultyID()).getLastName());
        setStudentID(pSM.getStudentByID(currentSubmission.getStudentID()).getLastName());
        String s = fileToText((pSFM.getSourceFileByID(2)).getSourceFile());
        setSource1Area(s);
    }
    
    	public static void loadData() {
		try {
			pFM.instantiateFromCSV();
		}
		catch (FileNotFoundException e){
			System.out.println("file not found");
		}
		
		try {
			pSM.instantiateFromCSV();
		}
		catch (FileNotFoundException e){
			System.out.println("file not found");
		}
		
		try{
			pSSM.instantiateFromCSV();
		}
		catch (FileNotFoundException e){
			System.out.println("file not found");
		}
		
		try {
			pAM.instantiateFromCSV();
		}
		catch (FileNotFoundException e){
			System.out.println("file not found");
		}
		
		try {
			pSFM.instantiateFromCSV();
		}
		catch (FileNotFoundException e){
			System.out.println("file not found");
		}
		
	}

}
