package com.bbot.darkweatherforecast.FifteenDays;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DailyForecastsItem{

	@SerializedName("Temperature")
	public Temperature temperature;

	@SerializedName("Night")
	public Night night;

	@SerializedName("EpochDate")
	public int epochDate;

	@SerializedName("Moon")
	public Moon moon;

	@SerializedName("DegreeDaySummary")
	public DegreeDaySummary degreeDaySummary;

	@SerializedName("RealFeelTemperatureShade")
	public RealFeelTemperatureShade realFeelTemperatureShade;

	@SerializedName("AirAndPollen")
	public List<AirAndPollenItem> airAndPollen;

	@SerializedName("HoursOfSun")
	public double hoursOfSun;

	@SerializedName("Sun")
	public Sun sun;

	@SerializedName("Sources")
	public List<String> sources;

	@SerializedName("Date")
	public String date;

	@SerializedName("RealFeelTemperature")
	public RealFeelTemperature realFeelTemperature;

	@SerializedName("Day")
	public Day day;

	@SerializedName("Link")
	public String link;

	@SerializedName("MobileLink")
	public String mobileLink;

	public Temperature getTemperature(){
		return temperature;
	}

	public Night getNight(){
		return night;
	}

	public int getEpochDate(){
		return epochDate;
	}

	public Moon getMoon(){
		return moon;
	}

	public DegreeDaySummary getDegreeDaySummary(){
		return degreeDaySummary;
	}

	public RealFeelTemperatureShade getRealFeelTemperatureShade(){
		return realFeelTemperatureShade;
	}

	public List<AirAndPollenItem> getAirAndPollen(){
		return airAndPollen;
	}

	public double getHoursOfSun(){
		return hoursOfSun;
	}

	public Sun getSun(){
		return sun;
	}

	public List<String> getSources(){
		return sources;
	}

	public String getDate(){
		return date;
	}

	public RealFeelTemperature getRealFeelTemperature(){
		return realFeelTemperature;
	}

	public Day getDay(){
		return day;
	}

	public String getLink(){
		return link;
	}

	public String getMobileLink(){
		return mobileLink;
	}
}