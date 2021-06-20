package com.bbot.darkweatherforecast.GetCall;

import com.google.gson.annotations.SerializedName;

public class Elevation{

	@SerializedName("Metric")
	private Metric metric;

	@SerializedName("Imperial")
	private Imperial imperial;

	public Metric getMetric(){
		return metric;
	}

	public Imperial getImperial(){
		return imperial;
	}
}