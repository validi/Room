package com.aurora.a3_17homeworkexercise1.Recycler;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.aurora.a3_17homeworkexercise1.R;
import com.aurora.a3_17homeworkexercise1.db.entity.Student;

import java.util.ArrayList;


public class StudentsAdapter extends RecyclerView.Adapter<StudentsAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Student> contactssList;
Clik_Item clik_item;
   


    public StudentsAdapter(Context context, ArrayList<Student> contacts,Clik_Item clik_item) {
        this.context = context;
        this.contactssList = contacts;
        this.clik_item=clik_item;

    }
   
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_student, parent, false);

        return new MyViewHolder(itemView);

    }
    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView emil;
        public TextView cuntry;


        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.txtName);
            emil = view.findViewById(R.id.txtEmail);
            cuntry = view.findViewById(R.id.txtCountry);

        }
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {


        final Student student = contactssList.get(position);

        holder.name.setText(student.getName());
        holder.emil.setText(student.getEmail());
        holder.cuntry.setText(student.getCountry());

        holder.itemView.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

               clik_item.Onrecived(student);
            }
        });

    }

    @Override
    public int getItemCount() {

        return contactssList.size();
    }

    public interface Clik_Item {
        void Onrecived(Student student);
    }

}

