package com.example.bill.adapter.guest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bill.R;

import java.util.ArrayList;

public class GuestAdapter extends RecyclerView.Adapter<GuestAdapter.GuestViewHolder> {

    private ArrayList<String> guests;

    public GuestAdapter() {
        this.guests = new ArrayList<>();
    }

    public ArrayList<String> getGuests() {
        return guests;
    }

    public void setGuests(ArrayList<String> guests) {
        this.guests = guests;
        notifyDataSetChanged();
    }

    private OnClickListener onClickListener;

    public interface OnClickListener{
        void onClickPosition(int position);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public GuestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.guest_item, parent, false);
        return new GuestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GuestViewHolder holder, int position) {
        holder.textViewGuest.setText(guests.get(position));
    }

    @Override
    public int getItemCount() {
        return guests.size();
    }

    class GuestViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewGuest;
        public GuestViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewGuest = itemView.findViewById(R.id.textViewGuest);

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
