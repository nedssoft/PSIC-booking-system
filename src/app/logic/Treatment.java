/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.logic;

import app.db.PhysicianDb;
import app.db.TreatmentDb;
import java.util.ArrayList;

/**
 *
 * @author nedsoft
 */
public class Treatment {

    private int id, physicianId;
    private String name, status, date, room, expertise;

    public Treatment(int id, int physicianId, String name, String status, String date, String room, String expertise) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.date = date;
        this.room = room;
        this.expertise = expertise;
        this.physicianId = physicianId;
    }

    public static Treatment create(int id, int physicianId, String name, String status, String date, String room, String expertise) {
        Treatment tr = new Treatment(id, physicianId, name, status, date, room, expertise);
        TreatmentDb.save(tr);
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
     * @return the expertise
     */
    public Physician getPhysician() {
        return PhysicianDb.findById(physicianId);
    }

    /**
     * @param expertise the expertise to set
     */
    public void setPhysicianId(int id) {
        this.physicianId = id;
    }

    public String toString() {
        return "ID " + id + "\nName: " + name + "\nStatus: " + status + "\nRoom: " + room + "\nDate: " + date + "\nExpertise: " + expertise + "\n=====Physician=======\n" + this.getPhysician();
    }

    public static ArrayList<Treatment> findAllByExpertise(String exp) {
        ArrayList<Treatment> match = new ArrayList<Treatment>();
        ArrayList<Treatment> trs = TreatmentDb.all();
        for (Treatment tr : trs) {
            if (tr.getExpertise() == exp) {
                match.add(tr);
            }
        }
        return match;
    }

}
