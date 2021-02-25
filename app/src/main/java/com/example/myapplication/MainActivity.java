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

public class MainActivity extends AppCompatActivity {
    EditText name,email,password,pwordagain;
    Button register;
    TextView loginpage;
    ProgressBar pbar;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.apword);
        pwordagain = findViewById(R.id.pword);
        register = findViewById(R.id.register);
        loginpage = findViewById(R.id.loged);
        pbar = findViewById(R.id.progressBar);
        auth  = FirebaseAuth.getInstance();
        if(auth.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(),AppView.class));
            finish();
        }

    }

    public void Register(View view) {
        String memail = email.getText().toString().trim();
        String mpassword =  password.getText().toString().trim();
        String retypepassword =  pwordagain.getText().toString().trim();
        if(TextUtils.isEmpty(memail)){
            email.setError("Email is required");
            return;
        }
        if(TextUtils.isEmpty(mpassword)){
            password.setError("Password is required");
            return;
        }
        if(mpassword.length()<6){
            password.setError("Atleast 6 character is required");
            return;
        }
        if(!                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    retypepassword.matches(mpassword)){
            pwordagain.setError("Doesn't match the above password"+retypepassword+" "+mpassword);
            return;
        }
       pbar.setVisibility(view.VISIBLE);
        auth.createUserWithEmailAndPassword(memail,mpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete( Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this,"Registered Successfully",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),AppView.class));
                }
                else{
                    Toast.makeText(MainActivity.this,"Error!"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
