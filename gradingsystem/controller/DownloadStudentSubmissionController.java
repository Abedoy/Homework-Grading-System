/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.csustan.gradingsystem.controller;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.FileInputStream;
import java .nio.file.Files;

import edu.csustan.gradingsystem.manager.SourceFileManager;
import edu.csustan.gradingsystem.view.ControlledScreen;
import edu.csustan.gradingsystem.view.ScreensController;
import edu.csustan.gradingsystem.view.Screensframework;
import edu.csustan.gradingsystem.domain.SourceFile;

import java.io.File;
import java.io.FileWriter;
import java.util.logging.Logger;
import java.util.logging.Logger.*;

import javafx.scene.control.ButtonBuilder;
import javafx.scene.text.Text;
import javafx.scene.text.TextBuilder;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Window;


/**
 *
 * @author Anabel
 */
public class DownloadStudentSubmissionController implements Initializable, ControlledScreen {
	public static ScreensController myController;
        private Label label;
    @FXML
    private Button feedbackButton;
    @FXML
    private Font x1;
    @FXML
    private Button downloadButton;
       
    @FXML
    private Button Logout;
    private Window primaryStage;
    
    
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void feedbackButton(ActionEvent event) {
    	myController.setScreen(Screensframework.screenFeedbackID);
    }

    @FXML
    private void downloadButton(ActionEvent event) 
    {
        
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

	@Override
	public void setScreenParent(ScreensController screenPage) {
		myController = screenPage;
		
	}
    
    
      
}
