package com.irs.ghani.caltax.taxyearmodels;

public class IndividualTaxModel2022 {

    public static int taxableIncomeFromSalary;
    public static int taxableIncomeFromProperty;

    public static void setTaxableIncomeFromSalary(int salary)
    {
        taxableIncomeFromSalary = salary;
    }

    public static void setTaxableIncomeFromProperty(int propertyValue)
    {
        taxableIncomeFromProperty = propertyValue;
    }


}
