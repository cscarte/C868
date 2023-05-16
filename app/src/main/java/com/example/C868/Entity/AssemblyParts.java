package com.example.C868.Entity;

import java.util.List;

public class AssemblyParts extends Parts {
    private List<Integer> componentsIDList;

    public AssemblyParts(int partID, String partName, String partDescription, int partQty, String partLocation, Boolean partPurchased, List<Integer> componentsIDList) {
        super(partID, partName, partDescription, partQty, partLocation, partPurchased);
        this.componentsIDList = componentsIDList;
    }

    public List<Integer> getComponentsIDList() {
        return componentsIDList;
    }

    public void setComponentsIDList(List<Integer> componentsIDList) {
        this.componentsIDList = componentsIDList;
    }

    @Override
    public String toString() {
        return "AssemblyParts{" +
                "componentsIDList=" + componentsIDList +
                '}';
    }
}
