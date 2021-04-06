/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.db;

import app.logic.Physician;
import app.logic.Treatment;
import java.util.ArrayList;

/**
 *
 * @author nedsoft
 */
public class TreatmentDb {

    private static ArrayList<Treatment> db = new ArrayList<Treatment>();

    public static void save(Treatment tr) {
        db.add(tr);
    }

    public static Treatment findById(int id) {
        Treatment treatment = null;
        for (int i = 0; i < db.size(); i++) {
            Treatment tr = db.get(i);
            if (tr.getId() == id) {
                treatment = tr;
            }
        }
        return treatment;
    }

    public static ArrayList<Treatment> all() {

        if (db.size() == 0) {
            seed();
        }
        return db;
    }

    public static boolean remove(int id) {
        boolean removed = false;
        for (int i = 0; i < db.size(); i++) {
            Treatment p = db.get(i);
            if (p.getId() == id) {
                db.remove(i);
                removed = true;
            }
        }
        return removed;
    }

    public static void seed() {

        Treatment.create(1, 1, "Neural mobilisation", "available", "Monday 3rd May 2021,9:30-13:00", "medical consulting suite A", "Physiotherapy");

        Treatment.create(2, 1, "Pool rehabilitation", "available", "Saturday 15th May 2021,10:00-12:00", "Swimming pool", "Rehabilitation");

        Treatment.create(3, 2, "Mobilisation of the spine and joints", "available", "Tuesaday 4th May 2021,14:00-15:00", "Gym", "Osteopathy");

        Treatment.create(4, 2, "Pool rehabilitation", "available", "Friday 14th May 2021,10:00-12:00", "Swimming pool", "Rehabilitation");

        Treatment.create(5, 3, "Mobilisation of the spine and joints", "available", "Thursday 6th May 2021,14:00-15:00", "Gym", "Osteopathy");

        Treatment.create(6, 3, "Pool rehabilitation", "available", "Friday 22nd May 2021,10:00-12:00", "Swimming pool", "Rehabilitation");

        Treatment.create(7, 3, "Neural mobilisation", "available", "Friday 22nd May 2021,10:00-12:00", "medical consulting suite C", "Physiotherapy");

        Treatment.create(8, 4, "Massage", "available", "Friday 7th May 2021,17:00-18:00", "Gym", "Rehabilitation");
        Treatment.create(9, 4, "Pool rehabilitation", "available", "Saturady 29thth May 2021,9:00-12:00", "Swimming pool", "Rehabilitation");

        Treatment.create(10, 5, "Neural mobilisation", "available", "Saturday 8th May 2021 9:30-13:00", "medical consulting suite A", "Physiotherapy");

        Treatment.create(11, 5, "Acupuncture", "available", "Tuesday 25th May 2021,10:00-12:00", "Medical consulting suite D", "Osteopathy");

    }
}
