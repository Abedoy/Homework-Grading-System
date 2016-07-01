package edu.csustan.gradingsystem.util;

import java.util.ArrayList;

import edu.csustan.gradingsystem.domain.*;
import edu.csustan.gradingsystem.manager.FacultyManager;
import edu.csustan.gradingsystem.manager.PersonManager;
import edu.csustan.gradingsystem.manager.StudentManager;

/**
 * 
 * @author Brandon
 *
 * Application Security Manager acts as a wrapper for functionality
 */
public class ApplicationSecurityManager {
	//private static ArrayList<Person> activePersonList = new ArrayList<Person>();
	private Person activePerson;
	private static ArrayList<Person> activePersonList = new ArrayList<Person>();
	private static PersonManager personManager = new PersonManager();
	private FacultyManager facultyManager = new FacultyManager();
	private StudentManager studentManager = new StudentManager();
	private static final int ADMIN = 0;
	private static final int FACULTY = 1;
	private static final int STUDENT = 2;
	
	
	public static void main(String[] args){
		ApplicationSecurityManager asm = new ApplicationSecurityManager();
		System.out.println("Logged in as ID 1: " + asm.validatePerson("1", "p"));
		asm.printActivePerson();
		System.out.println("A=0, F=1, S=2: " + asm.getActivePersonType(asm.getActivePerson().getID()));
		asm.removeActivePerson(asm.getActivePerson().getID());
		System.out.println("Logged out");
		System.out.println("Logged in as ID 2: " + asm.validatePerson("2", "p"));
		asm.printActivePerson();
		System.out.println("A=0, F=1, S=2: " + asm.getActivePersonType(asm.getActivePerson().getID()));
		asm.removeActivePerson(asm.getActivePerson().getID());
		System.out.println("Logged out");
	}

	/**
	 * Validates a person's credentials and adds them to activePersonList
	 * @param ID
	 * @param password
	 * @return
	 */
	public boolean validatePerson(String ID, String password){
		boolean isValid = false;
		int id = 0;
		if(isNumeric(ID)){
			isValid = personManager.validatePerson(Integer.parseInt(ID), password);
			if(isValid) id = Integer.parseInt(ID);
		}else{
			isValid = personManager.validatePerson(ID, password);
			if(isValid) id = personManager.getID(ID);
		}
		if(isValid){
			setActivePerson(personManager.getPersonByID(id));
		}
		return isValid;
	}
	
	/**
	 * Checks if a particular person exists in the database
	 * ID can be in form of StudentID or email address
	 * @param ID
	 * @return
	 */
	public boolean isPerson(String ID){
		boolean exists = false;
		int id = -1;
		if(isNumeric(ID)){
			exists = personManager.checkForPerson(Integer.parseInt(ID));
			if(exists){
				id = Integer.parseInt(ID);
			}
		}else{
			exists = personManager.checkForPerson(ID);
			if(exists){
				id = personManager.getID(ID);
			}
		}
		setActivePerson(personManager.getPersonByID(id));
		return exists;
	}
	
	
	/**
	 * Checks if a Person is in the activePersonList (i.e. Logged in)
	 * @param ID
	 * @return
	 */
	/*//no longer necessary for desktop app
	public boolean isActive(int ID){
		//TODO compare ID with ID's of Persons in activePersonList
		boolean isActive = false;
		if(!activePersonList.isEmpty()){
			for(Person p : activePersonList){
				if(p.getID() == ID){
					isActive = true;
					break;
				}
			}
		}
		return isActive;
	}

	*/
	/**
	 * Adds a Person to the activePersonList
	 * @param person
	 */
	public void setActivePerson(Person person) {
		//TODO check for person type
		//TODO instantiate person as their user type
		if(studentManager.isStudent(person.getID())){
			activePerson = new Student(person);
			//TODO get major and assign to object as well
		}else if(facultyManager.isFaculty(person.getID())){
			activePerson = new Faculty(person);
		}else{
			activePerson = person;
		}
		activePersonList.add(activePerson);
		
	}
	
	/**
	 * Gets the current active person.
	 * @return
	 */
	public Person getActivePerson(){	
		return activePerson;
	}
	
	/**
	 * Returns true if the current active person is a faculty.
	 * This is only useful for single user environment for multi-user see: getActivePersonType(int id) 
	 * @return
	 */
	public boolean isFaculty(){
		return activePerson instanceof Faculty;
	}
	
	/**
	 * Returns true if the current active person is a student. 
	 * This is only useful for single user environment for multi-user see: getActivePersonType(int id)
	 * @return
	 */
	public boolean isStudent(){
		return activePerson instanceof Student;
	}
	
	/**
	 * Gets the type of a user by their id. If the id is not found returns -1.
	 * @param id
	 * @return
	 */
	public int getActivePersonType(int id){
		//This code will have to be adapted when we move to a multi-user environment
		int type = -1;
		if(activePerson.getID() == id){
			if(activePerson instanceof Administrator){
				type = ADMIN;
			}else if(activePerson instanceof Faculty){
				type = FACULTY;
			}else if(activePerson instanceof Student){
				type = STUDENT;
			}
		}
		return type;
	}

	/**
	 * Removes a person from the activePersonList (Logout)
	 * @param ID
	 */
	public void removeActivePerson(int id) {
		for(int i = 0; i < activePersonList.size(); i++){
			if(activePersonList.get(i).getID() == id){
				activePersonList.remove(i);
			}
		}
		activePerson = null;
	}
	
	/**
	 * Prints all information for the current active person
	 */
	public void printActivePerson(){
		if(activePerson != null){
			System.out.println(activePerson);
		}else{
			System.out.println("No active persons.");
		}
	}
	
	/**
	 * Private utility method that checks if a string contains only integers.
	 * @param str
	 * @return
	 */
	private boolean isNumeric(String str){
		return str.matches("\\d*");  //match a number with optional '-' and decimal.
	}

}
