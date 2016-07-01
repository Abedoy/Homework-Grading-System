package edu.csustan.gradingsystem.domain;

/**
 * Author: Thomas Falasco
 * Edited by: Brandon Halpin
 * This is our Faculty prototype class.
 */
public class Faculty extends Person {

	public Faculty(int ID, String firstName, String mInitial, String lastName,
			String email, String password) {
		super(ID, firstName, mInitial, lastName, email, password);
		// TODO Auto-generated constructor stub
	}
	
	public Faculty(Person p){
		super(p.getID(), p.getFirstName(), p.getmInitial(), p.getLastName(), p.getEmail(), p.getPassword());
	}

	
	

	
    
}
