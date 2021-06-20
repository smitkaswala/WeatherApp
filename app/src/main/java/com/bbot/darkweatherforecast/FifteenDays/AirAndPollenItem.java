package com.bbot.darkweatherforecast.FifteenDays;

import com.google.gson.annotations.SerializedName;

public class AirAndPollenItem{

	@SerializedName("Category")
	private String category;

	@SerializedName("Value")
	private int value;

	@SerializedName("CategoryValue")
	private int categoryValue;

	@SerializedName("Name")
	private String name;

	@SerializedName("Type")
	private String type;

	public String getCategory(){
		return category;
	}

	public int getValue(){
		return value;
	}

	public int getCategoryValue(){
		return categoryValue;
	}

	public String getName(){
		return name;
	}

	public String getType(){
		return type;
	}
}