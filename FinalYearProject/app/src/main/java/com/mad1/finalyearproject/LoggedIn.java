package com.mad1.finalyearproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoggedIn extends AppCompatActivity {
    Button logout;
    TextView generalAccess, developmentLabAccess, networkLabAccess;
    FirebaseAuth fAuth;
    FirebaseUser user;

    

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

        //get instance of a eu database
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://final-year-project-3600b-default-rtdb.europe-west1.firebasedatabase.app/");
        DatabaseReference myRef = database.getReference("User Info").child(userId);

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String generalAccess = dataSnapshot.child("Development Labs").getValue().toString();
                String developmentLabAccess = dataSnapshot.child("General Access").getValue().toString();
                String networkLabAccess = dataSnapshot.child("Network Labs").getValue().toString();

                if (generalAccess.equals("true")) {
                    LoggedIn.this.generalAccess.setText("Access: Granted");
                } else {
                    LoggedIn.this.generalAccess.setText("Access: Denied");
                }

                if (developmentLabAccess.equals("true")) {
                    LoggedIn.this.developmentLabAccess.setText("Access: Granted");
                } else {
                    LoggedIn.this.developmentLabAccess.setText("Access: Denied");
                }

                if (networkLabAccess.equals("true")) {
                    LoggedIn.this.networkLabAccess.setText("Access: Granted");
                } else {
                    LoggedIn.this.networkLabAccess.setText("Access: Denied");
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

    }
}

