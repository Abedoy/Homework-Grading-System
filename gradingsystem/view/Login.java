package edu.csustan.gradingsystem.view;

/*
 * This is a test login; It compares a String for authentication of the password. 
 * 
 */

import java.net.URL;
import java.util.ResourceBundle;

/*import edu.csustan.gradingsystem.domain.Person;
import edu.csustan.gradingsystem.util.ApplicationSecurityManager;
import edu.csustan.gradingsystem.view.DownloadStudentSubmission;
*/
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Login implements Initializable,  ControlledScreen {
         ScreensController myController;
         
         public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }

	/* private ApplicationSecurityManager applicationSecurityManager = new ApplicationSecurityManager();
         * 
         */

	// these are the labels that are in the Login.fxml file

	@FXML
	private Label message;

	@FXML
	private TextField username;

	@FXML
	private PasswordField password;

	// this is hooked into the button onAction=#login in the Login.fxml

	@FXML
        private void login(ActionEvent event){
       DownloadController.myController.setScreen(Screensframework.screenLandingID);
            username.clear();
            password.clear();
            
            
        }
	/*private void login(ActionEvent event) {
		if (username.getText().isEmpty()) {

			if (password.getText().isEmpty()) {
				message.setText("Enter a Username and password");
			} else {
				message.setText("Enter a Username");
			}
		}

		if (!username.getText().isEmpty()) {
			int userID = Integer.parseInt(username.getText());
			if (password.getText().isEmpty()) {
				message.setText("Enter a Password");
			} else if (applicationSecurityManager.isPerson(userID)) {
				if (!applicationSecurityManager.validatePerson(userID,
						password.getText())) {
					message.setText("You Password is Incorrect");
				} else {
					message.setText("Log in successful");
					// Redirect to Dashboard
				}
			} else {
				message.setText("That ID does not match any accounts in our system.");
			}

		}

		password.clear();
	}
	*/
            
	@FXML
        public void logout(ActionEvent event){
            message.setText(" logged out");
        }
	/*private void logout(ActionEvent e) {
		Person p = applicationSecurityManager.getActivePerson();
		applicationSecurityManager.removeActivePerson();
		message.setText(p.getFirstName() + " logged out");
	}
*/
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}

}

