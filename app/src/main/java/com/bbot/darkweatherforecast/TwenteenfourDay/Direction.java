package com.bbot.darkweatherforecast.TwenteenfourDay;

import com.google.gson.annotations.SerializedName;

public class Direction{

	@SerializedName("English")
	private String english;

	@SerializedName("Degrees")
	private int degrees;

	@SerializedName("Localized")
	private String localized;

	public String getEnglish(){
		return english;
	}

	public int getDegrees(){
		return degrees;
	}

	public String getLocalized(){
		return localized;
	}
}