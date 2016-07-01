package edu.csustan.gradingsystem.controller;
/*Thomas Riley
 * Last Updated: September 24 2013
 * Version 1.0.2
 * 
 * Simple GUI made with Swing. Currently no real layout, but it does successfully populate a JPanel for both main menu
 *  and submitting a java file, as well as pass in the java file and display results.
 */


import javax.swing.*;

import edu.csustan.gradingsystem.util.CompileAndGrade;

import java.awt.event.*;
import java.io.*;

public class CAGTesterGui extends JFrame{

	private static final long serialVersionUID = 1L; //eclipse yells at me when I don't have this, I don't know what it's for.
	private JPanel panel; //The panel everything takes place on
	private String filePath = "No File Selected"; // Current file path of file they've selected
	private String fileInput = null; //Don't worry about this, just for my personal testing
	JTextArea fileSelected = new JTextArea("No File Selected"); //String used to let the user know which file they've selected
	
	//Create new SimpleExample object, create JPanel.
	public CAGTesterGui()
	{
		panel = new JPanel();
		init();
	}
	
	// Initialize first panel
	private void init()
	
	{
		panel.removeAll(); //Remove everything
		panel.repaint(); //Redraw the panel
	    getContentPane().add(panel); //Add the panel to the Content Pane (no idea why. Was in tutorial)

	    JButton quitButton = new JButton("Quit"); //New quit button
	    JButton addFile = new JButton("Add File"); //New Add File button
	       
	    //Commands for the quit button
	    quitButton.addActionListener(new ActionListener()
	    {
	    	@Override
			public void actionPerformed(ActionEvent event)
	    	{
	    		System.exit(0); //When clicked, exit.
	    	}
	    });
	    
	    //Commands for the add file button
	    addFile.addActionListener(new ActionListener()
	    {
	    	@Override
			public void actionPerformed(ActionEvent event)
	    	{
	    		// Go to acceptFile()
	    		acceptFile();
	    	}
	    });
	    
	    panel.add(addFile); // Add the button to the panel 
	    panel.add(quitButton); // Add the button to the panel
        setTitle("Group Project 1"); // Set the title of the screen
	    setSize(300, 200); // Set size of the panel
	    setLocationRelativeTo(null); // No idea, was on tutorial
	    setDefaultCloseOperation(EXIT_ON_CLOSE); // Exit the program when the X is clicked
	    panel.revalidate(); // Make sure all the buttons are on the panel
	}
	
