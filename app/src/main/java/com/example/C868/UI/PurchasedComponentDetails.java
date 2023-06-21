package com.example.C868.UI;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.C868.Database.Repository;
import com.example.C868.Entity.AssemblyParts;
import com.example.C868.Entity.PurchasedComponents;
import com.example.c868.R;

import java.util.ArrayList;

public class PurchasedComponentDetails extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText purchasedComponentName;
    EditText purchasedComponentDescription;
    EditText purchasedComponentQuantity;

    EditText purchasedComponentLocation;
    Spinner purchasedComponentSpinnerLocation;
    EditText purchasedComponentVendor;
    EditText purchasedComponentLeadTime;

    Repository repository = new Repository(getApplication());

    PurchasedComponents purchasedComponents;

    ArrayList<AssemblyParts> assemblyPartsList = new ArrayList<>();

    int purchasedComponentID;
    String name;
    String description;
    int quantity;
    String location;
    String vendor;
    int leadTime;

    int assemblyID;

    private AssemblyParts assemblyParts;
    private AssemblyParts assemblyPartsSelected;

    Spinner spinnerAssemblyName;

    @SuppressLint("MissingInflatedId")
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchased_components_details);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Toolbar toolbar = findViewById(R.id.toolbarDetails);
        setSupportActionBar(toolbar);

        purchasedComponentID = getIntent().getIntExtra("partID", 0);

        //Get purchased component part name from clicked item in list on the PurchasedComponentList.java screen
        purchasedComponentName = findViewById(R.id.editTextViewPurchasedPartName);
        name = getIntent().getStringExtra("partName");
        purchasedComponentName.setText(name);

        //Get purchased component part description from clicked item in list on the PurchasedComponentList.java screen
        purchasedComponentDescription = findViewById(R.id.editTextViewPurchasedPartDescription);
        description = getIntent().getStringExtra("partDescription");
        purchasedComponentDescription.setText(description);

        //Get purchased component part quantity from clicked item in list on the PurchasedComponentList.java screen
        purchasedComponentQuantity = findViewById(R.id.editTextViewPurchasedPartQuantity);
        quantity = getIntent().getIntExtra("partQty", 0);
        purchasedComponentQuantity.setText(String.valueOf(quantity));

        //Get purchased component part location from clicked item in list on the PurchasedComponentList.java screen
        purchasedComponentSpinnerLocation = findViewById(R.id.spinnerPurchasedComponentLocation);
        location = getIntent().getStringExtra("partLocation");
        final ArrayAdapter<CharSequence> spinnerAdapterLocation = ArrayAdapter.createFromResource(this, R.array.warehouse, android.R.layout.simple_spinner_item);
        spinnerAdapterLocation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        purchasedComponentSpinnerLocation.setAdapter(spinnerAdapterLocation);

        if(location == null){
            purchasedComponentSpinnerLocation.setSelection(0);
        } else {
            purchasedComponentSpinnerLocation.setSelection(spinnerAdapterLocation.getPosition(location));
        }

        //Get purchased component part vendor from clicked item in list on the PurchasedComponentList.java screen
        purchasedComponentVendor = findViewById(R.id.editTextViewPurchasedComponentVendor2);
        vendor = getIntent().getStringExtra("purchasedPartVendor");
        purchasedComponentVendor.setText(vendor);

        //Get purchased component part lead time from clicked item in list on the PurchasedComponentList.java screen
        purchasedComponentLeadTime = findViewById(R.id.editTextViewPurchasedComponentLeadTime);
        leadTime = getIntent().getIntExtra("purchasedPartLeadTime", 0);
        purchasedComponentLeadTime.setText(String.valueOf(leadTime));

        assemblyID = getIntent().getIntExtra("purchasedPartAssemblyID", 0);

        assemblyPartsList.add(new AssemblyParts(0, "", "", 0,"" , false, ""));
        assemblyPartsList.addAll(repository.getmAllAssemblyParts());
        final ArrayAdapter<AssemblyParts> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, assemblyPartsList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerAssemblyName = findViewById(R.id.spinnerAssemblyName);
        spinnerAssemblyName.setAdapter(spinnerAdapter);

        if(assemblyID == 0){
            spinnerAssemblyName.setSelection(0);
        } else {
            spinnerAssemblyName.setSelection(assemblyID);
        }
        spinnerAssemblyName.setSelection(assemblyID);
        spinnerAssemblyName.setOnItemSelectedListener(this);
        spinnerAssemblyName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    assemblyParts = (AssemblyParts) parent.getSelectedItem();
                    assemblyID = assemblyParts.getPartID();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_purchased_components_details, menu);
        super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuDeletePurchasedPart) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Delete Purchased Component from system?");
            alert.setMessage("Are you sure you want to delete this purchased component from the system?");
            alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    for (PurchasedComponents purchasedComponents : repository.getmAllPurchasedComponents()) {
                        if (purchasedComponents.getPartID() == purchasedComponentID) {
                            PurchasedComponents selectedPurchasedPart = purchasedComponents;
                            repository.delete(selectedPurchasedPart);
                            Toast toast = Toast.makeText(getApplicationContext(), selectedPurchasedPart.getPartName() + " deleted", Toast.LENGTH_SHORT);
                            toast.show();

                            int purchasedComponentPosition = getIntent().getIntExtra("position", 0);
                            PurchasedComponentList.adapter.notifyItemChanged(purchasedComponentPosition);
                            PurchasedComponentList.purchasedComponentsList.clear();
                            PurchasedComponentList.purchasedComponentsList.addAll(repository.getmAllPurchasedComponents());
                            PurchasedComponentList.adapter.setPartsList(repository.getmAllPurchasedComponents());
                            finish();
                        }
                    }
                }
            });
            alert.setNegativeButton("no", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Purchased Component not deleted", Toast.LENGTH_LONG);
                    toast.show();
                }
            });
            alert.show();
        } else if (id == R.id.menuSavePurchasedPart) {
            name = purchasedComponentName.getText().toString();
            description = purchasedComponentDescription.getText().toString();
            quantity = Integer.parseInt(purchasedComponentQuantity.getText().toString());
            location = purchasedComponentSpinnerLocation.getSelectedItem().toString();
            vendor = purchasedComponentVendor.getText().toString();
            leadTime = Integer.parseInt(purchasedComponentLeadTime.getText().toString());

            assemblyPartsSelected = (AssemblyParts) spinnerAssemblyName.getSelectedItem();

            if (name.trim().isEmpty() || description.trim().isEmpty() || vendor.trim().isEmpty()) {
                Toast toast = Toast.makeText(getApplicationContext(), "Please fill out the following fields: name, description, and vendor", Toast.LENGTH_SHORT);
                toast.show();
                return false;
            }

            if (location.equals("Select a warehouse")) {
                location = null;
            }

            if (quantity > 50000) {
                Toast toast = Toast.makeText(getApplicationContext(), "Quantity cannot be greater than 50,000", Toast.LENGTH_SHORT);
                toast.show();
            } else if (leadTime > 365) {
                Toast toast = Toast.makeText(getApplicationContext(), "Lead time cannot be greater than 365 days", Toast.LENGTH_SHORT);
                toast.show();
            } else {
                if (repository.getmAllAssemblyParts().size() == 0) {
                    if (purchasedComponentID == 0) {
                        purchasedComponents = new PurchasedComponents(0, name, description, quantity, location, true, vendor, leadTime, assemblyID = 0);
                        repository.insert(purchasedComponents);
                        Toast toast = Toast.makeText(getApplicationContext(), purchasedComponentName.getText().toString() + " saved", Toast.LENGTH_SHORT);
                        toast.show();
                    } else {
                        purchasedComponents = new PurchasedComponents(purchasedComponentID, name, description, quantity, location, true, vendor, leadTime, assemblyID = 0);
                        repository.update(purchasedComponents);
                        Toast toast = Toast.makeText(getApplicationContext(), purchasedComponentName.getText().toString() + " saved", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                } else {
                    if (purchasedComponentID == 0) {
                        purchasedComponents = new PurchasedComponents(0, name, description, quantity, location, true, vendor, leadTime, assemblyID);
                        repository.insert(purchasedComponents);
                        Toast toast = Toast.makeText(getApplicationContext(), purchasedComponentName.getText().toString() + " saved", Toast.LENGTH_SHORT);
                        toast.show();
                    } else {
                        purchasedComponents = new PurchasedComponents(purchasedComponentID, name, description, quantity, location, true, vendor, leadTime, assemblyID);
                        repository.update(purchasedComponents);
                        Toast toast = Toast.makeText(getApplicationContext(), purchasedComponentName.getText().toString() + " saved", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            }
            int purchasedComponentPosition = getIntent().getIntExtra("position", 0);
            PurchasedComponentList.adapter.notifyItemChanged(purchasedComponentPosition);
            PurchasedComponentList.purchasedComponentsList.clear();
            PurchasedComponentList.purchasedComponentsList.addAll(repository.getmAllPurchasedComponents());
            PurchasedComponentList.adapter.setPartsList(repository.getmAllPurchasedComponents());
            finish();
        }
        return true;
    }
}