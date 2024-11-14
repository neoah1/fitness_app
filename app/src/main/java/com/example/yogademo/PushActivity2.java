package com.example.yogademo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PushActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push2); // Stel de juiste lay-out in voor PushActivity2

        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        // Behandel window insets voor een betere visuele ervaring
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Behandel klikken op oefening items
    public void Imagebuttonclicked(View view) {
        Intent intent = new Intent(PushActivity2.this, ExerciseDetailActivity.class);

        // Gebruik if-else in plaats van switch voor dynamische view ID's
        if (view.getId() == R.id.dumbellpress) {
            intent.putExtra("exercise_layout", R.layout.activity_inclinedumbellpress);
        } else if (view.getId() == R.id.seatedshoulder) {
            intent.putExtra("exercise_layout", R.layout.activity_seatedshoulder);
        } else if (view.getId() == R.id.cablelateral) {
            intent.putExtra("exercise_layout", R.layout.activity_cablelateral);
        } else if (view.getId() == R.id.triceprope) {
            intent.putExtra("exercise_layout", R.layout.activity_triceprope);
        } else if (view.getId() == R.id.benchpress) {
            intent.putExtra("exercise_layout", R.layout.activity_benchpress);
        } else {
            return; // Als er geen overeenkomende ID is, niets doen
        }

        startActivity(intent); // Start ExerciseDetailActivity
    }
}
