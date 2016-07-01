
package edu.csustan.gradingsystem.controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javafx.collections.*;

import java.sql.Date;
import java.sql.Time;

import edu.csustan.gradingsystem.domain.Assignment;
import edu.csustan.gradingsystem.manager.AssignmentManager;
import edu.csustan.gradingsystem.view.ControlledScreen;
import edu.csustan.gradingsystem.view.ScreensController;

/**
 *
 * @author Francis Tweedy
 * Modified by: Jacob Gasaway
 * Controller class for the AddAssignment view and AssignmentManager
 */
public class AddAssignmentController implements Initializable, ControlledScreen {
	
        ScreensController myController;
        
        @FXML
        private TextField AssignmentName;
        @FXML
        private TextField PossiblePoints;
        @FXML
        private TextField DueDate;
        @FXML
        private TextField TimeDue;
        @FXML
        private TextField StylePoints;
        @FXML
        private TextField FunctionalPoints;
        @FXML
        private TextArea Description;

    
    @FXML
    private void AssignmentName(MouseEvent event) {
    	AssignmentName.setText("");
    }
    
    @FXML
    private void PossiblePoints(MouseEvent event) {
    	PossiblePoints.setText("");
    }
    
    @FXML
    private void DueDate(MouseEvent event) {
    	DueDate.setText("");
    }
    
    @FXML
    private void TimeDue(MouseEvent event) {
    	TimeDue.setText("");
    }
    
    @FXML
    private void StylePoints(MouseEvent event) {
    	StylePoints.setText("");
    }
    
    @FXML
    private void FunctionalPoints(MouseEvent event) {
    	FunctionalPoints.setText("");
    	
    }
    @FXML
    private void Description(MouseEvent event){
    	Description.setText("");
    }
    
    
    /*
     * Author: Jacob Gasaway
     * 
     * This button will test the correctness of the format for some values and then upload
     * them to the database if correct.
     * 
     */
    @FXML
    private void SubmitAssignment(ActionEvent event) {
    	

    	if(getPossiblePts() >= 0 && getFuncPts() >= 0 && getStylePts() >= 0){
    		if(getDueDate() != null && getDueTime() != null){
    			Assignment assignment = new Assignment(AssignmentName.getText(), 0, "Term", true,
    					Description.getText(), getDueDate(), getDueTime(), getPossiblePts(), getFuncPts(),
    					getStylePts(), 0, 0, "CourseID");
        		AssignmentManager manager = new AssignmentManager();
        		manager.insertAssignment(assignment);
        		System.out.println("Upload Complete!");
    		}
    	}
    	else{
    		System.out.println("Please enter non-negative decimal value.");
    	}
    	
    }
    
    /*
     * *****************
     * 
     * Author: Jacob Gasaway
     * 
     * Check Methods - The Following methods will convert the necessary Strings 
     * into the correct data type. Then, it will check if the format for that 
     * data type is correct and return it. If not, it will set the TextField
     * to a default value.
     * 
     * *****************
     */
    
    private double getPossiblePts()
    {
    	try{
    		return Double.parseDouble(PossiblePoints.getText());
    	}
    	catch(NumberFormatException e){
    		System.out.println("Possible Points needs to be a decimal number!");
    		PossiblePoints.setText("-1.0");
    		return -1.0;
    	}
    	
    }
    private double getFuncPts()
    {
    	try{
    		return Double.parseDouble(FunctionalPoints.getText());
    	}
    	catch(NumberFormatException e){
    		System.out.println("Functional Points needs to be a decimal number!");
    		FunctionalPoints.setText("-1.0");
    		return -1.0;
    	}
    }
    private double getStylePts()
    {
    	try{
    		return Double.parseDouble(StylePoints.getText());
    	}
    	catch(NumberFormatException e){
    		System.out.println("Style Points needs to be a decimal number!");
    		StylePoints.setText("-1.0");
    		return -1.0;
    	}
    }
    private Date getDueDate()
    {
    	try{
    		return Date.valueOf(DueDate.getText());
    	}
    	catch(IllegalArgumentException e)
    	{
    		System.out.println("Due Date is in an incorrect format!");
    		DueDate.setText("yyyy-mm-dd");
    		return null;
    	}
    }
    private Time getDueTime()
    {
    	try{
    		return Time.valueOf(TimeDue.getText());
    	}
    	catch(IllegalArgumentException e)
    	{
    		System.out.println("Due Time is in an incorrect format!");
    		TimeDue.setText("hh:mm:ss");
    		return null;
    	}
    }
    
    /*
     * *****************
     * 
     * End of Check Methods.
     * 
     * *****************
     */

    
	@Override
	public void setScreenParent(ScreensController screenPage) {
		myController = screenPage;	
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}    
}