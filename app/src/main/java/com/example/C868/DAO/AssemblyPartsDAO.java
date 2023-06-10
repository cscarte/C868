package com.example.C868.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.C868.Entity.AssemblyParts;

import java.util.List;

@Dao
public interface AssemblyPartsDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(AssemblyParts assemblyParts);

    @Update
    void update(AssemblyParts assemblyParts);

    @Delete
    void delete(AssemblyParts assemblyParts);

    @Query("SELECT * FROM assemblyParts ORDER BY partID ASC")
    List<AssemblyParts> getAllAssemblyParts();

    @Query("DELETE FROM assemblyParts")
    int deleteAllAssemblyParts();

    @Query("SELECT COUNT(*) FROM assemblyParts")
    int getCountOfAssemblyParts();
}
