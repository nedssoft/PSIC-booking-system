/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.models;

import app.controllers.Physician;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author nedsoft
 */
public class PhysicianModel {

    private static ArrayList<Physician> db = new ArrayList<Physician>();

    public static Physician save(Physician physician) {
        db.add(physician);
        return physician;
    }

    public static Physician findById(int id) {
        Physician physician = null;
        for (int i = 0; i < db.size(); i++) {
            Physician p = db.get(i);
            if (p.getId() == id) {
                physician = p;
            }
        }
        return physician;
    }

    public static ArrayList<Physician> all() {
        seed();
        return db;
    }

    public static boolean update(int id, Physician physician) {
        boolean updated = false;
        for (int i = 0; i < db.size(); i++) {
            Physician p = db.get(i);
            if (p.getId() == id) {
                db.set(i, physician);
                updated = true;
            }
        }
        return updated;
    }

    public static boolean remove(int id) {
        boolean removed = false;
        for (int i = 0; i < db.size(); i++) {
            Physician p = db.get(i);
            if (p.getId() == id) {
                db.remove(i);
                removed = true;
            }
        }
        return removed;
    }

    public static ArrayList<Physician> findByExpertise(String exp) {
        ArrayList<Physician> matches = new ArrayList<Physician>();
        ArrayList<Physician> phys = all();
        for (Physician ph : phys) {

            if (Arrays.asList(ph.getExpertises()).contains(exp)) {
                matches.add(ph);
            }
        }
        return matches;
    }

    public static void seed() {

        Physician.create(1, "Dr. Ashly Tatnell", "978 Monica Circle", "+690-500-3079", new String[]{"Physiotherapy", "Rehabilitation"});
        Physician.create(2, "Dr. Wye Brockwell", "71906 Waywood Circle", "+790-994-9953", new String[]{"Osteopathy", "Rehabilitation"});
        Physician.create(3, "Dr.Cybil Everix", "41 Russell Pass", "+930-229-2270", new String[]{"Physiotherapy", "Osteopathy", "Rehabilitation"});
        Physician.create(4, "Jeddy Strongel", "79818 Transport Hill", "+885-418-8711", new String[]{"Rehabilitation"});
        Physician.create(5, "Dr. Joana Terren", "39 Muir Circle", "+224-521-3171", new String[]{"Physiotherapy", "Osteopathy"});

    }

}
