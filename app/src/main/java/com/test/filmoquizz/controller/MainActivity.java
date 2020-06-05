package com.test.filmoquizz.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import com.test.filmoquizz.R;
import com.test.filmoquizz.dao.DatabaseHelper;
import com.test.filmoquizz.model.User;


public class MainActivity extends OrmLiteBaseActivity<DatabaseHelper> {

    // Les préferences utilisateurs
    private SharedPreferences preferences;

    // On bind les éléments que nous allons avoir besoin.
    private EditText pseudoLogin;
    private EditText passwordLogin;
    private Button loginButton;
    private EditText pseudoRegister;
    private EditText passwordRegister;
    private EditText confirmPasswordRegister;
    private Button registerButton;

    // Constantes que nous utiliserons pour cette activity
    public static final String KEY_PREF_ID = "KEY_PREF_ID";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // On initialise l'accès au préférence, même si c'est le mode par défaut imposé par android de nos jours
        preferences = getPreferences(MODE_PRIVATE);

        // On vient connecter les différents éléments du layout sur notre méthode onCreate
        // Les éléments pour la connexion
        pseudoLogin = (EditText) findViewById(R.id.activity_main_login_username);
        passwordLogin = (EditText) findViewById(R.id.activity_main_login_password);
        loginButton = (Button) findViewById(R.id.activity_main_login_submit);

        // Les éléments pour l'enregistrement
        pseudoRegister = (EditText) findViewById(R.id.activity_main_register_pseudo);
        passwordRegister = (EditText) findViewById(R.id.activity_main_register_password);
        confirmPasswordRegister = (EditText) findViewById(R.id.activity_main_register_pseudo_confirm);
        registerButton = (Button) findViewById(R.id.activity_main_register_submit);

        // Notre Data Access Object pour l'utilisateur
        final RuntimeExceptionDao<User, Integer> userDao = getHelper().getUserRuntimeExceptionDao();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // On implémente notre logique de connexion ici
                // On récupère les différentes valeurs des champs
                String pseudo = pseudoLogin.getText().toString();
                String password = passwordLogin.getText().toString();

                for(User user : userDao) {
                    if(user.getPseudo().equals(pseudo) && user.getPassword().equals(password)) {
                        preferences.edit().putInt("user_id", user.getId()).apply();
                        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                        startActivity(intent);
                        return;
                    }
                }
                Toast.makeText(MainActivity.this, "Erreur de connexion", Toast.LENGTH_SHORT).show();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pseudo = pseudoRegister.getText().toString();
                String password = passwordRegister.getText().toString();
                String confirm = confirmPasswordRegister.getText().toString();
                if(!password.equals(confirm)) {
                    // Si le mot de passe ne correspond pas à la confirmation, on affiche un petit message d'erreur gentil
                    Toast.makeText(MainActivity.this, "Vos mots de passe ne sont pas identique!", Toast.LENGTH_SHORT).show();
                    // On remet les champs vides pour une meilleur expérience utilisateur
                    passwordRegister.setText("");
                    confirmPasswordRegister.setText("");
                } else if(pseudo.length() < 1 || password.length() < 1) {
                    // On test quand même que l'utilisateur entre des valeurs.
                    Toast.makeText(MainActivity.this, "Vous ne pouvez pas mettre de pseudo ou de mot de passe vide!", Toast.LENGTH_SHORT).show();
                } else {
                    User user = new User(pseudo, password);
                    try {
                        // On créé notre utilisateur dans la base de donnée
                        userDao.create(user);
                        // On démarre une nouvelle activité
                        System.out.println("User id nouvellement créé: " + user.getId());
                        // On le stock dans les préférences pour pouvoir le retrouver dans l'application et faire des recherches croisées dans notre base de donnée.
                        preferences.edit().putInt("user_id", user.getId()).apply();
                        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                        startActivity(intent);
                    } catch(RuntimeException e) {
                        System.out.println("Cet utilisateur existe déjà en base de donnée");
                    }
                }
            }
        });

    }
}