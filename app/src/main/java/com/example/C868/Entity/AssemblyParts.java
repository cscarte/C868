package com.example.C868.Entity;

import java.util.List;

public class AssemblyParts extends Parts {
    private List<Integer> componentsIDList;

    public AssemblyParts(int partID, String partName, String partDescription, int partQty, String partLocation, Boolean partPurchased) {
        super(partID, partName, partDescription, partQty, partLocation, partPurchased);
    }
}
