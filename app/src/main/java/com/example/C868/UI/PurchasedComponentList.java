package com.example.C868.UI;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.C868.Adapters.PurchasedPartsAdapter;
import com.example.C868.Database.Repository;
import com.example.C868.Entity.PurchasedComponents;
import com.example.c868.R;

import java.util.List;

public class PurchasedComponentList extends AppCompatActivity {
    static RecyclerView recyclerView;
    static PurchasedPartsAdapter adapter;
    static List<PurchasedComponents> purchasedComponentsList;
    static Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchased_components_list);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Toolbar toolbar = findViewById(R.id.toolbarPartsLIst);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerViewComponentParts);
        repository = new Repository(getApplication());

        purchasedComponentsList = repository.getmAllPurchasedComponents();

        adapter = new PurchasedPartsAdapter(this);
        recyclerView.setAdapter(adapter);

        //change 1st parameter of method setPartsList from List<parts> to List<PurchasedComponents>
        adapter.setPartsList(purchasedComponentsList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void onResume() {
        super.onResume();
        PurchasedPartsAdapter.clickEnabled = true;
    }

    public void onBackPressed() {
        super.onBackPressed();
        PurchasedPartsAdapter.clickEnabled = true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_purchased_components_list, menu);
        super.onCreateOptionsMenu(menu);
        return true;
    }

    public void enterAddPurchasedComponent(MenuItem item) {
        Intent intent = new Intent(PurchasedComponentList.this, PurchasedComponentDetails.class);
        startActivity(intent);
    }
}