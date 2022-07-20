package com.irs.ghani.caltax.taxyearmodels;

public class IndividualTaxModel2022 {

    public static int taxableIncomeFromSalary;
    public static int taxableIncomeFromProperty;
    public static int taxableIncomeFromBusiness;
    public static int incomeFromCapitalGain;
    public static int incomeFromOtherSources;
    public static int incomeFromAgriculture;

    public static void setTaxableIncomeFromSalary(int salary) {
        taxableIncomeFromSalary = salary;
    }

    public static void setTaxableIncomeFromProperty(int propertyValue) {
        taxableIncomeFromProperty = propertyValue;
    }

    public static void setTaxableIncomeFromBusiness(int value) {
        taxableIncomeFromBusiness = value;
    }

    public static void setIncomeFromCapitalGain(int incomeFromCapitalGain) {
        IndividualTaxModel2022.incomeFromCapitalGain = incomeFromCapitalGain;
    }

    public static void setIncomeFromOtherSources(int incomeFromOtherSources) {
        IndividualTaxModel2022.incomeFromOtherSources = incomeFromOtherSources;
    }

    public static void setIncomeFromAgriculture(int incomeFromAgriculture) {
        IndividualTaxModel2022.incomeFromAgriculture = incomeFromAgriculture;
    }
}
