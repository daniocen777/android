package com.danicode.clasesapp.dao;

import com.danicode.clasesapp.model.Student;

import java.util.List;

public interface DaoStudent {

    abstract String addStudent(Student student); // Insertar

    abstract List<Student> studentQry();

    abstract String getDNI(String dni); // get DNI

    abstract String updateStudent(Student student); // Actualizar

    abstract String updateDataStudent(Student student); // Edit nombre y dni

    abstract String updateCredit(Student student); // Actualizar créditos

    abstract Student getStudent(String dni); // Get estudiante por dni
}
