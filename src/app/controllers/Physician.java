/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controllers;

import app.models.PhysicianModel;
import app.models.TreatmentModel;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author nedsoft
 */
public class Physician extends Personnel {

    private String[] expertises;
    private int consultationMinutesLeft;
    private String consultationHours;

    public Physician(int id, String fn, String adr, String tel, String[] expertises, String cHour) {
        super(id, fn, adr, tel);
        this.setExpertises(expertises);
        consultationHours = cHour;
        consultationMinutesLeft = 120;
    }

    /**
     * @return the isFullyBooked
     */
    public boolean isFullyBooked() {
        return consultationMinutesLeft == 0;
    }

    /**
     * @return the consultationMinutesLeft
     */
    public int getConsultationMinutesLeft() {
        return consultationMinutesLeft;
    }

    /**
     * @param consultationMinutesLeft the consultationMinutesLeft to set
     */
    public void setConsultationMinutesLeft(int consultationMinutesLeft) {
        this.consultationMinutesLeft = consultationMinutesLeft;
    }

    /**
     * @return the consultationHour
     */
    public String getConsultationHours() {
        return consultationHours;
    }

    /**
     * @param consultationHour the consultationHour to set
     */
    public void setConsultationHours(String consultationHour) {
        this.consultationHours = consultationHour;
    }

    /**
     * @return the expertise
     */
    public String[] getExpertises() {
        return expertises;
    }

    /**
     * Assign expertise to this physician
     */
    public void setExpertises(String[] exps) {
        expertises = exps;
    }

    public static Physician create(int id, String fn, String adr, String tel, String[] expertises, String cHour) {
        Physician physician = new Physician(id, fn, adr, tel, expertises, cHour);
        PhysicianModel.save(physician);
        return physician;
    }

    public ArrayList<Treatment> getTreatments() {
        ArrayList<Treatment> matches = new ArrayList<Treatment>();
        ArrayList<Treatment> trs = TreatmentModel.all();

        for (Treatment tr : trs) {
            if (tr.getPhysicianId() == this.getId()) {
                matches.add(tr);
            }
        }
        return matches;
    }

    public ArrayList<Treatment> getTreatments(String status) {
        ArrayList<Treatment> matches = new ArrayList<Treatment>();
        ArrayList<Treatment> trs = TreatmentModel.all();
        for (Treatment tr : trs) {
            if (tr.getPhysicianId() == this.getId() && tr.getStatus().equalsIgnoreCase(status)) {
                matches.add(tr);
            }
        }
        return matches;
    }

    public String toString() {
        String exps = "";
        for (String exp : expertises) {
            exps = exps + exp + "\n";
        }
        return "ID: " + this.getId() + "\n" + "Full name: " + this.getFullName() + "\n" + "Address: " + this.getAddress() + "\n" + "Telephone: " + this.getTelephone() + "\nConsulation Hours: " + this.getConsultationHours() + "\n=====Areas of Expertise====\n" + exps;
    }

}
