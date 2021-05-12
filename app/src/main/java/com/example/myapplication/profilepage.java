package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class profilepage extends AppCompatActivity {
    String emailt;
    String phonet;
    String namet;
    String detailst;
    String urlt;
    String description;
    TextView name,email,phone,details;
    FirebaseAuth auth;
    FirebaseFirestore fstore;
    ImageView prof;
    String serve;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilepage);
       name = findViewById(R.id.name);
       email = findViewById(R.id.email);
       phone = findViewById(R.id.phone);
       details = findViewById(R.id.details);
       prof = findViewById(R.id.prof);

        auth  = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        emailt = getIntent().getStringExtra("email");

         serve = getIntent().getStringExtra("service");
        detailst = getIntent().getStringExtra("description");
        String id = getIntent().getStringExtra("id");
        namet = getIntent().getStringExtra("name");
        phonet = getIntent().getStringExtra("phone");
        emailt = getIntent().getStringExtra("email");
         urlt = getIntent().getStringExtra("imageUrl");
        details.setText(detailst);
        name.setText(namet);
        phone.setText(phonet);
        email.setText(emailt);


        Glide.with(profilepage.this).load(urlt).diskCacheStrategy(DiskCacheStrategy.NONE).into(prof);

    }

    public void back(View view) {
        Intent intent = new Intent(profilepage.this, services.class);
        intent.putExtra("serviceName",serve);
        startActivity(intent);

    }
}