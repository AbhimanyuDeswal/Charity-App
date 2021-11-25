package com.abhimanyu.charity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
    }

    @Override
    public int getItemCount() {
        return NGOList.size();
    }


    class NGOViewholder extends RecyclerView.ViewHolder {
        TextView name, cause, phone, email, location;
        public NGOViewholder(@NonNull View itemView)
        {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            cause = itemView.findViewById(R.id.cause);
            phone = itemView.findViewById(R.id.phone);
            email = itemView.findViewById(R.id.email);
            location = itemView.findViewById(R.id.location);
        }
    }
}
