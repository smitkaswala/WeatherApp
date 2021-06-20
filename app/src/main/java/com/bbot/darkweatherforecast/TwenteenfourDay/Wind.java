package com.bbot.darkweatherforecast.TwenteenfourDay;

import com.google.gson.annotations.SerializedName;

public class Wind{

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