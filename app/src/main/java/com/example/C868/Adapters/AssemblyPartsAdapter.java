package com.example.C868.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.C868.Entity.AssemblyParts;
import com.example.C868.UI.AssemblyPartsDetails;
import com.example.c868.R;

import java.util.ArrayList;
import java.util.List;

public class AssemblyPartsAdapter extends RecyclerView.Adapter<AssemblyPartsAdapter.AssemblyPartsViewHolder> {
    private final Context context;

    private List<AssemblyParts> assemblyPartsList = new ArrayList<>();

    private final LayoutInflater mInflater;
    public static boolean clickEnabled = true;

    public AssemblyPartsAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public AssemblyPartsAdapter.AssemblyPartsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.row_assembly_parts, parent, false);
        return new AssemblyPartsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AssemblyPartsViewHolder holder, int position) {
        if (assemblyPartsList != null) {
            AssemblyParts current = assemblyPartsList.get(position);
            String name = current.getPartName();
            holder.assemblyPartsItemView.setText(name);
        }
    }

    @Override
    public int getItemCount(){
        if (assemblyPartsList != null) {
            return assemblyPartsList.size();
        } else return 0;
    }

    public void setAssemblyPartsList(List<AssemblyParts> partsList) {
        assemblyPartsList = partsList;
        notifyDataSetChanged();
    }

    public void deleteItem(int position) {
        assemblyPartsList.remove(position);
        notifyItemRemoved(position);
    }

    class AssemblyPartsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView assemblyPartsItemView;

        public AssemblyPartsViewHolder(View view) {
            super(view);
            assemblyPartsItemView = view.findViewById(R.id.rowAssemblyPartsTextView);
            assemblyPartsItemView.setOnClickListener(v -> {
                if (clickEnabled) {
                    clickEnabled = false;
                    int position = getAdapterPosition();
                    int count = getItemCount();
                    final AssemblyParts current = assemblyPartsList.get(position);
                    Intent intent = new Intent(context, AssemblyPartsDetails.class);
                    intent.putExtra("position", position);
                    intent.putExtra("count", count);
                    intent.putExtra("partID", current.getPartID());
                    intent.putExtra("partName", current.getPartName());
                    intent.putExtra("partDescription", current.getPartDescription());
                    intent.putExtra("partQty", current.getPartQty());
                    intent.putExtra("partLocation", current.getPartLocation());
                    intent.putExtra("partPurchased", current.getPartPurchased());
                    intent.putExtra("resourceName", current.getResourceName());
                    context.startActivity(intent);
                }
            });
        }

        @Override
        public void onClick(View v) {

        }
    }
}
