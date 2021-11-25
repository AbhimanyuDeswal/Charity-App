package com.abhimanyu.charity;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.abhimanyu.charity.activity.DonationActivity;

import java.util.List;

public class NGOAdapter extends RecyclerView.Adapter<NGOAdapter.NGOViewholder>{

    List<NGO> NGOList;

    public NGOAdapter(List<NGO> NGOList) {
        this.NGOList = NGOList;
    }

    @NonNull
    @Override
    public NGOViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ngo, parent, false);
        return new NGOViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NGOViewholder holder, int position) {
        NGO model = NGOList.get(position);
        holder.name.setText(model.getName());
        holder.cause.setText(model.getCause());
        holder.phone.setText(model.getPhone());
        holder.email.setText(model.getEmail());
        holder.location.setText(model.getLocation());

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DonationActivity.class);
                intent.putExtra("name", model.getName());
                intent.putExtra("cause", model.getCause());
                intent.putExtra("phone", model.getPhone());
                intent.putExtra("email", model.getEmail());
                intent.putExtra("location", model.getLocation());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return NGOList.size();
    }


    class NGOViewholder extends RecyclerView.ViewHolder {
        TextView name, cause, phone, email, location;
        CardView parentLayout;
        public NGOViewholder(@NonNull View itemView)
        {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            cause = itemView.findViewById(R.id.cause);
            phone = itemView.findViewById(R.id.phone);
            email = itemView.findViewById(R.id.email);
            location = itemView.findViewById(R.id.location);

            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
