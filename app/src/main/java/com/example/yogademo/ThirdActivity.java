package com.example.yogademo;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ThirdActivity extends AppCompatActivity {

    String buttonvalue;  // Waarde van de knop
    Button startBttn;  // Startknop
    private CountDownTimer countDownTimer;  // Timer voor de aftelling
    TextView mtextview;  // Tekstweergave voor de tijd
    private boolean MTimeRunning;  // Timer draait?
    private long MTimeLeftInMilis;  // Tijd over in milliseconden

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Haal de button waarde op uit de intent
        Intent intent = getIntent();
        buttonvalue = intent.getStringExtra("value");

        // Converteer de button waarde naar een integer
        int intvalue = Integer.valueOf(buttonvalue);
        Log.d("ThirdActivity", "Button Value: " + intvalue);  // Debugging log

        // Stel de layout in op basis van de button waarde
        switch (intvalue) {
            case 1:
                setContentView(R.layout.activity_boat);
                break;
            case 2:
                setContentView(R.layout.activity_bridge);
                break;
            case 3:
                setContentView(R.layout.activity_chair);
                break;
            case 4:
                setContentView(R.layout.activity_child);
                break;
            case 5:
                setContentView(R.layout.activity_cobbler);
                break;
            case 6:
                setContentView(R.layout.activity_cow);
                break;
            case 7:
                setContentView(R.layout.activity_play);
                break;
            case 8:
                setContentView(R.layout.activity_pause);
                break;
            case 9:
                setContentView(R.layout.activity_plank);
                break;
            case 10:
                setContentView(R.layout.activity_crunches);
                break;
            case 11:
                setContentView(R.layout.activity_sit);
                break;
            case 12:
                setContentView(R.layout.activity_hip);
                break;
            case 13:
                setContentView(R.layout.activity_rotation);
                break;
            case 14:
                setContentView(R.layout.activity_vertical);
                break;
            case 15:
                setContentView(R.layout.activity_wind);
                break;
            default:
                Log.e("ThirdActivity", "No matching layout for button value: " + intvalue);
                setContentView(R.layout.activity_second);  // Default layout
                break;
        }

        // Pas EdgeToEdge en window insets toe na het instellen van de content view
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Vind de startknop en de tekstweergave voor de timer
        startBttn = findViewById(R.id.startButton);
        mtextview = findViewById(R.id.time);

        // Back button functionaliteit
        ImageView backButton = findViewById(R.id.back_button);
        backButton.setImageResource(R.drawable.baseline_keyboard_arrow_left_25);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();  // Ga terug naar de vorige activiteit
            }
        });

        // Startknop klikfunctionaliteit
        startBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MTimeRunning) {
                    stopTimer();  // Stop de timer
                } else {
                    startTimer();  // Start de timer
                }
            }
        });
    }

    private void stopTimer() {
        countDownTimer.cancel();  // Stop de afteltimer
        MTimeRunning = false;  // Timer is gestopt
        startBttn.setText("START");  // Zet de knoptekst op START
    }

    private void startTimer() {
        final CharSequence value1 = mtextview.getText();  // Huidige tijd
        String time = value1.toString();

        // Parse tijdsformaat veilig
        String[] timeParts = time.split(":");
        if (timeParts.length == 2) {
            String minutes = timeParts[0];  // Haal minuten op
            String seconds = timeParts[1];  // Haal seconden op

            try {
                int totalSeconds = Integer.parseInt(minutes) * 60 + Integer.parseInt(seconds);  // Totale seconden berekenen
                MTimeLeftInMilis = totalSeconds * 1000;  // Zet om naar milliseconden

                countDownTimer = new CountDownTimer(MTimeLeftInMilis, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        MTimeLeftInMilis = millisUntilFinished;  // Tijd bijwerken
                        updateTimer();  // Timer bijwerken
                    }

                    @Override
                    public void onFinish() {
                        // Start een pauze van 30 seconden voordat je naar de volgende activiteit gaat
                        Intent breakIntent = new Intent(ThirdActivity.this, BreakActivity.class);
                        breakIntent.putExtra("next_value", String.valueOf(Integer.valueOf(buttonvalue) + 1));
                        startActivity(breakIntent);  // Ga naar de pauzeactiviteit
                    }
                }.start();
                startBttn.setText("Pause");  // Zet de knoptekst op PAUZE
                MTimeRunning = true;  // Timer draait nu
            } catch (NumberFormatException e) {
                // Verwerk parsingfout door een standaardtijd in te stellen
                mtextview.setText("00:30");  // Stel in op standaard 30 seconden
            }
        } else {
            // Stel een standaardtijd in als het formaat onjuist is
            mtextview.setText("00:30");
        }
    }

    private void updateTimer() {
        int minutes = (int) MTimeLeftInMilis / 60000;  // Bereken minuten
        int seconds = (int) MTimeLeftInMilis % 60000 / 1000;  // Bereken seconden

        String timeLeft = "";  // Tijd over als string
        if (minutes < 10)
            timeLeft = "0";  // Voeg voorloopnullen toe voor minuten
        timeLeft += minutes + ":";  // Voeg minuten toe aan de string
        if (seconds < 10)
            timeLeft += "0";  // Voeg voorloopnullen toe voor seconden
        timeLeft += seconds;  // Voeg seconden toe aan de string

        mtextview.setText(timeLeft);  // Update de tekstweergave
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();  // Voer de standaard terugknopfunctionaliteit uit
    }
}
