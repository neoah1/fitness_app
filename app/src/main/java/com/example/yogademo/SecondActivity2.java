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

public class SecondActivity2 extends AppCompatActivity {

    int[] newArray; // Array voor de knoppen IDs

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second2);

        // Instellen van padding voor systeembalken
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        // Array van knoppen met hun bijbehorende IDs
        newArray = new int[]{
                R.id.legs_day1, R.id.legs_day2, R.id.push_day1, R.id.push_day2, R.id.pull_day1, R.id.pull_day2
        };
    }

    public void Imagebuttonclicked(View view) {
        Log.d("SecondActivity2", "Button clicked with ID: " + view.getId());
        // Controleer welke knop is aangeklikt
        for (int i=0; i<newArray.length; i++) {
            if (view.getId() == newArray[i]) {
                int value = i+1; // Verkrijg de index van de knop
                Log.i("First", String.valueOf(value));
                Intent intent = new Intent(SecondActivity2.this, ThirdActivity2.class);
                intent.putExtra("value", String.valueOf(value)); // Verstuur de waarde naar ThirdActivity
                startActivity(intent);
            }
        }
    }

    public void goback(View view) {
        Intent intent = new Intent(SecondActivity2.this, MainActivity.class);
        startActivity(intent);
        finish(); // Ga terug naar MainActivity
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(SecondActivity2.this, MainActivity.class);
        startActivity(intent);
        finish(); // Zorg ervoor dat MainActivity wordt geopend bij terugdrukken
    }
}
