package com.bbot.darkweatherforecast.GetCall;

import com.google.gson.annotations.SerializedName;

public class Region{

	@SerializedName("LocalizedName")
	private String localizedName;

	@SerializedName("ID")
	private String iD;

	@SerializedName("EnglishName")
	private String englishName;

	public String getLocalizedName(){
		return localizedName;
	}

	public String getID(){
		return iD;
	}

	public String getEnglishName(){
		return englishName;
	}
}