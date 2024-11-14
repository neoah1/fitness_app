package com.example.yogademo;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BreakActivity extends AppCompatActivity {

    private TextView breakTextView;
    private CountDownTimer breakTimer;
    private long breakTimeLeftInMillis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_break);

        breakTextView = findViewById(R.id.break_time); // Zoek de TextView voor de pauzetijd

        // Haal de waarde voor de volgende activiteit op
        Intent intent = getIntent();
        String nextValue = intent.getStringExtra("next_value");

        // Start de pauze timer
        startBreakTimer(nextValue);
    }

    private void startBreakTimer(final String nextValue) {
        breakTimeLeftInMillis = 30000; // 30 seconden in milliseconden

        breakTimer = new CountDownTimer(breakTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                breakTimeLeftInMillis = millisUntilFinished;
                updateBreakTimer(); // Update de pauzetimer
            }

            @Override
            public void onFinish() {
                // Ga naar de volgende activiteit na de pauze
                Intent intent = new Intent(BreakActivity.this, ThirdActivity.class);
                intent.putExtra("value", nextValue);
                startActivity(intent);
                finish(); // Verwijder BreakActivity uit de stapel
            }
        }.start(); // Start de timer
    }

    private void updateBreakTimer() {
        int minutes = (int) breakTimeLeftInMillis / 60000; // Bereken minuten
        int seconds = (int) breakTimeLeftInMillis % 60000 / 1000; // Bereken seconden

        String timeLeftFormatted = String.format("%02d:%02d", minutes, seconds);
        breakTextView.setText(timeLeftFormatted); // Toon de overgebleven tijd
    }
}
