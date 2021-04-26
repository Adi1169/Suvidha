package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

public class regsevice extends AppCompatActivity {
    RadioGroup radioGroup;
    RadioButton radioButton;
    String selectedTask="";
    FirebaseAuth Auth;
    FirebaseFirestore fstore;
    FirebaseStorage fire;
    EditText phone,description;
    String userId;
    String name,email;
    Button Register;
    ImageView profile;
    TextView browse;
    Uri filepath;
    Bitmap bitmap;
    int temp=0;


    int rating=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regsevice);
        profile=(ImageView) findViewById(R.id.profile);
        browse = (TextView) findViewById(R.id.browse);

        Auth = FirebaseAuth.getInstance();
        fire = FirebaseStorage.getInstance();
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
        if(temp!=1){
            browse.setError("Profile Picture is required");
        }
        DocumentReference documentReference =fstore.collection(selectedTask).document(userId);
        uploadtofirebase();

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
        startActivity(new Intent(getApplicationContext(),cate.class));
    }

    private void uploadtofirebase() {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("File Uploader");
        dialog.show();
        StorageReference uploader = fire.getReference().child(userId+selectedTask);
        uploader.putFile(filepath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                dialog.dismiss();
                Toast.makeText(regsevice.this,"file uploaded",Toast.LENGTH_SHORT).show();

            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                float percent = (100*snapshot.getBytesTransferred())/snapshot.getTotalByteCount();
                dialog.setMessage("Uploaded"+(int)percent+"%");
            }
        });
    }

    public void type(View view) {
        int radioid = radioGroup.getCheckedRadioButtonId();
        radioButton= findViewById(radioid);
        selectedTask= radioButton.getText().toString();
        Toast.makeText(regsevice.this,"the"+selectedTask,Toast.LENGTH_SHORT).show();


    }

    public void tocate(View view) {
        startActivity(new Intent(getApplicationContext(),cate.class));
    }

    public void permission(View view) {
        Dexter.withActivity(regsevice.this).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE).withListener(new PermissionListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
            @Override
            public void onPermissionGranted(PermissionGrantedResponse response) {
               Intent intent = new Intent(Intent.ACTION_PICK);
               intent.setType("image/*");
               startActivityForResult(Intent.createChooser(intent,"Please select Image"),1);
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse response) {

            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                token.continuePermissionRequest();
            }
        }).check();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @androidx.annotation.Nullable Intent data) {
        if(requestCode==1 && resultCode==RESULT_OK){
            filepath=data.getData();
            try{
                Toast.makeText(regsevice.this,"got it",Toast.LENGTH_SHORT).show();

                InputStream inputStream =getContentResolver().openInputStream(filepath);
                bitmap = BitmapFactory.decodeStream(inputStream);
                profile.setImageBitmap(bitmap);
                temp=1;
            }catch (Exception ex){

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}