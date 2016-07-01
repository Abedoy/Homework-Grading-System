package edu.csustan.gradingsystem.controller;

/*
 * This is a test login; It compares a String for authentication of the password. 
 * 
 */

import java.net.URL;
import java.util.ResourceBundle;

import edu.csustan.gradingsystem.view.ControlledScreen;
import edu.csustan.gradingsystem.view.ScreensController;
import edu.csustan.gradingsystem.view.Screensframework;
import edu.csustan.gradingsystem.domain.Person;
import edu.csustan.gradingsystem.util.ApplicationSecurityManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController implements Initializable,  ControlledScreen {
         ScreensController myController;
         
         public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }

	private ApplicationSecurityManager applicationSecurityManager = new ApplicationSecurityManager();

	// these are the labels that are in the Login.fxml file

	@FXML
	private Label message;

	@FXML
	private TextField username;

	@FXML
	private PasswordField password;

	// this is hooked into the button onAction=#login in the Login.fxml
	/*
	@FXML
	private void login(ActionEvent event){
		myController.setScreen(Screensframework.screen4ID);
	}
	*/
	@FXML
	private void login(ActionEvent event) {
		if (username.getText().isEmpty()) {

			if (password.getText().isEmpty()) {
				message.setText("Enter a Username and password");
			} else {
				message.setText("Enter a Username");
			}
		}

		if (!username.getText().isEmpty()) {
			String userID = username.getText();
			if (password.getText().isEmpty()) {
				message.setText("Enter a Password");
			} else if (applicationSecurityManager.isPerson(userID)) {
				if (!applicationSecurityManager.validatePerson(userID,
						password.getText())) {
					message.setText("Your Password is Incorrect");
					
				} else {
					message.setText("Log in successful");
					myController.setScreen(Screensframework.screenLandingID);
				}
			} else {
				message.setText("That ID or email does not match any accounts in our system.");
			}

		}
		password.clear();
	}
	
            
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

