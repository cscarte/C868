package com.example.C868.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.C868.Database.Repository;
import com.example.C868.Entity.AssemblyParts;
import com.example.c868.R;

import java.util.ArrayList;
import java.util.List;

public class AssemblyTableAdapter extends RecyclerView.Adapter<AssemblyTableAdapter.AssemblyViewHolder> {

    static Context context;
    static List<AssemblyParts> assemblyPartsList = new ArrayList<>();
    static AssemblyParts assemblyParts;

    static Repository repository;

    private final LayoutInflater mInflater;

    public AssemblyTableAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public AssemblyTableAdapter.AssemblyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.row_table_assemblies, parent, false);
        return new AssemblyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AssemblyTableAdapter.AssemblyViewHolder holder, int position) {
        if (assemblyPartsList != null && assemblyPartsList.size() > 0) {
            assemblyParts = assemblyPartsList.get(position);
            holder.assemblyPartName.setText(assemblyParts.getPartName());
            holder.assemblyPartDescription.setText(assemblyParts.getPartDescription());
            holder.assemblyPartQuantity.setText(String.valueOf(assemblyParts.getPartQty()));
        }
    }

    @Override
    public int getItemCount() {
        return assemblyPartsList.size();
    }

    public void setAssemblyParts(List<AssemblyParts> assemblyPartsList) {
        AssemblyTableAdapter.assemblyPartsList = assemblyPartsList;
        notifyDataSetChanged();
    }


    public class AssemblyViewHolder extends RecyclerView.ViewHolder {
        private final TextView assemblyPartName;
        private final TextView assemblyPartDescription;
        private final TextView assemblyPartQuantity;

        public AssemblyViewHolder(@NonNull View itemView) {
            super(itemView);
            assemblyPartName = itemView.findViewById(R.id.tableRowAName);
            assemblyPartDescription = itemView.findViewById(R.id.tableRowADesription);
            assemblyPartQuantity = itemView.findViewById(R.id.tableRowAQtyOnHand);
        }
    }
}
