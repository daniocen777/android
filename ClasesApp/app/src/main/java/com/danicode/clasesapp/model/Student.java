package com.danicode.clasesapp.model;

import java.io.Serializable;

public class Student implements Serializable {

    private Integer id;
    private String nombre;
    private String dni;
    private Integer horas;
    private Integer minutos;
    private Integer creditos;

    public Student() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Integer getHoras() {
        return horas;
    }

    public void setHoras(Integer horas) {
        this.horas = horas;
    }

    public Integer getMinutos() {
        return minutos;
    }

    public void setMinutos(Integer minutos) {
        this.minutos = minutos;
    }

    public Integer getCreditos() {
        return creditos;
    }

    public void setCreditos(Integer creditos) {
        this.creditos = creditos;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", dni='" + dni + '\'' +
                ", horas=" + horas +
                ", minutos=" + minutos +
                ", creditos=" + creditos +
                '}';
    }
}
