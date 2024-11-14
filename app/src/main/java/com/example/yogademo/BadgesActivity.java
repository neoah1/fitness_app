package com.example.yogademo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BadgesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BadgeAdapter badgeAdapter;
    private List<Map<String, Object>> badgeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_badges);

        recyclerView = findViewById(R.id.recycler_view_badges); // Vervang door je echte RecyclerView ID
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialiseer de badge-lijst
        badgeList = new ArrayList<>();
        initializeBadges();

        badgeAdapter = new BadgeAdapter(this, badgeList);
        recyclerView.setAdapter(badgeAdapter); // Stel de adapter in voor de RecyclerView
    }

    private void initializeBadges() {
        // Zorg ervoor dat je alle nodige badges toevoegt
        addBadge("Logged in for the first time", "Open de app voor de eerste keer", R.drawable.badgetest, true);
        addBadge("7 Day Login", "Log in voor 7 achtereenvolgende dagen", R.drawable.badgetest, false);
        addBadge("14 Day Login", "Log in voor 14 achtereenvolgende dagen", R.drawable.badgetest, false); // Voorbeeld van een behaalde badge
        addBadge("7 consecutive days", "Log in voor 7 achtereenvolgende dagen", R.drawable.badgetest, false);
        addBadge("14 consecutive days", "Log in voor 14 achtereenvolgende dagen", R.drawable.badgetest, false);
        // Voeg meer badges toe indien nodig
    }


    private void addBadge(String title, String description, int iconResId, boolean achieved) {
        Map<String, Object> badge = new HashMap<>();
        badge.put("title", title);
        badge.put("description", description);
        badge.put("iconResId", iconResId);
        badge.put("achieved", achieved);
        badgeList.add(badge); // Voeg de badge toe aan de lijst
    }

    public void goback(View view) {
        Intent intent = new Intent(BadgesActivity.this, MainActivity.class);
        startActivity(intent);
        finish(); // Sluit BadgesActivity
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(BadgesActivity.this, MainActivity.class);
        startActivity(intent);
        finish(); // Sluit BadgesActivity
    }
}
