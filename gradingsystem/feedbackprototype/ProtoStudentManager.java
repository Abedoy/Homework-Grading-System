package edu.csustan.gradingsystem.feedbackprototype;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import edu.csustan.gradingsystem.domain.Student;
 
public class ProtoStudentManager
{
public List<Student> loadedStudents;

    public ProtoStudentManager() {
        this.loadedStudents = new ArrayList<>();
    }

    public void instantiateFromCSV() throws FileNotFoundException
    {
		int ID;
		String firstName;
		String mInitial;
		String lastName;
		String email;
		String password;
    try (Scanner scanner = new Scanner(new File("Students.csv"))) {
        scanner.useDelimiter(",");
         
        //Get all tokens and store them in some data structure
        //I am just printing them
        while (scanner.hasNext())
        {
            ID = Integer.parseInt(scanner.next().trim());
                        firstName = scanner.next();
                        mInitial = scanner.next();
                        lastName = scanner.next();
                        email = scanner.next();
                        password = scanner.next();
                        
                        loadedStudents.add(new Student (ID, firstName, mInitial, lastName,
                        email,password ));
        }
    }
    }
	
	public Student getStudentByID(int tID)
		{
			for (Student S: loadedStudents)
			{
				if (S.getID() == tID)
				{
					return S;
				}
			}
			
			return null;
		}
}
 