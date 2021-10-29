package com.example.bill.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.bill.R;
import com.example.bill.base.lists.ListsViewModel;

public class DetailActivity extends AppCompatActivity {

    private TextView textViewDetailTitle;
    private TextView textViewDate;
    private TextView textViewListBill;
    private TextView textViewTotalBill;
    private TextView textViewTotalSumGuest;
    private TextView textViewNameBill;
    private ListsViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        int id = getIntent().getIntExtra("id", 0);
        model = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(ListsViewModel.class);
        textViewDate = findViewById(R.id.textViewDate);
        textViewDate.setText(String.valueOf(model.getListsList().get(id).getDate()));
        textViewDetailTitle = findViewById(R.id.textViewDetailTitle);
        String detailText = "Детальная страница заказа № " + id;
        textViewDetailTitle.setText(detailText);
        textViewListBill = findViewById(R.id.textViewListBill);
        textViewTotalBill = findViewById(R.id.textViewTotalBill);
        textViewTotalSumGuest = findViewById(R.id.textViewTotalSumGuest);
        textViewNameBill = findViewById(R.id.textViewNameBill);
        textViewNameBill.setText(String.valueOf(model.getListsList().get(id).getName()));
        textViewListBill.setText(getListBill(id));
        textViewTotalBill.setText(getListTotal(id));
        String totalSum = "Общая сумма: " + model.getListsList().get(id).getTotalSum();
        textViewTotalSumGuest.setText(totalSum);
    }

    public String getListBill(int id){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < model.getListsList().get(id).getBill().size(); i++) {
            sb.append(model.getListsList().get(id).getBill().get(i).getName())
                    .append(" - ")
                    .append(model.getListsList().get(id).getBill().get(i).getProdName())
                    .append(" - ")
                    .append(model.getListsList().get(id).getBill().get(i).getPrice())
                    .append(" руб.")
                    .append("\n");
        }
        return sb.toString();
    }
    public String getListTotal(int id){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < model.getListsList().get(id).getTotal().size(); i++) {
            sb.append(model.getListsList().get(id).getTotal().get(i).getName())
                    .append(" - ")
                    .append(model.getListsList().get(id).getTotal().get(i).getSum())
                    .append(" руб.")
                    .append("\n");
        }
        return sb.toString();
    }
}