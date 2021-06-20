package com.bbot.darkweatherforecast.FifteenDays;

import com.google.gson.annotations.SerializedName;

public class Cooling{

	@SerializedName("UnitType")
	private int unitType;

	@SerializedName("Value")
	private double value;

	@SerializedName("Unit")
	private String unit;

	public int getUnitType(){
		return unitType;
	}

	public double getValue(){
		return value;
	}

	public String getUnit(){
		return unit;
	}
}