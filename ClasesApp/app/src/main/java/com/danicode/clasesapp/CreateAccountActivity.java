package com.danicode.clasesapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.danicode.clasesapp.dao.DaoStudent;
import com.danicode.clasesapp.dao.impl.DaoStudentImpl;
import com.danicode.clasesapp.model.Student;

import java.util.LinkedList;
import java.util.List;

public class CreateAccountActivity extends AppCompatActivity {

    private EditText et_nombre;
    private EditText et_dni;
    private EditText et_horas;
    private EditText et_minutos;

    private DaoStudent daoStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        showToolbar("Nuevo Estudiante", true);

        et_nombre = findViewById(R.id.et_name);
        et_dni = findViewById(R.id.et_dni);
        et_horas = findViewById(R.id.et_hours);
        et_minutos = findViewById(R.id.et_minutes);

        daoStudent = new DaoStudentImpl(this);

    }

    // Método para mostrar el toolbar
    public void showToolbar(String title, boolean upButton) {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

    // Limpiar controles
    public void clean() {
        et_nombre.setText("");
        et_dni.setText("");
        et_horas.setText("");
        et_minutos.setText("");
    }

    // Validar
    private List<String> studentValidate() {
        List<String> list = new LinkedList<>();
        String nombre = et_nombre.getText().toString();
        String dni = et_dni.getText().toString();
        String st_horas = et_horas.getText().toString();
        String st_minutos = et_minutos.getText().toString();

        if (nombre.isEmpty()) {
            list.add("Debe ingresar su nombre");
        }
        if (dni.isEmpty()) {
            list.add("Debe ingresar su DNI");
        }
        if (dni.length() != 8) {
            list.add("DNI debe tener 8 caracteres. Usted digitó " + dni.length());
        }
        if (st_horas.isEmpty()) {
            list.add("Debe ingresar las horas");
        }
        if (st_minutos.isEmpty()) {
            list.add("Debe ingresar los minutos");
        }
        return list;
    }

    // Agregar estudiante
    public void addStudent(View view) {
        List<String> studentValidator = studentValidate();
        if (studentValidator.isEmpty()) {
            Student student = new Student();
            // Seteando los datos
            student.setNombre(et_nombre.getText().toString());
            student.setDni(et_dni.getText().toString());
            student.setHoras(Integer.parseInt(et_horas.getText().toString()));
            student.setMinutos(Integer.parseInt(et_minutos.getText().toString()));
            String dniOldStudent = daoStudent.getDNI(et_dni.getText().toString());
            if (!(dniOldStudent.equals(student.getDni()))) {
                String message = daoStudent.addStudent(student);
                if (message == null) {
                    Intent intent = new Intent(this, HomeActivity.class);
                    startActivity(intent);
                    Toast.makeText(this, "Datos guardados", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Este DNI ya se encuentra registrado", Toast.LENGTH_SHORT).show();
            }
        } else {
            for (String msg : studentValidator) {
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            }
        }

    }
}
