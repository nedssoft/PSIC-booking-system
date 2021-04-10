/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.models;

import app.controllers.Consultation;
import app.controllers.Physician;
import java.util.ArrayList;

/**
 *
 * @author nedsoft
 */
public class ConsultationModel {

    private static ArrayList<Consultation> db = new ArrayList<Consultation>();

    public static void save(Consultation exp) {
        db.add(exp);
    }

    public static Consultation findById(int id) {
        Consultation c = null;
        for (int i = 0; i < db.size(); i++) {
            Consultation p = db.get(i);
            if (p.getId() == id) {
                c = p;
            }
        }
        return c;
    }

    public static ArrayList<Consultation> all() {
        if (db.size() == 0) {
            seed();
        }
        return db;
    }

    public static boolean remove(int id) {
        boolean removed = false;
        for (int i = 0; i < db.size(); i++) {
            Consultation p = db.get(i);
            if (p.getId() == id) {
                db.remove(i);
                removed = true;
            }
        }
        return removed;
    }

    public static void seed() {
        Physician p1 = PhysicianModel.findById(1);
        Physician p2 = PhysicianModel.findById(2);
        Physician p3 = PhysicianModel.findById(3);
        Consultation c1 = Consultation.create(1, 1, "Jane Doe", p1.getExpertises()[0]);
        Consultation c2 = Consultation.create(2, 2, "Jon Doe", p2.getExpertises()[1]);
        Consultation c3 = Consultation.create(3, 3, "Peters Rock", p3.getExpertises()[0]);
        System.out.print(c1);
    }

}
