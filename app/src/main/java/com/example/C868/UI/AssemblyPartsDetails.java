package com.example.C868.UI;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.C868.Adapters.PurchasedPartsAdapterAssemblyDetails;
import com.example.C868.Database.Repository;
import com.example.C868.Entity.AssemblyParts;
import com.example.C868.Entity.PurchasedComponents;
import com.example.c868.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AssemblyPartsDetails extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText assemblyPartName;
    EditText assemblyPartDescription;
    EditText assemblyPartQuantity;
    Spinner assemblyPartLocation;

    RecyclerView recyclerViewAssemblyComponents;

    Spinner spinnerAssemblyResource;

    int partID;
    String name;
    String description;
    int quantity;
    String location;
    int resourceID;
    String resourceName;
    boolean partPurchasedStatus;

    int spinnerDefaultPosition = 0;

    Repository repository = new Repository(getApplication());

    PurchasedPartsAdapterAssemblyDetails purchasedPartsAdapter;

    List<PurchasedComponents> purchasedComponentsList;

    List<PurchasedComponents> purchasedComponentsByAssembly;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assembly_parts_details);

        Toolbar toolbar = findViewById(R.id.toolbarDetails);
        setSupportActionBar(toolbar);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewAssemblyComponents = findViewById(R.id.recyclerViewAssemblyPurchasedComponent);
        recyclerViewAssemblyComponents.setLayoutManager(linearLayoutManager);

        Resources resources = getResources();
        String[] resourceNames = resources.getStringArray(R.array.assembly_lines);
        String[] warehouse = resources.getStringArray(R.array.warehouse);

        purchasedPartsAdapter = new PurchasedPartsAdapterAssemblyDetails(this);

        partID = getIntent().getIntExtra("partID", 0);

        assemblyPartName = findViewById(R.id.editTextViewAssemblyPartName);
        name = getIntent().getStringExtra("partName");
        assemblyPartName.setText(name);

        assemblyPartDescription = findViewById(R.id.editTextViewAssemblyPartDescription);
        description = getIntent().getStringExtra("partDescription");
        assemblyPartDescription.setText(description);

        assemblyPartQuantity = findViewById(R.id.editTextViewAssemblyPartQuantity);
        quantity = getIntent().getIntExtra("partQty", 0);
        assemblyPartQuantity.setText(String.valueOf(quantity));

        assemblyPartLocation = findViewById(R.id.spinnerLocation);
        location = getIntent().getStringExtra("partLocation");
        ArrayAdapter<CharSequence> locationAdapter = ArrayAdapter.createFromResource(this, R.array.warehouse, android.R.layout.simple_spinner_item);
        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        assemblyPartLocation.setAdapter(locationAdapter);

        recyclerViewAssemblyComponents.setAdapter(purchasedPartsAdapter);
        purchasedComponentsList = repository.getmAllPurchasedComponents();

        purchasedComponentsByAssembly = new ArrayList<>();

        if (partID != 0) {
            for (PurchasedComponents purchasedComponents : purchasedComponentsList) {
                if (purchasedComponents.getPurchasedPartAssemblyID() == partID) {
                    purchasedComponentsByAssembly.add(purchasedComponents);
                }
            }
        }
        /**
        for (PurchasedComponents purchasedComponents : purchasedComponentsList) {
            if (purchasedComponents.getPurchasedPartAssemblyID() == partID) {
                purchasedComponentsByAssembly.add(purchasedComponents);
            }
        }*/

        //System.out.println("purchasedComponentsList: " + purchasedComponentsList);
        purchasedPartsAdapter.setPartsList(purchasedComponentsByAssembly);

        resourceName = getIntent().getStringExtra("resourceName");

        spinnerAssemblyResource = findViewById(R.id.spinner);
        spinnerAssemblyResource.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.assembly_lines, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAssemblyResource.setAdapter(spinnerAdapter);

        if (resourceName != null){
            spinnerDefaultPosition = Arrays.asList(resourceNames).indexOf(resourceName);
            spinnerAssemblyResource.setSelection(spinnerDefaultPosition);
        }
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
        PurchasedPartsAdapterAssemblyDetails.clickEnabled = true;
    }

    public void onBackPressed() {
        super.onBackPressed();
        PurchasedPartsAdapterAssemblyDetails.clickEnabled = true;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_assembly_parts_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuSaveAssemblyDetails) {
            partID = getIntent().getIntExtra("partID", 0);
            name = assemblyPartName.getText().toString();
            description = assemblyPartDescription.getText().toString();
            quantity = Integer.parseInt(assemblyPartQuantity.getText().toString());
            location = assemblyPartLocation.getSelectedItem().toString();
            partPurchasedStatus = getIntent().getBooleanExtra("partPurchasedStatus", false);
            String selectedAssemblyResource = spinnerAssemblyResource.getSelectedItem().toString();

            if (selectedAssemblyResource.equals("Select an assembly resource")){
                selectedAssemblyResource = null;
            }

            if (location.equals("Select a warehouse")) {
                location = null;
            }

            if (name.trim().isEmpty() || description.trim().isEmpty()) {
                Toast toast = Toast.makeText(getApplicationContext(), "Please fill out the following fields: name and description", Toast.LENGTH_SHORT);
                toast.show();
                return false;
            }

            if (partID == 0) {
                AssemblyParts assemblyParts = new AssemblyParts(0, name, description, quantity, location, partPurchasedStatus, selectedAssemblyResource);
                Repository repository = new Repository(getApplication());
                repository.insert(assemblyParts);
                Toast toast = Toast.makeText(getApplicationContext(), "Changes saved", Toast.LENGTH_SHORT);
                toast.show();
            } else {
                AssemblyParts assemblyParts = new AssemblyParts(partID, name, description, quantity, location, partPurchasedStatus, selectedAssemblyResource);
                Repository repository = new Repository(getApplication());
                repository.update(assemblyParts);
                Toast toast = Toast.makeText(getApplicationContext(), "Changes saved", Toast.LENGTH_SHORT);
                toast.show();
            }

            int position = getIntent().getIntExtra("position", 0);
            AssemblyPartsList.adapter.notifyItemChanged(position);
            AssemblyPartsList.assemblyPartsList.clear();
            AssemblyPartsList.assemblyPartsList.addAll(repository.getmAllAssemblyParts());
            AssemblyPartsList.adapter.setAssemblyPartsList(repository.getmAllAssemblyParts());
            finish();

        } else {
            if (purchasedComponentsByAssembly.size() > 0){
                Toast toast = Toast.makeText(getApplicationContext(), "Cannot delete part with purchased components", Toast.LENGTH_SHORT);
                toast.show();
            } else {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Delete Part");
            builder.setMessage("Are you sure you want to delete this part?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    for (AssemblyParts assemblyParts : repository.getmAllAssemblyParts()) {
                        if (assemblyParts.getPartID() == partID) {
                            repository.delete(assemblyParts);
                            Toast toast = Toast.makeText(getApplicationContext(), assemblyPartName.getText().toString() + " deleted", Toast.LENGTH_SHORT);
                            toast.show();
                            AssemblyPartsList.assemblyPartsList.clear();
                            AssemblyPartsList.assemblyPartsList.addAll(repository.getmAllAssemblyParts());
                            AssemblyPartsList.adapter.setAssemblyPartsList(repository.getmAllAssemblyParts());
                            finish();
                        }
                    }
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Part not deleted", Toast.LENGTH_SHORT);
                    toast.show();
                }
            });
            builder.show();
        }}
        return true;
    }
}