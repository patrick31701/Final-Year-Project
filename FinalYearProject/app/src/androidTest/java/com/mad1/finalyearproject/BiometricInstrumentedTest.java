package com.mad1.finalyearproject;

import static org.junit.Assert.assertEquals;

import android.content.Context;

import androidx.biometric.BiometricManager;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
public class BiometricInstrumentedTest {
    @Test
    public void testFingerprint() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        BiometricManager biometricManager = BiometricManager.from(context);
        assertEquals(BiometricManager.BIOMETRIC_SUCCESS, biometricManager.canAuthenticate());
    }
}
