package com.example.C868.Entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(tableName = "purchasedComponents")
public class PurchasedComponents extends Parts {
    private String purchasedPartVendor;
    private int purchasedPartLeadTimeDays;

    public PurchasedComponents(int partID, String partName, String partDescription, int partQty, String partLocation, Boolean partPurchased, String purchasedPartVendor, int purchasedPartLeadTimeDays) {
        super(partID, partName, partDescription, partQty, partLocation, partPurchased);
        this.purchasedPartVendor = purchasedPartVendor;
        this.purchasedPartLeadTimeDays = purchasedPartLeadTimeDays;
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

    @NonNull
    @Override
    public String toString() {
        return "PurchasedComponents{" +
                "purchasedPartVendor='" + purchasedPartVendor + '\'' +
                ", purchasedPartLeadTimeDays=" + purchasedPartLeadTimeDays +
                '}';
    }
}
