/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controllers;

import app.models.ConsultationModel;
import app.models.PhysicianModel;

/**
 *
 * @author nedsoft
 */
public class Consultation {

    private int id, physicianId;
    private String note, expertise, period;

    public Consultation(int id, int physicianId, String note, String expertise) {
        this.id = id;
        this.physicianId = physicianId;
        this.note = note;
        this.expertise = expertise;
        this.period = this.calculatePeriod();
    }

    public Consultation(int id, int physicianId, String note) {
        this.id = id;
        this.physicianId = physicianId;
        this.note = note;
        this.expertise = "";
        this.period = this.calculatePeriod();
    }

    public static Consultation create(int id, int physicianId, String note) {
        Consultation c = new Consultation(id, physicianId, note);
        ConsultationModel.save(c);
        return c;
    }

    public static Consultation create(int id, int physicianId, String note, String expertise) {
        Consultation c = new Consultation(id, physicianId, note, expertise);
        ConsultationModel.save(c);
        return c;
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
     * @return the physicianId
     */
    public int getPhysicianId() {
        return physicianId;
    }

    /**
     * @param physicianId the physicianId to set
     */
    public void setPhysicianId(int physicianId) {
        this.physicianId = physicianId;
    }

    /**
     * @return the note
     */
    public String getNote() {
        return note;
    }

    /**
     * @param note the note to set
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * @return the expertise
     */
    public String getExpertise() {
        return expertise;
    }

    /**
     * @param expertise the expertise to set
     */
    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    /**
     * @return the period
     */
    public String getPeriod() {
        return period;
    }

    /**
     * @param period the period to set
     */
    public void setPeriod(String period) {
        this.period = period;
    }

    /**
     * @return the physician
     */
    public Physician getPhysician() {
        return PhysicianModel.findById(physicianId);
    }

    public String toString() {
        Physician ph = this.getPhysician();
        if (expertise.isEmpty()) {
            return "ID: " + id + "\nNote: " + note + "\nTime slot: " + period + "\n===Physician===\n" + ph.toString();
        }
        return "ID: " + id + "\nNote: " + note + "\nTime slot: " + period + "\nExpertise: " + expertise + "\n===Physician===\n" + ph.toString();
    }
    
    private String calculatePeriod(){
    
        Physician ph = this.getPhysician();
        String cHours = ph.getConsultationHours();
        int minLeft = ph.getConsultationMinutesLeft();
        String [] hSplit1 = cHours.split(" ");
        String day = hSplit1[0];
        String [] hSplit2 = hSplit1[1].split("-");
        String sHour = hSplit2[0];
        int startHour = Integer.parseInt(sHour);
        String period = "";
        switch(minLeft){
            case 120:
                period = period +day+" " +sHour+"-"+sHour+":30pm";
                ph.setConsultationMinutesLeft(90);
                break;
            case 90:
                period = period+day+" "+ sHour+":30-"+String.valueOf((startHour +1))+"pm";
                ph.setConsultationMinutesLeft(60);
                break;
            case 60:
                period = period+day+" " +String.valueOf(startHour +1)+"-"+String.valueOf(startHour +1)+":30"+"pm";
                ph.setConsultationMinutesLeft(30);
                break;
            case 30:
            default:
                period = period+day+" " +String.valueOf(startHour +1)+"30-"+String.valueOf(startHour +2)+"pm";
                ph.setConsultationMinutesLeft(0);
                break;
        }
        return period;
        
    }

}
