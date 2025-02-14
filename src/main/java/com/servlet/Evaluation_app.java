package com.servlet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//INVESTMENT CLASS
public class Evaluation_app {
	
    // Profitability level calculation
    protected static double calculateInvestment(double amount, double estimatedProfit, String dependents, String propertyWorth, String otherInvestmentsWorth, String incarcerationDuration, String Field) {
      
        //Get the approximation model map 
        HashMap<String,HashMap<String,Double>> eval_map = Approximation_model.model_map();
        
        //Get values for model variables
        double coeffDependents = dependents.equals("none") ? eval_map.get("dependents").get("none") : dependents.equals("one") ? eval_map.get("dependents").get("one") : 
        	dependents.equals("two_three") ? eval_map.get("dependents").get("two_three") : 1.0;
        double coeffProperty = propertyWorth.equals("0.6") ? eval_map.get("propertyWorth").get("0.6") : 
        	propertyWorth.equals("1m_land") ? eval_map.get("propertyWorth").get("1m_land") : 
        	propertyWorth.equals("4m_land") ? eval_map.get("propertyWorth").get("4m_land") : 
        		propertyWorth.equals("10m_land") ? eval_map.get("propertyWorth").get("10m_land") : 4.0;
        double coeffInvestments = otherInvestmentsWorth.equals("0.6") ? eval_map.get("otherInvestmentsWorth").get("0.6") : 
        	otherInvestmentsWorth.equals("1m_invest") ? eval_map.get("otherInvestmentsWorth").get("1m_invest") : 
        		otherInvestmentsWorth.equals("4m_invest") ? eval_map.get("otherInvestmentsWorth").get("4m_invest") : 
        			otherInvestmentsWorth.equals("10m_invest") ? eval_map.get("otherInvestmentsWorth").get("10m_invest") : 4.0;
        double coeffIncarceration = incarcerationDuration.equals("0") ? eval_map.get("incarcerationDuration").get("0") : 
        	incarcerationDuration.equals("no") ? eval_map.get("incarcerationDuration").get("no") : 
        		incarcerationDuration.equals("one_year") ? eval_map.get("incarcerationDuration").get("one_year") : 1.0;

    	//double coeffDependents = dependents.equals("none") ? 4.0 : dependents.equals("one") ? 3.0 : dependents.equals("two_three") ? 2.0 : 1.0;
        //double coeffProperty = propertyWorth.equals("0.6") ? 0.6 : propertyWorth.equals("1m_land") ? 1.0 : propertyWorth.equals("4m_land") ? 2.0 : propertyWorth.equals("10m_land") ? 3.0 : 4.0;
        //double coeffInvestments = otherInvestmentsWorth.equals("0.6") ? 0.6 : otherInvestmentsWorth.equals("1m_invest") ? 1.0 : otherInvestmentsWorth.equals("4m_invest") ? 2.0 : otherInvestmentsWorth.equals("10m_invest") ? 3.0 : 4.0;
        //double coeffIncarceration = incarcerationDuration.equals("0") ? 3.5 : incarcerationDuration.equals("no") ? 3.0 : incarcerationDuration.equals("one_year") ? 2.0 : 1.0;
        List<String> high = new ArrayList<>(); 
        high.add("Agriculture");
        high.add("Education"); 
        high.add("Finance");
        high.add("Engineering");
        high.add("Medicine");
        high.add("Information Technology");
        high.add("Supply Chain and Logistics");
        List<String> medium = new ArrayList<>(); 
        medium.add("Sales and Marketing");
        medium.add("Law"); 
        medium.add("Entertainment");
        List<String> low = new ArrayList<>(); 
        low.add("Arts");
        low.add("Others"); 
        low.add("Religion");
        
        double coeffField = high.contains(Field) ? eval_map.get("field").get("high") : 
        	medium.contains(Field) ? eval_map.get("field").get("medium") : 1.0;
        //double coeffField = high.contains(Field) ? 3.0 : medium.contains(Field) ? 2.0 : 1.0;
        
        //System.out.println(estimatedProfit + " " + coeffDependents + " " + coeffProperty + " " + coeffInvestments
        //		+ " " + coeffIncarceration + " " + coeffField + " " + amount);	
        
        //Apply the Log-Transformed Regression Model
		HashMap<String, Double> model_params = eval_map.get("modelParameters");
		double intercept = model_params.get("intercept");
		double ratio = model_params.get("ratio");
		double dependents_val = model_params.get("dependents");
		double property = model_params.get("property");
		double investments = model_params.get("investments");
		double incarceration = model_params.get("incarceration");
		double field = model_params.get("field");
		double y = intercept * (ratio * estimatedProfit/amount) + (dependents_val * coeffDependents) + (property * coeffProperty) + 
        		(investments * coeffInvestments) + (incarceration * coeffIncarceration) + (field * coeffField);
        //double y = -4.3883 * (1.0635 * estimatedProfit/amount) + (0.6604 * coeffDependents) + (0.5072 * coeffProperty) + 
        //		(0.4082 * coeffInvestments) + (0.4792 * coeffIncarceration) + (0.5720 * coeffField);
        return Math.exp(y);
        //return estimatedProfit * coeffDependents * coeffProperty * coeffInvestments * coeffIncarceration * coeffField/amount;
    }

}
