package com.bbot.darkweatherforecast.FifteenDays;

import com.google.gson.annotations.SerializedName;

public class WindGust{

	@SerializedName("Speed")
	private Speed speed;

	@SerializedName("Direction")
	private Direction direction;

	public Speed getSpeed(){
		return speed;
	}

	public Direction getDirection(){
		return direction;
	}
}