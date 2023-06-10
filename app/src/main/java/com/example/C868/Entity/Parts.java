package com.example.C868.Entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "parts")
public class Parts {
    //Generate default values for parts attributes for Assembly parts and Purchased components
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

    public int getPartID() {
        return partID;
    }

    public void setPartID(int partID) {
        this.partID = partID;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getPartDescription() {
        return partDescription;
    }

    public void setPartDescription(String partDescription) {
        this.partDescription = partDescription;
    }

    public int getPartQty() {
        return partQty;
    }

    public void setPartQty(int partQty) {
        this.partQty = partQty;
    }

    public String getPartLocation() {
        return partLocation;
    }

    public void setPartLocation(String partLocation) {
        this.partLocation = partLocation;
    }

    public Boolean getPartPurchased() {
        return partPurchased;
    }

    public void setPartPurchased(Boolean partPurchased) {
        this.partPurchased = partPurchased;
    }

    @NonNull
    @Override
    public String toString() {
        return partName;
    }
}
