package com.bbot.darkweatherforecast.TwenteenfourDay;

import com.google.gson.annotations.SerializedName;

public class ResponseCall{

	@SerializedName("RainProbability")
	private int rainProbability;

	@SerializedName("Wind")
	private Wind wind;

	@SerializedName("Temperature")
	private Temperature temperature;

	@SerializedName("SnowProbability")
	private int snowProbability;

	@SerializedName("Snow")
	private Snow snow;

	@SerializedName("TotalLiquid")
	private TotalLiquid totalLiquid;

	@SerializedName("Ceiling")
	private Ceiling ceiling;

	@SerializedName("DateTime")
	private String dateTime;

	@SerializedName("RealFeelTemperature")
	private RealFeelTemperature realFeelTemperature;

	@SerializedName("Rain")
	private Rain rain;

	@SerializedName("PrecipitationProbability")
	private int precipitationProbability;

	@SerializedName("HasPrecipitation")
	private boolean hasPrecipitation;

	@SerializedName("RelativeHumidity")
	private int relativeHumidity;

	@SerializedName("UVIndexText")
	private String uVIndexText;

	@SerializedName("IconPhrase")
	private String iconPhrase;

	@SerializedName("CloudCover")
	private int cloudCover;

	@SerializedName("WindGust")
	private WindGust windGust;

	@SerializedName("UVIndex")
	private int uVIndex;

	@SerializedName("WeatherIcon")
	private int weatherIcon;

	@SerializedName("Ice")
	private Ice ice;

	@SerializedName("DewPoint")
	private DewPoint dewPoint;

	@SerializedName("IndoorRelativeHumidity")
	private int indoorRelativeHumidity;

	@SerializedName("IceProbability")
	private int iceProbability;

	@SerializedName("EpochDateTime")
	private int epochDateTime;

	@SerializedName("WetBulbTemperature")
	private WetBulbTemperature wetBulbTemperature;

	@SerializedName("Visibility")
	private Visibility visibility;

	@SerializedName("IsDaylight")
	private boolean isDaylight;

	@SerializedName("Link")
	private String link;

	@SerializedName("MobileLink")
	private String mobileLink;

	public int getRainProbability(){
		return rainProbability;
	}

	public Wind getWind(){
		return wind;
	}

	public Temperature getTemperature(){
		return temperature;
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

	public Ceiling getCeiling(){
		return ceiling;
	}

	public String getDateTime(){
		return dateTime;
	}

	public RealFeelTemperature getRealFeelTemperature(){
		return realFeelTemperature;
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

	public int getRelativeHumidity(){
		return relativeHumidity;
	}

	public String getUVIndexText(){
		return uVIndexText;
	}

	public String getIconPhrase(){
		return iconPhrase;
	}

	public int getCloudCover(){
		return cloudCover;
	}

	public WindGust getWindGust(){
		return windGust;
	}

	public int getUVIndex(){
		return uVIndex;
	}

	public int getWeatherIcon(){
		return weatherIcon;
	}

	public Ice getIce(){
		return ice;
	}

	public DewPoint getDewPoint(){
		return dewPoint;
	}

	public int getIndoorRelativeHumidity(){
		return indoorRelativeHumidity;
	}

	public int getIceProbability(){
		return iceProbability;
	}

	public int getEpochDateTime(){
		return epochDateTime;
	}

	public WetBulbTemperature getWetBulbTemperature(){
		return wetBulbTemperature;
	}

	public Visibility getVisibility(){
		return visibility;
	}

	public boolean isIsDaylight(){
		return isDaylight;
	}

	public String getLink(){
		return link;
	}

	public String getMobileLink(){
		return mobileLink;
	}
}