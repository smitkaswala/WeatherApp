package com.bbot.darkweatherforecast.SearchLocation;

import com.google.gson.annotations.SerializedName;

public class Country{

	@SerializedName("LocalizedName")
	private String localizedName;

	@SerializedName("ID")
	private String iD;

	public String getLocalizedName(){
		return localizedName;
	}

	public String getID(){
		return iD;
	}
}