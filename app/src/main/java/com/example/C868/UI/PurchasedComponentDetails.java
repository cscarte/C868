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
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.C868.Adapters.PurchasedPartsAdapter;
import com.example.C868.DAO.PurchasedComponentsDAO;
import com.example.C868.Database.Repository;
import com.example.C868.Entity.AssemblyParts;
import com.example.C868.Entity.PurchasedComponents;
import com.example.c868.R;

import java.util.ArrayList;
import java.util.List;

public class PurchasedComponentDetails extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText purchasedComponentName;
    EditText purchasedComponentDescription;
    EditText purchasedComponentPrice;
    EditText purchasedComponentQuantity;

    EditText purchasedComponentLocation;
    EditText purchasedComponentVendor;
    EditText purchasedComponentLeadTime;

    Repository repository = new Repository(getApplication());

    PurchasedComponents purchasedComponents;

    int purchasedComponentID;
    String name;
    String description;
    int quantity;
    String location;
    String vendor;
    int leadTime;

    int assemblyID;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchased_components_details);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Toolbar toolbar = findViewById(R.id.toolbarPurchasedComponentsDetails);
        setSupportActionBar(toolbar);

        purchasedComponentID = getIntent().getIntExtra("partID", 0);

        //Get purchased component part name from clicked item in list on the PurchasedComponentList.java screen
        purchasedComponentName = findViewById(R.id.editTextViewPurchasedComponentName);
        name = getIntent().getStringExtra("partName");
        purchasedComponentName.setText(name);

        //Get purchased component part description from clicked item in list on the PurchasedComponentList.java screen
        purchasedComponentDescription = findViewById(R.id.editTextViewPurchasedComponentDescription2);
        description = getIntent().getStringExtra("partDescription");
        purchasedComponentDescription.setText(description);

        //Get purchased component part quantity from clicked item in list on the PurchasedComponentList.java screen
        purchasedComponentQuantity = findViewById(R.id.editTextViewPurchasedComponentQuantity);
        quantity = getIntent().getIntExtra("partQty", 0);
        purchasedComponentQuantity.setText(String.valueOf(quantity));

        //Get purchased component part location from clicked item in list on the PurchasedComponentList.java screen
        purchasedComponentLocation = findViewById(R.id.editTextViewPurchasedComponentLocation);
        location = getIntent().getStringExtra("partLocation");
        purchasedComponentLocation.setText(location);

        //Get purchased component part vendor from clicked item in list on the PurchasedComponentList.java screen
        purchasedComponentVendor = findViewById(R.id.editTextViewPurchasedComponentVendor2);
        vendor = getIntent().getStringExtra("partVendor");
        purchasedComponentVendor.setText(vendor);

        //Get purchased component part lead time from clicked item in list on the PurchasedComponentList.java screen
        purchasedComponentLeadTime = findViewById(R.id.editTextViewPurchasedComponentLeadTime);
        leadTime = getIntent().getIntExtra("partLeadTime", 0);
        purchasedComponentLeadTime.setText(String.valueOf(leadTime));

        assemblyID = getIntent().getIntExtra("purchasedPartAssemblyID", 0);
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
                            PurchasedComponentList.adapter.notifyItemRemoved(purchasedComponentPosition);
                            PurchasedComponentList.adapter.deleteItem(purchasedComponentPosition);
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
            if (repository.getmAllAssemblyParts().size() == 0) {
                if (purchasedComponentID == -1) {
                    purchasedComponents = new PurchasedComponents(0, purchasedComponentName.getText().toString(), purchasedComponentDescription.getText().toString(), Integer.parseInt(purchasedComponentQuantity.getText().toString()), purchasedComponentLocation.getText().toString(), true, purchasedComponentVendor.getText().toString(), Integer.parseInt(purchasedComponentLeadTime.getText().toString()), assemblyID = 0);
                    repository.insert(purchasedComponents);
                } else {
                    purchasedComponents = new PurchasedComponents(purchasedComponentID, purchasedComponentName.getText().toString(), purchasedComponentDescription.getText().toString(), Integer.parseInt(purchasedComponentQuantity.getText().toString()), purchasedComponentLocation.getText().toString(), true, purchasedComponentVendor.getText().toString(), Integer.parseInt(purchasedComponentLeadTime.getText().toString()), assemblyID = 0);
                    repository.update(purchasedComponents);
                }
            } else {
                if (purchasedComponentID == -1) {
                    purchasedComponents = new PurchasedComponents(0, purchasedComponentName.getText().toString(), purchasedComponentDescription.getText().toString(), Integer.parseInt(purchasedComponentQuantity.getText().toString()), purchasedComponentLocation.getText().toString(), true, purchasedComponentVendor.getText().toString(), Integer.parseInt(purchasedComponentLeadTime.getText().toString()), assemblyID);
                    repository.insert(purchasedComponents);
                } else {
                    purchasedComponents = new PurchasedComponents(purchasedComponentID, purchasedComponentName.getText().toString(), purchasedComponentDescription.getText().toString(), Integer.parseInt(purchasedComponentQuantity.getText().toString()), purchasedComponentLocation.getText().toString(), true, purchasedComponentVendor.getText().toString(), Integer.parseInt(purchasedComponentLeadTime.getText().toString()), assemblyID);
                    repository.update(purchasedComponents);
                }
            }

            System.out.println("Purchased Component ID: " + purchasedComponentID);
            Toast toast = Toast.makeText(getApplicationContext(), "Purchased Component saved", Toast.LENGTH_SHORT);
            toast.show();
        }
        return true;
    }
}