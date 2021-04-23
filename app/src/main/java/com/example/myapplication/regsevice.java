package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

public class regsevice extends AppCompatActivity {
    RadioGroup radioGroup;
    RadioButton radioButton;
    String selectedTask="";
    FirebaseAuth Auth;
    FirebaseFirestore fstore;
    EditText phone,description;
    String userId;
    String name,email;
    Button Register;
    int rating=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regsevice);
        Auth = FirebaseAuth.getInstance();

        fstore = FirebaseFirestore.getInstance();
        phone = findViewById(R.id.phone);
        description = findViewById(R.id.description);
        radioGroup = findViewById(R.id.radiogroup);
        Register = findViewById(R.id.Register);
        userId = Auth.getCurrentUser().getUid();
        Toast.makeText(regsevice.this,userId,Toast.LENGTH_SHORT).show();
        DocumentReference dRefer =fstore.collection("users").document(userId);
        dRefer.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable  DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                name= value.getString("name");
                email= value.getString("email");
                Toast.makeText(regsevice.this,name+"i am in "+email,Toast.LENGTH_SHORT).show();
            }
        });
        if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(email)){
            Register.setClickable(true);
        }
    }

    public void onregister(View view) {

        String pho = phone.getText().toString().trim();
        String info = description.getText().toString().trim();
        if(TextUtils.isEmpty(pho)){
            phone.setError("phone no is required");
            return;
        }
        if(TextUtils.isEmpty(info)){
            description.setError("description required");
            return;
        }
        DocumentReference documentReference =fstore.collection(selectedTask).document(userId);
        Map<String,Object> user = new HashMap<>();





       Toast.makeText(regsevice.this," the "+name+" and "+email+" i am out"+userId,Toast.LENGTH_SHORT).show();
        user.put("name",name);
        user.put("email",email);
        user.put("phone",pho);
        user.put("description",info);
        user.put("rating",rating);
        user.put("service",selectedTask);
        user.put("id",userId);

        documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d("TAG","onSuccess: user profile is created for "+ userId);
            }
        });

        Toast.makeText(regsevice.this,"added successfully",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(),AppView.class));
    }

    public void type(View view) {
        int radioid = radioGroup.getCheckedRadioButtonId();
        radioButton= findViewById(radioid);
        selectedTask= radioButton.getText().toString();
        Toast.makeText(regsevice.this,"the"+selectedTask,Toast.LENGTH_SHORT).show();


    }

}