package com.bbot.darkweatherforecast.TwenteenfourDay;

import com.google.gson.annotations.SerializedName;

public class WetBulbTemperature{

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