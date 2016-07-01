package edu.csustan.gradingsystem.test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.csustan.gradingsystem.domain.Assignment;
import edu.csustan.gradingsystem.feedbackprototype.ProtoAssignmentsManager;
import static org.junit.Assert.*;

/**
 *
 * @author Erika Avila
 */
public class ProtoAssignmentsManagerTest {
    
    public ProtoAssignmentsManagerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of instantiateFromCSV method, of class ProtoAssignmentsManager.
     */
    @Test
    public void testInstantiateFromCSV() throws Exception {
        System.out.println("instantiateFromCSV");
        new ProtoAssignmentsManager().instantiateFromCSV();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAssignmentByID method, of class ProtoAssignmentsManager.
     */
    @Test
    public void testGetAssignmentByID() {
        System.out.println("getAssignmentByID");
        int ID = 0;
        ProtoAssignmentsManager instance = new ProtoAssignmentsManager();
        Assignment expResult = null;
        Assignment result = instance.getAssignmentByID(ID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}