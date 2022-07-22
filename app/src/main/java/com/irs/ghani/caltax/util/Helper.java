package com.irs.ghani.caltax.util;

import static com.irs.ghani.caltax.util.TaxModelHelper.currentScreensSelection;

import com.irs.ghani.caltax.taxyearmodels.IndividualTaxModel2022;

import java.text.DecimalFormat;

public class Helper {


    //GENERAL HELPER FUNCTIONS

    public static double getProgress() {
        return (((float) (TaxModelHelper.currentScreensSelection - 1) / (float) TaxModelHelper.totalScreensSelection) * 100);
    }

    public static String convertExponentToReadableString(double value)
    {
        String pattern = "##.##############";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        return decimalFormat.format(value);
    }




}
