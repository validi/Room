package com.aurora.a3_17homeworkexercise1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.aurora.a3_17homeworkexercise1.Recycler.StudentsAdapter;
import com.aurora.a3_17homeworkexercise1.db.StudentAppDataBase;
import com.aurora.a3_17homeworkexercise1.db.entity.Student;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fab;
    RecyclerView recyclerView;
    StudentAppDataBase studentAppDataBase;
   ArrayList <Student> students=new ArrayList<>();
   StudentsAdapter studentsAdapter;
   int index_click_student=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      init();

      initRecycler();

    }

    private void initRecycler() {
        recyclerView= (RecyclerView) findViewById(R.id.recycler);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        students.addAll(studentAppDataBase.getStudentDAO().getStident()) ;
        studentsAdapter=new StudentsAdapter(this, students, new StudentsAdapter.Clik_Item() {
            @Override
            public void Onrecived(Student student) {
                index_click_student=students.indexOf(student);
                Intent intent=new Intent(MainActivity.this,StudentRegister.class);
                Toast.makeText(MainActivity.this, student.getId()+"", Toast.LENGTH_SHORT).show();
                intent.putExtra("id",student.getId()+"");
                intent.putExtra("name",student.getName()+"");
                intent.putExtra("email",student.getEmail()+"");
                intent.putExtra("country",student.getCountry()+"");

                startActivityForResult(intent,100);

            }
        });
        recyclerView.setAdapter(studentsAdapter);

    }

    private void init() {

        //////////
        fab= (FloatingActionButton) findViewById(R.id.floatingActionButton);
        /////////////
        studentAppDataBase= Room.databaseBuilder(this,StudentAppDataBase.class,"StudentDB")
                .allowMainThreadQueries()
                .build();

    }

    public void onClick(View view) {
        switch (view.getId()){
           case  R.id.floatingActionButton:

               Intent intent=new Intent(MainActivity.this,StudentRegister.class);

               intent.putExtra("id","0");
               startActivityForResult(intent,100);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100){
            if(resultCode == Activity.RESULT_OK){
                String state=data.getStringExtra("state");
                Student student=new Student(Integer.parseInt(data.getStringExtra("id"))
                        ,data.getStringExtra("name")
                        , data.getStringExtra("email")
                        , data.getStringExtra("country")
                );
                if(state.equals("insert"))
                {

                  long id= studentAppDataBase.getStudentDAO().addStudent(student);
                  if(id>0){
                      student.setId((int) id);
                      students.add(student);

                  }

                }
                else if(state.equals("update")){
                    studentAppDataBase.getStudentDAO().updateStudent(student);
                   // students.remove(click_student);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        students.remove(index_click_student);
                        students.add(index_click_student,student);
                    }
                }else if(state.equals("delete")){
                    studentAppDataBase.getStudentDAO().deleteStudent(student);
                    students.remove(index_click_student);
                }
                studentsAdapter.notifyDataSetChanged();
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }
}