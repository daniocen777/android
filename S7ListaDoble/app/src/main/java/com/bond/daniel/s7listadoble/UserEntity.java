package com.bond.daniel.s7listadoble;

import java.io.Serializable;

/**
 * Created by DANIEL on 13/10/2015.
 */
public class UserEntity implements Serializable {

    private int id;
    private String name;
    private String email;

    public UserEntity(int id, String name, String email) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
