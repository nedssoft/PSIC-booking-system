/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.logic;
import app.db.*;

/**
 *
 * @author nedsoft
 */
public class Patient extends Personnel {
 
    
    public Patient(int id, String fn, String adr, String tel) {
        super(id,fn, adr, tel);
    }
   
    public static Patient create(int id, String fn, String adr, String tel) {
        Patient patient = new Patient(id,fn, adr, tel);
        PatientDb.save(patient);
       return patient;
    }
    
    public String toString() {
        return "ID: "+ this.getId()+"\n"+ "Full name: " + this.getFullName()+ "\n"+ "Address: "+ this.getAddress()+"\n"+ "Telephone: "+ this.getTelephone()+"\n";
    }
    
}
