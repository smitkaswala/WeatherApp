package com.bbot.darkweatherforecast.FifteenDays;

import com.google.gson.annotations.SerializedName;

public class Night{

	@SerializedName("RainProbability")
	private int rainProbability;

	@SerializedName("Wind")
	private Wind wind;

	@SerializedName("SnowProbability")
	private int snowProbability;

	@SerializedName("Snow")
	private Snow snow;

	@SerializedName("TotalLiquid")
	private TotalLiquid totalLiquid;

	@SerializedName("ShortPhrase")
	private String shortPhrase;

	@SerializedName("Ice")
	private Ice ice;

	@SerializedName("HoursOfRain")
	private double hoursOfRain;

	@SerializedName("HoursOfIce")
	private double hoursOfIce;

	@SerializedName("Rain")
	private Rain rain;

	@SerializedName("PrecipitationProbability")
	private int precipitationProbability;

	@SerializedName("HasPrecipitation")
	private boolean hasPrecipitation;

	@SerializedName("ThunderstormProbability")
	private int thunderstormProbability;

	@SerializedName("IceProbability")
	private int iceProbability;

	@SerializedName("IconPhrase")
	private String iconPhrase;

	@SerializedName("CloudCover")
	private int cloudCover;

	@SerializedName("LongPhrase")
	private String longPhrase;

	@SerializedName("Icon")
	private int icon;

	@SerializedName("WindGust")
	private WindGust windGust;

	@SerializedName("HoursOfSnow")
	private double hoursOfSnow;

	@SerializedName("HoursOfPrecipitation")
	private double hoursOfPrecipitation;

	public int getRainProbability(){
		return rainProbability;
	}

	public Wind getWind(){
		return wind;
	}

	public int getSnowProbability(){
		return snowProbability;
	}

	public Snow getSnow(){
		return snow;
	}

	public TotalLiquid getTotalLiquid(){
		return totalLiquid;
	}

	public String getShortPhrase(){
		return shortPhrase;
	}

	public Ice getIce(){
		return ice;
	}

	public double getHoursOfRain(){
		return hoursOfRain;
	}

	public double getHoursOfIce(){
		return hoursOfIce;
	}

	public Rain getRain(){
		return rain;
	}

	public int getPrecipitationProbability(){
		return precipitationProbability;
	}

	public boolean isHasPrecipitation(){
		return hasPrecipitation;
	}

	public int getThunderstormProbability(){
		return thunderstormProbability;
	}

	public int getIceProbability(){
		return iceProbability;
	}

	public String getIconPhrase(){
		return iconPhrase;
	}

	public int getCloudCover(){
		return cloudCover;
	}

	public String getLongPhrase(){
		return longPhrase;
	}

	public int getIcon(){
		return icon;
	}

	public WindGust getWindGust(){
		return windGust;
	}

	public double getHoursOfSnow(){
		return hoursOfSnow;
	}

	public double getHoursOfPrecipitation(){
		return hoursOfPrecipitation;
	}
}