package com.example.bill.base.bill;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Bill.class}, version = 3, exportSchema = false)
public abstract class BillDatabase extends RoomDatabase {
    private static final String DB_NAME = "bill";
    private static final Object LOCK = new Object();
    private static BillDatabase database;

    public static BillDatabase getInstance(Context context){
        synchronized (LOCK){
            if(database == null){
                database = Room.databaseBuilder(context.getApplicationContext(), BillDatabase.class, DB_NAME)
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build();
            }
        }
        return database;
    }

    public abstract BillDao billDao();
}
