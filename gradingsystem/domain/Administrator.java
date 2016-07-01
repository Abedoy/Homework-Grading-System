package edu.csustan.gradingsystem.domain;

/* 
 * Author: Thomas Falasco
 * 
 * This is our prototype class for Administrator.
 */
public class Administrator extends Person 
{
	
	

	public Administrator(int ID, String firstName, String mInitial,
			String lastName, String email, String password) {
		super(ID, firstName, mInitial, lastName, email, password);
		// TODO Auto-generated constructor stub
	}

	public void addCourse()
	{
		//This method creates a course
	}
	
	public void editCourse()
	{
		//This method edits the course
	}
	
	public void removeCourse()
	{
		//This method removes the course.
	}
	
	public void addFaculty()
	{
		//This method creates a faculty member.
	}
	
	public void editFaculty()
	{
		//This method edits the Faculty member.
	}
	
	public void removeFaculty()
	{
		//This method removes the faculty member from the system.
	}
	
	public void addStudent()
	{
		//This method creates the student.
	}
	
	public void editStudent()
	{
		//This method edits the Student.
	}
	
	public void removeStudent()
	{
		//This method removes the student from the system.
	}
	
    public static void main(String[] args)
    {
    	
    }	
}
