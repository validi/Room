package com.aurora.a3_17homeworkexercise1.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.aurora.a3_17homeworkexercise1.db.entity.Student;

@Database(entities = {Student.class},version = 1)
public abstract class StudentAppDataBase extends RoomDatabase {

    public abstract StudentDAO getStudentDAO();
}
