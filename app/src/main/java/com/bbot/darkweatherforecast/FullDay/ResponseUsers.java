package com.bbot.darkweatherforecast.FullDay;

import com.google.gson.annotations.SerializedName;

public class ResponseUsers{

	@SerializedName("Wind")
	private Wind wind;

	@SerializedName("Temperature")
	private Temperature temperature;

	@SerializedName("Past24HourTemperatureDeparture")
	private Past24HourTemperatureDeparture past24HourTemperatureDeparture;

	@SerializedName("PressureTendency")
	private PressureTendency pressureTendency;

	@SerializedName("ObstructionsToVisibility")
	private String obstructionsToVisibility;

	@SerializedName("Ceiling")
	private Ceiling ceiling;

	@SerializedName("RealFeelTemperatureShade")
	private RealFeelTemperatureShade realFeelTemperatureShade;

	@SerializedName("EpochTime")
	private int epochTime;

	@SerializedName("RealFeelTemperature")
	private RealFeelTemperature realFeelTemperature;

	@SerializedName("PrecipitationType")
	private Object precipitationType;

	@SerializedName("HasPrecipitation")
	private boolean hasPrecipitation;

	@SerializedName("RelativeHumidity")
	private int relativeHumidity;

	@SerializedName("PrecipitationSummary")
	private PrecipitationSummary precipitationSummary;

	@SerializedName("TemperatureSummary")
	private TemperatureSummary temperatureSummary;

	@SerializedName("LocalObservationDateTime")
	private String localObservationDateTime;

	@SerializedName("UVIndexText")
	private String uVIndexText;

	@SerializedName("WeatherText")
	private String weatherText;

	@SerializedName("CloudCover")
	private int cloudCover;

	@SerializedName("WindGust")
	private WindGust windGust;

	@SerializedName("UVIndex")
	private int uVIndex;

	@SerializedName("Precip1hr")
	private Precip1hr precip1hr;

	@SerializedName("WeatherIcon")
	private int weatherIcon;

	@SerializedName("DewPoint")
	private DewPoint dewPoint;

	@SerializedName("Pressure")
	private Pressure pressure;

	@SerializedName("IsDayTime")
	private boolean isDayTime;

	@SerializedName("IndoorRelativeHumidity")
	private int indoorRelativeHumidity;

	@SerializedName("ApparentTemperature")
	private ApparentTemperature apparentTemperature;

	@SerializedName("WetBulbTemperature")
	private WetBulbTemperature wetBulbTemperature;

	@SerializedName("Visibility")
	private Visibility visibility;

	@SerializedName("WindChillTemperature")
	private WindChillTemperature windChillTemperature;

	@SerializedName("Link")
	private String link;

	@SerializedName("MobileLink")
	private String mobileLink;

	public Wind getWind(){
		return wind;
	}

	public Temperature getTemperature(){
		return temperature;
	}

	public Past24HourTemperatureDeparture getPast24HourTemperatureDeparture(){
		return past24HourTemperatureDeparture;
	}

	public PressureTendency getPressureTendency(){
		return pressureTendency;
	}

	public String getObstructionsToVisibility(){
		return obstructionsToVisibility;
	}

	public Ceiling getCeiling(){
		return ceiling;
	}

	public RealFeelTemperatureShade getRealFeelTemperatureShade(){
		return realFeelTemperatureShade;
	}

	public int getEpochTime(){
		return epochTime;
	}

	public RealFeelTemperature getRealFeelTemperature(){
		return realFeelTemperature;
	}

	public Object getPrecipitationType(){
		return precipitationType;
	}

	public boolean isHasPrecipitation(){
		return hasPrecipitation;
	}

	public int getRelativeHumidity(){
		return relativeHumidity;
	}

	public PrecipitationSummary getPrecipitationSummary(){
		return precipitationSummary;
	}

	public TemperatureSummary getTemperatureSummary(){
		return temperatureSummary;
	}

	public String getLocalObservationDateTime(){
		return localObservationDateTime;
	}

	public String getUVIndexText(){
		return uVIndexText;
	}

	public String getWeatherText(){
		return weatherText;
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

	public Precip1hr getPrecip1hr(){
		return precip1hr;
	}

	public int getWeatherIcon(){
		return weatherIcon;
	}

	public DewPoint getDewPoint(){
		return dewPoint;
	}

	public Pressure getPressure(){
		return pressure;
	}

	public boolean isIsDayTime(){
		return isDayTime;
	}

	public int getIndoorRelativeHumidity(){
		return indoorRelativeHumidity;
	}

	public ApparentTemperature getApparentTemperature(){
		return apparentTemperature;
	}

	public WetBulbTemperature getWetBulbTemperature(){
		return wetBulbTemperature;
	}

	public Visibility getVisibility(){
		return visibility;
	}

	public WindChillTemperature getWindChillTemperature(){
		return windChillTemperature;
	}

	public String getLink(){
		return link;
	}

	public String getMobileLink(){
		return mobileLink;
	}
}