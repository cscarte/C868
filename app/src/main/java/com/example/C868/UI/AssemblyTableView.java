package com.example.C868.UI;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.C868.Adapters.AssemblyTableAdapter;
import com.example.C868.Database.Repository;
import com.example.C868.Entity.AssemblyParts;
import com.example.c868.R;

import java.util.ArrayList;
import java.util.List;

public class AssemblyTableView extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AssemblyTableAdapter assemblyTableAdapter;

    private Repository repository = new Repository(getApplication());

    private List<AssemblyParts> assemblyPartsList = new ArrayList<>();

    private SearchView searchView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_assemblies);

        assemblyPartsList = repository.getmAllAssemblyParts();
        searchView = findViewById(R.id.tableSearchBar);
        //searchView.setQueryHint("Search by Part Name");
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            public boolean onQueryTextSubmit(String query) {
                filterText(query);
                return false;
            }

            public boolean onQueryTextChange(String newText) {
                filterText(newText);
                return false;
            }
        });
        recyclerView = findViewById(R.id.tableAssemblyPartsRecyclerView);
        assemblyTableAdapter = new AssemblyTableAdapter(this);
        recyclerView.setAdapter(assemblyTableAdapter);
        assemblyTableAdapter.setAssemblyParts(assemblyPartsList);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void filterText(String newText) {
        List<AssemblyParts> filteredList = new ArrayList<>();
        for (AssemblyParts assemblyParts : assemblyPartsList) {
            if (assemblyParts.getPartName().toLowerCase().contains(newText.toLowerCase())) {
                filteredList.add(assemblyParts);
            }
        }
        if (filteredList.isEmpty()) {
            Toast toast = Toast.makeText(getApplicationContext(), "No matches found", Toast.LENGTH_SHORT);
        } else {
            assemblyTableAdapter.setAssemblyParts(filteredList);
        }
    }
}
