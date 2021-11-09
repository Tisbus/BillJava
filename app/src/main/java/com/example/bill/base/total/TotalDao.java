package com.example.bill.base.total;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TotalDao {

    @Query("SELECT * FROM total ORDER BY name DESC")
    LiveData<List<Total>> totalAll();
    @Query("DELETE FROM total")
    void deleteAll();
    @Insert
    void insert(Total total);
}
