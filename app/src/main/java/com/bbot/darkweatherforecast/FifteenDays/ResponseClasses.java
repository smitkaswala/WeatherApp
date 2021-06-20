package com.bbot.darkweatherforecast.FifteenDays;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseClasses {

	@SerializedName("Headline")
	private Headline headline;

	@SerializedName("DailyForecasts")
	private List<DailyForecastsItem> dailyForecasts;

	public Headline getHeadline(){
		return headline;
	}

	public List<DailyForecastsItem> getDailyForecasts(){
		return dailyForecasts;
	}
}