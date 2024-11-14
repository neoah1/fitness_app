package com.example.yogademo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class WeightLogAdapter extends ArrayAdapter<WeightEntry> {
    // Datum formatter om datum en tijd weer te geven
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

    public WeightLogAdapter(Context context, List<WeightEntry> entries) {
        super(context, 0, entries); // Roep de superconstructor aan
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        WeightEntry entry = getItem(position); // Verkrijg gewichtinvoer
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_2, parent, false); // Inflater voor lijstitem
        }

        TextView text1 = convertView.findViewById(android.R.id.text1); // Eerste tekstweergave
        TextView text2 = convertView.findViewById(android.R.id.text2); // Tweede tekstweergave

        // Zet de tijdstempel om naar een leesbare datum
        if (entry != null) {
            String formattedDate = DATE_FORMAT.format(new Date(entry.getTimestamp())); // Format datum
            text1.setText(formattedDate); // Zet de datumtekst
            text2.setText("Weight: " + entry.getWeight() + " kg"); // Zet de gewichtstekst
        }

        return convertView; // Retourneer het item
    }
}
