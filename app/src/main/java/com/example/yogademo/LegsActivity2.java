package com.example.yogademo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LegsActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legs2); // Stel de juiste layout voor LegsActivity2 in

        Toolbar toolbar = findViewById(R.id.toolBar); // Zoek de Toolbar
        setSupportActionBar(toolbar); // Stel de Toolbar in

        // Behandel vensterinvoegen voor een betere visuele ervaring
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom); // Voeg padding toe voor systeembalken
            return insets;
        });
    }

    // Behandel klikken op oefenitems
    public void Imagebuttonclicked(View view) {
        Intent intent = new Intent(LegsActivity2.this, ExerciseDetailActivity.class); // Maak een intent voor de ExerciseDetailActivity

        // Gebruik if-else in plaats van switch voor dynamische view-ID's
        if (view.getId() == R.id.squat) {
            intent.putExtra("exercise_layout", R.layout.activity_squat);
        } else if (view.getId() == R.id.lyinglegcurl) {
            intent.putExtra("exercise_layout", R.layout.activity_legcurl);
        } else if (view.getId() == R.id.deadlift) {
            intent.putExtra("exercise_layout", R.layout.activity_deadlift);
        } else if (view.getId() == R.id.machinecalf) {
            intent.putExtra("exercise_layout", R.layout.activity_machinecalf);
        } else if (view.getId() == R.id.legextension) {
            intent.putExtra("exercise_layout", R.layout.activity_legextension);
        } else {
            return; // Als er geen overeenkomstige ID is, doe dan niets
        }

        startActivity(intent); // Start de ExerciseDetailActivity
    }
}
