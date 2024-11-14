package com.example.yogademo;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Login extends AppCompatActivity {
    TextInputEditText editTextEmail, edittextPassword; // Velden voor e-mail en wachtwoord
    Button buttonLogin; // Inlogknop
    FirebaseAuth mAuth; // Firebase authenticatie
    ProgressBar progressBar; // Laadbalk
    TextView textView; // TextView voor registratie
    BadgeManager badgeManager; // BadgeManager voor het beheren van badges
    User currentUser; // Huidige gebruiker

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser firebaseUser = mAuth.getCurrentUser(); // Huidige Firebase gebruiker ophalen
        if (firebaseUser != null) {
            currentUser = new User(firebaseUser.getUid()); // Huidige gebruiker initialiseren met Firebase ID
            badgeManager = new BadgeManager(); // BadgeManager initialiseren

            // Laad bestaande badge status
            badgeManager.loadBadgeStatus(currentUser);

            // Verhoog het aantal login dagen
            currentUser.incrementLoginDays(); // Login dagen verhogen

            // Controleer badges na het laden van de badge status
            badgeManager.checkBadges(currentUser); // Controleer op badge prestaties

            Intent intent = new Intent(getApplicationContext(), MainActivity.class); // Navigeer naar MainActivity
            startActivity(intent);
            finish();
        } else {
            Log.w("LoginActivity", "Firebase user is null");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editTextEmail = findViewById(R.id.email); // E-mail invoerveld
        edittextPassword = findViewById(R.id.password); // Wachtwoord invoerveld
        buttonLogin = findViewById(R.id.btn_login); // Inlogknop
        mAuth = FirebaseAuth.getInstance(); // FirebaseAuth instantie ophalen
        progressBar = findViewById(R.id.progressBar); // Laadbalk
        textView = findViewById(R.id.registerNow); // Tekstview voor registratie
        badgeManager = new BadgeManager(); // BadgeManager initialiseren

        textView.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Register.class); // Navigeer naar registratie
            startActivity(intent);
            finish();
        });

        buttonLogin.setOnClickListener(view -> {
            progressBar.setVisibility(View.VISIBLE); // Laadbalk zichtbaar maken
            String email = editTextEmail.getText().toString(); // E-mail ophalen
            String password = edittextPassword.getText().toString(); // Wachtwoord ophalen

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(Login.this, "Enter Email", Toast.LENGTH_SHORT).show(); // E-mail invoeren
                progressBar.setVisibility(View.GONE); // Laadbalk verbergen
                return;
            }

            if (TextUtils.isEmpty(password)) {
                Toast.makeText(Login.this, "Enter Password", Toast.LENGTH_SHORT).show(); // Wachtwoord invoeren
                progressBar.setVisibility(View.GONE); // Laadbalk verbergen
                return;
            }

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressBar.setVisibility(View.GONE); // Laadbalk verbergen
                            if (task.isSuccessful()) {
                                Toast.makeText(Login.this, "Login Successful.", Toast.LENGTH_SHORT).show(); // Succesvolle login

                                // Initialiseer currentUser met de huidige Firebase user ID
                                currentUser = new User(mAuth.getCurrentUser().getUid());

                                // Laad bestaande badge status
                                badgeManager.loadBadgeStatus(currentUser);

                                // Controleer of dit de eerste login is
                                checkAndUpdateFirstLoginBadge(currentUser.getUid());

                                // Verhoog login dagen en controleer badges
                                currentUser.incrementLoginDays(); // Login dagen verhogen
                                badgeManager.checkBadges(currentUser); // Controleer op badge prestaties

                                Intent intent = new Intent(getApplicationContext(), MainActivity.class); // Navigeer naar MainActivity
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(Login.this, "Authentication failed.", Toast.LENGTH_SHORT).show(); // Authenticatie mislukt
                            }
                        }
                    });
        });
    }

    private void checkAndUpdateFirstLoginBadge(String userId) {
        // Haal Firestore instantie op
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Referentie naar het badge document van de gebruiker in Firestore
        DocumentReference userRef = db.collection("users").document(userId);

        // Werk de badge van de gebruiker bij voor de eerste login als deze nog niet behaald is
        userRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful() && task.getResult() != null) {
                boolean firstLoginBadgeAchieved = task.getResult().getBoolean("firstLoginBadgeAchieved") != null && task.getResult().getBoolean("firstLoginBadgeAchieved");

                if (!firstLoginBadgeAchieved) {
                    userRef.update("firstLoginBadgeAchieved", true)
                            .addOnSuccessListener(aVoid -> {
                                // Werk het bijbehorende badge document bij
                                updateBadgeInFirestore("logged_in_for_first_time"); // Vervang met jouw badge ID
                                Toast.makeText(Login.this, "First login badge achieved!", Toast.LENGTH_SHORT).show(); // Eerste login badge behaald
                            })
                            .addOnFailureListener(e -> {
                                Toast.makeText(Login.this, "Error updating badge status", Toast.LENGTH_SHORT).show(); // Fout bij het bijwerken van badge status
                            });
                }
            }
        });
    }

    private void updateBadgeInFirestore(String badgeId) {
        // Referentie naar het badge document
        DocumentReference badgeRef = FirebaseFirestore.getInstance().collection("badges").document(badgeId);

        // Werk de behaalde status van de badge bij
        badgeRef.update("achieved", true)
                .addOnSuccessListener(aVoid -> {
                    Log.d("Badge Update", "Badge marked as achieved."); // Badge gemarkeerd als behaald
                })
                .addOnFailureListener(e -> {
                    Log.w("Badge Update", "Error marking badge as achieved", e); // Fout bij het markeren van badge als behaald
                });
    }
}
