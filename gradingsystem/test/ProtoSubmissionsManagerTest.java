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

import edu.csustan.gradingsystem.domain.StudentSubmission;
import edu.csustan.gradingsystem.feedbackprototype.ProtoSubmissionsManager;
import static org.junit.Assert.*;

/**
 *
 * @author EV
 */
public class ProtoSubmissionsManagerTest {
    
    public ProtoSubmissionsManagerTest() {
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
     * Test of instantiateFromCSV method, of class ProtoSubmissionsManager.
     */
    @Test
    public void testInstantiateFromCSV() throws Exception {
        System.out.println("instantiateFromCSV");
        new ProtoSubmissionsManager().instantiateFromCSV();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSubmissionByID method, of class ProtoSubmissionsManager.
     */
    @Test
    public void testGetSubmissionByID() {
        System.out.println("getSubmissionByID");
        int tID = 0;
        ProtoSubmissionsManager instance = new ProtoSubmissionsManager();
        StudentSubmission expResult = null;
        StudentSubmission result = instance.getSubmissionByID(tID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}