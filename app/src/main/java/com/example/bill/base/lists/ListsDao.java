package com.example.bill.base.lists;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface ListsDao {
    @Query("SELECT * FROM lists ORDER BY id DESC")
    LiveData<List<ListsAll>> listsAll();
    @Query("SELECT * FROM lists ORDER BY id DESC")
    List<ListsAll> allLists();
    @Insert
    void insert(ListsAll listsAll);
    @Delete
    void delete(ListsAll listsAll);
    @Query("DELETE FROM lists")
    void deleteAll();
}
