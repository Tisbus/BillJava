package com.example.bill.activity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.content.Intent;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import com.example.bill.R;
import com.example.bill.base.bill.Bill;
import com.example.bill.adapter.bill.BillAdapter;
import com.example.bill.base.bill.BillViewModel;
import com.example.bill.base.total.TotalViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BillAdapter adapter;
    private RecyclerView recyclerView;
    private BillViewModel viewModel;
    private TotalViewModel totalViewModel;
    private FloatingActionButton floatAddTotal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(BillViewModel.class);
        totalViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(TotalViewModel.class);
        recyclerView = findViewById(R.id.recyclerViewBill);
        adapter = new BillAdapter();
        getData();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        floatAddTotal = findViewById(R.id.floatTotalSummary);
    }

    public void onClickNewAddBill(View view) {
        Intent intentGet = new Intent(this, AddNewBillActivity.class);
        startActivity(intentGet);
    }

    public void getData(){
        LiveData<List<Bill>> allBill = viewModel.getBill();
        allBill.observe(this, new Observer<List<Bill>>(){
            @Override
            public void onChanged(@Nullable List<Bill> bills) {
                adapter.setBillList(bills);
                if(bills.size() > 0){
                    floatAddTotal.setVisibility(View.VISIBLE);
                }else{
                    floatAddTotal.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    public void onclickTotalSummary(View view) {
        /*delete total pered summary*/
        totalViewModel.deleteAll();
        Intent intentGo = new Intent(this, SummaryActivity.class);
        startActivity(intentGo);
    }
}