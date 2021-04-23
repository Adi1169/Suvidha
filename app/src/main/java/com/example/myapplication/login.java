
package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;



public class login extends AppCompatActivity {
    EditText email,password;
    Button login;
    TextView registerpage,forgot;
    ProgressBar pbar;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {                                                                                                                                                                                        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.Review);
        password = findViewById(R.id.pword);
        login = findViewById(R.id.Register);
        registerpage = findViewById(R.id.registerpage);

        pbar = findViewById(R.id.progressBar);
        auth  = FirebaseAuth.getInstance();
    }
    public void logged(View view) {
        Toast.makeText(login.this,"i am in", Toast.LENGTH_SHORT).show();
        pbar.setVisibility(view.VISIBLE);
        String memail = email.getText().toString().trim();
        String mpassword =  password.getText().toString().trim();
        if(TextUtils.isEmpty(memail)){
            email.setError("Email is required");
            pbar.setVisibility(view.INVISIBLE);
            return;
        }
        if(TextUtils.isEmpty(mpassword)){
            password.setError("Password is required");
            pbar.setVisibility(view.INVISIBLE);
            return;
        }
        if(mpassword.length()<6){
            password.setError("Atleast 6 character is required");
            pbar.setVisibility(view.INVISIBLE);
            return;
        }
        auth.signInWithEmailAndPassword(memail,mpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(login.this,"successfully logged in", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),AppView.class));
                    finish();
                }
                else{
                    Toast.makeText(login.this,"Error!"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void onregister(View view) {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
    }



}



