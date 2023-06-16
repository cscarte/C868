package com.example.C868.UI;

import android.annotation.SuppressLint;
import android.content.Context;
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
import com.example.C868.Logins.LoginBuyer;
import com.example.C868.Logins.LoginUser;
import com.example.c868.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText usernameEditText;
    EditText passwordEditText;

    List<AssemblyParts> assemblyPartsList;
    List<PurchasedComponents> purchasedComponentsList;

    PurchasedComponents purchasedComponents;

    AssemblyParts assemblyParts;

    static String username;
    String password;
    Button loginButton;
    Button loginBuyerButton;


    LoginBuyer loginBuyer = new LoginBuyer(null, null, false);

    LoginUser loginUser = new LoginUser(null, null, false);


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
                loginUser = new LoginUser(usernameEditText.getText().toString(), passwordEditText.getText().toString(), false);
                loginUser.loginUser(usernameEditText.getText().toString(), passwordEditText.getText().toString());
                if(loginUser.isValidUserPasswordCombo() == true){
                    username = usernameEditText.getText().toString();
                    Intent intent = new Intent(MainActivity.this, MainMenu.class);
                    intent.putExtra("username", username);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        loginBuyerButton = findViewById(R.id.loginBuyerButton);
        loginBuyerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginBuyer = new LoginBuyer(usernameEditText.getText().toString(), passwordEditText.getText().toString(), false);
                loginBuyer.loginUser(usernameEditText.getText().toString(), passwordEditText.getText().toString());
                if(loginBuyer.isValidUserPasswordCombo() == true){
                    username = usernameEditText.getText().toString();
                    Intent intent = new Intent(MainActivity.this, MainMenu.class);
                    intent.putExtra("username", username);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void enterMainMenu(View view) {
        Intent intent = new Intent(MainActivity.this, MainMenu.class);
        startActivity(intent);
    }
}