package com.irs.ghani.caltax.taxyearmodels;

import android.util.Log;

public class IndividualTaxModel2022 {

    public static double totalTaxableIncome;
    public static double taxableIncomeFromSalary;
    public static int taxableIncomeFromProperty;
    public static int taxableIncomeFromBusiness;
    public static int incomeFromCapitalGain;
    public static int incomeFromOtherSources;
    public static int incomeFromAgriculture;

    //Helper Values+
    public static double previousSavedData;

    //Helper Functions
    public static void setPreviousSavedData(double previousSavedData) {
        IndividualTaxModel2022.previousSavedData = previousSavedData;
    }

    public static void setTotalTaxableIncome(double totalTaxableIncome) {
        IndividualTaxModel2022.totalTaxableIncome += totalTaxableIncome;
    }

    public static void adJustTotalTaxableIncome() {
        IndividualTaxModel2022.totalTaxableIncome -= previousSavedData;
        setPreviousSavedData(0.0);
        printTaxModelData2022();
    }

    //Setting up Tax Model
    public static void setTaxableIncomeFromSalary(double salary) {
        IndividualTaxModel2022.taxableIncomeFromSalary = salary;
        IndividualTaxModel2022.setTotalTaxableIncome(salary);
        IndividualTaxModel2022.setPreviousSavedData(salary);
        IndividualTaxModel2022.printTaxModelData2022();
    }

    public static void setTaxableIncomeFromProperty(int propertyValue) {
        IndividualTaxModel2022.taxableIncomeFromProperty = propertyValue;
        IndividualTaxModel2022.printTaxModelData2022();
    }

    public static void setTaxableIncomeFromBusiness(int value) {
        IndividualTaxModel2022.taxableIncomeFromBusiness = value;
        IndividualTaxModel2022.printTaxModelData2022();
    }

    public static void setIncomeFromCapitalGain(int incomeFromCapitalGain) {
        IndividualTaxModel2022.incomeFromCapitalGain = incomeFromCapitalGain;
        IndividualTaxModel2022.printTaxModelData2022();
    }

    public static void setIncomeFromOtherSources(int incomeFromOtherSources) {
        IndividualTaxModel2022.incomeFromOtherSources = incomeFromOtherSources;
        IndividualTaxModel2022.printTaxModelData2022();
    }

    public static void setIncomeFromAgriculture(int incomeFromAgriculture) {
        IndividualTaxModel2022.incomeFromAgriculture = incomeFromAgriculture;
        IndividualTaxModel2022.printTaxModelData2022();
    }

    public static void printTaxModelData2022() {
        Log.d("Data Tax Model 2022", "Total Taxable Income: " + totalTaxableIncome);
        Log.d("Data Tax Model 2022", "Previous Saved Data: " + previousSavedData);

        Log.d("Data Tax Model 2022", "Taxable Income From Salary: " + taxableIncomeFromSalary);
        Log.d("Data Tax Model 2022", "Taxable Income From Property: " + taxableIncomeFromProperty);
        Log.d("Data Tax Model 2022", "Taxable Income From Business: " + taxableIncomeFromBusiness);
        Log.d("Data Tax Model 2022", "Taxable Income From Capital Gain: " + incomeFromCapitalGain);
        Log.d("Data Tax Model 2022", "Taxable Income From Other Sources: " + incomeFromOtherSources);
        Log.d("Data Tax Model 2022", "Taxable Income From Agriculture: " + incomeFromAgriculture);
    }
}
