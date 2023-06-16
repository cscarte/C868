package com.example.C868.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.c868.R;

public class AssemblyTransactionSelectionMenu extends AppCompatActivity {
    Button addAssemblyTransactionButton;
    Button subtractAssemblyTransactionButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assembly_transaction_selection_menu);

        addAssemblyTransactionButton = findViewById(R.id.buttonAdditionScreen);
        subtractAssemblyTransactionButton = findViewById(R.id.buttonSubtractionScreen);
    }

    public void enterAdditionScreen(View view) {
        Intent intent = new Intent(AssemblyTransactionSelectionMenu.this, AssemblyAdditionTransaction.class);
        startActivity(intent);
    }

    public void enterSubtractionScreen(View view) {
        Intent intent = new Intent(AssemblyTransactionSelectionMenu.this, AssemblySubtractionTransaction.class);
        startActivity(intent);
    }
}
