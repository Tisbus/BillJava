package com.example.bill.base.bill;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import android.os.AsyncTask;
import androidx.annotation.NonNull;

import java.util.List;

public class BillViewModel extends AndroidViewModel {
    private static BillDatabase database;
    private LiveData<List<Bill>> bill;
    private List<Bill> billList;

    public BillViewModel(@NonNull Application application) {
        super(application);
        database = BillDatabase.getInstance(getApplication());
        bill = database.billDao().allBill();
        billList = database.billDao().allListBill();
    }

    public List<Bill> getBillList() {
        return billList;
    }

    public LiveData<List<Bill>> getBill() {
        return bill;
    }

    public void insert(Bill bill){new InsertTask().execute(bill);}
    public void delete(Bill bill){new DeleteTask().execute(bill);}
    public void deleteAll(){new DeleteAllTask().execute();}

    public static class InsertTask extends AsyncTask<Bill, Void, Void>{
        @Override
        protected Void doInBackground(Bill... bills) {
            if(bills != null && bills.length > 0){
                database.billDao().insert(bills[0]);
            }
            return null;
        }
    }

    public static class DeleteTask extends AsyncTask<Bill, Void, Void>{
        @Override
        protected Void doInBackground(Bill... bills) {
            if(bills != null && bills.length > 0){
                database.billDao().delete(bills[0]);
            }
            return null;
        }
    }

    public static class DeleteAllTask extends AsyncTask<Void, Void, Void>{
        @Override
        protected Void doInBackground(Void... voids) {
            database.billDao().deleteAll();
            return null;
        }
    }
}
