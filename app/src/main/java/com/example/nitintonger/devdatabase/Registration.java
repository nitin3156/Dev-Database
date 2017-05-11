package com.example.nitintonger.devdatabase;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Registration extends AppCompatActivity {
    EditText text3, text4;
    Button buttonS;
    private Toolbar toolbar;
    private FirebaseAuth auth;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        progressDialog=new ProgressDialog(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        auth = FirebaseAuth.getInstance();
        text3 = (EditText) findViewById(R.id.editText);
        text4 = (EditText) findViewById(R.id.editText2);
        buttonS = (Button) findViewById(R.id.button);


    }

    public void button_submit(View view) {
        String a=text3.getText().toString();
        String b=text4.getText().toString();
        text3.setText("");text4.setText("");

        progressDialog.setMessage("Registering User...Please Wait.");
        progressDialog.show();
        auth.createUserWithEmailAndPassword(a,b).addOnCompleteListener(Registration.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    //display some message here
                    Toast.makeText(getApplicationContext(),"Successfully registered",Toast.LENGTH_LONG).show();
                }else{
                    //display some message here
                    Toast.makeText(getApplicationContext(),"Registration Error Try Again!",Toast.LENGTH_LONG).show();
                }
                progressDialog.dismiss();
            }
        });


    }
}
