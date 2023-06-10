package com.example.C868.Entity;

import androidx.room.Entity;

@Entity(tableName = "assemblyParts")
public class AssemblyParts extends Parts {
    private String resourceName;

    public AssemblyParts(int partID, String partName, String partDescription, int partQty, String partLocation, Boolean partPurchased, String resourceName){
        super(partID, partName, partDescription, partQty, partLocation, partPurchased);
        this.resourceName = resourceName;
    }
    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }
}