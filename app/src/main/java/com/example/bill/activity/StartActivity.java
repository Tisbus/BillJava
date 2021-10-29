package com.example.bill.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.example.bill.R;
import java.util.ArrayList;
import java.util.List;

public class StartActivity extends AppCompatActivity {

    private Button buttonAdd;
    private Button buttonDelete;
    private EditText editTextName;
    private List<View> addEdit;
    private ArrayList<String> guests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);
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
        guests = new ArrayList<>();
        for (int i = 0; i < addEdit.size(); i++){
            editTextName = addEdit.get(i).findViewById(R.id.edit_text);
            guests.add(getLetterName(editTextName.getText().toString()));
        }
        Intent intentGet = new Intent(this, MainActivity.class);
        intentGet.putStringArrayListExtra("guests", guests);
        startActivity(intentGet);
    }

    public String getLetterName(String name){
        return name.toUpperCase().trim();
    }

}