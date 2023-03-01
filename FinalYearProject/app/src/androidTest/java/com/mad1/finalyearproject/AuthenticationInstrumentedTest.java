package com.mad1.finalyearproject;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class AuthenticationInstrumentedTest {

    private FirebaseAuth mAuth;

    @Before
    public void setUp() {
        // Initialize Firebase Auth
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        FirebaseApp.initializeApp(context);
        mAuth = FirebaseAuth.getInstance();
    }

    @Test
    public void signInWithEmailAndPassword_validCredentials() {
        mAuth.signInWithEmailAndPassword("test@lit.ie", "password")
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        assertNotNull(user);
                    } else {
                        fail("signInWithEmailAndPassword failed with valid credentials: " + task.getException().getMessage());
                    }
                });
    }

    @Test
    public void signInWithEmailAndPassword_invalidCredentials() {
        mAuth.signInWithEmailAndPassword("test@example.com", "invalidpassword")
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        fail("signInWithEmailAndPassword succeeded with invalid credentials");
                    } else {
                        assertNotNull(task.getException());
                    }
                });
    }

}
