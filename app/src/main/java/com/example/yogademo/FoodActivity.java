package com.example.yogademo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FoodActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // Zet de rand tot rand modus aan
        setContentView(R.layout.activity_food); // Stel de layout in
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom); // Voeg padding toe voor systeembalken
            return insets;
        });

        String[] tstory = getResources().getStringArray(R.array.title_story); // Haal titels op
        final String[] dstory = getResources().getStringArray(R.array.details_story); // Haal details op

        listView = findViewById(R.id.list); // Zoek de ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.row, R.id.rowtxt, tstory); // Maak een adapter
        listView.setAdapter(adapter); // Stel de adapter in voor de ListView

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String t = dstory[i]; // Haal het detail op bij de geselecteerde titel
                Intent intent = new Intent(FoodActivity.this, FoodActivityDetails.class); // Maak een intent voor de detailactiviteit
                intent.putExtra("story", t); // Voeg de detailtekst toe aan de intent
                startActivity(intent); // Start de detailactiviteit
            }
        });
    }

    public void foodgoback(View view) {
        Intent intent = new Intent(FoodActivity.this, MainActivity.class); // Maak een intent voor de hoofdpagina
        startActivity(intent); // Start de hoofdpagina
        finish(); // Sluit deze activiteit
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(FoodActivity.this, MainActivity.class); // Maak een intent voor de hoofdpagina
        startActivity(intent); // Start de hoofdpagina
        finish(); // Sluit deze activiteit
    }
}