	private void acceptFile()
	{		
		panel.removeAll(); // Clear the panel
		panel.repaint(); // Repaint the panel
		JButton selectButton = new JButton("Select File"); //Add the button to the panel
		JButton submitButton = new JButton("Submit"); //Add the button to the panel
		JButton styleButton = new JButton("Check Style"); //Add the button to the panel
		JButton backButton = new JButton("Back"); //Add the button to the panel
		JButton quitButton = new JButton("Quit"); //Add the button to the panel
		JButton inputFile = new JButton("Input File"); //Add the button to the panel
		
		// Instructions for the select file button
		selectButton.addActionListener(new ActionListener()
	    {
	    	@Override
			public void actionPerformed(ActionEvent event)
	    	{
	    		JFileChooser fc = new JFileChooser(); // Create new file chooser
	    		int chosen = fc.showOpenDialog(null); // Show the file chooser
	    		if (chosen == JFileChooser.APPROVE_OPTION)
	    		{
	    			File chosenFile = fc.getSelectedFile();
	    			filePath = chosenFile.getAbsolutePath();
	    			
	    			//Allowed file types
	    			if (filePath.substring(filePath.length() - 5, filePath.length()).equalsIgnoreCase(".java")
	    					|| filePath.substring(filePath.length() - 4, filePath.length()).equalsIgnoreCase(".cpp")
	    					|| filePath.substring(filePath.length() - 4, filePath.length()).equalsIgnoreCase(".zip")
	    					|| filePath.substring(filePath.length() - 4, filePath.length()).equalsIgnoreCase(".tar")
	    					|| filePath.substring(filePath.length() - 4, filePath.length()).equalsIgnoreCase(".rar")
	    					|| filePath.substring(filePath.length() - 3, filePath.length()).equalsIgnoreCase(".7z")
	    					|| filePath.substring(filePath.length() - 7, filePath.length()).equalsIgnoreCase(".tar.gz"))
	    			{
	    				fileSelected.setText(chosenFile.getName());
		    			filePath = filePath.replace("\\", "/");
		    			panel.revalidate();
	    			}
	    			
	    			// If it's not an allowed file type, don't accept the file
	    			else
	    			{
	    				JOptionPane.showMessageDialog(null, "You must select either a .cpp or .java file");
	    				filePath = "No File Selected";
	    				fileSelected.setText("No File Selected");
	    				panel.revalidate();
	    				
	    			}
	    		}
	    	}
	    	
	    });
		
		// Don't worry about this code, this is for my testing
		inputFile.addActionListener(new ActionListener()
	    {
	    	@Override
			public void actionPerformed(ActionEvent event)
	    	{
	    		JFileChooser fc = new JFileChooser();
	    		int chosen = fc.showOpenDialog(null);
	    		if (chosen == JFileChooser.APPROVE_OPTION)
	    		{
	    			File chosenFile = fc.getSelectedFile();
	    			fileInput = chosenFile.getAbsolutePath();
	    			if (fileInput.substring(fileInput.length() - 4, fileInput.length()).equalsIgnoreCase(".txt"))
	    			{
		    			filePath = filePath.replace("\\", "/");
	    			} else
	    			{
	    				JOptionPane.showMessageDialog(null, "You must select a .txt file");
	    				fileInput = "";
	    			}
	    		}
	    	}
	    	
	    });
		
		// Instructions for the back button. Bad programming style, but works.
		backButton.addActionListener(new ActionListener()
	    {
	    	@Override
			public void actionPerformed(ActionEvent event)
	    	{
	    		init();
	    	}
	    	
	    });
		
		// Instructions for the submit button.
		submitButton.addActionListener(new ActionListener()
	    {
	    	@Override
			public void actionPerformed(ActionEvent event)
	    	{
	    			    		
	    		if(!filePath.equalsIgnoreCase("no file selected"))
	    		{
	    			if(fileInput == null)
	    			{
	    				CompileAndGrade cag = new CompileAndGrade(filePath);
	    				JOptionPane.showMessageDialog(null, cag.checkForErrors());
	    			}
	    			
	    			else
	    			{
	    				CompileAndGrade cag = new CompileAndGrade(filePath, fileInput);
	    				JOptionPane.showMessageDialog(null, cag.checkForErrors());
	    			}
	    		}
	    		
	    		else
	    		{
	    			JOptionPane.showMessageDialog(null, "You must select a file first!");
	    		} 
	    	}
	    	
	    });
		
		// Instructions for the style checking button.
		styleButton.addActionListener(new ActionListener()
	    {
	    	@Override
			public void actionPerformed(ActionEvent event)
	    	{
	    		
	    		if(!filePath.equalsIgnoreCase("no file selected"))
	    		{
	    			CompileAndGrade cag = new CompileAndGrade(filePath);
	    			JOptionPane.showMessageDialog(null, cag.checkForStyle());
	    		}
	    		
	    		else
	    		{
	    			JOptionPane.showMessageDialog(null, "You must select a file first!");
	    		} 
	    	}
	    	
	    });
		
		// Instructions for the quit button.
		quitButton.addActionListener(new ActionListener()
	    {
	    	@Override
			public void actionPerformed(ActionEvent event)
	    	{
	    		System.exit(0);
	    	}
	    });
		
		// Add all the buttons
		panel.add(inputFile);
		panel.add(fileSelected);
		panel.add(styleButton);
		panel.add(backButton);
		panel.add(selectButton);
		panel.add(submitButton);
		panel.add(quitButton);
		panel.revalidate();
		setTitle("Scan File");
	}
	
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				CAGTesterGui ex = new CAGTesterGui();
				ex.setVisible(true);
			}
		});
	}
}