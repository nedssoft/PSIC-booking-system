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
public class PatientTest {
    
    private static Patient instance = null;
    public PatientTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        int id = 1;
        String fn = "Jon Doe";
        String adr = "22 Mongo park Place";
        String tel = "099876543";
        instance = Patient.create(id, fn, adr, tel);
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of create method, of class Patient.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        String fn = "Jon Doe";
        assertNotNull(instance);
        assertEquals(fn, instance.getFullName());
    }

    /**
     * Test of getAppointments method, of class Patient.
     */
    @Test
    public void testGetAppointments() {
        System.out.println("getAppointments");
        ArrayList<Appointment> result = instance.getAppointments();
        assertEquals(1, result.size());
    }

    /**
     * Test of toString method, of class Patient.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String expResult = "ID: " + instance.getId() + "\n" + "Full name: " + instance.getFullName() + "\n" + "Address: " + instance.getAddress() + "\n" + "Telephone: " + instance.getTelephone() + "\n";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
