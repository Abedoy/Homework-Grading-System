package edu.csustan.gradingsystem.feedbackprototype;


import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.sql.Time;
import java.util.*;

import edu.csustan.gradingsystem.domain.Assignment;
 
public class ProtoAssignmentsManager
{
public List<Assignment> loadedAssignments;

    public ProtoAssignmentsManager() {
        this.loadedAssignments = new ArrayList<>();
    }

    public void instantiateFromCSV() throws FileNotFoundException
    {
			String title;
			int assignmentID;
			boolean isAvailable;
			String description;
			Date dueDate;
			String unitTest = "not needed";
			String term = "";
			Time timeDue = null;
			double totalPossibleScore;
			double functionalPossibleScore;
			double stylePossibleScore;
			int facultyID;
			int courseSec;
			String courseID;
			int studentID = 0;
    try (Scanner scanner = new Scanner(new File("assignments.csv"))) {
        scanner.useDelimiter(",");
         
        //Get all tokens and store them in some data structure
        //I am just printing them
        while (scanner.hasNext())
        {
		
            title = scanner.next().trim();
                        assignmentID = scanner.nextInt();
                        isAvailable = scanner.nextBoolean();
                        description = scanner.next();
                        dueDate = java.sql.Date.valueOf(scanner.next());
                        totalPossibleScore = scanner.nextDouble();
                        functionalPossibleScore = scanner.nextDouble();
                        stylePossibleScore = scanner.nextDouble();
                        facultyID =scanner.nextInt();
                        courseSec = scanner.nextInt();
                        courseID = scanner.next();
                        
                        
                        loadedAssignments.add(new Assignment(title, assignmentID, term, isAvailable,
                    			description, dueDate, timeDue,
                    			totalPossibleScore, functionalPossibleScore,
                    			stylePossibleScore, facultyID, courseSec,
                    			courseID));
        }
    }
    }
	
		public Assignment getAssignmentByID(int ID)
		{
			for (Assignment A : loadedAssignments)
			{
				if (A.getAssignmentNo() == ID)
				{
					return A;
				}
			}
			
			return null;
		}
}
 