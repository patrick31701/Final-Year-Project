package com.mad1.finalyearproject;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class FirebaseRealtimeDBInstrumentedTest {

    FirebaseAuth fAuth;
    FirebaseUser user;

    @Before
    public void setUp() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        FirebaseApp.initializeApp(context);
        fAuth = FirebaseAuth.getInstance();
        user = fAuth.getCurrentUser();
    }

    @Test
    public void testDatabase() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                assertNotNull(snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                fail("Database error: " + error.getMessage());
            }
        });
    }

    @Test
    public void testWriteToDatabase() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("test").setValue("test");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                assertNotNull(snapshot.child("test").getValue());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                fail("Database error: " + error.getMessage());
            }
        });
    }

    @Test
    public void testReadFromUserInfo() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("User Info").child("bDDI4D8TEMQvCJRnaRd04HE2GfF3").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                assertNotNull(snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                fail("Database error: " + error.getMessage());
            }
        });
    }
}
