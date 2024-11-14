package com.example.yogademo;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

public class Register extends AppCompatActivity {

    TextInputEditText editTextEmail, edittextPassword; // Velden voor email en wachtwoord
    Button buttonReg; // Registratieknop
    FirebaseAuth mAuth; // Firebase Auth object
    ProgressBar progressBar; // Laadindicator
    TextView textView; // Link naar inlogpagina

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class); // Ga naar MainActivity als gebruiker al is ingelogd
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialiseer de velden
        editTextEmail = findViewById(R.id.email);
        edittextPassword = findViewById(R.id.password);
        buttonReg = findViewById(R.id.btn_register);
        mAuth = FirebaseAuth.getInstance(); // Verkrijg Firebase Auth instantie
        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.loginNow);

        // Kliklistener voor het inloglink
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        // Kliklistener voor de registratieknop
        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE); // Toon de laadindicator
                String email, password;
                email = String.valueOf(editTextEmail.getText()); // Verkrijg email
                password = String.valueOf(edittextPassword.getText()); // Verkrijg wachtwoord

                // Controleer of de velden leeg zijn
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Register.this, "Voer een email in", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(Register.this, "Voer een wachtwoord in", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Maak gebruiker aan met email en wachtwoord
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE); // Verberg de laadindicator
                                if (task.isSuccessful()) {
                                    Toast.makeText(Register.this, "Account aangemaakt.", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), Login.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(Register.this, "Authenticatie mislukt.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}
