package com.example.yogademo;

public class WeightEntry {
    private String userId; // Gebruikers-ID
    private float weight; // Gewicht van de gebruiker
    private long timestamp; // Tijdstip van invoer
    private String id; // ID van de invoer

    public WeightEntry() {
        // Standaardconstructor vereist voor DataSnapshot.getValue(WeightEntry.class)
    }

    public WeightEntry(String userId, float weight, long timestamp) {
        this.userId = userId; // Stel gebruikers-ID in
        this.weight = weight; // Stel gewicht in
        this.timestamp = timestamp; // Stel tijdstip in
    }

    public String getUserId() {
        return userId; // Geef gebruikers-ID terug
    }

    public float getWeight() {
        return weight; // Geef gewicht terug
    }

    public long getTimestamp() {
        return timestamp; // Geef tijdstip terug
    }

    public String getId() {
        return id; // Geef ID terug
    }

    public void setId(String id) {
        this.id = id; // Stel ID in
    }

    @Override
    public String toString() {
        // Zet tijdstempel om naar een leesbare datum
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(new java.util.Date(timestamp)); // Formatteer datum
        return "Weight: " + weight + "kg, Date: " + date; // Retourneer gewicht en datum
    }
}
