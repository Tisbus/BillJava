package com.example.bill.base.guest;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GuestViewModel extends AndroidViewModel {
    private static GuestDatabase database;
    private LiveData<List<Guest>> guest;
    public GuestViewModel(@NonNull Application application) {
        super(application);
        database = GuestDatabase.getInstance(getApplication());
        guest = database.guestDao().allGuest();
    }

    public LiveData<List<Guest>> getGuest() {
        return guest;
    }

    public void insert(Guest guest){
        new InsertTask().execute(guest);
    }
    public void deleteAll(){
        new DeleteAllTask().execute();
    }

    public class InsertTask extends AsyncTask<Guest, Void, Void>{
        @Override
        protected Void doInBackground(Guest... guests) {
            if(guests != null && guests.length > 0){
                database.guestDao().insert(guests[0]);
            }
            return null;
        }
    }

    public class DeleteAllTask extends AsyncTask<Void, Void, Void>{
        @Override
        protected Void doInBackground(Void... voids) {
            database.guestDao().deleteAll();
            return null;
        }
    }
}
