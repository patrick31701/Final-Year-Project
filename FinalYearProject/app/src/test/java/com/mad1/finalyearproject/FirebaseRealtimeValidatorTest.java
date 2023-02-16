package com.mad1.finalyearproject;

import junit.framework.TestCase;

import org.junit.Test;

public class FirebaseRealtimeValidatorTest extends TestCase {

    @Test
    public void testFirebaseRealtimeValidator() {
        FirebaseRealtimeValidator firebaseRealtimeValidator = new FirebaseRealtimeValidator();
        assertNotNull(firebaseRealtimeValidator);
    }

}