package com.bbot.darkweatherforecast.FullDay;

import com.google.gson.annotations.SerializedName;

public class TemperatureSummary{

	@SerializedName("Past6HourRange")
	private Past6HourRange past6HourRange;

	@SerializedName("Past24HourRange")
	private Past24HourRange past24HourRange;

	@SerializedName("Past12HourRange")
	private Past12HourRange past12HourRange;

	public Past6HourRange getPast6HourRange(){
		return past6HourRange;
	}

	public Past24HourRange getPast24HourRange(){
		return past24HourRange;
	}

	public Past12HourRange getPast12HourRange(){
		return past12HourRange;
	}
}