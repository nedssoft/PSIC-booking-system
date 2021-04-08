/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.models;

import app.controllers.Expertise;
import java.util.ArrayList;

/**
 *
 * @author nedsoft
 */
public class ExpertiseModel {

    private static ArrayList<Expertise> db = new ArrayList<Expertise>();

    public static void save(Expertise exp) {
        db.add(exp);
    }

    public static Expertise findById(int id) {
        Expertise expertise = null;
        for (int i = 0; i < db.size(); i++) {
            Expertise p = db.get(i);
            if (p.getId() == id) {
                expertise = p;
            }
        }
        return expertise;
    }

    public static ArrayList<Expertise> all() {
        if(db.size() == 0) {
          seed();
        }
        return db;
    }


    public static boolean remove(int id) {
        boolean removed = false;
        for (int i = 0; i < db.size(); i++) {
            Expertise p = db.get(i);
            if (p.getId() == id) {
                db.remove(i);
                removed = true;
            }
        }
        return removed;
    }

    public static void seed() {
        String[] exps = {"Physiotherapy", "Osteopathy", "Rehabilitation"};
        for (int i = 1; i <= exps.length; i++) {
            int id = i;
            Expertise.create(id, exps[i - 1]);
        }
    }

}
