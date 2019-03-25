package com.danicode.clasesapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.danicode.clasesapp.dao.DaoStudent;
import com.danicode.clasesapp.dao.impl.DaoStudentImpl;
import com.danicode.clasesapp.model.Student;

import java.util.LinkedList;
import java.util.List;

public class EditStudentActivity extends AppCompatActivity {

    private EditText et_editNombre;
    private EditText et_editDni;
    private EditText et_editHoras;
    private EditText et_editMinutos;

    private Student student;
    private String nombre;
    private String dni;
    private Integer horas;
    private Integer minutos;
    private Integer creditos;

    private DaoStudent daoStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);
        showToolbar("Editar Estudiante", true);

        et_editNombre = findViewById(R.id.et_edit_name);
        et_editDni = findViewById(R.id.et_edit_dni);
        et_editHoras = findViewById(R.id.et_edit_hours);
        et_editMinutos = findViewById(R.id.et_edit_minutes);

        daoStudent = new DaoStudentImpl(this);

        Bundle objStudent = getIntent().getExtras();
        if (objStudent != null) {
            student = (Student) objStudent.getSerializable("student");
            et_editNombre.setText(student.getNombre().toString());
            et_editDni.setText(student.getDni().toString());
            et_editHoras.setText(student.getHoras().toString());
            et_editMinutos.setText(student.getMinutos().toString());


        } else {
            Toast.makeText(this, "ERROR al recibir datos", Toast.LENGTH_SHORT).show();
        }
    }

    // Método para mostrar el toolbar
    public void showToolbar(String title, boolean upButton) {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

    // Validar editText
    public List<String> validate() {
        List<String> list = new LinkedList<>();
        if (et_editNombre.getText().toString().isEmpty()) {
            list.add("Debe ingesar su nombre");
        }
        if (et_editDni.getText().toString().isEmpty()) {
            list.add("Debe ingesar su DNI");
        }
        if (et_editDni.length() != 8) {
            list.add("DNI debe tener 8 caracteres. Usted digitó " + et_editDni.length());
        }
        if (et_editHoras.getText().toString().isEmpty()) {
            list.add("Debe ingesar las horas");
        }
        if (et_editMinutos.getText().toString().isEmpty()) {
            list.add("Debe ingresar los minutos");
        }
        return list;
    }

    // Limpiar controles
    public void clean() {
        et_editNombre.setText("");
        et_editDni.setText("");
        et_editHoras.setText("");
        et_editMinutos.setText("");
    }

    // Editar Datos
    public void editDataStudent2(View view) {
        Log.i("editDataStudent2", "Está entrando al método par editar");
        List<String> list = validate();
        if (list.isEmpty()) {
            Log.i("editDataStudent2", "No hay campos incorrectos");
            String resultDB;
            Integer horasEdit = Integer.parseInt(et_editHoras.getText().toString());
            Integer minutosEdit = Integer.parseInt(et_editMinutos.getText().toString());
            Integer creditosEdit = student.getCreditos();

            if (minutosEdit >= 60) {
                horasEdit += 1;
                minutosEdit = minutosEdit % 60;
            }
            if (horasEdit >= 5) {
                creditosEdit += horasEdit / 5;
                horasEdit = horasEdit % 5;
            }

            student.setNombre(et_editNombre.getText().toString());
            student.setDni(et_editDni.getText().toString());
            student.setHoras(horasEdit);
            student.setMinutos(minutosEdit);
            student.setCreditos(creditosEdit);

            resultDB = daoStudent.updateDataStudent(student);
            if (resultDB == null) {
                Log.i("editDataStudent2", "Respuesta de updateDataStudent: null => OK");
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                Toast.makeText(this, "Datos actualizados", Toast.LENGTH_SHORT).show();
            } else {
                Log.i("editDataStudent2", "ERROR: " + resultDB);
                Toast.makeText(this, resultDB, Toast.LENGTH_SHORT).show();
            }
        } else {
            Log.i("editDataStudent2", "ERRORES al llenar los campos");
            for (String msg : list) {
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                Log.i("editDataStudent2", msg);
            }
        }
    }

}
