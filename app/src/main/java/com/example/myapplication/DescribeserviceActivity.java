package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class DescribeserviceActivity extends AppCompatActivity {
    TextView em,descrip;
    String  val ;
    String serve;
    String id;
    String description;
    EditText review;
    FirebaseAuth auth;
    FirebaseFirestore fstore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        review = findViewById(R.id.rev);
        setContentView(R.layout.describeservice);
        em = findViewById(R.id.Review);
        auth  = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        descrip = findViewById(R.id.description);1
        val = getIntent().getStringExtra("email");1
        serve = getIntent().getStringExtra("serve");
        description = getIntent().getStringExtra("description");
        id = getIntent().getStringExtra("id");
        descrip.setText(description);
        em.setText(val);

    }

    public void Addreview(View view) {


    }
}