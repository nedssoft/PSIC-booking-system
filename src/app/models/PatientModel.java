/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.models;

import app.controllers.PatientController;
import java.util.ArrayList;

/**
 *
 * @author nedsoft
 */
public class PatientModel {

    private static ArrayList<PatientController> db = new ArrayList<PatientController>();

    public static PatientController save(PatientController patient) {
        db.add(patient);
        return patient;
    }

    public static PatientController findById(int id) {
        PatientController patient = null;
        for (int i = 0; i < db.size(); i++) {
            PatientController p = db.get(i);
            if (p.getId() == id) {
                patient = p;
            }
        }
        return patient;
    }

    public static ArrayList<PatientController> all() {
        return db;
    }

    public static boolean remove(int id) {
        boolean removed = false;
        for (int i = 0; i < db.size(); i++) {
            PatientController p = db.get(i);
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
            PatientController.create(id, fn, adr, tel);
        }
    }
}
