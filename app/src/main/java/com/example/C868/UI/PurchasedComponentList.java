package com.example.C868.UI;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.C868.Adapters.PurchasedPartsAdapter;
import com.example.C868.Database.Repository;
import com.example.C868.Entity.PurchasedComponents;
import com.example.c868.R;

import java.util.List;
import java.util.Objects;

public class PurchasedComponentList extends AppCompatActivity {
    static RecyclerView recyclerView;
    static PurchasedPartsAdapter adapter;
    static List<PurchasedComponents> purchasedComponentsList;
    static Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchased_components);

        //Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recyclerViewPurchasedParts);
        repository = new Repository(getApplication());

        purchasedComponentsList = repository.getmAllPurchasedComponents();

        adapter = new PurchasedPartsAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new androidx.recyclerview.widget.LinearLayoutManager(this));

        //change 1st parameter of method setPartsList from List<parts> to List<PurchasedComponents>
        adapter.setPartsList(purchasedComponentsList);
    }

    protected void onResume() {
        super.onResume();
        PurchasedPartsAdapter.clickEnabled = true;
    }
}