package com.example.yogademo;

public class Badge {
    private String name; // Naam van de badge
    private String description; // Beschrijving van de badge
    private int iconResId; // Resource ID voor het badge-icoon
    private boolean achieved; // Geeft aan of de badge behaald is

    // Constructor
    public Badge(String name, String description, int iconResId, boolean achieved) {
        this.name = name;
        this.description = description;
        this.iconResId = iconResId;
        this.achieved = achieved;
    }

    // Getters
    public String getName() {
        return name; // Geef de naam van de badge
    }

    public String getDescription() {
        return description; // Geef de beschrijving van de badge
    }

    public int getIconResId() {
        return iconResId; // Geef het icoon resource ID
    }

    public boolean isAchieved() {
        return achieved; // Geef aan of de badge behaald is
    }

    // Setter voor de behaald status
    public void setAchieved(boolean achieved) {
        this.achieved = achieved; // Zet de behaald status
        // Verander het icoon resource ID wanneer behaald
        if (achieved) {
            this.iconResId = R.drawable.ic_check; // Verander naar het icoon voor behaald
        }
    }
}
