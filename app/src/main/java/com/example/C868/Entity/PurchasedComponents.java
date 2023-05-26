package com.example.C868.Entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(tableName = "purchasedComponents")
public class PurchasedComponents extends Parts {
    private String purchasedPartVendor;
    private int purchasedPartLeadTimeDays;

    private int purchasedPartAssemblyID;

    public PurchasedComponents(int partID, String partName, String partDescription, int partQty, String partLocation, Boolean partPurchased, String purchasedPartVendor, int purchasedPartLeadTimeDays, int purchasedPartAssemblyID) {
        super(partID, partName, partDescription, partQty, partLocation, partPurchased);
        this.purchasedPartVendor = purchasedPartVendor;
        this.purchasedPartLeadTimeDays = purchasedPartLeadTimeDays;
        this.purchasedPartAssemblyID = purchasedPartAssemblyID;
    }

    public String getPurchasedPartVendor() {
        return purchasedPartVendor;
    }

    public void setPurchasedPartVendor(String purchasedPartVendor) {
        this.purchasedPartVendor = purchasedPartVendor;
    }

    public int getPurchasedPartLeadTimeDays() {
        return purchasedPartLeadTimeDays;
    }

    public void setPurchasedPartLeadTimeDays(int purchasedPartLeadTimeDays) {
        this.purchasedPartLeadTimeDays = purchasedPartLeadTimeDays;
    }

    public int getPurchasedPartAssemblyID() {
        return purchasedPartAssemblyID;
    }

    public void setPurchasedPartAssemblyID(int purchasedPartAssemblyID) {
        this.purchasedPartAssemblyID = purchasedPartAssemblyID;
    }

    @NonNull
    @Override
    public String toString() {
        return "PurchasedComponents{" +
                "purchasedPartVendor='" + purchasedPartVendor + '\'' +
                ", purchasedPartLeadTimeDays=" + purchasedPartLeadTimeDays +
                '}';
    }
}
