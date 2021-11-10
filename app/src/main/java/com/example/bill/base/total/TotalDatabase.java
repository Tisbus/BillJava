package com.example.bill.base.total;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Total.class}, version = 2, exportSchema = false)
public abstract class TotalDatabase extends RoomDatabase {
    private static TotalDatabase database;
    private final static String DB_NAME = "total";
    private final static Object LOCK = new Object();
    public static TotalDatabase getInstance(Context context){
        synchronized (LOCK){
            if(database == null){
                database = Room.databaseBuilder(context.getApplicationContext(), TotalDatabase.class, DB_NAME)
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build();
            }
            return database;
        }
    }

    public abstract TotalDao totalDao();
}
