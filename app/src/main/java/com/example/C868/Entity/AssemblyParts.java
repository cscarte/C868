package com.example.C868.Entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import java.util.List;

@Entity(tableName = "assemblyParts")
public class AssemblyParts extends Parts {

    public AssemblyParts(int partID, String partName, String partDescription, int partQty, String partLocation, Boolean partPurchased) {
        super(partID, partName, partDescription, partQty, partLocation, partPurchased);
    }
}
