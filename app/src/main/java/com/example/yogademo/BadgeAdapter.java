package com.example.yogademo;

import android.content.Context;
import android.content.Intent; // Voeg deze import toe voor Intent
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Map;

public class BadgeAdapter extends RecyclerView.Adapter<BadgeAdapter.BadgeViewHolder> {
    private Context context; // Context van de applicatie
    private List<Map<String, Object>> badgeList; // Lijst van badges

    // Constructor
    public BadgeAdapter(Context context, List<Map<String, Object>> badgeList) {
        this.context = context; // Zet de context
        this.badgeList = badgeList; // Zet de badge lijst
    }

    @NonNull
    @Override
    public BadgeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflatie van badge_item layout
        View view = LayoutInflater.from(context).inflate(R.layout.badge_item, parent, false);
        return new BadgeViewHolder(view); // Retourneer een nieuwe BadgeViewHolder
    }

    @Override
    public void onBindViewHolder(@NonNull BadgeViewHolder holder, int position) {
        Map<String, Object> badge = badgeList.get(position); // Verkrijg badge op positie

        // Zorg ervoor dat badge niet null is
        if (badge != null) {
            // Zet badge titel en beschrijving
            holder.badgeTitle.setText((String) badge.get("title"));
            holder.badgeDescription.setText((String) badge.get("description"));

            // Controleer of de badge behaald is en update het icoon
            boolean isAchieved = (boolean) badge.get("achieved");
            if (isAchieved) {
                holder.badgeIcon.setImageResource(R.drawable.ic_check); // Icoon voor behaald
                holder.badgeIndicator.setBackgroundColor(context.getResources().getColor(android.R.color.holo_green_light));
            } else {
                holder.badgeIcon.setImageResource((Integer) badge.get("iconResId")); // Origineel icoon
                holder.badgeIndicator.setBackgroundColor(context.getResources().getColor(android.R.color.darker_gray));
            }
            holder.badgeIndicator.setVisibility(View.VISIBLE); // Maak de indicator zichtbaar

            // Zet OnClickListener voor badge icoon
            holder.badgeIcon.setOnClickListener(v -> {
                // Start MainActivity wanneer badge icoon wordt geklikt
                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
            });

            // Optioneel: Toon details van de badge bij klik
            holder.itemView.setOnClickListener(v -> {
                Toast.makeText(context, badge.get("title") + " clicked", Toast.LENGTH_SHORT).show(); // Toont badge titel
            });
        }
    }

    @Override
    public int getItemCount() {
        return badgeList.size(); // Geef aantal badges in de lijst
    }

    // ViewHolder voor badges
    static class BadgeViewHolder extends RecyclerView.ViewHolder {
        ImageView badgeIcon; // Badge icoon
        TextView badgeTitle; // Badge titel
        TextView badgeDescription; // Badge beschrijving
        View badgeIndicator; // Indicator voor behaalde status

        public BadgeViewHolder(@NonNull View itemView) {
            super(itemView);
            badgeIcon = itemView.findViewById(R.id.badge_icon); // Vind badge icoon
            badgeTitle = itemView.findViewById(R.id.badge_title); // Vind badge titel
            badgeDescription = itemView.findViewById(R.id.badge_description); // Vind badge beschrijving
            badgeIndicator = itemView.findViewById(R.id.badge_indicator); // Vind indicator
        }
    }
}
