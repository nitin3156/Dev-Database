package com.example.nitintonger.devdatabase;


import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText editText,editText2;
    private Toolbar toolbar;
    private FirebaseAuth auth;
    TextView textView9,textView10;
    ProgressDialog progressDialog;
    Button button,buttonn,buttoncard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        button=(Button)findViewById(R.id.button);
        //buttonn=(Button)findViewById(R.id.button6);
        editText=(EditText)findViewById(R.id.editText);
        buttoncard=(Button)findViewById(R.id.button8);
        editText2=(EditText)findViewById(R.id.editText2);
        progressDialog=new ProgressDialog(this);
        auth = FirebaseAuth.getInstance();
    }


   public void operation(final View view)
  {
        startActivity(new Intent(getApplicationContext(),overcast.class));
        String a=editText.getText().toString();
        String b=editText2.getText().toString();
        if(editText.length()==0)
        {
            editText.setError("Email Required!!");
        }
        else if(editText2.length()==0)
        {
            editText2.setError("Password Required!!");
        }
        else {
            editText2.setText("");
            progressDialog.setMessage("Please Wait..");
            progressDialog.show();
            auth.signInWithEmailAndPassword(a, b).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Login is successful..", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), overcast.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Please Try Again!!!", Toast.LENGTH_SHORT).show();
                    }
                    progressDialog.dismiss();
                }
            });
        }

  }
    public  void registration(View view1)
    {
        Intent intent1=new Intent(this,Registration.class);
        startActivity(intent1);
    }
    public void send_mail(View view)
    {
        if(editText.length()==0)
        {
            editText.setError("Enter Email!!!");
        }
        else {
            progressDialog.setMessage("Please Wait!!");
            progressDialog.show();
            auth.sendPasswordResetEmail(editText.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                            }

                            progressDialog.dismiss();
                        }
                    });
        }
    }
}
