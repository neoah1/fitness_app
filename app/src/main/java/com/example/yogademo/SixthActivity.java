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

public class SixthActivity extends AppCompatActivity {

    int[] newArray; // Array voor de knoppen IDs

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legs2);

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
                R.id.legpress, R.id.bulgarian, R.id.abductor, R.id.standingcalf, R.id.legextension, // Alle oefen IDs
        };
    }

    public void Imagebuttonclicked(View view) {
        // Controleer welke knop is aangeklikt
        for (int i = 0; i < newArray.length; i++) {
            if (view.getId() == newArray[i]) {
                int value = i + 1; // Verkrijg de index van de knop
                Log.i("SixthActivity", "Button clicked with value: " + value);

                // Maak intent en navigeer naar ThirdActivity2
                Intent intent = new Intent(SixthActivity.this, ThirdActivity2.class);
                intent.putExtra("value", String.valueOf(value));
                startActivity(intent);
                return; // Stop na het starten van de activiteit
            }
        }
    }
}
