package com.example.C868.UI;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.C868.Database.Repository;
import com.example.C868.Entity.AssemblyParts;
import com.example.C868.Entity.PurchasedComponents;
import com.example.c868.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AssemblyTransactions extends AppCompatActivity {

    Repository repository = new Repository(getApplication());
    TextView assemblyNameTransaction;
    TextView assemblyQtyOnHand;

    TextView qtyOnHand;

    EditText qtyAdjustment;

    List<AssemblyParts> assemblyPartsList = repository.getmAllAssemblyParts();
    List<PurchasedComponents> purchasedComponentsList = repository.getmAllPurchasedComponents();
    List<PurchasedComponents> purchasedComponentsInAssembly = repository.getmAllPurchasedComponents();
    List<Integer> purchasedComponentQuantities2;
    List<Integer> purchasedComponentQuantities;

    AssemblyParts assemblyParts;
    PurchasedComponents purchasedComponents;

    Spinner assemblySpinner;
    int spinnerDefaultPosition = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assembly_transactions);

        assemblyNameTransaction = findViewById(R.id.assemblyTransactionNameTextView);
        assemblyQtyOnHand = findViewById(R.id.assemblyTransactionQtyOnHandTextView);
        qtyOnHand = findViewById(R.id.assemblyQtyOnHandTextView);
        assemblySpinner = findViewById(R.id.assemblyNameTransactionSpinner);
        qtyAdjustment = findViewById(R.id.assemblyQtyToAdjustTextEdit);

        final ArrayAdapter<AssemblyParts> assemblySpinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, assemblyPartsList);
        assemblySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        assemblySpinner.setAdapter(assemblySpinnerAdapter);

        if (assemblyPartsList.isEmpty()) {
            Toast toast = Toast.makeText(getApplicationContext(), "Please add an assembly part before attempting to add a transaction.", Toast.LENGTH_SHORT);
            toast.show();
            spinnerDefaultPosition = 0;
        } else {
            spinnerDefaultPosition = assemblySpinner.getSelectedItemPosition();
            assemblyParts = assemblyPartsList.get(spinnerDefaultPosition);
        }

        assemblySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                assemblyParts = (AssemblyParts) parent.getItemAtPosition(position);
                qtyOnHand.setText(String.valueOf(assemblyParts.getPartQty()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void addAssemblyTransaction(View view) {
        int qty = Integer.parseInt(qtyOnHand.getText().toString());
        int adjustmentQty = Integer.parseInt(qtyAdjustment.getText().toString());
        purchasedComponentsList = repository.getmAllPurchasedComponents();
        purchasedComponentQuantities = new ArrayList<>();
        purchasedComponentQuantities2 = new ArrayList<>();

        for (PurchasedComponents purchasedComponents1 : purchasedComponentsList) {
            if (purchasedComponents1.getPurchasedPartAssemblyID() == assemblyParts.getPartID()) {
                purchasedComponentQuantities.add(purchasedComponents1.getPartQty());
                purchasedComponentsInAssembly.add(purchasedComponents1);
            }
        }

        System.out.println("The minimum value in the list is " + Collections.min(purchasedComponentQuantities) + ".");
        System.out.println(purchasedComponentsInAssembly);

        if (Collections.min(purchasedComponentQuantities) >= adjustmentQty) {
            for (PurchasedComponents purchasedComponents1 : purchasedComponentsInAssembly) {
                if (purchasedComponents1.getPurchasedPartAssemblyID() == assemblyParts.getPartID()) {
                    purchasedComponents1.setPartQty(purchasedComponents1.getPartQty() - adjustmentQty);
                    repository.update(purchasedComponents1);
                }
            }
            assemblyParts.setPartQty(qty + adjustmentQty);
            repository.update(assemblyParts);
            qtyOnHand.setText(String.valueOf(assemblyParts.getPartQty()));
            Toast toast1 = Toast.makeText(getApplicationContext(), "Assembly transaction added.", Toast.LENGTH_SHORT);
            toast1.show();
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Insufficient components to make assembly", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void subtractAssemblyTransaction(View view) {
        int qty = Integer.parseInt(qtyOnHand.getText().toString());
        int adjustmentQty = Integer.parseInt(qtyAdjustment.getText().toString());
        purchasedComponentsList = repository.getmAllPurchasedComponents();
        purchasedComponentQuantities = new ArrayList<>();

        if (qty > 0) {
            for (PurchasedComponents purchasedComponents1 : purchasedComponentsList) {
                if (purchasedComponents1.getPurchasedPartAssemblyID() == assemblyParts.getPartID()) {
                    purchasedComponents1.setPartQty(purchasedComponents1.getPartQty() + adjustmentQty);
                    repository.update(purchasedComponents1);
                }
            }

            int newQty = qty - adjustmentQty;
            assemblyParts.setPartQty(newQty);
            repository.update(assemblyParts);
            qtyOnHand.setText(String.valueOf(assemblyParts.getPartQty()));
            Toast toast = Toast.makeText(getApplicationContext(), "Assembly transaction subtracted.", Toast.LENGTH_SHORT);
            toast.show();
            System.out.println("This assembly has " + assemblyParts.getPartQty() + " pieces on hand.");
            onBackPressed();
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Assembly quantity cannot be less than 0.", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}

