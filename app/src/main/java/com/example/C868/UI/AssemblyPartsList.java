package com.example.C868.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.C868.Adapters.AssemblyPartsAdapter;
import com.example.C868.Database.Repository;
import com.example.C868.Entity.AssemblyParts;
import com.example.c868.R;

import java.util.List;

public class AssemblyPartsList extends AppCompatActivity {

    static RecyclerView recyclerView;
    static List<AssemblyParts> assemblyPartsList;
    static Repository repository;
    static AssemblyPartsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assembly_parts_list);

        Toolbar toolbar = findViewById(R.id.toolbarPartsLIst);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerViewAssemblyParts);
        repository = new Repository(getApplication());

        assemblyPartsList = repository.getmAllAssemblyParts();

        adapter = new AssemblyPartsAdapter(this);
        recyclerView.setAdapter(adapter);

        //change 1st parameter of method setPartsList from List<parts> to List<AssemblyParts>
        adapter.setAssemblyPartsList(assemblyPartsList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void onResume() {
        super.onResume();
        AssemblyPartsAdapter.clickEnabled = true;
    }

    public void onBackPressed() {
        super.onBackPressed();
        AssemblyPartsAdapter.clickEnabled = true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_assembly_parts_list, menu);
        super.onCreateOptionsMenu(menu);
        return true;
    }

    public void enterAddAssemblyPart(MenuItem menuItem) {
        Intent intent = new Intent(AssemblyPartsList.this, AssemblyPartsDetails.class);
        startActivity(intent);
    }
}