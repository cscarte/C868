package com.example.C868.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.C868.Database.Repository;
import com.example.C868.Entity.AssemblyParts;
import com.example.C868.Entity.PurchasedComponents;
import com.example.c868.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<AssemblyParts> assemblyPartsList;
    List<PurchasedComponents> purchasedComponentsList;

    PurchasedComponents purchasedComponents;

    AssemblyParts assemblyParts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Repository repository = new Repository(getApplication());
        assemblyPartsList = repository.getmAllAssemblyParts();
        purchasedComponentsList = repository.getmAllPurchasedComponents();



        if (assemblyPartsList.size() < 1) {
            assemblyParts = new AssemblyParts(1, "Assembly Part 1", "Assembly Part 1 Description", 1, "Assembly Part 1 Location", false, 1);
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
        Intent intent = new Intent(MainActivity.this, AssemblyParts.class);
        startActivity(intent);
    }

    public void enterPurchasedComponentsScreen(View view) {
        Intent intent = new Intent(MainActivity.this, PurchasedComponentList.class);
        startActivity(intent);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_activity, menu);
        return true;
    }
}