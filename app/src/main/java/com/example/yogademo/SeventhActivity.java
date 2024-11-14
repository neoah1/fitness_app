package com.example.yogademo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SeventhActivity extends AppCompatActivity {

    int[] newArray; // Array voor de knoppen IDs

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull);

        // Instellen van toolbar
        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        // Handling van insets voor een betere visuele ervaring
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        newArray = new int[]{
                R.id.barbellrow, R.id.seatedcablerow, R.id.latpulldown, R.id.bentoverdumbell, R.id.preachercurl,
        };
    }

    public void Imagebuttonclicked(View view) {
        // Controleer welke knop is aangeklikt
        for (int i = 0; i < newArray.length; i++) {
            if (view.getId() == newArray[i]) {
                int value = i + 1; // Verkrijg de index van de knop
                Log.i("FifthActivity", "Button clicked with value: " + value);

                // Maak intent en navigeer naar PullActivity
                Intent intent = new Intent(SeventhActivity.this, PullActivity.class);
                intent.putExtra("value", String.valueOf(value));
                startActivity(intent);
                return; // Stop na het starten van de activiteit
            }
        }
    }
}
