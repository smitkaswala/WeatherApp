package com.bbot.darkweatherforecast.GetCall;

import com.google.gson.annotations.SerializedName;

public class SupplementalAdminAreasItem{

	@SerializedName("LocalizedName")
	private String localizedName;

	@SerializedName("Level")
	private int level;

	@SerializedName("EnglishName")
	private String englishName;

	public String getLocalizedName(){
		return localizedName;
	}

	public int getLevel(){
		return level;
	}

	public String getEnglishName(){
		return englishName;
	}
}