package com.abhimanyu.charity;

import static java.text.DateFormat.getDateTimeInstance;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    List<History> historyList;

    public HistoryAdapter(List<History> historyList) {
        this.historyList = historyList;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.history, parent, false);

        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.HistoryViewHolder holder, int position) {
        History history = historyList.get(position);
        holder.name.setText(history.getName());
        holder.donate.setText(history.getDonate());
        holder.amount.setText(history.getAmount());
        Map<String, Long> timestamp;
        timestamp = history.getTimestamp();
        String time;
        time = getTimeDate(timestamp.get("timestamp"));
        holder.timestamp.setText(time);

    }

    public static String getTimeDate(long timestamp){
        try{
            DateFormat dateFormat = getDateTimeInstance();
            Date netDate = (new Date(timestamp));
            return dateFormat.format(netDate);
        } catch(Exception e) {
            return "date";
        }
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    class HistoryViewHolder extends RecyclerView.ViewHolder {
        TextView name, donate, amount, timestamp;
        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameHistory);
            donate = itemView.findViewById(R.id.donateHistory);
            amount = itemView.findViewById(R.id.amountHistory);
            timestamp = itemView.findViewById(R.id.timestampHistory);

        }
    }
}
