/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.models;

import app.controllers.AppointmentController;
import app.controllers.PatientController;
import app.controllers.TreatmentController;
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
public class AppointmentModel {

    private static ArrayList<AppointmentController> db = new ArrayList<AppointmentController>();

    public static void save(AppointmentController exp) {
        db.add(exp);
    }

    public static AppointmentController findById(int id) {
        AppointmentController ap = null;
        for (int i = 0; i < db.size(); i++) {
            AppointmentController p = db.get(i);
            if (p.getId() == id) {
                ap = p;
            }
        }
        return ap;
    }

    public static ArrayList<AppointmentController> all() {
        if (db.size() == 0) {
            seed(5);
        }
        return db;
    }


    public static boolean remove(int id) {
        boolean removed = false;
        for (int i = 0; i < db.size(); i++) {
            AppointmentController p = db.get(i);
            if (p.getId() == id) {
                db.remove(i);
                removed = true;
            }
        }
        return removed;
    }

    public static void seed(int num) {
        ArrayList<TreatmentController> trs = TreatmentModel.all();
        ArrayList<PatientController> pas = PatientModel.all();
        int totalAps = db.size();
        for (int i = 0; i < num; i++) {
            TreatmentController tr = trs.get(i);
            PatientController pa = pas.get(i);
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
//            String bookedDate = myDateObj.format(myFormatObj);
            
            LocalDateTime dateObj = LocalDateTime.now();
            DateTimeFormatter dateFormatObj = DateTimeFormatter.ofPattern("E, MMM dd yyyy HH:mm:ss");
            String bookedDate = dateObj.format(dateFormatObj);
            AppointmentController ap = AppointmentController.create(id, treatmentId, patientId);
//            System.out.println("Ap " +ap);
            tr.setStatus(status);
            

        }
    }

}
