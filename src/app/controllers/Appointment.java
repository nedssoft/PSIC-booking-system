/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controllers;

import app.models.AppointmentModel;
import app.models.PatientModel;
import app.models.TreatmentModel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author nedsoft
 */
public class Appointment {

    private int id, treatmentId, patientId;
    private String status, bookedDate;

    public Appointment(int id, int treatmentId, int patientId) {
        this.id = id;
        this.treatmentId = treatmentId;
        this.patientId = patientId;
        this.status = "booked";
        this.bookedDate = getFormattedBookedDate();
    }

    public static Appointment create(int id, int treatmentId, int patientId) {
        Appointment ap = new Appointment(id, treatmentId, patientId);
        AppointmentModel.save(ap);
        return ap;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the treatmentId
     */
    public int getTreatmentId() {
        return treatmentId;
    }

    /**
     * @param treatmentId the treatmentId to set
     */
    public void setTreatmentId(int treatmentId) {
        this.treatmentId = treatmentId;
    }

    /**
     * @return the patientId
     */
    public int getPatientId() {
        return patientId;
    }

    /**
     * @param patientId the patientId to set
     */
    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the bookedDate
     */
    public String getBookedDate() {
        return bookedDate;
    }

    /**
     * @param bookedDate the bookedDate to set
     */
    public void setBookedDate(String bookedDate) {
        this.bookedDate = bookedDate;
    }

    /**
     * Get the Patient that made the booking
     *
     * @return Patient
     */
    public Patient getPatient() {
        return PatientModel.findById(patientId);
    }

    /**
     * Get the treatment for this booking
     *
     * @return Treatment
     */
    public Treatment getTreatment() {
        return TreatmentModel.findById(treatmentId);
    }

    public String toString() {
        Patient pa = this.getPatient();

        Treatment tr = this.getTreatment();
        Physician ph = tr.getPhysician();
        return ":::Treatment Name: "+tr.getName()
                + "\n:::Booked By:"
                + "Name: " + pa.getFullName()
                + "\nTelephone: " + pa.getTelephone()
                + "\nAddress: " + pa.getAddress()
                + "\n:::To By Attended to By (Physician): "
                + "\nName: " + ph.getFullName() + "\nTelephone: " + ph.getTelephone()
                + "\nAppointment Date: " + tr.getDate()
                + "\nRoom: " + tr.getRoom() + "\nStatus: " + status
                + "\nBooked Date: " + this.getBookedDate();
    }

    public String toString(boolean plain) {
        Patient pa = this.getPatient();

        Treatment tr = this.getTreatment();
        Physician ph = tr.getPhysician();
        return "\nID: " + id
                +"\nTreatment Name: "+tr.getName()
                + "\nAppointment Date: " + tr.getDate()
                + "\nRoom: " + tr.getRoom() + "\nStatus: " + status
                + "\nBooked Date: " + this.getBookedDate();
    }

    private static String getFormattedBookedDate() {

        LocalDateTime dateObj = LocalDateTime.now();
        DateTimeFormatter dateFormatObj = DateTimeFormatter.ofPattern("E, MMM dd yyyy HH:mm:ss");
        return dateObj.format(dateFormatObj);
    }

}
