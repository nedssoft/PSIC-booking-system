/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.models;

import app.controllers.Appointment;
import app.controllers.Patient;
import app.controllers.Treatment;
import java.util.ArrayList;

/**
 *
 * @author nedsoft
 */
public class AppointmentModel {

    private static ArrayList<Appointment> db = new ArrayList<Appointment>();

    public static void save(Appointment exp) {
        db.add(exp);
    }

    public static Appointment findById(int id) {
        Appointment ap = null;
        for (int i = 0; i < db.size(); i++) {
            Appointment p = db.get(i);
            if (p.getId() == id) {
                ap = p;
            }
        }
        return ap;
    }

    public static ArrayList<Appointment> all() {
        if (db.size() == 0) {
            seed();
        }
        return db;
    }

    public static boolean remove(int id) {
        boolean removed = false;
        for (int i = 0; i < db.size(); i++) {
            Appointment p = db.get(i);
            if (p.getId() == id) {
                db.remove(i);
                removed = true;
            }
        }
        return removed;
    }

    public static void seed() {
        Appointment.create(1, 1);
        Appointment.create(3, 4);
        Appointment.create(5, 2);
        Appointment.create(7, 8);
        Appointment.create(6, 10);
    }

}
