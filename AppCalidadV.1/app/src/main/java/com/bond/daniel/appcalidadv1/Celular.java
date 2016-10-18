package com.bond.daniel.appcalidadv1;

import java.io.Serializable;

/**
 * Created by DANIEL on 17/10/2016.
 */
public class Celular implements Serializable {
    private int id;
    private int imagen;
    private String desc;

    public Celular(int id, int imagen, String desc) {
        super();
        this.id = id;
        this.imagen = imagen;
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
