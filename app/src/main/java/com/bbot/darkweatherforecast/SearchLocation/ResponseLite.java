package com.bbot.darkweatherforecast.SearchLocation;

import com.google.gson.annotations.SerializedName;

public class ResponseLite {

	@SerializedName("AdministrativeArea")
	private AdministrativeArea administrativeArea;

	@SerializedName("Type")
	private String type;

	@SerializedName("Version")
	private int version;

	@SerializedName("LocalizedName")
	private String localizedName;

	@SerializedName("Country")
	private Country country;

	@SerializedName("Rank")
	private int rank;

	@SerializedName("Key")
	private String key;



	public AdministrativeArea getAdministrativeArea(){
		return administrativeArea;
	}

	public String getType(){
		return type;
	}

	public int getVersion(){
		return version;
	}

	public String getLocalizedName(){
		return localizedName;
	}

	public Country getCountry(){
		return country;
	}

	public int getRank(){
		return rank;
	}

	public String getKey(){
		return key;
	}
}