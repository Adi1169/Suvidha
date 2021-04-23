package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class services extends AppCompatActivity {
    String  val ;
    RecyclerView recyclerView;
    FirebaseFirestore firebaseFirestore;
    private CollectionReference serviceref;
    private FirestoreRecyclerAdapter adapter;
    private serviceAdapter serviceAdapter;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

         setContentView(R.layout.activity_services);
        val = getIntent().getStringExtra("serviceName");
        firebaseFirestore = FirebaseFirestore.getInstance();
        serviceref = firebaseFirestore.collection(val);
        setupRecyclerView();




    }

    private void setupRecyclerView() {
        Query query = serviceref.orderBy("name",Query.Direction.DESCENDING);
        FirestoreRecyclerOptions options = new FirestoreRecyclerOptions.Builder<ServiceModel>().setQuery(query,ServiceModel.class).build();
        serviceAdapter= new serviceAdapter(options);
        recyclerView = findViewById(R.id.recycleservice);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(serviceAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        serviceAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        serviceAdapter.stopListening();
    }
}