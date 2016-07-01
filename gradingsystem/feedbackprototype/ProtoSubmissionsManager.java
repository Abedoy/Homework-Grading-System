package edu.csustan.gradingsystem.feedbackprototype;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import edu.csustan.gradingsystem.domain.StudentSubmission;
 
public class ProtoSubmissionsManager
{
public List<StudentSubmission> loadedSubmissions;

    public ProtoSubmissionsManager() {
        this.loadedSubmissions = new ArrayList<>();
    }

    public void instantiateFromCSV() throws FileNotFoundException
    {
		int submissionID;
		int submissionCount;
		Date submissionDate;
		String sourceFile;
		String instructorFeedback;
		int studentID;
		int facultyID;
		int assignmentNo;  
    try (Scanner scanner = new Scanner(new File("submissions.csv"))) {
        scanner.useDelimiter(",");
         
        //Get all tokens and store them in some data structure
        //I am just printing them
        while (scanner.hasNext())
        {
                        submissionID = Integer.parseInt(scanner.next().trim());
                        submissionDate = java.sql.Date.valueOf(scanner.next());
                        instructorFeedback = "notUsed";
                        studentID = scanner.nextInt();
                        facultyID = scanner.nextInt();
                        assignmentNo= scanner.nextInt();
			
            loadedSubmissions.add(new StudentSubmission(studentID,facultyID,assignmentNo ));
        }
    }
    }
	//right now for prototype purposes submission count is submission ID
	public StudentSubmission getSubmissionByID(int tID)
		{
			for (StudentSubmission S: loadedSubmissions)
			{
				if (S.getSubmissionCount() == tID)
				{
					return S;
				}
			}
			
			return null;
		}
}
 