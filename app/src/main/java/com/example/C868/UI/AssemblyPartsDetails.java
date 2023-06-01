package com.example.C868.UI;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c868.R;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assembly_parts_details);

        Toolbar toolbar = findViewById(R.id.toolbarDetails);
        setSupportActionBar(toolbar);

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
}
