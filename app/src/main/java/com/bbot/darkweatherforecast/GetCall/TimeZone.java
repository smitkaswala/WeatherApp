package com.bbot.darkweatherforecast.GetCall;

import com.google.gson.annotations.SerializedName;

public class TimeZone{

	@SerializedName("NextOffsetChange")
	private Object nextOffsetChange;

	@SerializedName("GmtOffset")
	private double gmtOffset;

	@SerializedName("Code")
	private String code;

	@SerializedName("IsDaylightSaving")
	private boolean isDaylightSaving;

	@SerializedName("Name")
	private String name;

	public Object getNextOffsetChange(){
		return nextOffsetChange;
	}

	public double getGmtOffset(){
		return gmtOffset;
	}

	public String getCode(){
		return code;
	}

	public boolean isIsDaylightSaving(){
		return isDaylightSaving;
	}

	public String getName(){
		return name;
	}
}