//This is the feed back controller required for JavaFX


package edu.csustan.gradingsystem.controller;

import java.net.URL;
import java.util.ResourceBundle;

import edu.csustan.gradingsystem.view.FeedbackUpload;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
/**
 *
 * @author jphelan
 */
public class FeedbackController implements Initializable {
    
    // following code is used for demo purpose 
    // code need to auto fill field - database queries or passed in strings
    
    @FXML
    private Label verified;
    @FXML
    private TextField studentName;
    @FXML
    private TextField studentEmail;
    @FXML
    private TextField assign;
    @FXML
    private TextField sub;
    @FXML
    private TextArea testMessage;
    
    @FXML
    private void Name(MouseEvent event){
        studentName.setText("Bear Rodgers");
   }
     @FXML
     private void Email(MouseEvent event){
        studentEmail.setText("BearRodgers@csustan.edu");
   } 
     @FXML
     private void Assignment(MouseEvent event){
        assign.setText("Hello World");
   } 
     @FXML
     private void Subject(MouseEvent event){
        sub.setText("Your program does not work");
   } 
     @FXML
     private void Message(MouseEvent event){
        testMessage.setText("Stan State has a good nursing program. Maybe you should check that out.");
   }   
    @FXML
    private void uploadButtonAction(ActionEvent event) {
         FeedbackUpload.button();
    }
        @FXML
    private void submitButtonAction(ActionEvent event) {
        //SendForm.email(); 
      //  String VERIFIED; 
       // VERIFIED = SendForm.email();
      //  verified.setText(VERIFIED);
    }
        
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

   


}