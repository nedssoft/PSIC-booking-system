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

    public Physician(int id, String fn, String adr, String tel, String[] expertises) {
        super(id, fn, adr, tel);
        this.setExpertises(expertises);
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
    public void setExpertises(String [] exps) {
        expertises = exps;
    }

    public static Physician create(int id, String fn, String adr, String tel, String[] expertises) {
        Physician physician = new Physician(id, fn, adr, tel, expertises);
        PhysicianModel.save(physician);
        return physician;
    }
    
   public ArrayList<Treatment> getTreatments() {
     ArrayList<Treatment> matches = new ArrayList<Treatment>();
     ArrayList<Treatment> trs = TreatmentModel.all();
     
     for (Treatment tr: trs){
       if(tr.getPhysicianId() == this.getId()){
          matches.add(tr);
       }
     }
     return matches;
   }
   
    public ArrayList<Treatment> getTreatments(String status) {
     ArrayList<Treatment> matches = new ArrayList<Treatment>();
     ArrayList<Treatment> trs = TreatmentModel.all();
     for (Treatment tr: trs){
       if(tr.getPhysicianId() == this.getId() && tr.getStatus().equalsIgnoreCase(status)){
          matches.add(tr);
       }
     }
     return matches;
   }

    public String toString() {
        String exps = "";
        for(String exp: expertises){
            exps = exps+exp+"\n";
        }
        return "ID: " + this.getId() + "\n" + "Full name: " + this.getFullName() + "\n" + "Address: " + this.getAddress() + "\n" + "Telephone: " + this.getTelephone() + "\n"+"=====Areas of Expertise====\n" + exps;
    }
    
 
}
