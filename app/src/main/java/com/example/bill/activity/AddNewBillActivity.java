package com.example.bill.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bill.R;
import com.example.bill.adapter.guest.GuestAdapter;
import com.example.bill.base.bill.Bill;
import com.example.bill.base.bill.BillViewModel;

import java.util.ArrayList;


public class AddNewBillActivity extends AppCompatActivity {

    private EditText editNameProd;
    private EditText editPrice;
    private BillViewModel viewModel;
    private RecyclerView recyclerViewGuest;
    private GuestAdapter adapter;
    private String name;
    private ArrayList<String> guestName = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_bill);
        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(BillViewModel.class);
        recyclerViewGuest = findViewById(R.id.recycler_guest);
        recyclerViewGuest.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter = new GuestAdapter();
        guestName = getIntent().getStringArrayListExtra("guests");
        adapter.setGuests(guestName);
        recyclerViewGuest.setAdapter(adapter);
        editNameProd = findViewById(R.id.editTextNameProduct);
        editPrice = findViewById(R.id.editTextPrice);
        adapter.setOnClickListener(new GuestAdapter.OnClickListener() {
            @Override
            public void onClickPosition(int position) {
                name = adapter.getGuests().get(position);
                Toast.makeText(AddNewBillActivity.this, "name is " + name, Toast.LENGTH_SHORT).show();
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