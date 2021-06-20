package com.bbot.darkweatherforecast.FullDay;

import com.google.gson.annotations.SerializedName;

public class Past24HourRange{

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