package com.corphish.nightlight.Engine;

import android.os.AsyncTask;
import android.util.Log;

import com.corphish.nightlight.Data.Constants;
import com.corphish.nightlight.Helpers.RootUtils;

/**
 * Created by Avinaba on 10/4/2017.
 * Basic functions of the app
 */

public class Core {
    private static void enableNightMode(int intensity) {
        Log.d("NL","Intensity - " + intensity);
        RootUtils.writeToFile("1", Constants.KCAL_SWITCH);
        RootUtils.writeToFile("256 256 "+(Constants.MAX_BLUE_LIGHT - intensity),Constants.KCAL_ADJUST);
    }

    private static void disableNightMode() {
        RootUtils.writeToFile("256 256 256", Constants.KCAL_ADJUST);
    }

    public static void applyNightMode(boolean e, int intensity) {
        if (e) enableNightMode(intensity);
        else disableNightMode();
    }

    public static void applyNightModeAsync(boolean b, int i) {
        new NightModeApplier(b, i).execute();
    }

    private static class NightModeApplier extends AsyncTask<Object, Object, Object> {
        boolean enabled;
        int intensity;

        NightModeApplier(boolean enabled, int intensity) {
            this.enabled = enabled;
            this.intensity = intensity;
        }

        @Override
        protected Object doInBackground(Object... bubbles) {
            applyNightMode(enabled, intensity);
            return null;
        }
    }
}