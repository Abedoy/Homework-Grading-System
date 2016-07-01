/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.csustan.gradingsystem.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author jpIII
 */
public class Header implements Initializable, ControlledScreen {

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
    private void logout (ActionEvent event){
        //This is my NULL POINTER ERROR
        // Any link in the menu, header or side pane must be explicitly declared here
         DownloadController.myController.setScreen(Screensframework.screenLoginID);
         
}

}
