/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.csustan.gradingsystem.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Adaias Bedoy
 */
public class DownloadStudentSubmission extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {                                          
        Parent root = FXMLLoader.load(getClass().getResource("DownloadStudentSubmission.fxml")); //load download fxml and make it the root node
        
        Scene scene = new Scene(root); //set up new scene by using root
        stage.setTitle("View and Download Source File"); //name scene
        stage.setScene(scene); //put the scene on the stage
        stage.show(); //show the stage
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}