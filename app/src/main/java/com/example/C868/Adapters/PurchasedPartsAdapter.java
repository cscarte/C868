package com.example.C868.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.C868.Entity.AssemblyParts;
import com.example.C868.Entity.Parts;
import com.example.C868.Entity.PurchasedComponents;
import com.example.c868.R;

import java.util.ArrayList;
import java.util.List;

public class PurchasedPartsAdapter extends RecyclerView.Adapter<PurchasedPartsAdapter.PartsViewHolder> {
    private final Context context;
    private List<PurchasedComponents> purchasedComponentsList = new ArrayList<>();
    private List<AssemblyParts> assemblyPartsList = new ArrayList<>();

    private List<Parts> partsList = new ArrayList<>();
    private final LayoutInflater mInflater;
    private long lastTimeClicked;
    private static final long clickTimeInterval = 300;
    public static boolean clickEnabled = true;

    public PurchasedPartsAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public PartsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.activity_purchased_parts_row, parent, false);
        return new PartsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PartsViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        if (purchasedComponentsList != null || assemblyPartsList != null) {
            partsList.addAll(purchasedComponentsList);
            partsList.addAll(assemblyPartsList);
            System.out.println("There are " + partsList + " parts in the system");
            return partsList.size();
        } else return 0;
    }

    public void setPartsList(List<Parts> partsList) {
        partsList.addAll(assemblyPartsList);
        partsList.addAll(purchasedComponentsList);
    }

    class PartsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView partsItemView;

        public PartsViewHolder(View view) {
            super(view);
            partsItemView = itemView.findViewById(R.id.partDetailsTextView);
            partsItemView.setOnClickListener(v -> {
                long now = System.currentTimeMillis();
                if (clickEnabled) {
                    clickEnabled = false;
                    int position = getAdapterPosition();
                }
            });
        }

        @Override
        public void onClick(View v) {

        }
    }
}