package edu.csustan.gradingsystem.test;
/**
 * @author Brandon
 * 
 * This is the test suite that will run all tests for the project.
 * Please add your test classes with the brackets on the line 
 * that begins with "@SuiteClasses" separated by commas.
 * 
 */
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AssignmentManagerTest.class, 
				PersonManagerTest.class }) //Add your test classes here
public class AllTests {
	public static final boolean DEBUG = false;
	//Leave this class empty. This functions as a container for the test runner.
}


