package com.example.C868.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

public class Parts {
    @PrimaryKey(autoGenerate = true)
    private int partID;
    private String partName;
    private String partDescription;
    private int partQty;
    private String partLocation;
    private Boolean partPurchased;

    public Parts(int partID, String partName, String partDescription, int partQty, String partLocation, Boolean partPurchased) {
        this.partID = partID;
        this.partName = partName;
        this.partDescription = partDescription;
        this.partQty = partQty;
        this.partLocation = partLocation;
        this.partPurchased = partPurchased;
    }
}
