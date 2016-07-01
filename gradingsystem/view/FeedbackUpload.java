/*
 The upload function for the feed backform button
 */

package edu.csustan.gradingsystem.view;

//extra imports left will be need for selecting multiple files

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;

import javafx.stage.Window;
 
public final class FeedbackUpload  {
    private static Window stage;

    public static void button() {
     //creates a new simple file chooser window
        //code needed to attach to email
        
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Open Resource File");
    fileChooser.showOpenDialog(stage);

}
    
    
}
 
 