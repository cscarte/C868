package com.example.C868.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.C868.Database.Repository;
import com.example.C868.Entity.PurchasedComponents;
import com.example.c868.R;

import java.util.ArrayList;
import java.util.List;

public class PurchasedPartsTableAdapter extends RecyclerView.Adapter<PurchasedPartsTableAdapter.PurchasedPartsViewHolder> {
    static Context context;
    static List<PurchasedComponents> purchasedComponentsList = new ArrayList<>();
    static PurchasedPartsTableAdapter purchasedPartsTableAdapter;
    static PurchasedComponents purchasedComponents;
    static Repository repository;
    private final LayoutInflater mInflater;

    public PurchasedPartsTableAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public PurchasedPartsTableAdapter.PurchasedPartsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.row_table_purchased_components, parent, false);
        return new PurchasedPartsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PurchasedPartsTableAdapter.PurchasedPartsViewHolder holder, int position) {
        if (purchasedComponentsList != null && purchasedComponentsList.size() > 0) {
            purchasedComponents = purchasedComponentsList.get(position);
            holder.purchasedPartName.setText(purchasedComponents.getPartName());
            holder.purchasedPartDescription.setText(purchasedComponents.getPartDescription());
            holder.purchasedPartQuantity.setText(String.valueOf(purchasedComponents.getPartQty()));
        }
    }

    @Override
    public int getItemCount() {
        return purchasedComponentsList.size();
    }


    public class PurchasedPartsViewHolder extends RecyclerView.ViewHolder {
        private final TextView purchasedPartName;
        private final TextView purchasedPartDescription;
        private final TextView purchasedPartQuantity;

        public PurchasedPartsViewHolder(@NonNull View itemView) {
            super(itemView);
            purchasedPartName = itemView.findViewById(R.id.tableRowPurchasedComponentName);
            purchasedPartDescription = itemView.findViewById(R.id.tableRowPurchasedComponentDescription);
            purchasedPartQuantity = itemView.findViewById(R.id.tableRowPurchasedComponentQtyOnHand);
        }
    }

    public void setPurchasedComponents(List<PurchasedComponents> purchasedComponentsList) {
        PurchasedPartsTableAdapter.purchasedComponentsList = purchasedComponentsList;
        notifyDataSetChanged();
    }
}
