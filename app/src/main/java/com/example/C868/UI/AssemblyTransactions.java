package com.example.C868.UI;

import android.os.Bundle;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.C868.Entity.AssemblyParts;
import com.example.C868.Entity.PurchasedComponents;
import com.example.c868.R;

import java.util.List;

public class AssemblyTransactions extends AppCompatActivity {
    TextView assemblyNameTransaction;
    TextView assemblyQtyOnHand;

    TextView qtyOnHand;

    List<AssemblyParts> assemblyPartsList;
    List<PurchasedComponents> purchasedComponentsList;

    AssemblyParts assemblyParts;
    PurchasedComponents purchasedComponents;

    Spinner assemblySpinner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assembly_transactions);

        assemblyNameTransaction = findViewById(R.id.assemblyTransactionNameTextView);
        assemblyQtyOnHand = findViewById(R.id.assemblyTransactionQtyOnHandTextView);
        qtyOnHand = findViewById(R.id.assemblyTransactionQtyOnHandTextView);

        assemblySpinner = findViewById(R.id.assemblyNameTransactionSpinner);
    }
}
