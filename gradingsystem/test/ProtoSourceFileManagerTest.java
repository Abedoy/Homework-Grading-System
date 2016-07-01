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

import edu.csustan.gradingsystem.domain.SourceFile;
import edu.csustan.gradingsystem.feedbackprototype.ProtoSourceFileManager;
import static org.junit.Assert.*;

/**
 *
 * @author EV
 */
public class ProtoSourceFileManagerTest {
    
    public ProtoSourceFileManagerTest() {
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
     * Test of instantiateFromCSV method, of class ProtoSourceFileManager.
     */
    @Test
    public void testInstantiateFromCSV() throws Exception {
        System.out.println("instantiateFromCSV");
        new ProtoSourceFileManager().instantiateFromCSV();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSourceFileByID method, of class ProtoSourceFileManager.
     */
    @Test
    public void testGetSourceFileByID() {
        System.out.println("getSourceFileByID");
        int tID = 0;
        ProtoSourceFileManager instance = new ProtoSourceFileManager();
        SourceFile expResult = null;
        SourceFile result = instance.getSourceFileByID(tID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}