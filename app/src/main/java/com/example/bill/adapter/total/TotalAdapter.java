package com.example.bill.adapter.total;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bill.R;
import com.example.bill.base.total.Total;

import java.util.ArrayList;

public class TotalAdapter extends RecyclerView.Adapter<TotalAdapter.TotalViewHolder> {

    private ArrayList<Total> totals;

    public TotalAdapter() {
        this.totals = new ArrayList();
    }

    public ArrayList<Total> getTotals() {
        return totals;
    }

    public void setTotals(ArrayList<Total> totals) {
        this.totals = totals;
        notifyDataSetChanged();
    }

    @Override
    public TotalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.total_item, parent, false);
        return new TotalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TotalViewHolder holder, int position) {
        Total total = getTotals().get(position);
        holder.textViewName.setText(total.getName());
        holder.textViewPrice.setText(String.valueOf(total.getSum()));
    }

    @Override
    public int getItemCount() {
        return totals.size();
    }

    class TotalViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewName;
        private TextView textViewPrice;

        public TotalViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewN);
            textViewPrice = itemView.findViewById(R.id.textViewT);
        }
    }
}
