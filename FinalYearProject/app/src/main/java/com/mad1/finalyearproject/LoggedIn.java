package com.mad1.finalyearproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoggedIn extends AppCompatActivity {
    Button logout;
    FirebaseAuth fAuth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);
        logout = findViewById(R.id.btn_logout);
        fAuth = FirebaseAuth.getInstance();
        user = fAuth.getCurrentUser();

        logout.setOnClickListener(v -> {
            fAuth.signOut();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        });
    }
}

