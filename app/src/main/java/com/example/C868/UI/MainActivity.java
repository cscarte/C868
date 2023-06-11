package com.example.C868.UI;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.C868.Database.Repository;
import com.example.C868.Entity.AssemblyParts;
import com.example.C868.Entity.PurchasedComponents;
import com.example.c868.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText usernameEditText;
    EditText passwordEditText;

    List<AssemblyParts> assemblyPartsList;
    List<PurchasedComponents> purchasedComponentsList;

    PurchasedComponents purchasedComponents;

    AssemblyParts assemblyParts;

    String username;
    String password;
    Button loginButton;

    @SuppressLint({"ResourceType", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
        loginButton = findViewById(R.id.loginButton);

        Repository repository = new Repository(getApplication());
        assemblyPartsList = repository.getmAllAssemblyParts();
        purchasedComponentsList = repository.getmAllPurchasedComponents();

        Toolbar toolbar = findViewById(R.id.menuToolbar);
        setSupportActionBar(toolbar);

        usernameEditText = findViewById(R.id.userNameEditText);
        passwordEditText = findViewById(R.id.editTextTextPassword);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = usernameEditText.getText().toString();
                password = passwordEditText.getText().toString();
                if (username.equals("test") && password.equals("test")) {
                    enterMainMenu(v);
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Invalid Username or Password", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
    }

    public void enterMainMenu(View view) {
        Intent intent = new Intent(MainActivity.this, MainMenu.class);
        startActivity(intent);
    }
}