package com.example.C868.Entity;

import androidx.room.Entity;

@Entity(tableName = "assemblyParts")
public class AssemblyParts extends Parts {

    private int assemblyID;

    public AssemblyParts(int partID, String partName, String partDescription, int partQty, String partLocation, Boolean partPurchased, int assemblyID) {
        super(partID, partName, partDescription, partQty, partLocation, partPurchased);
        this.assemblyID = assemblyID;
    }

    public int getAssemblyID() {
        return assemblyID;
    }

    public void setAssemblyID(int assemblyID) {
        this.assemblyID = assemblyID;
    }
}