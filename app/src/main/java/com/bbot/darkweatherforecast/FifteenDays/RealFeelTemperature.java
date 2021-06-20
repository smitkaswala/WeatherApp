package com.bbot.darkweatherforecast.FifteenDays;

import com.google.gson.annotations.SerializedName;

public class RealFeelTemperature{

	@SerializedName("Minimum")
	private Minimum minimum;

	@SerializedName("Maximum")
	private Maximum maximum;

	public Minimum getMinimum(){
		return minimum;
	}

	public Maximum getMaximum(){
		return maximum;
	}
}