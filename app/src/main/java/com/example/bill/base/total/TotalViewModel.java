package com.example.bill.base.total;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TotalViewModel extends AndroidViewModel {

    private static TotalDatabase database;
    private LiveData<List<Total>> totals;


    public TotalViewModel(@NonNull Application application) {
        super(application);
        database = TotalDatabase.getInstance(getApplication());
        totals = database.totalDao().totalAll();
    }

    public LiveData<List<Total>> getTotals() {
        return totals;
    }

    public void deleteAll(){
        new DeleteAllTask().execute();
    }

    public void insert(Total total){
        new InsertTask().execute(total);
    }

    public class InsertTask extends AsyncTask<Total, Void, Void>{
        @Override
        protected Void doInBackground(Total... totals) {
            if(totals != null && totals.length > 0){
                database.totalDao().insert(totals[0]);
            }
            return null;
        }
    }

    public class DeleteAllTask extends AsyncTask<Void, Void, Void>{
        @Override
        protected Void doInBackground(Void... voids) {
            database.totalDao().deleteAll();
            return null;
        }
    }
}
