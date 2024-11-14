package com.example.yogademo;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ExerciseDetailActivity extends AppCompatActivity {

    private TableLayout tableLayout;
    private int setCount = 1; // Begin bij 1 om 2 in de eerste nieuwe set weer te geven

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Haal de oefenindeling uit de intent
        int layoutId = getIntent().getIntExtra("exercise_layout", R.layout.activity_second2);
        setContentView(layoutId);  // Laad de indeling op basis van de doorgegeven oefening

        // Initialiseer de TableLayout en knoppen
        tableLayout = findViewById(R.id.tableLayout);
        Button addSetButton = findViewById(R.id.add_set_button);
        ImageButton backButton = findViewById(R.id.back_button); // Initialiseer de terugknop

        // Stel de listener in voor het toevoegen van nieuwe sets
        addSetButton.setOnClickListener(v -> addNewSetRow());

        // Stel de listener in voor de terugknop
        backButton.setOnClickListener(v -> finish()); // Sluit de huidige activiteit
    }

    // Methode om een nieuwe setrij aan de table layout toe te voegen
    private void addNewSetRow() {
        setCount++;  // Verhoog het setnummer om bij 2 te beginnen voor de eerste nieuwe set

        // Maak een nieuwe TableRow
        TableRow newRow = new TableRow(this);
        newRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT)); // Stel de breedte in op match_parent

        // Stel TextView voor SET nummer in
        TextView setTextView = new TextView(this);
        setTextView.setText(String.valueOf(setCount));  // Toon het setnummer
        setTextView.setTextColor(getResources().getColor(android.R.color.black));  // Stel de tekstkleur in op zwart
        setTextView.setTextSize(20);
        setTextView.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1)); // Gebruik layout_weight om ruimte te delen
        setTextView.setGravity(Gravity.CENTER);  // Centreer de tekst

        // Maak EditText voor KG invoer
        EditText kgEditText = new EditText(this);
        kgEditText.setHint("kg");  // Stel hint in voor kg invoer
        kgEditText.setTextColor(getResources().getColor(android.R.color.black));  // Stel de tekstkleur in op zwart
        kgEditText.setBackgroundColor(android.graphics.Color.TRANSPARENT);
        kgEditText.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1)); // Stel breedte in op 0dp voor gewichtverdeling
        kgEditText.setInputType(android.text.InputType.TYPE_CLASS_NUMBER | android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL);
        kgEditText.setTextSize(20);
        kgEditText.setGravity(Gravity.CENTER);  // Centreer de tekst
        kgEditText.setPadding(0, 0, 0, 0);  // Zorg voor geen padding

        // Maak EditText voor REPS invoer
        EditText repsEditText = new EditText(this);
        repsEditText.setHint("reps");  // Stel hint in voor reps invoer
        repsEditText.setTextColor(getResources().getColor(android.R.color.black));  // Stel de tekstkleur in op zwart
        repsEditText.setBackgroundColor(android.graphics.Color.TRANSPARENT);
        repsEditText.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1)); // Stel breedte in op 0dp voor gewichtverdeling
        repsEditText.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);
        repsEditText.setTextSize(20);
        repsEditText.setGravity(Gravity.CENTER);  // Centreer de tekst
        repsEditText.setPadding(0, 0, 0, 0);  // Zorg voor geen padding

        // Voeg alle weergaven toe aan de nieuwe rij
        newRow.addView(setTextView);
        newRow.addView(kgEditText);
        newRow.addView(repsEditText);

        // Voeg de nieuwe rij toe aan de TableLayout
        tableLayout.addView(newRow);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
