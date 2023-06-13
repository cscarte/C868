package com.example.C868.UI;

import android.os.Bundle;
import android.widget.ArrayAdapter;
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

public class AssemblyTransactions extends AppCompatActivity {

    Repository repository = new Repository(getApplication());
    TextView assemblyNameTransaction;
    TextView assemblyQtyOnHand;

    TextView qtyOnHand;

    List<AssemblyParts> assemblyPartsList = repository.getmAllAssemblyParts();
    List<PurchasedComponents> purchasedComponentsList = repository.getmAllPurchasedComponents();

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
        qtyOnHand = findViewById(R.id.assemblyTransactionQtyOnHandTextView);
        assemblySpinner = findViewById(R.id.assemblyNameTransactionSpinner);


        final ArrayAdapter<AssemblyParts> assemblySpinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, assemblyPartsList);
        assemblySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        assemblySpinner.setAdapter(assemblySpinnerAdapter);

        if (assemblyPartsList.isEmpty()) {
            Toast toast = Toast.makeText(getApplicationContext(), "Please add an assembly part before attempting to add a transaction.", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            spinnerDefaultPosition = assemblySpinner.getSelectedItemPosition();
            assemblyParts = assemblyPartsList.get(spinnerDefaultPosition);
        }
    }
}
