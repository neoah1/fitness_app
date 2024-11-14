package com.example.yogademo;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class BadgeManager {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth auth = FirebaseAuth.getInstance();

    public void checkBadges(User user) {
        // Zorg ervoor dat de gebruiker niet null is en een geldige login dagen telt
        if (user != null) {
            int loginDays = user.getLoginDays();

            // Controleer de "A new start" badge voor 1-dags login
            if (loginDays == 1) {
                user.setBadgeAchieved("badge_daily_login"); // Behaal "A new start" badge
                updateBadge("firstLoginBadgeAchieved", true);
            }
            if (loginDays >= 7) {
                updateBadge("7_day_login", true); // Behaal "7 day login" badge
            }
            if (loginDays >= 14) {
                updateBadge("14_day_login", true); // Behaal "14 day login" badge
            }
            if (user.getConsecutiveLoginDays() >= 7) {
                updateBadge("7_consecutive_days", true); // Behaal "7 consecutive days" badge
            }
            if (user.getConsecutiveLoginDays() >= 14) {
                updateBadge("14_consecutive_days", true); // Behaal "14 consecutive days" badge
            }

            saveBadgeStatus(user); // Sla de badge-status op na controles
        } else {
            Log.w("BadgeManager", "Gebruiker is null"); // Waarschuwing als de gebruiker null is
        }
    }

    // Update de badge-status in Firestore
    private void updateBadge(String badgeId, boolean achieved) {
        db.collection("users").document(auth.getCurrentUser().getUid())
                .update(badgeId, achieved) // Update specifiek badge veld
                .addOnSuccessListener(aVoid -> Log.d("BadgeManager", badgeId + " badge succesvol bijgewerkt!"))
                .addOnFailureListener(e -> Log.w("BadgeManager", "Fout bij het bijwerken van " + badgeId + " badge", e));
    }

    // Sla de badge-status voor de gebruiker op
    public void saveBadgeStatus(User user) {
        String userId = auth.getCurrentUser().getUid(); // Verkrijg de huidige gebruikers-ID
        db.collection("users").document(userId)
                .set(user) // Sla het gehele gebruikersobject inclusief badges op
                .addOnSuccessListener(aVoid -> Log.d("BadgeManager", "Gebruikersbadge-status opgeslagen"))
                .addOnFailureListener(e -> Log.w("BadgeManager", "Fout bij het opslaan van badge-status", e));
    }

    // Laad de huidige badge-status voor de gebruiker
    public void loadBadgeStatus(User user) {
        String userId = auth.getCurrentUser().getUid(); // Verkrijg de huidige gebruikers-ID
        db.collection("users").document(userId)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            user.incrementLoginDays(); // Verhoog het aantal inlogdagen
                            HashMap<String, Boolean> badgeMap = new HashMap<>();
                            // Vul badgeMap met waarden uit het document
                            for (Map.Entry<String, Object> entry : document.getData().entrySet()) {
                                if (entry.getValue() instanceof Boolean) {
                                    badgeMap.put(entry.getKey(), (Boolean) entry.getValue());
                                }
                            }
                            user.getBadges().putAll(badgeMap); // Laad badge-statussen
                        } else {
                            Log.d("BadgeManager", "Document bestaat niet");
                        }
                    } else {
                        Log.d("BadgeManager", "Fout bij ophalen: ", task.getException());
                    }
                });
    }
}
