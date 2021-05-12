package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class DescribeserviceActivity extends AppCompatActivity {
    TextView em,descrip;
    String  val ;
    String serve;
    String id;
    String description;
    EditText review;
    FirebaseAuth auth;
    FirebaseFirestore fstore;
    ImageView i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        review = findViewById(R.id.rev);
        setContentView(R.layout.describeservice);
        em = findViewById(R.id.Review);
        auth  = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        descrip = findViewById(R.id.description);
        val = getIntent().getStringExtra("email");
        serve = getIntent().getStringExtra("serve");
        description = getIntent().getStringExtra("description");
        id = getIntent().getStringExtra("id");
        descrip.setText(description);
        em.setText(val);
        i = findViewById(R.id.i);
        Glide.with(DescribeserviceActivity.this).load("https://firebasestorage.googleapis.com/v0/b/auth-42912.appspot.com/o/2dNNMMaThRVDkgODp9yDVor1IyB2Driver?alt=media&token=d29aa93c-24d7-49e6-bef4-846122c90099").diskCacheStrategy(DiskCacheStrategy.NONE).into(i);



    }

    public void Addreview(View view) {


    }
}
