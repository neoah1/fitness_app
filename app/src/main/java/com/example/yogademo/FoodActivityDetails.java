package com.example.yogademo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FoodActivityDetails extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // Zet de rand tot rand modus aan
        setContentView(R.layout.activity_food_details); // Stel de layout in
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom); // Voeg padding toe voor systeembalken
            return insets;
        });

        textView = findViewById(R.id.txt); // Zoek de TextView
        String dstory = getIntent().getStringExtra("story"); // Haal de detailtekst op uit de intent
        textView.setText(dstory); // Stel de tekst in de TextView in
    }

    public void gooback(View view) {
        Intent intent = new Intent(FoodActivityDetails.this, FoodActivity.class); // Maak een intent voor de FoodActivity
        startActivity(intent); // Start de FoodActivity
        finish(); // Sluit deze activiteit
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(FoodActivityDetails.this, FoodActivity.class); // Maak een intent voor de FoodActivity
        startActivity(intent); // Start de FoodActivity
        finish(); // Sluit deze activiteit
    }
}
