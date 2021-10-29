package com.example.bill.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bill.R;
import com.example.bill.adapter.lists.BillListAdapter;
import com.example.bill.base.lists.ListsViewModel;

public class ListBillActivity extends AppCompatActivity {

    private RecyclerView recyclerViewListBill;
    private ListsViewModel viewModel;
    private BillListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_bill);
        recyclerViewListBill = findViewById(R.id.recyclerViewListBill);
        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(ListsViewModel.class);
        adapter = new BillListAdapter();
        getData();
        recyclerViewListBill.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewListBill.setAdapter(adapter);
        adapter.setOnClickListener(new BillListAdapter.OnClickListener() {
            @Override
            public void onClickPosition(int position) {
                int id = adapter.getListsAll().get(position).getId();
                getDetail(id);
                Toast.makeText(ListBillActivity.this, "id is " + id, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getData() {
        adapter.setListsAll(viewModel.getListsList());
    }

    public void getDetail(int position) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("id", position);
        startActivity(intent);
    }

}