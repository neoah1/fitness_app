package com.example.yogademo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PullActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legs);

        // Toolbar instellen
        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        // Afhandelen van insets voor een betere visuele ervaring
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Methode voor het afhandelen van oefening item klikken
    public void Imagebuttonclicked(View view) {
        Intent intent = new Intent(PullActivity2.this, ExerciseDetailActivity.class);

        // Afhankelijk van de geklikte knop, de juiste lay-out instellen
        if (view.getId() == R.id.dumbellcurl) {
            intent.putExtra("exercise_layout", R.layout.activity_dumbellcurl);
        } else if (view.getId() == R.id.hammercurl) {
            intent.putExtra("exercise_layout", R.layout.activity_hammercurl);
        } else if (view.getId() == R.id.barbellshrug) {
            intent.putExtra("exercise_layout", R.layout.activity_barbellshrug);
        } else if (view.getId() == R.id.concentrationcurl) {
            intent.putExtra("exercise_layout", R.layout.activity_concentrationcurl);
        } else if (view.getId() == R.id.tbarrow) {
            intent.putExtra("exercise_layout", R.layout.activity_tbarrows);
        }
        startActivity(intent);
    }
}
