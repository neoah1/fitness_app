package com.example.yogademo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PushActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push); // Zorg ervoor dat de juiste lay-out is ingesteld

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
        Intent intent = new Intent(PushActivity.this, ExerciseDetailActivity.class);

        // Gebruik if-else in plaats van switch voor dynamische view ID's
        if (view.getId() == R.id.inclinebench) {
            intent.putExtra("exercise_layout", R.layout.activity_inclinebench);
        } else if (view.getId() == R.id.tricepextension) {
            intent.putExtra("exercise_layout", R.layout.activity_tricepextension);
        } else if (view.getId() == R.id.lateralrais) {
            intent.putExtra("exercise_layout", R.layout.activity_lateralraise);
        } else if (view.getId() == R.id.skullcrushers) {
            intent.putExtra("exercise_layout", R.layout.activity_skullcrushers);
        } else if (view.getId() == R.id.dumbellfly) {
            intent.putExtra("exercise_layout", R.layout.activity_dumbellfly);
        } else {
            return; // Als er geen overeenkomende ID is, niets doen
        }

        startActivity(intent); // Start ExerciseDetailActivity
    }
}
