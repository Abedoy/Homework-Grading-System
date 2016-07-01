/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.csustan.gradingsystem.view;

import java.net.URL;                      //all these imports are typically used for basic controller methods
import java.util.ResourceBundle;          //most of these imports are not currently being used however
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Adaias Bedoy
 */
public class SaveHW implements Initializable {
        ScreensController myController;
    
        public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
        }
    
@FXML
    private Label label;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
}
