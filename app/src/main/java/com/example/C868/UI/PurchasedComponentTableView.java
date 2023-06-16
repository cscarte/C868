package com.example.C868.UI;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.C868.Adapters.PurchasedPartsTableAdapter;
import com.example.C868.Database.Repository;
import com.example.C868.Entity.PurchasedComponents;
import com.example.c868.R;

import java.sql.Date;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

public class PurchasedComponentTableView extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PurchasedPartsTableAdapter purchasedPartsTableAdapter;
    private Repository repository = new Repository(getApplication());
    private List<PurchasedComponents> purchasedComponentsList = new ArrayList<>();
    private SearchView searchView;
    long timestamp = System.currentTimeMillis();
    Date currentTime = new Date(timestamp);
    TextView currentTimeText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_purchased_components);
        purchasedComponentsList = repository.getmAllPurchasedComponents();

        String currentDateTimeString = DateFormat.getDateTimeInstance().format(timestamp);
        String[] split = currentDateTimeString.split(" ");

        currentTimeText = findViewById(R.id.currentTimeTextView);
        currentTimeText.setText(split[0] + " " + split[1] + " " + split[2] + " " + split[3]);

        searchView = findViewById(R.id.tableSearchBarPurchasedComponents);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            public boolean onQueryTextSubmit(String query) {
                if (query.isEmpty()) {
                    purchasedComponentsList.clear();
                    purchasedComponentsList = repository.getmAllPurchasedComponents();
                    purchasedPartsTableAdapter.setPurchasedComponents(purchasedComponentsList);
                } else {
                    //filterText(query);
                }
                return false;
            }

            public boolean onQueryTextChange(String newText) {
                filterTextPurchasedParts(newText);
                return false;
            }
        });

        recyclerView = findViewById(R.id.tablePurchasedComponentsRecyclerView);
        purchasedPartsTableAdapter = new PurchasedPartsTableAdapter(this);
        recyclerView.setAdapter(purchasedPartsTableAdapter);
        purchasedPartsTableAdapter.setPurchasedComponents(purchasedComponentsList);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void filterTextPurchasedParts(String newText) {
        List<PurchasedComponents> filteredList = new ArrayList<>();
        for (PurchasedComponents purchasedComponents : purchasedComponentsList) {
            if (purchasedComponents.getPartName().toLowerCase().contains(newText.toLowerCase())) {
                filteredList.add(purchasedComponents);
            }
        }
        if (filteredList.isEmpty()) {
            purchasedPartsTableAdapter.setPurchasedComponents(purchasedComponentsList);
        }
        purchasedPartsTableAdapter.setPurchasedComponents(filteredList);
    }
}