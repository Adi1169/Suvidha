package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class AppView extends AppCompatActivity {
    TextView email,name;
    FirebaseAuth auth;
    FirebaseFirestore fstore;
    String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_view);


    }

    public void logout(View view) {
     //   name = findViewById(R.id.username);
       // email = findViewById(R.id.email);
       // auth = FirebaseAuth.getInstance();
        //fstore = FirebaseFirestore.getInstance();
        //userId = auth.getCurrentUser().getUid();
        //final DocumentReference documentReference = fstore.collection("users").document(userId);
        //documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
          //  @Override
            //public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
              //  name.setText(value.getString("name"));
                //email.setText(value.getString("email"));
            //}
        //});


        //FirebaseAuth.getInstance().signOut();
        //startActivity(new Intent(getApplicationContext(),login.class));
        //finish();
    }

    public void cook(View view) {
        startActivity(new Intent(getApplicationContext(),cook.class));
    }

    public void giveservice(View view) {
        startActivity(new Intent(getApplicationContext(),regsevice.class));
    }
}

