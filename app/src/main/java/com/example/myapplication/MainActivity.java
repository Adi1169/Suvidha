package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class
MainActivity extends AppCompatActivity {
    EditText name,email,password,pwordagain;
    Button register;
    TextView loginpage;
    ProgressBar pbar;
    FirebaseAuth auth;
    FirebaseFirestore fstore;
    String UserID,use;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        email = findViewById(R.id.Review);
        password = findViewById(R.id.apword);
        pwordagain = findViewById(R.id.pword);
        register = findViewById(R.id.register);
        loginpage = findViewById(R.id.loged);
        pbar = findViewById(R.id.progressBar);
        auth  = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        if(auth.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(),AppView.class));
            finish();
        }

    }

    public void Register(View view) {
        pbar.setVisibility(view.VISIBLE);
        final String memail = email.getText().toString().trim();
        String mpassword =  password.getText().toString().trim();
        String retypepassword =  pwordagain.getText().toString().trim();

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
        if(!retypepassword.matches(mpassword)){
            pwordagain.setError("Doesn't match the above password"+retypepassword+" "+mpassword);
            pbar.setVisibility(view.INVISIBLE);
            return;
        }

        auth.createUserWithEmailAndPassword(memail,mpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete( Task<AuthResult> task) {
                if(task.isSuccessful()){
                    use = name.getText().toString().trim();
                    Toast.makeText(MainActivity.this,"Registered Successfully",Toast.LENGTH_SHORT).show();
                    UserID= auth.getCurrentUser().getUid();
                    DocumentReference documentReference =fstore.collection("users").document(UserID);
                    Map<String,Object>user = new HashMap<>();
                    user.put("name",use);
                    user.put("email",memail);

                    documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d("TAG","onSuccess: user profile is created for "+ UserID);
                        }
                    });
                    startActivity(new Intent(getApplicationContext(),login.class));
                }
                else{
                    Toast.makeText(MainActivity.this,"Error!"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void enterlogin(View view) {
        startActivity(new Intent(getApplicationContext(),login.class));
        finish();
    }
}
