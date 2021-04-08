/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.models;

import app.controllers.Patient;
import java.util.ArrayList;

/**
 *
 * @author nedsoft
 */
public class PatientModel {

    private static ArrayList<Patient> db = new ArrayList<Patient>();

    public static Patient save(Patient patient) {
        db.add(patient);
        return patient;
    }

    public static Patient findById(int id) {
        Patient patient = null;
        for (int i = 0; i < db.size(); i++) {
            Patient p = db.get(i);
            if (p.getId() == id) {
                patient = p;
            }
        }
        return patient;
    }

    public static ArrayList<Patient> all() {
        return db;
    }

    public static boolean remove(int id) {
        boolean removed = false;
        for (int i = 0; i < db.size(); i++) {
            Patient p = db.get(i);
            if (p.getId() == id) {
                db.remove(i);
                removed = true;
            }
        }
        return removed;
    }

    public static void seed(int num) {
        int totalPatients = db.size();
        for (int i = 1; i <= num; i++) {
            int id = totalPatients + i;
            String fn = "Patient-" + id;
            String adr = id + " Wonderland, Mars";
            String tel = "070000000" + id;
            Patient.create(id, fn, adr, tel);
        }
    }
}
