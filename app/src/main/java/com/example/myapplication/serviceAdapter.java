package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class serviceAdapter extends FirestoreRecyclerAdapter <ServiceModel,serviceAdapter.serviceHolder>{
    Context context;
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public serviceAdapter(@NonNull FirestoreRecyclerOptions<ServiceModel> options) {
        super(options);

    }

    @Override
    protected void onBindViewHolder(@NonNull serviceHolder serviceHolder, int i, @NonNull final ServiceModel serviceModel) {
       // Picasso.with(context).load("https://firebasestorage.googleapis.com/v0/b/auth-42912.appspot.com/o/2dNNMMaThRVDkgODp9yDVor1IyB2Driver?alt=media&token=d29aa93c-24d7-49e6-bef4-846122c90099").into(serviceHolder.profile);
       // Glide.with(context).load("https://firebasestorage.googleapis.com/v0/b/auth-42912.appspot.com/o/2dNNMMaThRVDkgODp9yDVor1IyB2Driver?alt=media&token=d29aa93c-24d7-49e6-bef4-846122c90099").into(serviceHolder.profile);
        serviceHolder.firephone.setText(serviceModel.getPhone());
        serviceHolder.firename.setText(serviceModel.getName());

        serviceHolder.fireemail.setText(serviceModel.getEmail());

        Intent intent;
         final String email = serviceModel.getEmail().toString();
        final String service = serviceModel.getService().toString();
        final String id = serviceModel.getId().toString();
        final int rating = serviceModel.getRating();
        final String description = serviceModel.getDescription().toString();
        final String phone = serviceModel.getPhone().toString();
        final String name = serviceModel.getName().toString();
        final  String username = serviceModel.getUsername().toString();
        final String imageUrl = serviceModel.getImageUrl().toString();

        serviceHolder.More.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),profilepage.class);
                intent.putExtra("email",email);
                intent.putExtra("service",service);
                intent.putExtra("id",id);
                intent.putExtra("rating",rating);
                intent.putExtra("description",description);
                intent.putExtra("phone",phone);
                intent.putExtra("name",name);
                intent.putExtra("username",username);
                intent.putExtra("imageUrl",imageUrl);
                view.getContext().startActivity(intent);




            }
        });

    }

    @NonNull
    @Override
    public serviceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.service_item_single,parent,false);
        return new serviceHolder(v);
    }

    class serviceHolder extends RecyclerView.ViewHolder{
        TextView fireinfo,firename,fireemail,firephone;

        ImageView More,profile;
        public serviceHolder(@NonNull View itemView) {
            super(itemView);
            fireemail = itemView.findViewById(R.id.fireemail);
            firename = itemView.findViewById(R.id.firename);
            firephone = itemView.findViewById(R.id.firephone);
            More = itemView.findViewById(R.id.More);
            profile = itemView.findViewById(R.id.prof);



        }
    }
}
