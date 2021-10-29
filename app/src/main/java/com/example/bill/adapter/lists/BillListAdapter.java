package com.example.bill.adapter.lists;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bill.R;
import com.example.bill.base.lists.ListsAll;

import java.util.ArrayList;
import java.util.List;

public class BillListAdapter extends RecyclerView.Adapter<BillListAdapter.BillListHolder> {
    private List<ListsAll> listsAll;

    public void setListsAll(List<ListsAll> listsAll) {
        this.listsAll = listsAll;
        notifyDataSetChanged();
    }

    private OnClickListener onClickListener;

    public interface OnClickListener{
        void onClickPosition(int position);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public BillListAdapter() {
        this.listsAll = new ArrayList<>();
    }

    public List<ListsAll> getListsAll() {
        return listsAll;
    }

    @Override
    public BillListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_bill_item, parent, false);
        return new BillListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BillListHolder holder, int position) {
        ListsAll lists = listsAll.get(position);
        holder.textViewDate.setText(lists.getDate());
        holder.textViewName.setText(lists.getName());
        holder.textViewTotalSum.setText(String.valueOf(lists.getTotalSum()));
    }

    @Override
    public int getItemCount() {
        return listsAll.size();
    }

    class BillListHolder extends RecyclerView.ViewHolder{

        private TextView textViewDate;
        private TextView textViewName;
        private TextView textViewTotalSum;

        public BillListHolder(@NonNull View itemView) {
            super(itemView);
            textViewDate = itemView.findViewById(R.id.textViewDate);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewTotalSum = itemView.findViewById(R.id.textViewTotalSum);
            if(onClickListener != null){
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onClickListener.onClickPosition(getAdapterPosition());
                    }
                });
            }
        }
    }
}
