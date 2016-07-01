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

import edu.csustan.gradingsystem.domain.Student;
import edu.csustan.gradingsystem.feedbackprototype.ProtoStudentManager;
import static org.junit.Assert.*;

/**
 *
 * @author EV
 */
public class ProtoStudentManagerTest {
    
    public ProtoStudentManagerTest() {
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
     * Test of instantiateFromCSV method, of class ProtoStudentManager.
     */
    @Test
    public void testInstantiateFromCSV() throws Exception {
        System.out.println("instantiateFromCSV");
        new ProtoStudentManager().instantiateFromCSV();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStudentByID method, of class ProtoStudentManager.
     */
    @Test
    public void testGetStudentByID() {
        System.out.println("getStudentByID");
        int tID = 0;
        ProtoStudentManager instance = new ProtoStudentManager();
        Student expResult = null;
        Student result = instance.getStudentByID(tID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}