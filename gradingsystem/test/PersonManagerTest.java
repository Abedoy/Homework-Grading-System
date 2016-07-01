package edu.csustan.gradingsystem.test;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.csustan.gradingsystem.domain.Person;
import edu.csustan.gradingsystem.manager.PersonManager;

public class PersonManagerTest {
	private static Person person;
	private static PersonManager personManager = new PersonManager();
	
	/**
	 * Runs before each test method
	 * inserts test object to DB
	 */
	@Before
	public void setUp(){
		//Declare variables for new Person to be created
		int personID = 987654321;
		String firstName = "Jane";
		String mInitial = "F";
		String lastName = "Doe";
		String password = "aPassword";
		String email = "email@host.com";
		//Create new Person
		person = new Person(personID, firstName, mInitial, lastName, email , password);
		personManager.insertPerson(person);
	}
	
	/**
	 *  Attempts to insert a new object
	 */
	@Test
	public void insertTest(){
		System.out.println("insertTest...");
		int ID = 999999999;
		Person p = new Person(ID, "Test", "A", "Person","email", "pass");
		personManager.insertPerson(p); //Insert new person into database
		Person testPerson = personManager.getPersonByID(p.getID());

		//Verify all data from the database matches the new object
		assertTrue(p.getFirstName().equals(testPerson.getFirstName()));
		assertTrue(p.getLastName().equals(testPerson.getLastName()));
		assertTrue(p.getmInitial().equals(testPerson.getmInitial()));
		assertTrue(p.getEmail().equals(testPerson.getEmail()));
		personManager.deletePersonByID(ID);
	}
	
	@Test
	public void checkForPersonTest(){
		System.out.println("checkForPersonTest...");
		assertTrue(personManager.checkForPerson(person.getID()));
		assertFalse(personManager.checkForPerson(0));
	}
	
	@Test
	public void getPersonByIDTest(){
		System.out.println("getpersonByIDTest...");
		assertNotNull(personManager.getPersonByID(person.getID()));
		assertNull(personManager.getPersonByID(0));
	}
	
	/**
	 * Run After each method
	 * Deletes test object from DB
	 */
	@After
	public void tearDown(){
		//Remove new Test object from database
		personManager.deletePersonByID(person.getID());
		assertFalse(personManager.checkForPerson(person.getID())); //Verify test person has been removed from DB
	}
}
