package com.example.C868.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.C868.Entity.Parts;

import java.util.List;

@Dao
public interface PartsDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Parts parts);

    @Update
    void update(Parts parts);

    @Delete
    void delete(Parts parts);

    @Query("SELECT * FROM parts ORDER BY partID ASC")
    List<Parts> getAllParts();

    @Query("SELECT * FROM parts WHERE partID = :partID")
    List<Parts> getPartsByPartsID(int partID);

    @Query("DELETE FROM parts")
    int deleteAllParts();

    @Query("SELECT COUNT(*) FROM parts")
    int getCountOfParts();
}
