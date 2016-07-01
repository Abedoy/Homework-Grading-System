package edu.csustan.gradingsystem.view;

import java.net.URL;
import java.util.ResourceBundle;

import edu.csustan.gradingsystem.controller.DownloadStudentSubmissionController;
import edu.csustan.gradingsystem.controller.FeedbackController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
* FXML Controller class for the SideMenu
* 
*/
public class SideMenu implements Initializable , ControlledScreen {

    ScreensController myController;
    @Override
         public void setScreenParent(ScreensController screenParent){
         myController = screenParent;
         
    }
    
    /**
* Initializes the controller class.
*/
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
   @FXML
    private void HelloWorld(ActionEvent event){
	// Any link in the menu, header or side pane must be explicitly declared here
        DownloadController.myController.setScreen(Screensframework.screenDownloadID);
        
    }

      
    
}