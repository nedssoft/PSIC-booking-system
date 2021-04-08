/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.models;

import app.controllers.Physician;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author nedsoft
 */
public class PhysicianModel {

    private static ArrayList<Physician> db = new ArrayList<Physician>();

    public static Physician save(Physician physician) {
        db.add(physician);
        return physician;
    }

    public static Physician findById(int id) {
        Physician physician = null;
        for (int i = 0; i < db.size(); i++) {
            Physician p = db.get(i);
            if (p.getId() == id) {
                physician = p;
            }
        }
        return physician;
    }

    public static ArrayList<Physician> all() {
        seed();
        return db;
    }

    public static boolean update(int id, Physician physician) {
        boolean updated = false;
        for (int i = 0; i < db.size(); i++) {
            Physician p = db.get(i);
            if (p.getId() == id) {
                db.set(i, physician);
                updated = true;
            }
        }
        return updated;
    }

    public static boolean remove(int id) {
        boolean removed = false;
        for (int i = 0; i < db.size(); i++) {
            Physician p = db.get(i);
            if (p.getId() == id) {
                db.remove(i);
                removed = true;
            }
        }
        return removed;
    }
    public static ArrayList<Physician> findByExpertise(String exp) {
      ArrayList<Physician> matches = new ArrayList<Physician>();
      ArrayList<Physician> phys = all();
      for (Physician ph: phys){
          
          if(Arrays.asList(ph.getExpertises()).contains(exp)){
              matches.add(ph);
          }
      }
      return matches;
    }
    public static void seed() {

        Physician.create(1, "Dr. Ashly Tatnell", "978 Monica Circle", "+690-500-3079", new String[]{"Physiotherapy", "Rehabilitation"});
        
//        Treatment tr = new Treatment(1, 1,"Neural mobilisation", "available", "Monday 3rd May 2021,9:30-13:00", "medical consulting suite A", "Physiotherapy");
//        TreatmentDb.save(tr);
//        Treatment tr2 = new Treatment(2, 1,"Pool rehabilitation", "available", "Saturday 15th May 2021,10:00-12:00", "Swimming pool", "Rehabilitation");
//        TreatmentDb.save(tr2);
        
        Physician.create(2, "Dr. Wye Brockwell", "71906 Waywood Circle", "+790-994-9953", new String[]{"Osteopathy", "Rehabilitation"});
//        Treatment tr3 = new Treatment(3, 2,"Mobilisation of the spine and joints", "available", "Tuesaday 4th May 2021,14:00-15:00", "Gym", "Osteopathy");
//        TreatmentDb.save(tr3);
//        Treatment tr4 = new Treatment(4, 2,"Pool rehabilitation", "available", "Friday 14th May 2021,10:00-12:00", "Swimming pool", "Rehabilitation");
//        TreatmentDb.save(tr4);
        
         Physician.create(3, "Dr.Cybil Everix", "41 Russell Pass", "+930-229-2270", new String[]{"Physiotherapy","Osteopathy", "Rehabilitation"});
//        Treatment tr5 = new Treatment(5, 3,"Mobilisation of the spine and joints", "available", "Thursday 6th May 2021,14:00-15:00", "Gym", "Osteopathy");
//        TreatmentDb.save(tr5);
//        Treatment tr6 = new Treatment(6, 3,"Pool rehabilitation", "available", "Friday 22nd May 2021,10:00-12:00", "Swimming pool", "Rehabilitation");
//        TreatmentDb.save(tr6);
//        Treatment tr7 = new Treatment(7, 3,"Neural mobilisation", "available", "Friday 22nd May 2021,10:00-12:00", "medical consulting suite C", "Physiotherapy");
//        TreatmentDb.save(tr7);
        
         Physician.create(4, "Jeddy Strongel", "79818 Transport Hill", "+885-418-8711", new String[]{ "Rehabilitation"});
//        Treatment tr8 = new Treatment(8, 4,"Massage", "available", "Friday 7th May 2021,17:00-18:00", "Gym", "Rehabilitation");
//        TreatmentDb.save(tr8);
//        Treatment tr9 = new Treatment(9, 4,"Pool rehabilitation", "available", "Saturady 29thth May 2021,9:00-12:00", "Swimming pool", "Rehabilitation");
//        TreatmentDb.save(tr9);
//        
       Physician.create(5, "Dr. Joana Terren", "39 Muir Circle", "+224-521-3171", new String[]{"Physiotherapy", "Osteopathy"});
        
//        Treatment tr10 = new Treatment(10, 5,"Neural mobilisation", "available", "Saturday 8th May 2021 9:30-13:00", "medical consulting suite A", "Physiotherapy");
//        TreatmentDb.save(tr10);
//        Treatment tr11 = new Treatment(11, 5,"Acupuncture", "available", "Tuesday 25th May 2021,10:00-12:00", "Medical consulting suite D", "Osteopathy");
//        TreatmentDb.save(tr11);
        
    }
    
}
