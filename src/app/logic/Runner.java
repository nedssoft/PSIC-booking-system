/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.logic;

import app.db.*;
import java.util.ArrayList;

/**
 *
 * @author nedsoft
 */
public class Runner {

    public static void main(String[] args) {
//
        PatientDb.seed(10);
        PhysicianDb.seed();
        TreatmentDb.seed();
        AppointmentDb.seed(5);
        
//        ArrayList<Patient> patients = PatientDb.all();
//        ArrayList<Treatment> treatments = TreatmentDb.all();
//        for (Patient patient : patients) {
//            System.out.println(patient);
//        }
//
//        ArrayList<Physician> physicians = PhysicianDb.all();
//
//        for (Physician physician : physicians) {
//            System.out.println(physician);
//        }
//
//        for (Treatment tr : treatments) {
//            System.out.println("=======Treatments=========");
//            System.out.println(tr);
//        }
//         System.out.println(TreatmentDb.findById(1));
//        ArrayList<Appointment> aps = AppointmentDb.all();
//        System.out.println("=======Appointments=========");
//        for (Appointment ap : aps) {
//            System.out.println(ap);
//        }
        
        ArrayList<Treatment> matchedTrs = Treatment.findAllByExpertise("Osteopathy");
        
        for(Treatment tr: matchedTrs){
          System.out.println(tr);
        }
           

    }
}
