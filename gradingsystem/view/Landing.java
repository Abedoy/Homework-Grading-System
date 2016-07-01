package edu.csustan.gradingsystem.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
* FXML Controller class
*
* @author jphelan
*/
public class Landing implements Initializable , ControlledScreen {

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
   
      
    
}