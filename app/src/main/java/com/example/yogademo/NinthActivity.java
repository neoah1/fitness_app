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

public class NinthActivity extends AppCompatActivity {

    int[] newArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push);

        // Toolbar instellen
        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        // Afhandelen van insets voor een betere visuele ervaring
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Array van oefening ID's instellen
        newArray = new int[]{
                R.id.inclinebench, R.id.tricepextension, R.id.lateralrais, R.id.skullcrushers, R.id.dumbellfly,
        };
    }

    // Methode voor het afhandelen van afbeelding knoppen klikken
    public void Imagebuttonclicked(View view) {
        for (int i = 0; i < newArray.length; i++) {
            if (view.getId() == newArray[i]) {
                int value = i + 1;
                Log.i("FifthActivity", "Button clicked with value: " + value);

                // Intent maken en navigeren naar PushActivity
                Intent intent = new Intent(NinthActivity.this, PushActivity.class);
                intent.putExtra("value", String.valueOf(value));
                startActivity(intent);
                return; // Stop na het starten van de activiteit
            }
        }
    }
}
