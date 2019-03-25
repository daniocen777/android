package com.danicode.clasesapp;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import com.danicode.clasesapp.dao.DaoStudent;
import com.danicode.clasesapp.dao.impl.DaoStudentImpl;
import com.danicode.clasesapp.model.Student;

import java.util.LinkedList;
import java.util.List;

public class CreditsActivity extends AppCompatActivity {

    private TextView tv_nombre;
    private EditText et_horas;
    private EditText et_minutos;
    private TextView tv_tiempoAcumulado;
    private TextView tv_creditos;
    private Student student;
    private Integer horas;
    private Integer minutos;
    private Integer creditos;
    private Button btn_cobrar_credito;

    private DaoStudent daoStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);
        showToolbar("Créditos de estudiante", true);

        daoStudent = new DaoStudentImpl(this);

        tv_nombre = findViewById(R.id.tv_complete_name);
        et_horas = findViewById(R.id.et_hours_credit);
        et_minutos = findViewById(R.id.et_minutes_credit);
        tv_tiempoAcumulado = findViewById(R.id.tv_accumulated_time);
        tv_creditos = findViewById(R.id.tv_total_credit);
        btn_cobrar_credito = findViewById(R.id.btn_take_credit);

        Bundle objStudent = getIntent().getExtras();

        if (objStudent != null) {
            student = (Student) objStudent.getSerializable("student");
            tv_nombre.setText(student.getNombre().toString());
            tv_tiempoAcumulado.setText("Tiempo: " + student.getHoras().toString() + " horas "
                    + student.getMinutos().toString() + " minutos");
            tv_creditos.setText("Créditos: " + student.getCreditos().toString());
        }

        horas = Integer.parseInt(student.getHoras().toString());
        minutos = Integer.parseInt(student.getMinutos().toString());
        creditos = Integer.parseInt(student.getCreditos().toString());

        if (creditos > 0) {
            btn_cobrar_credito.setEnabled(true);
        } else {
            btn_cobrar_credito.setEnabled(false);
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
        if (et_horas.getText().toString().isEmpty()) {
            list.add("Debe ingesar las horas");
        }
        if (et_minutos.getText().toString().isEmpty()) {
            list.add("Debe ingresar los minutos");
        }
        return list;
    }

    public void addHours(View view) {
        List<String> list = validate();
        if (list.isEmpty()) {
            String resultDB;
            horas = horas + Integer.parseInt(et_horas.getText().toString());
            minutos = minutos + Integer.parseInt(et_minutos.getText().toString());
            if (minutos >= 60) {
                horas += 1;
                minutos = minutos % 60;
            }
            if (horas >= 5) {
                creditos += horas / 5;
                horas = horas % 5;
            }
            student.setHoras(horas);
            student.setMinutos(minutos);
            student.setCreditos(creditos);

            resultDB = daoStudent.updateStudent(student);
            if (resultDB == null) {
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                Toast.makeText(this, "Datos actualizados", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, resultDB, Toast.LENGTH_SHORT).show();
            }
        } else {
            for (String msg : list) {
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Diálogo cobrar créditos
    public void takeCredit(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view2 = inflater.inflate(R.layout.dialog_take_credit, null);
        builder.setView(view2);
        final AlertDialog dialog = builder.create();
        dialog.show();

        // EditText creditos
        final EditText et_credit = view2.findViewById(R.id.et_take_credit);
        et_credit.setInputType(InputType.TYPE_CLASS_NUMBER);
        // Botón cobrar
        Button btn_cobrar = view2.findViewById(R.id.btn_take_credit);
        Button btn_cancelar = view2.findViewById(R.id.btn_dialog_take_credit_cancel);
        // Error en esta línea
        //Integer creditoTomado = Integer.parseInt(et_credit.getText().toString());

        btn_cobrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(et_credit.getText().toString().isEmpty())) {
                    if (Integer.parseInt(et_credit.getText().toString()) <= creditos) {
                        student.setCreditos(creditos - Integer.parseInt(et_credit.getText().toString()));
                        String resultDBTakeCredit = daoStudent.updateCredit(student);
                        if (resultDBTakeCredit == null) {
                            dialog.dismiss();
                            Intent intent = new Intent(CreditsActivity.this, HomeActivity.class);
                            startActivity(intent);
                            Toast.makeText(CreditsActivity.this, "Crédito tomado", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(CreditsActivity.this, resultDBTakeCredit, Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(CreditsActivity.this, "No tiene suficientes créditos", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(CreditsActivity.this, "¿Cuántos créditos quiere cobrar?", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
}
