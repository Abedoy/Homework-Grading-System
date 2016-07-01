package edu.csustan.gradingsystem.view;



import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
*
* @author jphelan
*/

/*
 * This method hold the list of screenIDs used to navigate from different GUI pages
 * How to add your GUI to the screen framework:
 * Add the desired fxml file and its controller file to the package. View not needed as the screenframework will take care of the view for you
 * public static String screen#ID = "Any string name";
 * public static String screen#File = "NameofFile.fxml";
 * Look at the next method's comments for instructions on what changes you will have to make.
 */

/*
 * Another thing you will have to do is modify whatever controllers you import to this package in the following way:
 * This will let the master controller work with the controller you imported and gives it instructions on how to set itself up on the screen
 * public class ________ implements Initializable {
        ScreensController myController;
    
        public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }
    
 */
public class Screensframework extends Application {
    
    public static String screenLoginID = "Login";
    public static String screenLoginFile = "Login.fxml";
    public static String screenDownloadID = "View and Download";
    public static String screenDownloadFile = "DownloadStudentSubmission.fxml";
    public static String screenFeedbackID = "Feedback";
    public static String screenFeedbackFile = "Feedback.fxml";
    
    public static String screenLandingID = "Landing";
    public static String screenLandingFile = "Landing.fxml";
    

    
    
    @Override
    /*
     * This method loads the screens and fxml files
     * If you added another ID you will need to modify this method as well by adding a new line of code:
     * mainContainer.loadScreen(Screensframework.screen#ID, Screensframework.screen#File);
     */
    public void start(Stage primaryStage) {
         
       
        ScreensController mainContainer = new ScreensController();
        mainContainer.loadScreen(Screensframework.screenLoginID, Screensframework.screenLoginFile);
        mainContainer.loadScreen(Screensframework.screenDownloadID, Screensframework.screenDownloadFile);
        mainContainer.loadScreen(Screensframework.screenFeedbackID, Screensframework.screenFeedbackFile);
        mainContainer.loadScreen(Screensframework.screenLandingID, Screensframework.screenLandingFile);
        
        mainContainer.setScreen(Screensframework.screenLoginID);
        
        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
       
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
