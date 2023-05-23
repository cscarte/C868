package com.example.C868.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.C868.Entity.AssemblyParts;
import com.example.C868.Entity.PurchasedComponents;
import com.example.c868.R;

import java.util.ArrayList;
import java.util.List;

public class PartsAdapter {
    private final Context context;
    private List<PurchasedComponents> purchasedComponentsList = new ArrayList<>();
    private List<AssemblyParts> assemblyPartsList = new ArrayList<>();
    private final LayoutInflater mInflater;
    private long lastTimeClicked;
    private static final long clickTimeInterval = 300;
    public static boolean clickEnabled = true;

    public PartsAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
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
                    }
            );
        }

        @Override
        public void onClick(View v) {

        }
    }
}
