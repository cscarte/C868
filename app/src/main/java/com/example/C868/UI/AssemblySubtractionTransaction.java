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

import java.util.List;

public class AssemblySubtractionTransaction extends AppCompatActivity {

    Repository repository = new Repository(getApplication());
    TextView assemblyNameTransaction;
    TextView assemblyQtyOnHand;

    TextView qtyOnHand;

    EditText qtyAdjustment;

    List<AssemblyParts> assemblyPartsList = repository.getmAllAssemblyParts();
    List<PurchasedComponents> purchasedComponentsList = repository.getmAllPurchasedComponents();
    List<PurchasedComponents> purchasedComponentsInAssembly = repository.getmAllPurchasedComponents();

    AssemblyParts assemblyParts;


    Spinner assemblySpinner;
    int spinnerDefaultPosition = 0;
    int qty = 0;
    int adjustmentQty = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assembly_transactions_subtraction);

        assemblyNameTransaction = findViewById(R.id.assemblyTransactionNameTextView);
        assemblyQtyOnHand = findViewById(R.id.assemblyTransactionQtyOnHandTextView);
        qtyOnHand = findViewById(R.id.assemblyQtyOnHandTextView);
        assemblySpinner = findViewById(R.id.assemblyNameTransactionSpinner);
        qtyAdjustment = findViewById(R.id.assemblyQtyToAdjustTextEdit);

        final ArrayAdapter<AssemblyParts> assemblySpinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, assemblyPartsList);
        assemblySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        try {
            assemblySpinner.setAdapter(assemblySpinnerAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }

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

    public void subtractAssemblyTransaction(View view) {
        if (!qtyOnHand.getText().toString().isEmpty()) {
            qty = Integer.parseInt(qtyOnHand.getText().toString());
        } else {
            qty = 0;
        }

        try {
            if (!qtyAdjustment.getText().toString().isEmpty()) {
                adjustmentQty = Integer.parseInt(qtyAdjustment.getText().toString());
            } else {
                adjustmentQty = 0;
            }
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }

        if (!qtyAdjustment.getText().toString().isEmpty()) {
            adjustmentQty = Integer.parseInt(qtyAdjustment.getText().   toString());
        } else {
            adjustmentQty = 0;
        }

        purchasedComponentsList = repository.getmAllPurchasedComponents();

        if (adjustmentQty <= 0) {
            Toast toast = Toast.makeText(getApplicationContext(), "Please enter a positive number.", Toast.LENGTH_SHORT);
            toast.show();
        } else {

            if (qty - adjustmentQty >= 0) {
                for (PurchasedComponents purchasedComponents1 : purchasedComponentsList) {
                    if (purchasedComponents1.getPurchasedPartAssemblyID() == assemblyParts.getPartID()) {
                        purchasedComponents1.setPartQty(purchasedComponents1.getPartQty() + adjustmentQty);
                        repository.update(purchasedComponents1);
                    }
                }

                assemblyParts.setPartQty(qty - adjustmentQty);
                repository.update(assemblyParts);
                qtyOnHand.setText(String.valueOf(assemblyParts.getPartQty()));
                Toast toast1 = Toast.makeText(getApplicationContext(), "Assembly subtracted, components returned to stock.", Toast.LENGTH_SHORT);
                toast1.show();
                purchasedComponentsInAssembly.clear();
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), "Assembly does not exist or insufficient assemblies in stock to subtract", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }
}