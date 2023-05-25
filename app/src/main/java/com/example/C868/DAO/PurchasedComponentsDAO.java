package com.example.C868.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.C868.Entity.PurchasedComponents;

import java.util.List;

@Dao
public interface PurchasedComponentsDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(PurchasedComponents purchasedComponents);

    @Update
    void update(PurchasedComponents purchasedComponents);

    @Delete
    void delete(PurchasedComponents purchasedComponents);

    @Query("SELECT * FROM purchasedComponents ORDER BY partID ASC")
    LiveData<List<PurchasedComponents>> getAllAssemblyParts();

    @Query("SELECT * FROM purchasedComponents WHERE partID = :partID")
    LiveData<List<PurchasedComponents>> getAssemblyPartsByPartsID(int partID);

    @Query("DELETE FROM purchasedComponents")
    int deleteAllAssemblyParts();

    @Query("SELECT COUNT(*) FROM purchasedComponents")
    int getCountOfAssemblyParts();
}
