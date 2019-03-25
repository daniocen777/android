package com.danicode.clasesapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.danicode.clasesapp.dao.DaoStudent;
import com.danicode.clasesapp.dao.impl.DaoStudentImpl;
import com.danicode.clasesapp.model.Student;

import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private EditText et_dni;
    private DaoStudent daoStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        et_dni = findViewById(R.id.et_search_dni);

        daoStudent = new DaoStudentImpl(this);

    }

    // Método para ir a crear cuenta
    public void goCreateAccount(View view) {
        Intent intent = new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
    }

    // Buscar
    public void searchStudent(View view) {
        String dni = et_dni.getText().toString();
        if (!(dni.isEmpty())) {
            Student student = daoStudent.getStudent(dni);
            if (student != null) {
                Intent intent = new Intent(this, CreditsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("student", student);
                intent.putExtras(bundle);
                startActivity(intent);
            } else {
                Toast.makeText(this, "No se encontró el DNI", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Debe ingresar DNI", Toast.LENGTH_SHORT).show();
        }
    }

    public void goListStudents(View view) {
        Intent intent = new Intent(this, StudentActivity.class);
        startActivity(intent);
    }
}

