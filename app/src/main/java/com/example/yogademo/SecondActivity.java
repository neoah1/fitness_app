package com.example.yogademo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity {

    int[] newArray; // Array om de IDs van knoppen op te slaan

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        // Initialiseer de array met knoppen
        newArray = new int[]{
                R.id.boat_pose, R.id.bridge_pose, R.id.chair_pose, R.id.child_pose,
                R.id.cobbler_pose, R.id.cow_pose, R.id.play_pose, R.id.pause_pose,
                R.id.plank_pose, R.id.crunches_pose, R.id.sit_pose, R.id.hip_pose,
                R.id.rotation_pose, R.id.vertical_pose, R.id.wind_pose,
        };
    }

    public void Imagebuttonclicked(View view) {
        // Controleer welke knop is geklikt
        for (int i=0; i<newArray.length; i++) {
            if (view.getId() == newArray[i]) {
                int value = i+1; // Verkrijg de index van de knop
                Log.i("First", String.valueOf(value)); // Log de waarde
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                intent.putExtra("value", String.valueOf(value)); // Verstuur de waarde naar ThirdActivity
                startActivity(intent);
            }
        }
    }

    public void goback(View view) {
        Intent intent = new Intent(SecondActivity.this, MainActivity.class);
        startActivity(intent);
        finish(); // Ga terug naar MainActivity
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(SecondActivity.this, MainActivity.class);
        startActivity(intent);
        finish(); // Zorg ervoor dat MainActivity wordt geopend bij terugdrukken
    }
}
