package com.example.yogademo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String buttonValue = intent.getStringExtra("value"); // Haal de waarde van de knop op

        // Zet de knopwaarde om naar een integer
        int intValue = Integer.parseInt(buttonValue);
        Log.d("ThirdActivity2", "Button Value: " + intValue);

        // Stel de juiste lay-out in op basis van de geselecteerde dag
        switch (intValue) {
            case 1: // Benen Dag 1
                setContentView(R.layout.activity_legs);
                break;
            case 2: // Benen Dag 2
                setContentView(R.layout.activity_legs2);
                break;
            case 3: // Push Dag 1
                setContentView(R.layout.activity_push);
                break;
            case 4: // Push Dag 2
                setContentView(R.layout.activity_push2);
                break;
            case 5: // Pull Dag 1
                setContentView(R.layout.activity_pull);
                break;
            case 6: // Pull Dag 2
                setContentView(R.layout.activity_pull2);
                break;
            default:
                setContentView(R.layout.activity_second2);  // Standaard lay-out
                break;
        }
    }

    // Deze methode behandelt klikken voor oefeningselectie
    public void Imagebuttonclicked(View view) {
        int exerciseId = view.getId(); // Haal de ID van de aangeklikte oefening op
        Intent intent = new Intent(ThirdActivity2.this, ExerciseDetailActivity.class);

        // Controleer op oefeningen in activity_legs.xml
        if (exerciseId == R.id.squat) {
            intent.putExtra("exercise_layout", R.layout.activity_squat);
        } else if (exerciseId == R.id.lyinglegcurl) {
            intent.putExtra("exercise_layout", R.layout.activity_legcurl);
        } else if (exerciseId == R.id.deadlift) {
            intent.putExtra("exercise_layout", R.layout.activity_deadlift);
        } else if (exerciseId == R.id.machinecalf) {
            intent.putExtra("exercise_layout", R.layout.activity_machinecalf);
        } else if (exerciseId == R.id.legextension) {
            intent.putExtra("exercise_layout", R.layout.activity_legextension);
        }
        // Controleer op oefeningen in activity_legs2.xml
        else if (exerciseId == R.id.legpress) {
            intent.putExtra("exercise_layout", R.layout.activity_legpress);
        } else if (exerciseId == R.id.bulgarian) {
            intent.putExtra("exercise_layout", R.layout.activity_bulgarian);
        } else if (exerciseId == R.id.abductor) {
            intent.putExtra("exercise_layout", R.layout.activity_abductor);
        } else if (exerciseId == R.id.standingcalf) {
            intent.putExtra("exercise_layout", R.layout.activity_standingcalf);
        }
        // Controleer op oefeningen in activity_pull.xml
        else if (exerciseId == R.id.barbellrow) {
            intent.putExtra("exercise_layout", R.layout.activity_barbellrow);
        } else if (exerciseId == R.id.seatedcablerow) {
            intent.putExtra("exercise_layout", R.layout.activity_seatedcable);
        } else if (exerciseId == R.id.latpulldown) {
            intent.putExtra("exercise_layout", R.layout.activity_latpulldown);
        } else if (exerciseId == R.id.bentoverdumbell) {
            intent.putExtra("exercise_layout", R.layout.activity_dumbellfly);
        } else if (exerciseId == R.id.preachercurl) {
            intent.putExtra("exercise_layout", R.layout.activity_preachercurl);
        }
        // Controleer op oefeningen in activity_pull2.xml
        else if (exerciseId == R.id.dumbellcurl) {
            intent.putExtra("exercise_layout", R.layout.activity_dumbellcurl);
        } else if (exerciseId == R.id.hammercurl) {
            intent.putExtra("exercise_layout", R.layout.activity_hammercurl);
        } else if (exerciseId == R.id.barbellshrug) {
            intent.putExtra("exercise_layout", R.layout.activity_barbellshrug);
        } else if (exerciseId == R.id.concentrationcurl) {
            intent.putExtra("exercise_layout", R.layout.activity_concentrationcurl);
        } else if (exerciseId == R.id.tbarrow) {
            intent.putExtra("exercise_layout", R.layout.activity_tbarrows);
        }
        // Controleer op oefeningen in activity_push.xml
        else if (exerciseId == R.id.inclinebench) {
            intent.putExtra("exercise_layout", R.layout.activity_inclinebench);
        } else if (exerciseId == R.id.tricepextension) {
            intent.putExtra("exercise_layout", R.layout.activity_tricepextension);
        } else if (exerciseId == R.id.lateralrais) {
            intent.putExtra("exercise_layout", R.layout.activity_lateralraise);
        } else if (exerciseId == R.id.skullcrushers) {
            intent.putExtra("exercise_layout", R.layout.activity_skullcrushers);
        } else if (exerciseId == R.id.dumbellfly) {
            intent.putExtra("exercise_layout", R.layout.activity_dumbellflyes);
        }
        // Controleer op oefeningen in activity_push2.xml
        else if (exerciseId == R.id.dumbellpress) {
            intent.putExtra("exercise_layout", R.layout.activity_inclinedumbellpress);
        } else if (exerciseId == R.id.seatedshoulder) {
            intent.putExtra("exercise_layout", R.layout.activity_seatedshoulder);
        } else if (exerciseId == R.id.cablelateral) {
            intent.putExtra("exercise_layout", R.layout.activity_cablelateral);
        } else if (exerciseId == R.id.triceprope) {
            intent.putExtra("exercise_layout", R.layout.activity_triceprope);
        } else if (exerciseId == R.id.benchpress) {
            intent.putExtra("exercise_layout", R.layout.activity_benchpress);
        } else {
            // Behandel onbekende oefening ID's
            Log.e("ThirdActivity2", "Unknown exercise ID");
            return;  // Ga terug zonder de activiteit te starten als de ID onbekend is
        }

        // Start de ExerciseDetailActivity en geef de geselecteerde oefenlay-out door
        startActivity(intent);
    }

    public void goback(View view) {
        Intent intent = new Intent(ThirdActivity2.this, SecondActivity2.class);
        startActivity(intent);
        finish(); // Sluit deze activiteit
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(ThirdActivity2.this, SecondActivity2.class);
        startActivity(intent);
        finish(); // Sluit deze activiteit
    }
}
