package com.danicode.clasesapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.danicode.clasesapp.adapter.StudentAdapterRecycler;
import com.danicode.clasesapp.dao.DaoStudent;
import com.danicode.clasesapp.dao.impl.DaoStudentImpl;
import com.danicode.clasesapp.model.Student;

import java.util.LinkedList;
import java.util.List;

public class StudentActivity extends AppCompatActivity {

    private DaoStudent daoStudent;
    private RecyclerView recyclerView;
    private List<Student> studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        showToolbar("Lista de Estudiantes", true);

        daoStudent = new DaoStudentImpl(this);

        studentList = daoStudent.studentQry();
        if (studentList != null) {
            recyclerView = findViewById(R.id.studentRecycler);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            StudentAdapterRecycler adapter = new StudentAdapterRecycler(studentList);
            recyclerView.setAdapter(adapter);
            // Para el onclick
            adapter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(StudentActivity.this, EditStudentActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("student", studentList.get(recyclerView.getChildAdapterPosition(view)));
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        } else {
            Toast.makeText(this, "La lista está nula", Toast.LENGTH_SHORT).show();
        }
    }

    // Método para mostrar el toolbar
    public void showToolbar(String title, boolean upButton) {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

}
