/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controllers;

import app.models.AppointmentModel;
import app.models.PatientModel;
import java.util.ArrayList;

/**
 *
 * @author nedsoft
 */
public class Patient extends Personnel {
    private static int id = 1;
    public Patient(String fn, String adr, String tel) {
        super(id, fn, adr, tel);
        id = id + 1;
    }

    public static Patient create(String fn, String adr, String tel) {
        Patient patient = new Patient(fn, adr, tel);
        PatientModel.save(patient);
        return patient;
    }

    public ArrayList<Appointment> getAppointments() {
        ArrayList<Appointment> aps = AppointmentModel.all();
        ArrayList<Appointment> matches = new ArrayList<Appointment>();
        for (Appointment ap : aps) {
            if (ap.getPatientId() == this.getId()) {
                matches.add(ap);
            }
        }
        return matches;
    }

    public String toString() {
        return "ID: " + this.getId() + "\n" + "Full name: " + this.getFullName() + "\n" + "Address: " + this.getAddress() + "\n" + "Telephone: " + this.getTelephone() + "\n";
    }

}
