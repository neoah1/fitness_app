package com.example.yogademo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    Button button, button1, button2;
    TextView textView;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Zorgt voor goede weergave van de systeembalken
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        auth = FirebaseAuth.getInstance();
        button = findViewById(R.id.logout);
        textView = findViewById(R.id.user_details);
        user = auth.getCurrentUser();

        // Controleer of de gebruiker ingelogd is
        if (user == null) {
            // Als de gebruiker niet is ingelogd, ga naar het login-scherm
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish(); // Voorkom dat de gebruiker terug kan keren naar dit scherm
        } else {
            // Als de gebruiker wel is ingelogd, toon het e-mailadres
            textView.setText(user.getEmail());
        }

        // Bovenste werkbalk instellenw
        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        // Initialiseer knoppen
        button1 = findViewById(R.id.startfitness1);
        button2 = findViewById(R.id.startfitness2);

        // Logout functie
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Uitloggen en terug naar login-scherm
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        // Navigeren naar SecondActivity
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });

        // Navigeren naar SecondActivity2
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity2.class);
                startActivity(intent);
            }
        });
    }

    // Functie om de werkbalk in te stellen
    public void setSupportActionBar(Toolbar toolbar) {
        // Lege methode voor custom toolbar
    }

    // Functies voor navigatie naar verschillende activiteiten
    public void beforeage18(View view) {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(intent);
    }

    public void afterage18(View view) {
        Intent intent = new Intent(MainActivity.this, SecondActivity2.class);
        startActivity(intent);
    }

    public void food(View view) {
        Intent intent = new Intent(MainActivity.this, FoodActivity.class);
        startActivity(intent);
    }

    public void weight(View view) {
        Intent intent = new Intent(MainActivity.this, WeightGraphActivity.class);
        startActivity(intent);
    }

    public void badge(View view) {
        Intent intent = new Intent(MainActivity.this, BadgesActivity.class);
        startActivity(intent);
    }
}
