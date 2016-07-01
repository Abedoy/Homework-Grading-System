/*Thomas Riley
 *Last Updated: October 1 2013
 *Version 1.0.0
 *
 * A new UI created completely in JavaFX. Doesn't have access to all the features of the Swing UI, but
 * 	works nonetheless.
 * 
 * TODO Add filechooser support for multiple files
 * 
 * TODO Make it look more like Francis' UI mockup
 */

package edu.csustan.gradingsystem.controller;


import java.io.File;

import edu.csustan.gradingsystem.util.CompileAndGrade;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
 
public class CompileAndGradeUIFX extends Application {
	String fileName;
	String inputFile;
	
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Assignment Title Here");        
        primaryStage.setScene(new Scene(addGridPane(), 370, 400));
        primaryStage.show();
    }
    
    public GridPane addGridPane() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));

        final Text programOutput = new Text("");
        final ScrollPane sp = new ScrollPane();
        sp.setContent(programOutput);
        sp.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        Button inputMap = new Button();
        inputMap.setText("Select Map");
        inputMap.setOnAction(new EventHandler<ActionEvent>()
            	{
            		@Override
            		public void handle(ActionEvent event)
            		{
            			FileChooser fileChooser = new FileChooser();
            			fileChooser.setTitle("Select Map");
            			File file = fileChooser.showOpenDialog(null);
            			if(file != null)
            			{
            				inputFile = file.getAbsolutePath();
            				inputFile = inputFile.replace("\\", "/");
            			}
            		}
            	});
        
        Button submitFile = new Button();
        final Text fileSelected = new Text ("No File Selected");
       
        submitFile.setText("Submit File");
        submitFile.setOnAction(new EventHandler<ActionEvent>()
        	{
        		@Override
        		public void handle(ActionEvent event)
        		{
        			if(fileName != null)
        			{
        				if(inputFile == null)
    	    			{
    	    				CompileAndGrade cag = new CompileAndGrade(fileName);
    	    				programOutput.setText(cag.checkForErrors());
    	    				sp.setVvalue(50);
    	    			}
    	    			
    	    			else
    	    			{
    	    				CompileAndGrade cag = new CompileAndGrade(fileName, inputFile);
    	    				programOutput.setText(cag.checkForErrors());
    	    			}
        			}
        		}
        	});
        Button addFile = new Button();
        addFile.setText("Add File");
        addFile.setOnAction(new EventHandler<ActionEvent>()
        	{
        		@Override
        		public void handle(ActionEvent event)
        		{
        			FileChooser fileChooser = new FileChooser();
        			fileChooser.setTitle("Select File");
        			File file = fileChooser.showOpenDialog(null);
        			if(file != null)
        			{
        				fileName = file.getAbsolutePath();
        				fileName = fileName.replace("\\", "/");
        				fileSelected.setText(file.getName());
        			}
        		}
        	});
        
        grid.add(sp, 1, 13, 13, 5);
        grid.add(fileSelected, 8, 5, 1, 5);
        grid.add(submitFile, 10, 10, 1, 2);
        grid.add(addFile, 8, 10, 1, 2);
        grid.add(inputMap, 8, 25, 1, 2);
        return grid;
    }
}