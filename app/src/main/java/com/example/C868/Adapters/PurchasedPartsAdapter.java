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
    public static boolean clickEnabled = true;

    public PurchasedPartsAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public PartsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.row_purchased_components, parent, false);
        return new PartsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PurchasedPartsAdapter.PartsViewHolder holder, int position) {
        if (purchasedComponentsList != null) {
            PurchasedComponents current = purchasedComponentsList.get(position);
            String name = current.getPartName();
            holder.partsItemView.setText(name);
        } else {
            holder.partsItemView.setText("No Parts");
        }
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

        private PartsViewHolder(View view) {
            super(view);
            partsItemView = itemView.findViewById(R.id.partDetailsTextView);
            partsItemView.setOnClickListener(v -> {
                long now = System.currentTimeMillis();
                if (clickEnabled) {
                    clickEnabled = false;
                    int position = getAdapterPosition();
                    int count = getItemCount();
                    final PurchasedComponents current = purchasedComponentsList.get(position);
                    Intent intent = new Intent(context, PurchasedComponentDetails.class);
                    intent.putExtra("position", position);
                    intent.putExtra("count", count);
                    intent.putExtra("partID", current.getPartID());
                    intent.putExtra("partName", current.getPartName());
                    intent.putExtra("partDescription", current.getPartDescription());
                    intent.putExtra("partQty", current.getPartQty());
                    intent.putExtra("partLocation", current.getPartLocation());
                    intent.putExtra("partPurchased", current.getPartPurchased());
                    intent.putExtra("purchasedPartVendor", current.getPurchasedPartVendor());
                    intent.putExtra("purchasedPartLeadTime", current.getPurchasedPartLeadTimeDays());
                    intent.putExtra("purchasedPartAssemblyID", current.getPurchasedPartAssemblyID());
                    context.startActivity(intent);
                }
            });
        }

        @Override
        public void onClick(View v) {

        }
    }
}