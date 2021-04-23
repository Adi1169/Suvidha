package com.example.myapplication;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class serviceAdapter extends FirestoreRecyclerAdapter <ServiceModel,serviceAdapter.serviceHolder>{
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

        serviceHolder.firephone.setText(serviceModel.getPhone());
        serviceHolder.firename.setText(serviceModel.getName());
        serviceHolder.fireinfo.setText(serviceModel.getDescription());
        serviceHolder.fireemail.setText(serviceModel.getEmail());
        Intent intent;
         final String email = serviceModel.getEmail().toString();
        final String service = serviceModel.getService().toString();
        final String id = serviceModel.getId().toString();
        final int rating = serviceModel.getRating();
        final String description = serviceModel.getDescription().toString();
        final String phone = serviceModel.getPhone().toString();
        final String name = serviceModel.getName().toString();

        serviceHolder.More.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),DescribeserviceActivity.class);
                intent.putExtra("email",email);
                intent.putExtra("service",service);
                intent.putExtra("id",id);
                intent.putExtra("rating",rating);
                intent.putExtra("description",description);
                intent.putExtra("phone",phone);
                intent.putExtra("name",name);
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
        Button More;
        public serviceHolder(@NonNull View itemView) {
            super(itemView);
            fireemail = itemView.findViewById(R.id.fireemail);
            fireinfo = itemView.findViewById(R.id.fireinfo);
            firename = itemView.findViewById(R.id.firename);
            firephone = itemView.findViewById(R.id.firephone);
            More = itemView.findViewById(R.id.More);
        }
    }
}
