package com.example.bill.base.lists;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {ListsAll.class}, version = 1, exportSchema = false)
@TypeConverters({ListsConverters.class})
public abstract class ListsDatabase extends RoomDatabase {
    private static ListsDatabase database;
    private static final String DB_NAME = "lists";
    private static final Object LOCK = new Object();
    public static ListsDatabase getInstance(Context context){
        synchronized (LOCK){
            if(database == null){
                database = Room.databaseBuilder(context.getApplicationContext(), ListsDatabase.class, DB_NAME)
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build();
            }
            return database;
        }
    }

    public abstract ListsDao listsDao();
}
