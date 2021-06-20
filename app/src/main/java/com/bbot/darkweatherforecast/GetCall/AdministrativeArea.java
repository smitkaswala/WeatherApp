package com.bbot.darkweatherforecast.GetCall;

import com.google.gson.annotations.SerializedName;

public class AdministrativeArea{

	@SerializedName("CountryID")
	private String countryID;

	@SerializedName("LocalizedType")
	private String localizedType;

	@SerializedName("LocalizedName")
	private String localizedName;

	@SerializedName("Level")
	private int level;

	@SerializedName("ID")
	private String iD;

	@SerializedName("EnglishType")
	private String englishType;

	@SerializedName("EnglishName")
	private String englishName;

	public String getCountryID(){
		return countryID;
	}

	public String getLocalizedType(){
		return localizedType;
	}

	public String getLocalizedName(){
		return localizedName;
	}

	public int getLevel(){
		return level;
	}

	public String getID(){
		return iD;
	}

	public String getEnglishType(){
		return englishType;
	}

	public String getEnglishName(){
		return englishName;
	}
}