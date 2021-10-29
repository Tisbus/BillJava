package com.example.bill.base.guest;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = {Guest.class}, version = 1, exportSchema = false)
public abstract class GuestDatabase extends RoomDatabase {

    private final static String DB_NAME = "guests";
    private final static Object LOCK = new Object();
    private static GuestDatabase database;

    public static GuestDatabase getInstance(Context context){
        synchronized (LOCK){
            if(database == null){
                database = Room.databaseBuilder(context.getApplicationContext(), GuestDatabase.class, DB_NAME)
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build();
            }
            return database;
        }
    }

    public abstract GuestDao guestDao();
}
