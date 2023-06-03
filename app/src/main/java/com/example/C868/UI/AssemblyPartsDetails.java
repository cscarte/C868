package com.example.C868.UI;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.C868.Adapters.PurchasedPartsAdapter;
import com.example.C868.Database.Repository;
import com.example.C868.Entity.PurchasedComponents;
import com.example.c868.R;

import java.util.List;

public class AssemblyPartsDetails extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText assemblyPartName;
    EditText assemblyPartDescription;
    EditText assemblyPartQuantity;
    EditText assemblyPartLocation;

    RecyclerView recyclerViewAssemblyComponents;

    int assemblyPartID;
    String name;
    String description;
    int quantity;
    String location;
    boolean partPurchasedStatus;

    PurchasedPartsAdapter purchasedPartsAdapter;

    List<PurchasedComponents> purchasedComponentsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assembly_parts_details);

        Toolbar toolbar = findViewById(R.id.toolbarDetails);
        setSupportActionBar(toolbar);

        Repository repository = new Repository(getApplication());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewAssemblyComponents = findViewById(R.id.recyclerViewAssemblyPurchasedComponent);
        recyclerViewAssemblyComponents.setLayoutManager(linearLayoutManager);

        assemblyPartID = getIntent().getIntExtra("partID", 0);

        assemblyPartName = findViewById(R.id.editTextViewAssemblyPartName);
        name = getIntent().getStringExtra("partName");
        assemblyPartName.setText(name);

        assemblyPartDescription = findViewById(R.id.editTextViewAssemblyPartDescription);
        description = getIntent().getStringExtra("partDescription");
        assemblyPartDescription.setText(description);

        assemblyPartQuantity = findViewById(R.id.editTextViewAssemblyPartQuantity);
        quantity = getIntent().getIntExtra("partQty", 0);
        assemblyPartQuantity.setText(String.valueOf(quantity));

        assemblyPartLocation = findViewById(R.id.editTextViewAssemblyPartLocation);
        location = getIntent().getStringExtra("partLocation");
        assemblyPartLocation.setText(location);

        purchasedPartsAdapter = new PurchasedPartsAdapter(this);
        recyclerViewAssemblyComponents.setAdapter(purchasedPartsAdapter);
        purchasedComponentsList = repository.getmAllPurchasedComponents();
        System.out.println("purchasedComponentsList: " + purchasedComponentsList);
        purchasedPartsAdapter.setPartsList(purchasedComponentsList);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    public void onResume() {
        super.onResume();
        PurchasedPartsAdapter.clickEnabled = true;
    }

    public void onBackPressed() {
        super.onBackPressed();
        PurchasedPartsAdapter.clickEnabled = true;
    }
}
