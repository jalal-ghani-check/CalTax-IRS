package com.irs.ghani.caltax.util;

import com.irs.ghani.caltax.taxyearmodels.IndividualTaxModel2022;

public class TaxModelHelper {


    public static boolean isIndividualSalary = false;
    public static boolean isIndividualCapitalGain = false;
    public static boolean isIndividualBusiness = false;
    public static boolean isIndividualOtherSources = false;
    public static boolean isIndividualProperty = false;

    public static boolean isYear2022 = false;
    public static boolean isYear2023 = false;

    public static int totalScreensSelection = 3;
    public static int currentScreensSelection = 1;

    public static void reset() {
        totalScreensSelection = 3;
        currentScreensSelection = 1;
    }

    public static void setTaxableSalary(double salary) {
        IndividualTaxModel2022.setTaxableIncomeFromSalary(salary);
    }

    public static void setTaxableIncomeFromProperty(int propertyValue) {
        IndividualTaxModel2022.setTaxableIncomeFromProperty(propertyValue);
    }

    public static void setTaxableIncomeFromBusiness(int Value) {
        IndividualTaxModel2022.setTaxableIncomeFromBusiness(Value);
    }

    public static void setCapitalGainData(int capitalGain, int otherSources, int agriculture) {
        IndividualTaxModel2022.setIncomeFromCapitalGain(capitalGain);
        IndividualTaxModel2022.setIncomeFromOtherSources(otherSources);
        IndividualTaxModel2022.setIncomeFromAgriculture(agriculture);
    }

    public static void printTaxModelData() {
        IndividualTaxModel2022.printTaxModelData2022();
    }

    public static void adJustTotalTaxableIncome() {
        IndividualTaxModel2022.adJustTotalTaxableIncome();
    }

    public static void backPressed() {
        if (TaxModelHelper.currentScreensSelection > 1)
            TaxModelHelper.currentScreensSelection--;
        TaxModelHelper.adJustTotalTaxableIncome();
    }
}
