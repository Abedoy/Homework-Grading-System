package edu.csustan.gradingsystem.controller;

import java.net.URL;
import java.util.ResourceBundle;

import edu.csustan.gradingsystem.view.ControlledScreen;
import edu.csustan.gradingsystem.view.ScreensController;
import edu.csustan.gradingsystem.view.Screensframework;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
* FXML Controller class
*
* @author Adaias Bedoy
*/
public class MenuController implements Initializable , ControlledScreen {

    ScreensController myController;
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
    private void Logout(ActionEvent event) {
     myController.setScreen(Screensframework.screenLoginID);
    }
    @FXML
    private void HelloWorld(ActionEvent event){
        myController.setScreen(Screensframework.screenDownloadID);
    }
}