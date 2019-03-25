package com.danicode.clasesapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class AdminSQLOpenHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "administration2"; // BD
    public static final int DB_VERSION = 1;
    public static final String TABLE_STUDENT = "student2"; // Nombre de tabla
    // Campos de la tabla
    public static final String ID_STUDENT = "id";
    public static final String NAME = "name";
    public static final String DNI = "dni";
    public static final String HOURS = "hours";
    public static final String MINUTES = "minutes";
    public static final String CREDIT = "credit";
    // Tabla
    public static final String TABLE = "create table " + TABLE_STUDENT + "(" + ID_STUDENT
            + " integer primary key autoincrement," + NAME + " text not null,"
            + DNI + " text not null," + HOURS + " integer," + MINUTES + " integer,"
            + CREDIT + " integer);";


    public AdminSQLOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Crear la tabla
        sqLiteDatabase.execSQL(TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("drop table if exists " + TABLE_STUDENT);
        onCreate(sqLiteDatabase);
        Log.i("onUpgrade DB", "Método onUpgrade en la base de datos student");
    }
}
