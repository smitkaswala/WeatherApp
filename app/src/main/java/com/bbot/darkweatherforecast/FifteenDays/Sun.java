package com.bbot.darkweatherforecast.FifteenDays;

import com.google.gson.annotations.SerializedName;

public class Sun{

	@SerializedName("EpochSet")
	private int epochSet;

	@SerializedName("Set")
	private String set;

	@SerializedName("EpochRise")
	private int epochRise;

	@SerializedName("Rise")
	private String rise;

	public int getEpochSet(){
		return epochSet;
	}

	public String getSet(){
		return set;
	}

	public int getEpochRise(){
		return epochRise;
	}

	public String getRise(){
		return rise;
	}
}