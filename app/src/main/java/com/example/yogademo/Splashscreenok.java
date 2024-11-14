package com.example.yogademo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Splashscreenok extends AppCompatActivity {

    Animation up, down; // Animaties
    ImageView imageView; // Afbeelding voor splashscreen
    TextView textView; // Tekst voor splashscreen

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splashscreenok);

        // Instellen van padding voor systeembalken
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView imageView = findViewById(R.id.appsplash);
        up = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.up);
        imageView.setAnimation(up);

        TextView textView = findViewById(R.id.appname);
        down = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.down);
        textView.setAnimation(down);

        // Start een nieuwe activiteit na 3,5 seconden
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish(); // Sluit de splashscreen activiteit
            }
        }, 3500);
    }
}
