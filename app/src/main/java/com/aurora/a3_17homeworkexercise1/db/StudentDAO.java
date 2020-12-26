package com.aurora.a3_17homeworkexercise1.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.aurora.a3_17homeworkexercise1.db.entity.Student;

import java.util.List;

@Dao
public interface StudentDAO  {

    @Insert
    public long addStudent(Student student);

    @Delete
    public void deleteStudent(Student student);

    @Update
    public void updateStudent(Student student);

    @Query("select * from Students")
    public List<Student>getStident();

    @Query("select * from Students where id==:id")
    public Student getStidentById(int id);

}
