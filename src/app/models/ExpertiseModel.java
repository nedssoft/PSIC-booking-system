/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.models;

import app.controllers.ExpertiseController;
import java.util.ArrayList;

/**
 *
 * @author nedsoft
 */
public class ExpertiseModel {

    private static ArrayList<ExpertiseController> db = new ArrayList<ExpertiseController>();

    public static void save(ExpertiseController exp) {
        db.add(exp);
    }

    public static ExpertiseController findById(int id) {
        ExpertiseController expertise = null;
        for (int i = 0; i < db.size(); i++) {
            ExpertiseController p = db.get(i);
            if (p.getId() == id) {
                expertise = p;
            }
        }
        return expertise;
    }

    public static ArrayList<ExpertiseController> all() {
        if(db.size() == 0) {
          seed();
        }
        return db;
    }


    public static boolean remove(int id) {
        boolean removed = false;
        for (int i = 0; i < db.size(); i++) {
            ExpertiseController p = db.get(i);
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
            ExpertiseController.create(id, exps[i - 1]);
        }
    }

}
