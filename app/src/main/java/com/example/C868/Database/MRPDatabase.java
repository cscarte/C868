package com.example.C868.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.C868.Entity.AssemblyParts;
import com.example.C868.Entity.Parts;
import com.example.C868.Entity.PurchasedComponents;

@Database(entities = {Parts.class, AssemblyParts.class, PurchasedComponents.class}, version = 1, exportSchema = false)
public abstract class MRPDatabase extends RoomDatabase {

    private static volatile MRPDatabase INSTANCE;

    public static MRPDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MRPDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = androidx.room.Room.databaseBuilder(context.getApplicationContext(), MRPDatabase.class, "MRPDatabase.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}