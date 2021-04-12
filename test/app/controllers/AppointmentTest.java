/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controllers;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author nedsoft
 */
public class AppointmentTest {
     Appointment instance;
    public AppointmentTest() {
        instance = null;
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of create method, of class Appointment.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        int id = 1;
        int treatmentId = 1;
        int patientId = 1;
        int expResult = id;
        assertNull(instance);
        instance = Appointment.create(treatmentId, patientId);
        assertNotNull(instance);
    }

    /**
     * Test of getId method, of class Appointment.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        instance = Appointment.create(2,3);
        int expResult = 1;
        int result = instance.getId();
        assertEquals(expResult, result);
 
    }


    /**
     * Test of setTreatmentId method, of class Appointment.
     */
    @Test
    public void testSetTreatmentId() {
        System.out.println("setTreatmentId");
        int treatmentId = 2;
        instance = Appointment.create(2,3);
        instance.setTreatmentId(treatmentId);
        int expResult = 2;
        assertEquals(expResult, instance.getTreatmentId());
        
    }
    
    /**
     * Test of getTreatment method, of class Appointment.
     */
    @Test
    public void testGetTreatment() {
        System.out.println("getTreatment");
        instance = Appointment.create(2,3);
        Treatment result = instance.getTreatment();
        assertNull(result);
    }

    
}
