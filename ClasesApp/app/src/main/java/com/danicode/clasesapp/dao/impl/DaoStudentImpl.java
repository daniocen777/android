package com.danicode.clasesapp.dao.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.danicode.clasesapp.AdminSQLOpenHelper;
import com.danicode.clasesapp.dao.DaoStudent;
import com.danicode.clasesapp.model.Student;

import java.util.LinkedList;
import java.util.List;

public class DaoStudentImpl implements DaoStudent {

    private AdminSQLOpenHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;


    public DaoStudentImpl(Context context) {
        this.context = context;
    }

    // Abrir base de datos
    public void openDataBase() {
        dbHelper = new AdminSQLOpenHelper(context);
        database = dbHelper.getWritableDatabase();
        Log.i("abrirDB", "Abrindo la base de datos");
    }

    // Cerrar la BD
    public void closeDB() {
        dbHelper.close();
        Log.i("cerrarDB", "Cerrando la base de datos");
    }

    @Override
    public String getDNI(String dni) {
        String result;
        openDataBase();
        Cursor cursor = database.rawQuery("select " + dbHelper.DNI +
                " from " + dbHelper.TABLE_STUDENT + " where " + dbHelper.DNI + "=" + dni, null);
        if (cursor.moveToFirst()) {
            Log.i("getDNI", "El DNI " + dni + " está en la base de datos");
            result = cursor.getString(0);
        } else {
            Log.i("getDNI", "El DNI " + dni + " no se encontró o hubo error");
            result = "DNI no encontrado";
        }
        closeDB();
        return result;
    }

    @Override
    public String addStudent(Student student) {
        openDataBase();
        Integer id = 0; // Crédito 0 al insertar nuevo estudiante
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbHelper.NAME, student.getNombre());
        contentValues.put(dbHelper.DNI, student.getDni());
        contentValues.put(dbHelper.HOURS, String.valueOf(student.getHoras()));
        contentValues.put(dbHelper.MINUTES, String.valueOf(student.getMinutos()));
        contentValues.put(dbHelper.CREDIT, String.valueOf(id));
        long result = database.insert(dbHelper.TABLE_STUDENT, null, contentValues);
        if (result != -1) {
            Log.i("addStudent", "CORRECT: Se agregaron los datos -- " + result);
            closeDB();
            return null;
        } else {
            Log.i("addStudent", "Error al enviar los datos: " + result);
            closeDB();
            return "Error al guardar datos: RESULT " + result;
        }
    }

    @Override
    public List<Student> studentQry() {
        List<Student> list = null;
        openDataBase();
        Cursor cursor = database.rawQuery("select " + dbHelper.ID_STUDENT + ","
                + dbHelper.NAME + "," + dbHelper.DNI + "," + dbHelper.HOURS + ","
                + dbHelper.MINUTES + "," + dbHelper.CREDIT + " from " + dbHelper.TABLE_STUDENT, null);
        list = new LinkedList<>();
        if (cursor.moveToFirst()) {
            do {
                Student student = new Student();
                student.setId(Integer.parseInt(cursor.getString(0)));
                student.setNombre(cursor.getString(1));
                student.setDni(cursor.getString(2));
                student.setHoras(Integer.parseInt(cursor.getString(3)));
                student.setMinutos(Integer.parseInt(cursor.getString(4)));
                student.setCreditos(Integer.parseInt(cursor.getString(5)));
                list.add(student);
            } while (cursor.moveToNext());
        }
        closeDB();
        return list;
    }

    @Override
    public Student getStudent(String dni) {
        openDataBase();
        Student student = new Student();
        Cursor cursor = database.rawQuery("select " + dbHelper.ID_STUDENT + ","
                + dbHelper.NAME + "," + dbHelper.DNI + "," + dbHelper.HOURS + ","
                + dbHelper.MINUTES + "," + dbHelper.CREDIT + " from " + dbHelper.TABLE_STUDENT
                + " where " + dbHelper.DNI + "=" + dni, null);
        if (cursor.moveToFirst()) {
            student.setId(Integer.parseInt(cursor.getString(0)));
            student.setNombre(cursor.getString(1));
            student.setDni(cursor.getString(2));
            student.setHoras(Integer.parseInt(cursor.getString(3)));
            student.setMinutos(Integer.parseInt(cursor.getString(4)));
            student.setCreditos(Integer.parseInt(cursor.getString(5)));
            Log.i("getStudent by DNI", "Se encontró el studiante: " + student.toString());
        } else {
            Log.i("getStudent by DNI", "No se encontró studiante con DNI: " + dni);
            student = null;
        }
        closeDB();
        return student;
    }

    @Override
    public String updateStudent(Student student) {
        openDataBase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbHelper.HOURS, String.valueOf(student.getHoras()));
        contentValues.put(dbHelper.MINUTES, String.valueOf(student.getMinutos()));
        contentValues.put(dbHelper.CREDIT, String.valueOf(student.getCreditos()));

        int result = database.update(dbHelper.TABLE_STUDENT, contentValues,
                dbHelper.ID_STUDENT + "=" + student.getId(), null);
        if (result != -1) {
            Log.i("updateStudent", "CORRECT: Se actualizaron los datos -- " + result);
            closeDB();
            return null;
        } else {
            Log.i("updateStudent", "Error al actualizar los datos: " + result);
            closeDB();
            return "ERROR al actualizar: " + result;
        }
    }

    @Override
    public String updateDataStudent(Student student) {
        openDataBase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbHelper.NAME, student.getNombre());
        contentValues.put(dbHelper.DNI, student.getDni());
        contentValues.put(dbHelper.HOURS, String.valueOf(student.getHoras()));
        contentValues.put(dbHelper.MINUTES, String.valueOf(student.getMinutos()));
        contentValues.put(dbHelper.CREDIT, String.valueOf(student.getCreditos()));

        int result = database.update(dbHelper.TABLE_STUDENT, contentValues,
                dbHelper.ID_STUDENT + "=" + student.getId(), null);
        if (result != -1) {
            Log.i("updateDataStudent", "CORRECT: Se actualizaron los datos del métood 'updateDataStudent' -- " + result);
            closeDB();
            return null;
        } else {
            Log.i("updateDataStudent", "Error al actualizar los datos: " + result);
            closeDB();
            return "ERROR al actualizar: " + result;
        }
    }

    @Override
    public String updateCredit(Student student) {
        openDataBase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbHelper.CREDIT, String.valueOf(student.getCreditos()));

        int result = database.update(dbHelper.TABLE_STUDENT, contentValues,
                dbHelper.ID_STUDENT + "=" + student.getId(), null);
        if (result != -1) {
            Log.i("updateCredit", "CORRECTO: Se actualizó el crédito -- " + result);
            closeDB();
            return null;
        } else {
            Log.i("updateCredit", "ERROR: Error al actualizar créditos: " + result);
            closeDB();
            return "ERROR al actualizar: " + result;
        }
    }
}
