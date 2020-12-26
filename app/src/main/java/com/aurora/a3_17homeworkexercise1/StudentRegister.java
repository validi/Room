package com.aurora.a3_17homeworkexercise1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class StudentRegister extends AppCompatActivity {

    TextInputEditText edtName,edtEmail,edtCountry;
    Button btnSave,btnCancelOrDelete;
    String id="0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_register2);
        init();
        id=getIntent().getStringExtra("id");
        Toast.makeText(this, id+"", Toast.LENGTH_SHORT).show();
        if(!id.equals("0")){
            btnSave.setText("Update");
            btnCancelOrDelete.setText("Delete");
            edtEmail.setText(getIntent().getStringExtra("email"));
            edtName.setText(getIntent().getStringExtra("name"));
            edtCountry.setText(getIntent().getStringExtra("country"));
        }

    }

    private void init() {
        edtCountry= (TextInputEditText) findViewById(R.id.edtCountry);
        edtName= (TextInputEditText) findViewById(R.id.edtName);
        edtEmail= (TextInputEditText) findViewById(R.id.edtEmail);
        //////////
        btnSave= (Button) findViewById(R.id.btnSave);
        btnCancelOrDelete= (Button) findViewById(R.id.btnCancel);

    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSave:
                if(id.equals("0")){
                    check("insert");
                }
                else {
                    check("update");
                }

                break;
            case R.id.btnCancel:
                if(id.equals("0")){
                    Intent returnIntent = new Intent();
                    setResult(Activity.RESULT_CANCELED, returnIntent);
                    finish();
                    break;
                }
                else {
                    check("delete");
                }

        }
    }
    public void check(String state){
        if(edtName.getText().toString().equals("")){
            edtName.setError("نباید خالی باشد");
        }else if(edtEmail.getText().toString().equals("")){
            edtEmail.setError("نباید خالی باشد");
        }else if(edtCountry.getText().toString().equals("")){
            edtCountry.setError("نباید خالی باشد");
        }
        else {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("state",state);
            returnIntent.putExtra("name",edtName.getText().toString());
            returnIntent.putExtra("email",edtEmail.getText().toString());
            returnIntent.putExtra("country",edtCountry.getText().toString());
            returnIntent.putExtra("id",id);
            setResult(Activity.RESULT_OK,returnIntent);
            finish();
        }
    }
}