package com.bbot.darkweatherforecast.FifteenDays;

import com.google.gson.annotations.SerializedName;

public class DegreeDaySummary{

	@SerializedName("Cooling")
	private Cooling cooling;

	@SerializedName("Heating")
	private Heating heating;

	public Cooling getCooling(){
		return cooling;
	}

	public Heating getHeating(){
		return heating;
	}
}