package com.example.C868.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.C868.Entity.AssemblyParts;

@Dao
public interface AssemblyPartsDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(AssemblyParts assemblyParts);

    @Update
    void update(AssemblyParts assemblyParts);

    @Delete
    void delete(AssemblyParts assemblyParts);
}
