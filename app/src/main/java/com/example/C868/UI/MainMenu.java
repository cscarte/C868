package com.example.C868.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.C868.Database.Repository;
import com.example.C868.Entity.AssemblyParts;
import com.example.C868.Entity.PurchasedComponents;
import com.example.c868.R;

import java.util.List;

public class MainMenu extends AppCompatActivity {
    List<AssemblyParts> assemblyPartsList;
    List<PurchasedComponents> purchasedComponentsList;

    PurchasedComponents purchasedComponents;

    AssemblyParts assemblyParts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Toolbar toolbar = findViewById(R.id.menuToolbar);
        setSupportActionBar(toolbar);

        Repository repository = new Repository(getApplication());
        assemblyPartsList = repository.getmAllAssemblyParts();
        purchasedComponentsList = repository.getmAllPurchasedComponents();

        if (assemblyPartsList.size() < 1) {
            assemblyParts = new AssemblyParts(1, "Assembly Part 1", "Assembly Part 1 Description", 1, "Assembly Part 1 Location", false, "Assembly Line 1");
            repository.insert(assemblyParts);
            assemblyParts = new AssemblyParts(2, "Assembly Part 2", "Assembly Part 2 Description", 1, "Assembly Part 2 Location", false, "Assembly Line 1");
            repository.insert(assemblyParts);
            assemblyParts = new AssemblyParts(3, "Assembly Part 3", "Assembly Part 3 Description", 1, "Assembly Part 3 Location", false, "Assembly Line 2");
            repository.insert(assemblyParts);
        }

        if (purchasedComponentsList.size() < 1) {
            purchasedComponents = new PurchasedComponents(1, "Purchased Component 1", "Purchased Component 1 Description", 1, "Purchased Component 1 Location", true, "Walmart", 1, 1);
            repository.insert(purchasedComponents);
            purchasedComponents = new PurchasedComponents(2, "Purchased Component 2", "Purchased Component 2 Description", 1, "Purchased Component 2 Location", true, "Walmart", 1, 1);
            repository.insert(purchasedComponents);
            purchasedComponents = new PurchasedComponents(3, "Purchased Component 3", "Purchased Component 3 Description", 1, "Purchased Component 3 Location", true, "Walmart", 1, 1);
            repository.insert(purchasedComponents);
            purchasedComponents = new PurchasedComponents(4, "Purchased Component 4", "Purchased Component 4 Description", 1, "Purchased Component 4 Location", true, "Walmart", 1, 1);
            repository.insert(purchasedComponents);
            purchasedComponents = new PurchasedComponents(5, "Purchased Component 5", "Purchased Component 5 Description", 1, "Purchased Component 5 Location", true, "Walmart", 1, 1);
            repository.insert(purchasedComponents);
            purchasedComponents = new PurchasedComponents(6, "Purchased Component 6", "Purchased Component 6 Description", 1, "Purchased Component 6 Location", true, "Walmart", 1, 1);
            repository.insert(purchasedComponents);
            purchasedComponents = new PurchasedComponents(7, "Purchased Component 7", "Purchased Component 7 Description", 1, "Purchased Component 7 Location", true, "Walmart", 1, 1);
            repository.insert(purchasedComponents);
            purchasedComponents = new PurchasedComponents(8, "Purchased Component 8", "Purchased Component 8 Description", 1, "Purchased Component 8 Location", true, "Walmart", 1, 1);
            repository.insert(purchasedComponents);
        }

        System.out.println("There are " + assemblyPartsList.size() + " assembly parts.");
        System.out.println("There are " + purchasedComponentsList.size() + " purchased components.");
    }

    public void enterAssemblyPartsScreen(View view) {
        Intent intent = new Intent(MainMenu.this, AssemblyPartsList.class);
        startActivity(intent);
    }

    public void enterPurchasedComponentsScreen(View view) {
        Intent intent = new Intent(MainMenu.this, PurchasedComponentList.class);
        startActivity(intent);
    }

    public void enterAssemblyTableScreen(View view) {
        Intent intent = new Intent(MainMenu.this, AssemblyTableView.class);
        startActivity(intent);
    }

    public void enterAssemblyTransactionScreen(View view) {
        Intent intent = new Intent(MainMenu.this, AssemblyTransactions.class);
        startActivity(intent);
    }

    public void enterPurchasedComponentTableScreen(View view) {
        Intent intent = new Intent(MainMenu.this, PurchasedComponentTableView.class);
        startActivity(intent);
    }
}
