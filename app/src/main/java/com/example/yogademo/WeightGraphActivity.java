package com.example.yogademo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class WeightGraphActivity extends AppCompatActivity {

    private EditText weightInput; // Invoerveld voor gewicht
    private Button submitButton; // Knop om gewicht op te slaan
    private ListView listViewLog; // Lijstweergave voor logboek
    private WeightLogAdapter weightLogAdapter; // Adapter voor logboek
    private List<WeightEntry> weightEntries; // Lijst van gewichtinvoeren
    private WeightGraphView weightGraphView; // Grafiekweergave
    private DatabaseReference databaseReference; // Database referentie
    private FirebaseUser user; // Huidige gebruiker

    // Constantes
    private static final int MAX_ENTRIES = 8; // Maximale aantal gewichtinvoeren

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_graph); // Zorg ervoor dat de XML-indeling invoer en grafiek bevat

        // Initialiseer de weergave-elementen
        weightInput = findViewById(R.id.editTextWeight);
        submitButton = findViewById(R.id.buttonAddWeight);
        listViewLog = findViewById(R.id.listViewLog);
        weightGraphView = findViewById(R.id.weightGraphView);

        user = FirebaseAuth.getInstance().getCurrentUser(); // Verkrijg huidige gebruiker
        databaseReference = FirebaseDatabase.getInstance().getReference("weights").child(user.getUid()); // Verbind met de database

        weightEntries = new ArrayList<>(); // Initialiseer lijst voor gewichtinvoeren
        weightLogAdapter = new WeightLogAdapter(this, weightEntries); // Maak de adapter aan
        listViewLog.setAdapter(weightLogAdapter); // Stel de adapter in voor de lijstweergave

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveWeight(); // Opslaan van gewicht
            }
        });

        listViewLog.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                deleteWeightEntry(position); // Verwijder gewichtinvoer
                return true;
            }
        });

        loadWeightData(); // Laad gewichtgegevens
    }

    private void saveWeight() {
        String weightString = weightInput.getText().toString().trim(); // Verkrijg gewicht van invoerveld
        if (weightString.isEmpty()) {
            Toast.makeText(this, "Please enter your weight", Toast.LENGTH_SHORT).show(); // Waarschuwing voor lege invoer
            return;
        }

        float weight = Float.parseFloat(weightString); // Zet gewicht om naar float
        String userId = user.getUid(); // Verkrijg gebruikers-ID
        String entryId = databaseReference.push().getKey(); // Genereer een unieke ID voor de invoer

        if (entryId != null) {
            WeightEntry weightEntry = new WeightEntry(userId, weight, System.currentTimeMillis()); // Maak een nieuwe gewichtinvoer
            databaseReference.child(entryId).setValue(weightEntry).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(WeightGraphActivity.this, "Weight saved successfully", Toast.LENGTH_SHORT).show(); // Succesbericht
                    weightInput.setText(""); // Maak invoerveld leeg
                    loadWeightData(); // Laad gewichtgegevens opnieuw
                } else {
                    Toast.makeText(WeightGraphActivity.this, "Failed to save weight", Toast.LENGTH_SHORT).show(); // Foutmelding
                }
            });
        }
    }

    private void loadWeightData() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                weightEntries.clear(); // Maak lijst leeg
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    WeightEntry entry = dataSnapshot.getValue(WeightEntry.class); // Verkrijg gewichtinvoer
                    if (entry != null) {
                        entry.setId(dataSnapshot.getKey()); // Bewaar de ID van de invoer voor verwijdering
                        weightEntries.add(entry); // Voeg invoer toe aan de lijst
                    }
                }

                // Sorteer de invoeren op tijdstempel (nieuwste eerst)
                weightEntries.sort((e1, e2) -> Long.compare(e2.getTimestamp(), e1.getTimestamp()));

                // Beperk tot de nieuwste MAX_ENTRIES invoeren
                if (weightEntries.size() > MAX_ENTRIES) {
                    weightEntries = weightEntries.subList(0, MAX_ENTRIES); // Beperk de lijst
                }

                weightLogAdapter.notifyDataSetChanged(); // Update de lijstweergave
                updateGraph(weightEntries); // Update de grafiek
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(WeightGraphActivity.this, "Failed to load data", Toast.LENGTH_SHORT).show(); // Foutmelding
            }
        });
    }

    private void updateGraph(List<WeightEntry> entries) {
        if (weightGraphView != null && !entries.isEmpty()) {
            List<Float> weights = new ArrayList<>(); // Lijst voor gewichten
            for (WeightEntry entry : entries) {
                weights.add(entry.getWeight()); // Voeg gewichten toe aan de lijst
            }

            // Bepaal de min en max gewichten voor labels
            float minWeight = weights.stream().min(Float::compare).orElse(0f);
            float maxWeight = weights.stream().max(Float::compare).orElse(100f); // Standaard naar 100 als er geen invoeren zijn

            // Stel gewichtgegevens in voor de grafiekweergave
            weightGraphView.setWeightData(weights, minWeight, maxWeight);
        }
    }

    private void deleteWeightEntry(int position) {
        WeightEntry entry = weightEntries.get(position); // Verkrijg de invoer op de opgegeven positie
        if (entry != null) {
            databaseReference.child(entry.getId()).removeValue().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(WeightGraphActivity.this, "Entry deleted", Toast.LENGTH_SHORT).show(); // Succesbericht
                    loadWeightData(); // Laad gewichtgegevens opnieuw
                } else {
                    Toast.makeText(WeightGraphActivity.this, "Failed to delete entry", Toast.LENGTH_SHORT).show(); // Foutmelding
                }
            });
        }
    }

    // Instellen van onClickListener voor de terugknop
    public void goback(View view) {
        Intent intent = new Intent(WeightGraphActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(WeightGraphActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
