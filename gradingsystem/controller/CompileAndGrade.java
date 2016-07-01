package edu.csustan.gradingsystem.controller;


/*Thomas Riley
 *Last Updated: October 3 2013
 *Version 3.0.5
 *
 *Accepts an absolute file path passed in from main and runs both javac and java on it to make sure it compiles
 * and at the very least starts without a runtime exception. Probes with test inputs and checks style.
 * 
 * 	List of software needed to have this run:
 * 		- G++
 * 		- Java
 * 		- 7zip (command line version, 7za.exe)
 * 
 *   If you have any questions about the program, contact me ASAP: triley1@csustan.edu
 *  
 *   TODO Find some way to fight infinite loops.
 *   
 *   FIXME All ProcessBuilders use absolute file paths to files on my personal computer. Before editing this code,
 *   	make SURE you change the files to the appropriate location.
 */

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompileAndGrade
{
	private String codeFile; // Absolute file path, including file
	private String folder; // Absolute file path to codeFile, without file
	private String classFile; // The class name, pulled from the .java file
	
	private ProcessBuilder compiler; // Generates the process to compile (javac or g++) the .java file
	private ProcessBuilder runtime; // Generates the process to run (java) the .class file
	
	private boolean bracketSeparateLine = false; // Style check -> Bracket ({) is on its own line
	private boolean bracketSameLine = true; // Style check -> Bracket ({) is on the same line as method
	private boolean preMethodComments = true; // Style check -> Some sort of comment before each method
	private boolean runtimeCheck = false; // Whether or not to check for runtime errors
	
	private int styleErrors = 0; // Number of style errors
//	private int scorePossible; // NOT IMPLEMENTED
//	private int scoreReceived; // NOT IMPLEMENTED
	
	private Map<String, String> valueCheck;
	
	// Constructor, requires full file path.
	// TODO Support List<String> for multiple files
	public CompileAndGrade(String fileName)
	{
		init(fileName);
	}
	//Jacob Gasaway
	// Requires fullpath names. Can store multiple file paths.
	public CompileAndGrade(List<String> fileName)
	{
		listInit(fileName);
	}
	//Jacob Gasaway
	//constructor with a test file(s). Multiple tests files needs to be implemented
	// as a List<String>
	public CompileAndGrade(List<String> fileName, String inputTestFile)
	{
		listInit(fileName);
		buildMap(inputTestFile);
	}
	
	// Constructor with input file
	public CompileAndGrade(String fileName, String inputFile)
	{
		init(fileName);
		buildMap(inputFile);
	}
	
	// Allows the user to check for either compiler and runtime issues
	public String checkForErrors()
	{
		return compileTest();
	}
	
	// Allows the testing of programming style.
	public String checkForStyle()
	{
		return styleTest();
	}
	
	// Build a map from the input file, delimited by " ==> "
	private void buildMap(String inputFile)
	{
		valueCheck = new HashMap<String, String>();
		String line;
		
		try {
			BufferedReader in = new BufferedReader(new FileReader(inputFile));
			
			while((line = in.readLine()) != null)
			{
				String[] pairs = line.split(" ==> ");
				valueCheck.put(pairs[0], pairs[1]);
			}
			
			in.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Initiates object. Finds the folder, extracts files, and locates the main file. 
	private void init(String fileName)
	{
		int lastSlash = 0;
		char[] arr = fileName.toCharArray();
	
		// Loop through the file path and find all instances of /. Find the last
		//  slash and set the folder equal to that string.
		for(int i = 0; i < arr.length; i++)
		{
			if (arr[i] == '/')
			{
				lastSlash = i;
			}
		}
		folder = fileName.substring(0,lastSlash);
		extractFiles(fileName);
		
		codeFile = getMainFile();
		
		
		if(codeFile.contains(".java"))
		{
			compiler = new ProcessBuilder("javac", codeFile);
			classFile = getClassFile(); //TODO Extract to student folder
			runtimeCheck = true;
		}
		
		else
		{
			compiler = new ProcessBuilder("g++", codeFile);
		}
		
		compiler.directory(new File(folder));
		compiler.redirectErrorStream(true);
	}
	
	// Jacob Gasaway
	//Does the same thing as init but with a list of files or compressed files. 
	//Finds folder, extracts, and find main file.
		private void listInit(List<String> fileName)
		{
			for(int j = 0; j < fileName.size(); j++)
			{
				int lastSlash = 0;
				char[] arr = fileName.get(j).toCharArray();
				
				// Loop through the file path and find all instances of /. Find the last
				//  slash and set the folder equal to that string.
				for(int i = 0; i < arr.length; i++)
				{
					if (arr[i] == '/')
					{
						lastSlash = i;
					}
				}
				folder = fileName.get(j).substring(0,lastSlash);
				extractFiles(fileName.get(j));
			}
			codeFile = getMainFile();
			
			
			if(codeFile.contains(".java"))
			{
				compiler = new ProcessBuilder("javac", codeFile);
				classFile = getClassFile(); //TODO Extract to student folder
				runtimeCheck = true;
			}
			
			else
			{
				compiler = new ProcessBuilder("g++", codeFile);
			}
			
			compiler.directory(new File(folder));
			compiler.redirectErrorStream(true);
		}
	
	private void extractFiles(String fileName)
	{
		try
		{
			if((fileName.contains(".zip")
					|| fileName.contains(".tar")
					|| fileName.contains(".rar")
					|| fileName.contains(".7z"))
					&& !fileName.contains(".tar.gz"))
			{
				//TODO Have a way to get the location of 7-zip on a user's computer
				ProcessBuilder extract = new ProcessBuilder("C:/Program Files/7-Zip/7zG.exe", "e", fileName);
				extract.directory(new File(folder));
				Process p = extract.start();
				try {
					p.waitFor();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			// TODO Move over to tar command when migrating to linux or mac
			else if(fileName.contains(".tar.gz"))
			{
				//ProcessBuilder extract1 = new ProcessBuilder("C:/bin/7za", "e", fileName);
				ProcessBuilder extract1 = new ProcessBuilder("Enter 7-zip executable location here", "e", fileName);
				extract1.directory(new File(folder));
				Process p = extract1.start();
				try {
					p.waitFor();
				
				
				//ProcessBuilder extract2 = new ProcessBuilder("C:/bin/7za", "e", fileName.substring(0, fileName.length() - 3));
				ProcessBuilder extract2 = new ProcessBuilder("Enter 7-zip executable location here", "e", fileName);
				extract2.directory(new File(folder));
				Process p2 = extract2.start();
				p2.waitFor();
				
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
		}
		
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Find the "main" method in the code files
	private String getMainFile()
	{
		File folderFile = new File(folder);
		File[] fileList = folderFile.listFiles();
		String line;
		
		System.out.println(fileList);
		
		for(int i = 0; i < fileList.length; i++)
		{
			if(fileList[i].getName().contains(".java") || fileList[i].getName().contains(".cpp"))
			{
				try
				{
					BufferedReader in = new BufferedReader(new FileReader(fileList[i]));
				
					while ((line = in.readLine()) != null)
					{
						line = line.replaceAll("\\s+","");
					
						if(fileList[i].getName().contains(".java"))
						{
							if(line.contains("String[]args") && line.contains("main"))
							{
								return fileList[i].getName();
							}
						}
					
						else if(fileList[i].getName().contains(".cpp"))
						{
							if(line.contains("intmain("));
							{
								return fileList[i].getName();
							}
						}
					}
				
					in.close();
				}
				
				catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return "ERROR!";	
	}
	
	// Allows for three different types of style testing.  Detects whether or not there is
	//  some type of comment before each method, and where the brackets are placed: either on 
	//  the same line or on a separate line.
	private String styleTest()
	{
		try {
			BufferedReader in = new BufferedReader(new FileReader(folder + "/" + codeFile));
			String line = null;
			String bracketTest = null;
			boolean lastComment = false;
			boolean isComment = false;
			
			while ((line = in.readLine()) != null)
			{
				bracketTest = line.replaceAll("\\s",""); // Used to remove whitespace from the line, allows a .equals() expression to test for {
				if(preMethodComments == true)
				{
					if(line.contains("*/") || line.contains("//"))
					{
						lastComment = true;
					}
				
					else if((line.contains("public") || line.contains("private")) && !line.contains(";"))
					{
						if(lastComment == false)
						{
							styleErrors += 1;
						}
						
						lastComment = false;
					}
					
					else if(line.trim().length() > 0 && (!line.contains("import") || !line.contains("package")))
					{
						lastComment = false;
					}
				}
				
				if(bracketSameLine == true)
				{
					if(line.contains("/*") || line.contains("//"))
					{
						isComment = true;
					}
					
					if(line.contains("*/"))
					{
						isComment = false;
					}
					
					if(bracketTest.equals("{") && isComment == false)
					{
						styleErrors += 1;
					}
				}
				
				if(bracketSeparateLine == true)
				{
					if(line.contains("/*") || line.contains("//"))
					{
						isComment = true;
					}
					
					if(line.contains("*/"))
					{
						isComment = false;
					}
					
					if(bracketTest.contains("{") && bracketTest.length() > 1 && isComment == false)
					{
						styleErrors += 1;
					}
				}
			}
			in.close();
			return "Style errors: " + styleErrors;
			
		} catch (IOException e)
		{
			e.printStackTrace();
			return "Error!";
		}
	}
	
	//    Read in the entire .java file and find the public class line. Extract the class name from that line and use that.
	//     TODO May run into some problems if a student decides to use a classloader. Check for semicolon. 
	private String getClassFile()
	{
		String line = null;
		String lineLowerCase = null;
		int location;
		int firstNonSpace = 0;
		int secondNonSpace = 0;
		try
		{
			BufferedReader in = new BufferedReader(new FileReader(folder + "/" + codeFile));
		
			// Iterate through the code. Find the public class line and find the location of where it starts.
			//  For loop is to check for multiple spaces.
			while ((line = in.readLine()) != null)
			{	    		
				lineLowerCase = line.toLowerCase();
				if(lineLowerCase.contains("public") && lineLowerCase.contains("class"))
				{
					location = lineLowerCase.indexOf("class");
					char[] arr = line.toCharArray();
					
					for(int i = location + 5; i < arr.length; i++)
					{
						if(!Character.isWhitespace(arr[i]))
						{
							firstNonSpace = i;
							break;
						}
					}
					
					for(int i = firstNonSpace; i < arr.length; i++)
					{
						if(i + 1 == arr.length || Character.isWhitespace(arr[i + 1]) || arr[i + 1] == '{')
						{
							secondNonSpace = i;
							break;
						}
					}
					
					in.close();
					return line.substring(firstNonSpace, secondNonSpace + 1);
				}
			}
		
			in.close();
		
			return "No public class found. I'm just as surprised as you.";
		
		} catch (IOException e)
		{
			e.printStackTrace();
			return "Error!";
		}
		
	}
	
	// This method checks whether or not there's an exception thrown by javac.exe. If it succeeds, attempt to
	//  run the program with runttimeTest()
	private String compileTest()
	{
	    compiler.redirectErrorStream(true);
	    
	    try
	    {
	    	
	    	Process compileProcess = compiler.start();
	    
	    	// Reads in the InputStream (what the cmd outputs)
	    	BufferedReader in = new BufferedReader(new InputStreamReader(compileProcess.getInputStream()));
	    	String line = null;
	    
	    	// Scan the output of the compile command. If The file is not found, print error. If the word "error"
	    	//  is found, print the number of errors found in the compile. Maybe print stack trace.
	    	while ((line = in.readLine()) != null)
	    	{	    		
	    		
	    		if(line.toLowerCase().contains("file not found"))
	    		{
	    			return "The required libraries are either not on your system or aren't part of the" +
	    					" environment variables. Can't analyze file without it!";
	    		}
	    		
	    		else if(line.toLowerCase().contains("invalid"))
	    		{
	    			return "Couldn't read file. Make sure you chose a valid .java or .cpp file";
	    		}
			
	    		else if(line.toLowerCase().contains("error"))
	    		{				
	    			return "Compiler error detected. Check your code!";
	    		}
	    	}	    
	    
	    	if(runtimeCheck == true)
	    	{
	    		String runtimeTestString = "";
	    		
	    		if(valueCheck != null)
	    		{
	    			for (Map.Entry<String, String> entry : valueCheck.entrySet())
	    			{
	    				runtimeTestString = runtimeTestString + runtimeTest(entry.getKey(), entry.getValue());
	    			}
	    		
	    			return "Successfully compiled.\n\nChecking for runtime errors.\n\n" + runtimeTestString;
	    		}
	    		
	    		return "Successfully compiled.\n\nRuntime errors not being tested for.";
	    	}
	    	
	    	else
	    	{
	    		return "Successfully compiled.\n\nRuntime errors not being tested for.";
	    	}
	    
		} catch (IOException e)
		{
			e.printStackTrace(); //TODO Better error handling
			return "Error!";
		}
	    
	}
	
	// Run the java file and make sure "exception" doesn't pop up anywhere. If it succeedes 
	//  send a bit of test data to the console and check it against the map's value
	//   TODO Add support for no map file. [Pass in null value?]
	private String runtimeTest(String testKey, String testValue)
	{
		
		try
		{
			runtime = new ProcessBuilder("java", classFile);
			runtime.directory(new File(folder));
			runtime.redirectErrorStream(true);
			
			Process runtimeProcess = runtime.start();
			BufferedReader in = new BufferedReader(new InputStreamReader(runtimeProcess.getInputStream()));
			
			String line = null;
			String returnStatement = null;
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(runtimeProcess.getOutputStream()));
		    
	    	//Scan the output of the compile command. If The file is not found, print error. If the word "exception"
	    	// is found, print the line the runtime exception was found at.
	    	while ((line = in.readLine()) != null)
	    	{
	    		if(line.toLowerCase().contains("exception"))
	    		{
	    			return "Found runtime exception: " + line;
	    		}
	    		
	    		//For use in testing only	    		
	    		else if(line.toLowerCase().contains("file not found"))
	    		{
	    			return "java is either not on your system or isn't part of the" +
	    					" environment variables. Can't analyze file without it!";
	    		}
	    		
	    		else
	    		{
	    			
	    			bw.write(testKey);
	    			bw.newLine();
	    			bw.flush();
	    			line = in.readLine();
	    			
	    			if(line.equalsIgnoreCase(testValue))
	    			{
	    				return testKey + ": Pass\n";
	    			}
	    			
	    			return testKey + ": Fail\n";	    			
	    			
	    		}
	    		
	    	}
	    	return returnStatement;
		} catch (IOException e)
		{
			e.printStackTrace(); //TODO better error handling
			return "Error!";
		} 
	}
}