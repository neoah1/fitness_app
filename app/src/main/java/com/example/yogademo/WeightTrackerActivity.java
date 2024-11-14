package com.example.yogademo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class WeightTrackerActivity extends AppCompatActivity {

    private EditText weightInput; // Invoerveld voor gewicht
    private Button submitButton; // Verzenden knop
    private DatabaseReference databaseReference; // Referentie naar de database
    private FirebaseUser user; // Huidige gebruiker

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_graph); // Zorg ervoor dat deze lay-out zowel invoer als grafiek bevat

        weightInput = findViewById(R.id.editTextWeight); // Zoek het gewicht invoerveld
        submitButton = findViewById(R.id.buttonAddWeight); // Zoek de verzendknop
        databaseReference = FirebaseDatabase.getInstance().getReference("weights"); // Krijg referentie naar "weights"
        user = FirebaseAuth.getInstance().getCurrentUser(); // Krijg huidige gebruiker

        // Controleer of de gebruiker is ingelogd
        if (user == null) {
            Toast.makeText(this, "User not logged in", Toast.LENGTH_SHORT).show(); // Toon foutmelding als gebruiker niet ingelogd is
            finish(); // Sluit de activiteit
        }

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveWeight(); // Bewaar gewicht wanneer op de knop wordt gedrukt
            }
        });
    }

    private void saveWeight() {
        String weightString = weightInput.getText().toString().trim(); // Verkrijg gewicht als string
        if (weightString.isEmpty()) {
            Toast.makeText(this, "Please enter your weight", Toast.LENGTH_SHORT).show(); // Toon foutmelding als gewicht leeg is
            return; // Stop de functie
        }

        // Probeer de string om te zetten naar float en behandel eventuele fout
        float weight;
        try {
            weight = Float.parseFloat(weightString); // Zet de string om naar float
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid weight format", Toast.LENGTH_SHORT).show(); // Toon foutmelding voor ongeldige invoer
            return; // Stop de functie
        }

        String userId = user.getUid(); // Verkrijg de gebruikers-ID
        long timestamp = System.currentTimeMillis(); // Verkrijg huidige tijdstempel
        WeightEntry weightEntry = new WeightEntry(userId, weight, timestamp); // Maak nieuwe gewichtinvoer aan
        String entryId = databaseReference.child(userId).push().getKey(); // Genereer een unieke sleutel voor elke invoer

        if (entryId != null) {
            databaseReference.child(userId).child(entryId).setValue(weightEntry) // Voeg de invoer toe aan de database
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(WeightTrackerActivity.this, "Weight saved successfully", Toast.LENGTH_SHORT).show(); // Toon succesbericht
                            loadWeightData(); // Vernieuw de grafiek
                            weightInput.setText(""); // Maak het invoerveld leeg
                        } else {
                            Toast.makeText(WeightTrackerActivity.this, "Failed to save weight: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show(); // Toon foutmelding
                        }
                    });
        } else {
            Toast.makeText(this, "Failed to generate entry ID", Toast.LENGTH_SHORT).show(); // Toon foutmelding als entry ID niet kon worden gegenereerd
        }
    }

    private void loadWeightData() {
        // Roep deze methode aan om de gegevens te laden en de grafiek bij te werken
        // Je moet misschien je bestaande loadWeightData-methode hier bijwerken
    }
}
