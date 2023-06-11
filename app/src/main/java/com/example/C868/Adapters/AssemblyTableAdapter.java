package com.example.C868.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.C868.Entity.AssemblyParts;
import com.example.c868.R;

import java.util.ArrayList;
import java.util.List;

public class AssemblyTableAdapter extends RecyclerView.Adapter<AssemblyTableAdapter.AssemblyViewHolder> {

    Context context;
    List<AssemblyParts> assemblyPartsList = new ArrayList<>();
    AssemblyParts assemblyParts;


    @NonNull
    @Override
    public AssemblyTableAdapter.AssemblyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_table_assemblies, parent, false);
        return new AssemblyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AssemblyTableAdapter.AssemblyViewHolder holder, int position) {
        if (assemblyPartsList != null && assemblyPartsList.size() > 0) {
            assemblyParts = assemblyPartsList.get(position);
            holder.assemblyPartName.setText(assemblyParts.getPartName());
            holder.assemblyPartDescription.setText(assemblyParts.getPartDescription());
            holder.assemblyPartQuantity.setText(assemblyParts.getPartQty());
        }
    }

    @Override
    public int getItemCount() {
        return assemblyPartsList.size();
    }

    public class AssemblyViewHolder extends RecyclerView.ViewHolder {
        TextView assemblyPartName;
        TextView assemblyPartDescription;
        TextView assemblyPartQuantity;

        public AssemblyViewHolder(@NonNull View itemView) {
            super(itemView);
            assemblyPartName = itemView.findViewById(R.id.tableRowAssemblyName);
            assemblyPartDescription = itemView.findViewById(R.id.tableRowAssemblyDescription);
            assemblyPartQuantity = itemView.findViewById(R.id.tableRowAssemblyQtyOnHand);
        }
    }
}
