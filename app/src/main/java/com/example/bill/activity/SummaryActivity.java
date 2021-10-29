package com.example.bill.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bill.R;
import com.example.bill.adapter.total.TotalAdapter;
import com.example.bill.base.bill.BillViewModel;
import com.example.bill.base.lists.ListsAll;
import com.example.bill.base.lists.ListsViewModel;
import com.example.bill.base.total.Total;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SummaryActivity extends AppCompatActivity {

    private BillViewModel viewModel;
    private ListsViewModel viewListModel;
    private TotalAdapter adapter;
    private RecyclerView recyclerView;
    private TextView textViewSum;
    private CheckBox checkBoxTip;
    private ConstraintLayout layoutTip;
    private ConstraintLayout constraintLayout;
    private TextView textViewQtyTip;
    private TextView textViewSumWithTip;
    private Button buttonAddTip;
    private EditText editTextTipQty;
    private EditText editTextNameBill;
    private ArrayList<Total> totals;
    int sum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        textViewSum = findViewById(R.id.textViewSum);
        layoutTip = findViewById(R.id.layoutTip);
        layoutTip.setVisibility(View.INVISIBLE);
        constraintLayout = findViewById(R.id.constrainLayout);
        constraintLayout.setVisibility(View.INVISIBLE);
        textViewQtyTip = findViewById(R.id.textViewQtyTip);
        textViewSumWithTip = findViewById(R.id.textViewSumWithTip);
        buttonAddTip = findViewById(R.id.buttonAddTip);
        editTextTipQty = findViewById(R.id.editTextTipQty);
        editTextNameBill = findViewById(R.id.editTextNameBill);
        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(BillViewModel.class);
        viewListModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(ListsViewModel.class);
        adapter = new TotalAdapter();
        recyclerView = findViewById(R.id.recyclerViewTotal);
        getData();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        checkBoxTip = findViewById(R.id.checkBoxTip);
        checkBoxTip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(checkBoxTip.isChecked()){
                    layoutTip.setVisibility(View.VISIBLE);
                }else{
                    layoutTip.setVisibility(View.INVISIBLE);
                }
            }
        });
        buttonAddTip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int editTip;
                int tipSum;
                if(editTextTipQty.getText().length() == 0){
                    Toast.makeText(SummaryActivity.this, "введите процент чаевых", Toast.LENGTH_SHORT).show();
                }else{
                    editTip = Integer.parseInt(String.valueOf(editTextTipQty.getText()));
                    tipSum = Integer.parseInt(String.valueOf(textViewSum.getText()));
                    sum = (tipSum * editTip) / 100 + tipSum;
                    int sumTip =  (tipSum * editTip) / 100;
                    textViewQtyTip.setText(String.valueOf(sumTip));
                    textViewSumWithTip.setText(String.valueOf(sum));
                    constraintLayout.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    public void getData(){
        totals = new ArrayList<>();

        String tmpName = "";
        String name = "";
        sum = 0;
        int summary = 0;

        if(viewModel.getBillList().size() > 0){
            for (int i = 0; i < viewModel.getBillList().size(); i++) {

                if(i == 0){
                    sum += viewModel.getBillList().get(i).getPrice();
                    summary += viewModel.getBillList().get(i).getPrice();
                    name = viewModel.getBillList().get(i).getName();
                }else if(i == viewModel.getBillList().size()-1){
                    sum += viewModel.getBillList().get(i).getPrice();
                    summary += viewModel.getBillList().get(i).getPrice();
                    totals.add(new Total(tmpName, sum));
                }else{
                    if (viewModel.getBillList().get(i).getName().equals(name)) {
                        sum += viewModel.getBillList().get(i).getPrice();
                        summary += viewModel.getBillList().get(i).getPrice();
                        tmpName = name;
                    } else {
                        totals.add(new Total(tmpName, sum));
                        sum = 0;
                        tmpName = "";
                        sum += viewModel.getBillList().get(i).getPrice();
                        summary += viewModel.getBillList().get(i).getPrice();
                        name = viewModel.getBillList().get(i).getName();
                        tmpName = name;
                    }
                }
            }
        }
        adapter.setTotals(totals);
        textViewSum.setText(String.valueOf(summary));
    }


    public void onClickAddBillFinish(View view) {
        String nameDate = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(new Date());
        String name = String.valueOf(editTextNameBill.getText());
        int sum = 0;
        if(Integer.parseInt(textViewSumWithTip.getText().toString()) == 0){
            sum = Integer.parseInt(String.valueOf(textViewSum.getText()));
        }else{
            sum = Integer.parseInt(String.valueOf(textViewSumWithTip.getText()));
        }
        /*Delete all history*/
        /*viewListModel.deleteAll();*/
        viewListModel.insert(new ListsAll(nameDate, name, viewModel.getBillList(), totals, sum));
        /*Delete this bill and total sum array*/
/*        viewModel.deleteAll();
        totals.clear();*/
        Intent intent = new Intent(this, ListBillActivity.class);
        startActivity(intent);
    }
}