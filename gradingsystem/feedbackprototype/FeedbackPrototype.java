/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.csustan.gradingsystem.feedbackprototype;

import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Andrew
 */
public class FeedbackPrototype extends Application {
        private static ProtoFacultyManager pFM = new ProtoFacultyManager();
        private static ProtoStudentManager pSM = new ProtoStudentManager();
        private static ProtoSubmissionsManager pSSM = new ProtoSubmissionsManager();
        private static ProtoAssignmentsManager pAM = new ProtoAssignmentsManager();
        private static ProtoSourceFileManager pSFM = new ProtoSourceFileManager();
    @Override
    public void start(Stage stage) throws Exception {
        
       
        
        Parent root = FXMLLoader.load(getClass().getResource("CodeViewer.fxml"));
       
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        
        
        
        stage.show();
        
        
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
    
    	public static void loadData() {
		try {
			pFM.instantiateFromCSV();
		}
		catch (FileNotFoundException e){
			System.out.println("file not found");
		}
		
		try {
			pSM.instantiateFromCSV();
		}
		catch (FileNotFoundException e){
			System.out.println("file not found");
		}
		
		try{
			pSSM.instantiateFromCSV();
		}
		catch (FileNotFoundException e){
			System.out.println("file not found");
		}
		
		try {
			pAM.instantiateFromCSV();
		}
		catch (FileNotFoundException e){
			System.out.println("file not found");
		}
		
		try {
			pSFM.instantiateFromCSV();
		}
		catch (FileNotFoundException e){
			System.out.println("file not found");
		}
		
	}
}