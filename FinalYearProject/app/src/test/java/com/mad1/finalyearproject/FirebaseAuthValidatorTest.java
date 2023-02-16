package com.mad1.finalyearproject;

import com.google.firebase.auth.FirebaseAuth;

import junit.framework.TestCase;

import org.junit.Test;

public class FirebaseAuthValidatorTest extends TestCase {

    @Test
    public void testFirebaseAuthValidator() {
        FirebaseAuth fAuth = null;
        FirebaseAuthValidator firebaseAuthValidator = new FirebaseAuthValidator(fAuth);
        assertNotNull(firebaseAuthValidator);
    }

}