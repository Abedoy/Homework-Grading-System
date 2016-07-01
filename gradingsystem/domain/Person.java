package edu.csustan.gradingsystem.domain;

/**
 * Author: Thomas Falasco
 * Edited by: Brandon Halpin
 * 
 * This is prototype Person class.
 * 
 * This is the super class.
 */
public class Person {
	
	private int ID;
	private String firstName, lastName, mInitial, email, password;
	
	
	/**
	 * Constructor using all fields
	 * @param ID
	 * @param firstName
	 * @param lastName
	 * @param mInitial
	 * @param password
	 */
	public Person(int ID, String firstName, String mInitial, String lastName,
			 String email, String password) {
		super();
		this.ID = ID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mInitial = mInitial;
		this.email = email;
		this.password = password;
	}

	//Accessors and modifiers
	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		ID = ID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getmInitial() {
		return mInitial;
	}

	public void setmInitial(String mInitial) {
		this.mInitial = mInitial;
	}
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String toString(){
		String out = "PersonID: " + ID + " First: " + firstName + " MI: " +
				mInitial + " Last: " + lastName + " email: " + email + " Password: " + password;
		return out;
	
	}
}
