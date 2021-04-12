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
        if (db.size() == 0) {
            seed();
        }
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

    public static void seed() {
        String[] names = {
            "Maddie Dailey",
            "Vere MacCafferky",
            "Sarina Makeswell",
            "Gilbertine Peckham",
            "Dierdre Hodcroft",
            "Reagen Proschek",
            "Marigold Truse",
            "Dudley Cazereau",
            "Arlina Lippingwell",
            "Crissy Chartman"
        };

        for (int i = 1; i <= names.length; i++) {
            String fn = names[i-1];
            String adr = (i + 1) + fn +" Ave. Wonderland, Mars";
            String tel = "070000000" + (i+1);
            Patient.create(fn, adr, tel);
        }
    }
}
