// this sends the email with stmp through gmail can be modified to use POP or IMAP if needed

package edu.csustan.gradingsystem.util;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
/**
 *
 * @author jphelan with help from STACKOVERFLOW
 */
public class SendForm {
    
 
  
// need to add working gmail username and password for this to work
    
    
    private static String SMPT_HOSTNAME = "smtp.gmail.com";
    private static String USERNAME = "";
    private static String PASSWORD = "";
    private static String VERIFIED;
   
    public static String email() {

        // Recipient's email ID needs to be mentioned.
        // this needs to be the faculty email
        // any email can be put in as a string for testing
        String to = "";

        // Sender's email ID needs to be mentioned
        String from = USERNAME;


        // Get system properties
        Properties properties = System.getProperties();

        // Mail server settings
        // Might need to add port in future for reliability
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.host", SMPT_HOSTNAME);

        // Get the default Session object.
        // Session session = Session.getDefaultInstance(properties);

        // create a session with an Authenticator
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(
                    to));

            // Set Subject: header field
            message.setSubject("This is the Subject Line!");

            // Now set the actual message
            message.setText("This is a test email");

            // Send message          
            Transport.send(message);
            VERIFIED = "Your feedback has been sucessfully sent";
        } catch (MessagingException mex) {
            mex.printStackTrace();

        }
       
        return VERIFIED;
    }

   
    
}

 