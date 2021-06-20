package com.bbot.darkweatherforecast.FullDay;

import com.google.gson.annotations.SerializedName;

public class PrecipitationSummary{

	@SerializedName("Past6Hours")
	private Past6Hours past6Hours;

	@SerializedName("Precipitation")
	private Precipitation precipitation;

	@SerializedName("Past9Hours")
	private Past9Hours past9Hours;

	@SerializedName("Past3Hours")
	private Past3Hours past3Hours;

	@SerializedName("PastHour")
	private PastHour pastHour;

	@SerializedName("Past18Hours")
	private Past18Hours past18Hours;

	@SerializedName("Past24Hours")
	private Past24Hours past24Hours;

	@SerializedName("Past12Hours")
	private Past12Hours past12Hours;

	public Past6Hours getPast6Hours(){
		return past6Hours;
	}

	public Precipitation getPrecipitation(){
		return precipitation;
	}

	public Past9Hours getPast9Hours(){
		return past9Hours;
	}

	public Past3Hours getPast3Hours(){
		return past3Hours;
	}

	public PastHour getPastHour(){
		return pastHour;
	}

	public Past18Hours getPast18Hours(){
		return past18Hours;
	}

	public Past24Hours getPast24Hours(){
		return past24Hours;
	}

	public Past12Hours getPast12Hours(){
		return past12Hours;
	}
}