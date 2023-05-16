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

}
