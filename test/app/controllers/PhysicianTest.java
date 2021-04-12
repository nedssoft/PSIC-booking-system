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
public class PhysicianTest {

    private static Physician ph = null;

    public PhysicianTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        String fn = "Jane Doe";
        String adr = "12 Gunner Road";
        String tel = "4265233276";
        String[] expertises = {"Rehabilitation", "Osteopathy"};
        String cHour = "Monday 1-3pm";
        ph = Physician.create(fn, adr, tel, expertises, cHour);
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of isFullyBooked method, of class Physician.
     */
    @Test
    public void testIsFullyBooked() {
        System.out.println("isFullyBooked");
        boolean expResult = false;
        boolean result = ph.isFullyBooked();
        assertEquals(expResult, result);
    }

    /**
     * Test of getConsultationMinutesLeft method, of class Physician.
     */
    @Test
    public void testGetConsultationMinutesLeft() {
        System.out.println("getConsultationMinutesLeft");
        int expResult = 120;
        int result = ph.getConsultationMinutesLeft();
        assertEquals(expResult, result);
    }


    /**
     * Test of getExpertises method, of class Physician.
     */
    @Test
    public void testGetExpertises() {
        System.out.println("getExpertises");
  
        int expResult = 2;
        String[] result = ph.getExpertises();
        assertEquals(expResult, result.length);
    }


    /**
     * Test of create method, of class Physician.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        assertNotNull(ph);
    
        assertEquals(1, ph.getId());
    }

    /**
     * Test of toString method, of class Physician.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
         String exps = "";
         String [] expertises = ph.getExpertises();
        for (String exp : expertises) {
            exps = exps + exp + "\n";
        }
        String expResult = "ID: " + ph.getId() + "\n" + "Full name: " + ph.getFullName() + "\n" + "Address: " + ph.getAddress() + "\n" + "Telephone: " + ph.getTelephone() + "\nConsulation Hours: " + ph.getConsultationHours() + "\n=====Areas of Expertise====\n" + exps;
        assertEquals(expResult, ph.toString());
    }

}
