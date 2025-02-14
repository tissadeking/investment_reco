package com.servlet;
import java.util.HashMap;
import java.util.Map;


public class Approximation_model {
	public static HashMap<String,HashMap<String,Double>> model_map(){
		
		//add set values for model variables
		HashMap<String, Double> dependents_map = new HashMap<>();
		dependents_map.put("none", 4.0);
		dependents_map.put("one", 3.0);
		dependents_map.put("two_three", 2.0);
		dependents_map.put("four_above", 1.0);
		
		HashMap<String, Double> propertyWorth_map = new HashMap<>();
		propertyWorth_map.put("0.6", 0.6);
		propertyWorth_map.put("1m_land", 1.0);
		propertyWorth_map.put("4m_land", 2.0);
		propertyWorth_map.put("10m_land", 3.0);
		propertyWorth_map.put("above_10m_land", 4.0);
		
		HashMap<String, Double> otherInvestmentsWorth_map = new HashMap<>();
		otherInvestmentsWorth_map.put("0.6", 0.6);
		otherInvestmentsWorth_map.put("1m_invest", 1.0);
		otherInvestmentsWorth_map.put("4m_invest", 2.0);
		otherInvestmentsWorth_map.put("10m_invest", 3.0);
		otherInvestmentsWorth_map.put("above_10m_invest", 4.0);
		
		HashMap<String, Double> incarcerationDuration_map = new HashMap<>();
		incarcerationDuration_map.put("0", 3.5);
		incarcerationDuration_map.put("no", 3.0);
		incarcerationDuration_map.put("one_year", 2.0);
		incarcerationDuration_map.put("above_one", 1.0);
		
		HashMap<String, Double> field_map = new HashMap<>();
		field_map.put("high", 3.0);
		field_map.put("medium", 2.0);
		field_map.put("low", 1.0);
		
		//add model coefficients and intercept
		HashMap<String, Double> model_paras = new HashMap<>();
		model_paras.put("intercept", -4.1890);
		model_paras.put("ratio", 1.0920);
		model_paras.put("dependents", 0.6245);
		model_paras.put("property", 0.4838);
		model_paras.put("investments", 0.4326);
		model_paras.put("incarceration", 0.4735);
		model_paras.put("field", 0.5530);
				
		
		//Map<String,Map<String,Double>> items_map = new HashMap<String,HashMap<String,Double>>();
		HashMap<String,HashMap<String,Double>> items_map = new HashMap<>();
		items_map.put("dependents", dependents_map);
		items_map.put("propertyWorth", propertyWorth_map);
		items_map.put("otherInvestmentsWorth", otherInvestmentsWorth_map);
		items_map.put("incarcerationDuration", incarcerationDuration_map);
		items_map.put("field", field_map);
		items_map.put("modelParameters", model_paras);
		//System.out.println(items_map);
		return items_map;
	}
	
}
