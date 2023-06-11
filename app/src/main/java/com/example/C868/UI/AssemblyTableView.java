package com.example.C868.UI;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.C868.Adapters.AssemblyTableAdapter;
import com.example.c868.R;

public class AssemblyTableView extends AppCompatActivity {
    RecyclerView recyclerView;
    AssemblyTableAdapter assemblyTableAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_assemblies);

        recyclerView = findViewById(R.id.tableAssemblyPartsRecyclerView);
        assemblyTableAdapter = new AssemblyTableAdapter();
        recyclerView.setAdapter(assemblyTableAdapter);
    }
}
