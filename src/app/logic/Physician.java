/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.logic;

import app.db.*;
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
        PhysicianDb.save(physician);
        return physician;
    }
    
   

    public String toString() {
        String exps = "";
        for(String exp: expertises){
            exps = exps+exp+"\n";
        }
        return "ID: " + this.getId() + "\n" + "Full name: " + this.getFullName() + "\n" + "Address: " + this.getAddress() + "\n" + "Telephone: " + this.getTelephone() + "\n"+"=====Areas of Expertise====\n" + exps;
    }
}
