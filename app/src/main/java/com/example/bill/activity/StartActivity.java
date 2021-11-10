package com.example.bill.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.example.bill.R;
import com.example.bill.base.bill.BillViewModel;
import com.example.bill.base.guest.Guest;
import com.example.bill.base.guest.GuestViewModel;
import com.example.bill.base.lists.ListsAll;
import com.example.bill.base.lists.ListsViewModel;
import com.example.bill.base.total.TotalViewModel;

import java.util.ArrayList;
import java.util.List;

public class StartActivity extends AppCompatActivity {

    private Button buttonAdd;
    private Button buttonDelete;
    private EditText editTextName;
    private List<View> addEdit;
    private GuestViewModel model;
    private BillViewModel viewModel;
    private ListsViewModel viewListModel;
    private TotalViewModel totalViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);
        model = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(GuestViewModel.class);
        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(BillViewModel.class);
        viewListModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(ListsViewModel.class);
        totalViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(TotalViewModel.class);

        /*Delete all history*/
        viewListModel.deleteAll();

        /*Delete All Guest*/
        model.deleteAll();

        /*Delete All Total*/
        totalViewModel.deleteAll();

/*        Delete this bill*/
        viewModel.deleteAll();

        buttonAdd = findViewById(R.id.buttonAdd);
        addEdit = new ArrayList<>();
        final LinearLayout liner = (LinearLayout) findViewById(R.id.liner);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View view = getLayoutInflater().inflate(R.layout.add_edittext_guest, null);
                buttonDelete = view.findViewById(R.id.button_delete);
                editTextName = view.findViewById(R.id.edit_text);
                    buttonDelete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ((LinearLayout) view.getParent()).removeView(view);
                            addEdit.remove(view);
                        }
                    });
                addEdit.add(view);
                liner.addView(view);
            }
        });

    }

    public void onNextStep(View view) {
        for (int i = 0; i < addEdit.size(); i++){
            editTextName = addEdit.get(i).findViewById(R.id.edit_text);
            if(!editTextName.getText().toString().equals("")){
                model.insert(new Guest(getLetterName(editTextName.getText().toString())));
            }
        }
        Intent intentGet = new Intent(this, MainActivity.class);
        startActivity(intentGet);
    }

    public String getLetterName(String name){
        return name.toUpperCase().trim();
    }

}