package com.example.bill.adapter.bill;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bill.R;
import com.example.bill.base.bill.Bill;

import java.util.ArrayList;
import java.util.List;

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.BillViewHolder> {

    private List<Bill> billList;

    public void setBillList(List<Bill> billList) {
        this.billList = billList;
        notifyDataSetChanged();
    }

    public List<Bill> getBillList() {
        return billList;
    }

    public BillAdapter() {
        this.billList = new ArrayList<>();
    }

    @NonNull
    @Override
    public BillViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.bill_item, viewGroup, false);
        return new BillViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BillViewHolder billViewHolder, int i) {
        Bill bill = billList.get(i);
        billViewHolder.textViewName.setText(bill.getName());
        billViewHolder.textViewProdName.setText(bill.getProdName());
        billViewHolder.textViewPrice.setText(String.valueOf(bill.getPrice()));
    }

    @Override
    public int getItemCount() {
        return billList.size();
    }

    class BillViewHolder extends RecyclerView.ViewHolder{

        private TextView textViewName;
        private TextView textViewProdName;
        private TextView textViewPrice;

        public BillViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewProdName = itemView.findViewById(R.id.textViewProdName);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
        }
    }
}
