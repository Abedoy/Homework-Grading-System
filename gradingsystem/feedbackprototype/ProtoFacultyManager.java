package edu.csustan.gradingsystem.feedbackprototype;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import edu.csustan.gradingsystem.domain.Faculty;
 
public class ProtoFacultyManager
{
public List<Faculty> loadedFaculty;

    public ProtoFacultyManager() {
        this.loadedFaculty = new ArrayList<>();
    }

    public void instantiateFromCSV() throws FileNotFoundException
    {
		int ID;
		String firstName;
		String mInitial;
		String lastName;
		String email;
		String password;
    try (Scanner scanner = new Scanner(new File("Faculty.csv"))) {
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
                        
                        loadedFaculty.add(new Faculty (ID, firstName, mInitial, lastName,
                        email,password ));
        }
    }
    }
	
	public Faculty getFacultyByID(int tID)
		{
			for (Faculty F: loadedFaculty)
			{
				if (F.getID() == tID)
				{
					return F;
				}
			}
			
			return null;
		}
		
}
 