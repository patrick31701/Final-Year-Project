package com.mad1.finalyearproject;


import androidx.biometric.BiometricManager;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class BiometricValidatorTest extends TestCase {

    @Test
    public void testBiometricValidator() {
        BiometricManager biometricManager = null;
        BiometricValidator biometricValidator = new BiometricValidator(biometricManager);
        assertNotNull(biometricValidator);
    }
}