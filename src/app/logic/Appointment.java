/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.logic;

import app.db.AppointmentDb;
import app.db.PatientDb;
import app.db.TreatmentDb;

/**
 *
 * @author nedsoft
 */
public class Appointment {

    private int id, treatmentId, patientId;
    private String status, bookedDate;

    public Appointment(int id, int treatmentId, int patientId, String status, String bookedDate) {
        this.id = id;
        this.treatmentId = treatmentId;
        this.patientId = patientId;
        this.status = status;
        this.bookedDate = bookedDate;
    }

    public static Appointment create(int id, int treatmentId, int patientId, String status, String bookedDate) {
        Appointment ap = new Appointment(id, treatmentId, patientId, status, bookedDate);
        AppointmentDb.save(ap);
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
        return PatientDb.findById(patientId);
    }

    /**
     * Get the treatment for this booking
     *
     * @return Treatment
     */
    public Treatment getTreatment() {
        return TreatmentDb.findById(treatmentId);
    }

    public String toString() {
        Patient pa = this.getPatient();

        Treatment tr = this.getTreatment();
        Physician ph = tr.getPhysician();
        return "=====Booked By=====\n" + "Name: " + pa.getFullName()
                + "\nTelephone: " + pa.getFullName()
                + "\n=====To By Attended to By (Physician)======= "
                + "\nName: " + ph.getFullName() + "\nTelephone: " + ph.getTelephone()
                + "\nAppointment Date: " + tr.getDate()
                + "\nRoom: " + tr.getRoom() + "\nStatus: " + status
                +"\nBooked Date: "+ this.getBookedDate();
    }

}
