package com.example.C868.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.C868.Entity.AssemblyParts;
import com.example.C868.Entity.PurchasedComponents;
import com.example.c868.R;

import java.util.ArrayList;
import java.util.List;

public class PurchasedPartsAdapterAssemblyDetails extends RecyclerView.Adapter<PurchasedPartsAdapterAssemblyDetails.PartsViewHolder> {
    private final Context context;
    private static List<PurchasedComponents> purchasedComponentsList = new ArrayList<>();

    private List<AssemblyParts> assemblyPartsList = new ArrayList<>();
    private final LayoutInflater mInflater;
    public static boolean clickEnabled = true;

    public PurchasedPartsAdapterAssemblyDetails(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public PartsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.row_purchased_components_assembly_details, parent, false);
        return new PartsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PurchasedPartsAdapterAssemblyDetails.PartsViewHolder holder, int position) {
        if (purchasedComponentsList != null) {
            PurchasedComponents current = purchasedComponentsList.get(position);
            String name = current.getPartName();

            holder.partsItemView.setText(name);
        } else {
            holder.partsItemView.setText("No Parts");
        }
        holder.itemView.setOnClickListener(v -> {
            //holder.checkBox.performClick();
        });
    }

    @Override
    public int getItemCount() {
        if (purchasedComponentsList != null) {
            return purchasedComponentsList.size();
        } else return 0;
    }

    public void setPartsList(List<PurchasedComponents> partsList) {
        purchasedComponentsList = partsList;
        notifyDataSetChanged();
    }

    public void deleteItem(int position) {
        purchasedComponentsList.remove(position);
        notifyItemRemoved(position);
    }

    class PartsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView partsItemView;
        public CheckBox checkBox;

        private PartsViewHolder(View view) {
            super(view);
            partsItemView = itemView.findViewById(R.id.partDetailsAssemblyDetailsTextView);
            //checkBox = itemView.findViewById(R.id.checkBoxPurchasedPartsAssemblyDetails);
            partsItemView.setOnClickListener(v -> {
                //checkBox.performClick();
            });
        }

        @Override
        public void onClick(View v) {

        }
    }
}