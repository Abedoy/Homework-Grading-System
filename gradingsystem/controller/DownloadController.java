/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.csustan.gradingsystem.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.MalformedURLException;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import edu.csustan.gradingsystem.view.ControlledScreen;
import edu.csustan.gradingsystem.view.ScreensController;
import edu.csustan.gradingsystem.view.Screensframework;
import javafx.fxml.FXMLLoader;

/**
 *
 * @author Adaias Bedoy
 */
public class DownloadController implements Initializable, ControlledScreen {
    
     ScreensController myController;
     public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }
    
    @FXML
    private Label label;
    
    @FXML
    private void feedbackButton(ActionEvent event) {
        myController.setScreen(Screensframework.screenFeedbackID);
    }
    
    @FXML
    public void downloadButton(ActionEvent event) { //opens up SaveHW to make a mockup of what the download process would look like
    	myController.setScreen(Screensframework.screenDownloadID);
    }
    @FXML
        /*
     * This method is to be completely erased and replaced when course list is finished
     */
    private void linkCS4800(ActionEvent event) { //mock up links to a picture of UI design
try {    
     String url = "http://i.imgur.com/MuzQokv.jpg";
     java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
} catch (java.io.IOException e) {
     System.out.println(e.getMessage());
}
    }
            
    @FXML
        /*
     * This method is to be completely erased and replaced when course list is finished
     */
    private void linkCS3500(ActionEvent event) { //placeholder method for link
        System.out.println("You clicked me!4");
        label.setText("Hello World!4");
    }
    
                
    @FXML
        /*
     * This method is to be completely erased and replaced when course list is finished
     */
    private void linkCS2500(ActionEvent event) { //placeholder method for link
        System.out.println("You clicked me!5");
        label.setText("Hello World!5");
    }
    
    @FXML
        /*
     * This method is to be completely erased and replaced when course list is finished
     */
    private void linkHome(ActionEvent event) { //links to UI mockup online
try {
     String url = "http://i.imgur.com/mgvES0u.jpg";
     java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
} catch (java.io.IOException e) {
     System.out.println(e.getMessage());
}
    }

    @FXML
        /*
     * This method is to be completely erased and replaced when student class is finished
     */
    private void linkStudentList(ActionEvent event) { //placeholder method
        System.out.println("You clicked me!6");
        label.setText("Hello World!6");
    }
        @FXML
    private void Logout(ActionEvent event) {
     myController.setScreen(Screensframework.screenLoginID);
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
