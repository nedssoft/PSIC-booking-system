/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.db;

import app.logic.Appointment;
import app.logic.Patient;
import app.logic.Treatment;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author nedsoft
 */
public class AppointmentDb {

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
            seed(5);
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

    public static void seed(int num) {
        ArrayList<Treatment> trs = TreatmentDb.all();
        ArrayList<Patient> pas = PatientDb.all();
        int totalAps = db.size();
        for (int i = 0; i < num; i++) {
            Treatment tr = trs.get(i);
            Patient pa = pas.get(i);
            int id = i + 1;
            String status = "booked";
            int patientId = pa.getId();
            int treatmentId = tr.getId();
//            System.out.println("treatment ID: "+treatmentId);
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
//            String bookedDate = dateFormat.format(date);  

            LocalDateTime myDateObj = LocalDateTime.now();
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("E, MMM dd yyyy HH:mm:ss");
            String bookedDate = myDateObj.format(myFormatObj);

            Appointment ap = Appointment.create(id, treatmentId, patientId, status, bookedDate);
//            System.out.println("Ap " +ap);
            tr.setStatus(status);
            

        }
    }

}
