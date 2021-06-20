package com.bbot.darkweatherforecast.GetCall;

import com.google.gson.annotations.SerializedName;

public class GeoPosition{

	@SerializedName("Elevation")
	private Elevation elevation;

	@SerializedName("Latitude")
	private double latitude;

	@SerializedName("Longitude")
	private double longitude;

	public Elevation getElevation(){
		return elevation;
	}

	public double getLatitude(){
		return latitude;
	}

	public double getLongitude(){
		return longitude;
	}
}