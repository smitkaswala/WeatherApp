package com.bbot.darkweatherforecast.FifteenDays;

import com.google.gson.annotations.SerializedName;

public class Moon{

	@SerializedName("EpochSet")
	private int epochSet;

	@SerializedName("Set")
	private String set;

	@SerializedName("Phase")
	private String phase;

	@SerializedName("EpochRise")
	private int epochRise;

	@SerializedName("Age")
	private int age;

	@SerializedName("Rise")
	private String rise;

	public int getEpochSet(){
		return epochSet;
	}

	public String getSet(){
		return set;
	}

	public String getPhase(){
		return phase;
	}

	public int getEpochRise(){
		return epochRise;
	}

	public int getAge(){
		return age;
	}

	public String getRise(){
		return rise;
	}
}