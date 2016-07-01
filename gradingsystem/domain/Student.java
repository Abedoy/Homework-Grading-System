package edu.csustan.gradingsystem.domain;

/**
 * Author: Thomas Falasco
 * Edited by: Brandon Halpin
 * The Student class inherits from Person
 * 
 */
public class Student extends Person
{

	private String major;
	
	

	public Student(int ID, String firstName, String mInitial, String lastName,
			String email, String password) {
		super(ID, firstName, mInitial, lastName, email, password);
		// TODO Auto-generated constructor stub
	}

	public Student(Person p) {
		super(p.getID(), p.getFirstName(), p.getmInitial(), p.getLastName(), p.getEmail(), p.getPassword());
		major = "Undeclared";
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}
	
	

}
