package com.bbot.darkweatherforecast.FullDay;

import com.google.gson.annotations.SerializedName;

public class PressureTendency{

	@SerializedName("Code")
	private String code;

	@SerializedName("LocalizedText")
	private String localizedText;

	public String getCode(){
		return code;
	}

	public String getLocalizedText(){
		return localizedText;
	}
}