package edu.csustan.gradingsystem.test;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.csustan.gradingsystem.domain.Faculty;
import edu.csustan.gradingsystem.feedbackprototype.ProtoFacultyManager;
import static org.junit.Assert.*;

/**
 *
 * @author EV
 */
public class ProtoFacultyManagerTest {
    
    public ProtoFacultyManagerTest() {
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
     * Test of instantiateFromCSV method, of class ProtoFacultyManager.
     */
    @Test
    public void testInstantiateFromCSV() throws Exception {
        System.out.println("instantiateFromCSV");
        new ProtoFacultyManager().instantiateFromCSV();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFacultyByID method, of class ProtoFacultyManager.
     */
    @Test
    public void testGetFacultyByID() {
        System.out.println("getFacultyByID");
        int tID = 0;
        ProtoFacultyManager instance = new ProtoFacultyManager();
        Faculty expResult = null;
        Faculty result = instance.getFacultyByID(tID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}