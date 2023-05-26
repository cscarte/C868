package com.example.C868.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.C868.Entity.PurchasedComponents;
import com.example.C868.UI.PurchasedComponentDetails;
import com.example.c868.R;

import java.util.ArrayList;
import java.util.List;

public class PurchasedPartsAdapter extends RecyclerView.Adapter<PurchasedPartsAdapter.PartsViewHolder> {
    private final Context context;
    private List<PurchasedComponents> purchasedComponentsList = new ArrayList<>();

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
        View view = mInflater.inflate(R.layout.activity_purchased_components_row, parent, false);
        return new PartsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PartsViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        if (purchasedComponentsList != null) {
            purchasedComponentsList.addAll(purchasedComponentsList);
            System.out.println("There are " + purchasedComponentsList.size() + " parts in the system");
            return purchasedComponentsList.size();
        } else return 0;
    }

    public void setPartsList(List<PurchasedComponents> partsList) {
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
                    Intent intent = new Intent(context, PurchasedComponentDetails.class);
                    intent.putExtra("partID", purchasedComponentsList.get(position).getPartID());
                    intent.putExtra("partName", purchasedComponentsList.get(position).getPartName());
                    intent.putExtra("partDescription", purchasedComponentsList.get(position).getPartDescription());
                    intent.putExtra("partQty", purchasedComponentsList.get(position).getPartQty());
                    intent.putExtra("partLocation", purchasedComponentsList.get(position).getPartLocation());
                    intent.putExtra("partPurchased", purchasedComponentsList.get(position).getPartPurchased());
                    intent.putExtra("purchasedPartVendor", purchasedComponentsList.get(position).getPurchasedPartVendor());
                    intent.putExtra("purchasedPartLeadTime", purchasedComponentsList.get(position).getPurchasedPartLeadTimeDays());
                    intent.putExtra("purchasedPartAssemblyID", purchasedComponentsList.get(position).getPurchasedPartAssemblyID());
                    context.startActivity(intent);
                }
            });
        }

        @Override
        public void onClick(View v) {

        }
    }
}