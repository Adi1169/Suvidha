package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class cate extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseFirestore fstore;
    TextView email,name;
    String userId;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cate);
        intent = new Intent(cate.this,services.class);
    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),login.class));
        finish();
    }

    public void onregister(View view) {
        startActivity(new Intent(getApplicationContext(),regsevice.class));
    }

    public void sendService(View view) {
        switch (view.getId()){
            case R.id.Chef:
                Toast.makeText(cate.this,"Chef",Toast.LENGTH_SHORT).show();
                intent.putExtra("serviceName","Chef");
                startActivity(intent);
                break;
            case R.id.Driver:
                Toast.makeText(cate.this,"Driver",Toast.LENGTH_SHORT).show();
                intent.putExtra("serviceName","Driver");
                startActivity(intent);
                break;
            case R.id.Nurse:
                Toast.makeText(cate.this,"Nurse",Toast.LENGTH_SHORT).show();
                intent.putExtra("serviceName","Nurse");
                startActivity(intent);
                break;
            case R.id.Plumber:
                Toast.makeText(cate.this,"Plumber",Toast.LENGTH_SHORT).show();
                intent.putExtra("serviceName","Plumber");
                startActivity(intent);
                break;
            case R.id.Mechanic:
                Toast.makeText(cate.this,"Mechanic",Toast.LENGTH_SHORT).show();
                intent.putExtra("serviceName","Mechanic");
                startActivity(intent);
                break;

        }
    }
}