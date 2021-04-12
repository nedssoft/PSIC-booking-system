/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controllers;

import app.models.PhysicianModel;
import app.models.TreatmentModel;
import java.util.ArrayList;

/**
 *
 * @author nedsoft
 */
public class Treatment {

    private int id, physicianId;
    private String name, status, date, room, expertise;
    private static int _id = 1;
    public Treatment(int physicianId, String name, String status, String date, String room, String expertise) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.date = date;
        this.room = room;
        this.expertise = expertise;
        this.physicianId = physicianId;
        _id = _id + 1;
    }

    public static Treatment create(int physicianId, String name, String status, String date, String room, String expertise) {
        Treatment tr = new Treatment(physicianId, name, status, date, room, expertise);
        TreatmentModel.save(tr);
        return tr;
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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
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
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the room
     */
    public String getRoom() {
        return room;
    }

    /**
     * @param room the room to set
     */
    public void setRoom(String room) {
        this.room = room;
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
     * @return the physician
     */
    public Physician getPhysician() {
        return PhysicianModel.findById(physicianId);
    }

    public int getPhysicianId() {
        return physicianId;
    }

    /**
     * @param expertise the expertise to set
     */
    public void setPhysicianId(int id) {
        this.physicianId = id;
    }

    public String toString() {
        return "ID: " + id + "\nName: " + name + "\nStatus: " + status + "\nRoom: " + room + "\nDate: " + date + "\nExpertise: " + expertise;
    }
    
     public String toString(boolean withPhysician) {
         
        return "ID: " + id + "\nName: " + name + "\nStatus: " + status + "\nRoom: " + room + "\nDate: " + date + "\nExpertise: " + expertise + "\n=====Physician=======\n" + this.getPhysician();
    }

    public static ArrayList<Treatment> findAllByExpertise(String exp) {
        ArrayList<Treatment> match = new ArrayList<Treatment>();
        ArrayList<Treatment> trs = TreatmentModel.all();
        for (Treatment tr : trs) {
            if (tr.getExpertise() == exp) {
                match.add(tr);
            }
        }
        return match;
    }

    /**
     *
     * @param name the name of the physician
     * @return
     */
    public static ArrayList<Treatment> findByPhysician(String name) {
        ArrayList<Treatment> trs = TreatmentModel.all();
        ArrayList<Treatment> matches = new ArrayList<Treatment>();
        for (Treatment tr : trs) {
            Physician ph = tr.getPhysician();

            if (ph != null && ph.getFullName().toLowerCase().equalsIgnoreCase(name)) {
                matches.add(tr);
            }
        }
        return matches;
    }

    /**
     *
     * @param name the name of the physician
     * @return
     */
    public static ArrayList<Treatment> findByPhysician(int physicianId) {
        ArrayList<Treatment> trs = TreatmentModel.all();
        ArrayList<Treatment> matches = new ArrayList<Treatment>();
        for (Treatment tr : trs) {
            Physician ph = tr.getPhysician();

            if (ph != null && ph.getId() == physicianId) {
                matches.add(tr);
            }
        }
        return matches;
    }
}
