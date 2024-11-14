package com.example.yogademo;

import java.util.ArrayList;
import java.util.List;

public class BadgeData {
    public static List<Badge> getBadges() {
        List<Badge> badges = new ArrayList<>();

        // Voorbeeld van badges
        badges.add(new Badge("Daily Login", "Log in voor 7 achtereenvolgende dagen", R.drawable.badgetest, false));
        badges.add(new Badge("Workout Warrior", "Voltooi 10 workouts", R.drawable.badgetest, false));
        // Voeg meer badges toe indien nodig

        return badges; // Geef de lijst met badges terug
    }
}
