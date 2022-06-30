package com.irs.ghani.caltax.util;

import android.animation.ValueAnimator;

public class Helper {

    public static boolean isIndividualSalary = false;
    public static boolean isIndividualCapitalGain = false;
    public static boolean isIndividualBusiness = false;
    public static boolean isIndividualOtherSources = false;
    public static boolean isIndividualProperty = false;

    public static int totalScreensSelection = 3;
    public static int currentScreensSelection = 1;

    public static void reset() {
        totalScreensSelection = 3;
        currentScreensSelection = 1;
    }

    public static double getProgress() {
        return (((float) (currentScreensSelection - 1) / (float) totalScreensSelection) * 100);
    }

    public static float getProgressFloat() {
        return (((float) (currentScreensSelection - 1) / (float) totalScreensSelection) * 100);
    }

    public static int getProgressInt() {
        return (int) (((float) (currentScreensSelection - 1) / (float) totalScreensSelection) * 100);
    }

}
