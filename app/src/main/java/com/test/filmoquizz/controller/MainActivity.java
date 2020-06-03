package com.test.filmoquizz.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.test.filmoquizz.R;

public class MainActivity extends AppCompatActivity {

    // On bind les éléments que nous allons avoir besoin.
    private EditText pseudoLogin;
    private EditText pseudoPassword;
    private Button loginButton;
    private EditText pseudoRegister;
    private EditText passwordRegister;
    private EditText confirmPasswordRegister;
    private Button registerButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // On vient connecter les différents éléments du layout sur notre méthode onCreate
        // Les éléments pour la connexion
        pseudoLogin = (EditText) findViewById(R.id.activity_main_login_username);
        pseudoPassword = (EditText) findViewById(R.id.activity_main_login_password);
        loginButton = (Button) findViewById(R.id.activity_main_login_submit);

        // Les éléments pour l'enregistrement
        pseudoRegister = (EditText) findViewById(R.id.activity_main_register_pseudo);
        passwordRegister = (EditText) findViewById(R.id.activity_main_register_password);
        confirmPasswordRegister = (EditText) findViewById(R.id.activity_main_register_pseudo_confirm);
        registerButton = (Button) findViewById(R.id.activity_main_register_submit);

    }
}