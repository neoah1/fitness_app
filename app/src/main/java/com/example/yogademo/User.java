package com.example.yogademo;

// User.java
import java.util.HashMap;

public class User {
    private String uid; // Gebruikers ID
    private int loginDays; // Aantal inlogdagen
    private int consecutiveLoginDays; // Aantal aaneengeschakelde inlogdagen
    private HashMap<String, Boolean> badges; // Badge-statussen

    // Constructor
    public User(String uid) {
        this.uid = uid;
        this.loginDays = 0; // Begin met 0 inlogdagen
        this.consecutiveLoginDays = 0; // Begin met 0 aaneengeschakelde inlogdagen
        this.badges = new HashMap<>(); // Nieuwe HashMap voor badges
    }

    // Getter voor UID
    public String getUid() {
        return uid;
    }

    // Andere getters en setters...
    public int getLoginDays() {
        return loginDays; // Geef het aantal inlogdagen terug
    }

    public void incrementLoginDays() {
        loginDays++; // Verhoog het aantal inlogdagen met 1
    }

    public int getConsecutiveLoginDays() {
        return consecutiveLoginDays; // Geef het aantal aaneengeschakelde inlogdagen terug
    }

    public void setConsecutiveLoginDays(int consecutiveLoginDays) {
        this.consecutiveLoginDays = consecutiveLoginDays; // Stel het aantal in
    }

    public HashMap<String, Boolean> getBadges() {
        return badges; // Geef de badges terug
    }

    public void setBadgeAchieved(String badgeId) {
        badges.put(badgeId, true); // Zet de badge als behaald
    }
}
