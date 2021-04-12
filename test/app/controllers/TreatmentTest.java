/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controllers;

import java.util.ArrayList;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author nedsoft
 */
public class TreatmentTest {
    
    private static Treatment instance = null;
    public TreatmentTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        int id = 1;
        int physicianId = 1;
        String name = "Dr. Cypher Rim";
        String status = "available";
        String date = "Saturday 12th May, 12:30";
        String room = "Room A";
        String expertise = "Rehabilitation";
        instance = Treatment.create(physicianId, name, status, date, room, expertise);
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of create method, of class Treatment.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        assertNotNull(instance);
        assertTrue(instance instanceof Treatment);
    }

    /**
     * Test of getName method, of class Treatment.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");

        String expResult = "Dr. Cypher Rim";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    
}
