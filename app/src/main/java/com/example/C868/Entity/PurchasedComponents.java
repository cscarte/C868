package com.example.C868.Entity;

public class PurchasedComponents extends Parts {
    private String purchasedPartVendor;
    private int purchasedPartLeadTimeDays;

    public PurchasedComponents(int partID, String partName, String partDescription, int partQty, String partLocation, Boolean partPurchased) {
        super(partID, partName, partDescription, partQty, partLocation, partPurchased);
    }
}
