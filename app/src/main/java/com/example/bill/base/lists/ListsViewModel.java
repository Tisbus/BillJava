package com.example.bill.base.lists;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.room.Database;

import java.util.List;

public class ListsViewModel extends AndroidViewModel {
    private static ListsDatabase database;
    private LiveData<List<ListsAll>> lists;
    private List<ListsAll> listsList;

    public LiveData<List<ListsAll>> getLists() {
        return lists;
    }

    public List<ListsAll> getListsList() {
        return listsList;
    }

    public ListsViewModel(@NonNull Application application) {
        super(application);
        database = ListsDatabase.getInstance(getApplication());
        lists = database.listsDao().listsAll();
        listsList = database.listsDao().allLists();
    }

    public void insert(ListsAll listsAll){
        new InsertTask().execute(listsAll);
    }
    public void delete(ListsAll listsAll){
        new DeleteTask().execute(listsAll);
    }
    public void deleteAll(){
        new DeleteAllTask().execute();
    }


    public class InsertTask extends AsyncTask<ListsAll, Void, Void>{

        @Override
        protected Void doInBackground(ListsAll... listsAll) {
            if(listsAll != null & listsAll.length > 0){
                database.listsDao().insert(listsAll[0]);
            }
            return null;
        }
    }

    public class DeleteTask extends AsyncTask<ListsAll, Void, Void>{

        @Override
        protected Void doInBackground(ListsAll... listsAll) {
            if(listsAll != null & listsAll.length > 0){
                database.listsDao().delete(listsAll[0]);
            }
            return null;
        }
    }

    public class DeleteAllTask extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            database.listsDao().deleteAll();
            return null;
        }
    }


}
