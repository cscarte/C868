package com.example.C868.UI;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.C868.Adapters.PurchasedPartsAdapter;
import com.example.C868.Entity.Parts;
import com.example.c868.R;

import java.util.List;
import java.util.Objects;

public class PurchasedPartsList extends AppCompatActivity {
    static RecyclerView recyclerView;
    static PurchasedPartsAdapter adapter;
    static List<Parts> partsList;
    //static Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchased_parts);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recyclerViewPurchasedParts);
    }
}