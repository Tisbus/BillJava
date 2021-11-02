package com.example.bill.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bill.R;
import com.example.bill.adapter.guest.GuestAdapter;
import com.example.bill.base.bill.Bill;
import com.example.bill.base.bill.BillViewModel;
import com.example.bill.base.guest.Guest;
import com.example.bill.base.guest.GuestViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class AddNewBillActivity extends AppCompatActivity {

    private EditText editNameProd;
    private EditText editPrice;
    private BillViewModel viewModel;
    private GuestViewModel guestViewModel;
    private RecyclerView recyclerViewGuest;
    private GuestAdapter adapter;
    private String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_bill);
        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(BillViewModel.class);
        guestViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(GuestViewModel.class);
        recyclerViewGuest = findViewById(R.id.recycler_guest);
        recyclerViewGuest.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter = new GuestAdapter();
        getGuest();
        recyclerViewGuest.setAdapter(adapter);
        editNameProd = findViewById(R.id.editTextNameProduct);
        editPrice = findViewById(R.id.editTextPrice);
        adapter.setOnClickListener(new GuestAdapter.OnClickListener() {
            @Override
            public void onClickPosition(int position) {
                name = Objects.requireNonNull(guestViewModel.getGuest().getValue()).get(position).getName();
                Toast.makeText(AddNewBillActivity.this, "name is " + name, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getGuest(){
        LiveData<List<Guest>> liveGuest = guestViewModel.getGuest();
        liveGuest.observe(this, new Observer<List<Guest>>() {
            @Override
            public void onChanged(List<Guest> guests) {
                adapter.setGuests(guests);
            }
        });
    }

    public void onClickAddBill(View view) {
        String prod = editNameProd.getText().toString().trim();
        int price = Integer.parseInt(editPrice.getText().toString().trim());
        viewModel.insert(new Bill(name, prod, price));
        Intent goIntent = new Intent(this, MainActivity.class);
        startActivity(goIntent);
        finish();
    }
}