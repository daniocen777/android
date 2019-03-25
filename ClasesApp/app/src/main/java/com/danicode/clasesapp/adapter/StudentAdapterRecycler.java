package com.danicode.clasesapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.danicode.clasesapp.R;
import com.danicode.clasesapp.model.Student;

import java.util.LinkedList;
import java.util.List;

public class StudentAdapterRecycler extends RecyclerView.Adapter<StudentAdapterRecycler.ViewHolderStudent>
        implements View.OnClickListener {

    List<Student> studentList = new LinkedList<>();
    private View.OnClickListener listener;

    public StudentAdapterRecycler(List<Student> studentList) {
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public ViewHolderStudent onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_student, null, false);

        view.setOnClickListener(this);
        return new ViewHolderStudent(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderStudent holder, int position) {
        // Llenar los datos
        holder.tv_nombre.setText(studentList.get(position).getNombre());
        holder.tv_dni.setText("DNI: " + studentList.get(position).getDni());
        holder.tv_horas.setText("Horas: " + studentList.get(position).getHoras().toString());
        holder.tv_minutos.setText("Minutos: " + studentList.get(position).getMinutos().toString());
        holder.tv_creditos.setText("Créditos: " + studentList.get(position).getCreditos().toString());
    }

    @Override
    public int getItemCount() {
        return studentList.size(); // Tamaño de la lista
    }

    // Método para escuchar
    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (listener != null) {
            listener.onClick(view);
        }
    }

    public class ViewHolderStudent extends RecyclerView.ViewHolder {

        // Componentes del item_list_student
        TextView tv_nombre;
        TextView tv_dni;
        TextView tv_horas;
        TextView tv_minutos;
        TextView tv_creditos;

        public ViewHolderStudent(View itemView) {
            super(itemView);
            tv_nombre = itemView.findViewById(R.id.tv_student_name_item);
            tv_dni = itemView.findViewById(R.id.tv_student_dni_item);
            tv_horas = itemView.findViewById(R.id.tv_student_hours_item);
            tv_minutos = itemView.findViewById(R.id.tv_student_minutes_item);
            tv_creditos = itemView.findViewById(R.id.tv_student_credits_item);
        }
    }
}
