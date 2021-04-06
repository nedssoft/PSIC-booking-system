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
public class Expertise {

    private int id;
    private String name;
    
    public Expertise(int id, String name) {
      this.id = id;
      this.name = name;
    }
    
    public static Expertise create(int id, String name) {
     Expertise exp = new Expertise(id, name);
     ExpertiseDb.save(exp);
     return exp;
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
    
}
