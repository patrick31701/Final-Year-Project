package com.mad1.finalyearproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.Executor;

public class LoggedIn extends AppCompatActivity {
    Button logout;
    TextView generalAccess, developmentLabAccess, networkLabAccess;
    FirebaseAuth fAuth;
    FirebaseUser user;
    BiometricPrompt.PromptInfo promptInfo;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);
        logout = findViewById(R.id.btn_logout);
        generalAccess = findViewById(R.id.generalAccessText);
        developmentLabAccess = findViewById(R.id.developmentLabAccessText);
        networkLabAccess = findViewById(R.id.networkLabAccessText);
        fAuth = FirebaseAuth.getInstance();
        user = fAuth.getCurrentUser();
        String userId = getIntent().getStringExtra("userId");

        SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        String email = sharedPreferences.getString("email", "");

        //get instance of a eu database
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://final-year-project-3600b-default-rtdb.europe-west1.firebasedatabase.app/");
        DatabaseReference myRef = database.getReference("User Info").child(userId);
        DatabaseReference myRef2 = database.getReference("User Data");

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String generalAccess = dataSnapshot.child("Development Labs").getValue().toString();
                String developmentLabAccess = dataSnapshot.child("General Access").getValue().toString();
                String networkLabAccess = dataSnapshot.child("Network Labs").getValue().toString();

                if (generalAccess.equals("true")) {
                    LoggedIn.this.generalAccess.setText("Access: Granted");
                    LoggedIn.this.generalAccess.setTextColor(getResources().getColor(R.color.green));
                } else {
                    LoggedIn.this.generalAccess.setText("Access: Denied");
                    LoggedIn.this.generalAccess.setEnabled(false);
                    LoggedIn.this.generalAccess.setTextColor(getResources().getColor(R.color.red));
                }

                if (developmentLabAccess.equals("true")) {
                    LoggedIn.this.developmentLabAccess.setText("Access: Granted");
                    LoggedIn.this.developmentLabAccess.setTextColor(getResources().getColor(R.color.green));
                } else {
                    LoggedIn.this.developmentLabAccess.setText("Access: Denied");
                    LoggedIn.this.developmentLabAccess.setEnabled(false);
                    LoggedIn.this.developmentLabAccess.setTextColor(getResources().getColor(R.color.red));
                }

                if (networkLabAccess.equals("true")) {
                    LoggedIn.this.networkLabAccess.setText("Access: Granted");
                    LoggedIn.this.networkLabAccess.setTextColor(getResources().getColor(R.color.green));
                } else {
                    LoggedIn.this.networkLabAccess.setText("Access: Denied");
                    LoggedIn.this.networkLabAccess.setEnabled(false);
                    LoggedIn.this.networkLabAccess.setTextColor(getResources().getColor(R.color.red));
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(LoggedIn.this, "Failed to read value.", Toast.LENGTH_SHORT).show();
            }
        });
        //logout button
        logout.setOnClickListener(v -> {
            fAuth.signOut();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        });

        networkLabAccess.setOnClickListener(v -> {
            biometricLogin();
        });

        developmentLabAccess.setOnClickListener(v -> {
            biometricLogin();
        });

        generalAccess.setOnClickListener(v -> {
            biometricLogin();
        });
    }

    public void biometricLogin() {
        Executor executor = ContextCompat.getMainExecutor(this);
        androidx.biometric.BiometricPrompt biometricPrompt = new androidx.biometric.BiometricPrompt(LoggedIn.this, executor, new androidx.biometric.BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                Toast.makeText(getApplicationContext(), "Authentication error: " + errString, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                count = 0;
                Toast.makeText(getApplicationContext(), "Authentication succeeded!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                count++;
                if (count == 3) {
                    Toast.makeText(getApplicationContext(), "Authentication failed", Toast.LENGTH_SHORT).show();
                    fAuth.signOut();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                } else
                    Toast.makeText(getApplicationContext(), "Authentication failed", Toast.LENGTH_SHORT).show();
            }
        });

        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Biometric login to validate access")
                .setSubtitle("Log in using your biometric credential")
                .setNegativeButtonText("Cancel")
                .build();
        biometricPrompt.authenticate(promptInfo);
    }

}

