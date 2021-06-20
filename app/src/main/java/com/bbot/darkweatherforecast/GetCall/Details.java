package com.bbot.darkweatherforecast.GetCall;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Details{

	@SerializedName("Climo")
	private String climo;

	@SerializedName("MarineStation")
	private String marineStation;

	@SerializedName("Metar")
	private String metar;

	@SerializedName("Satellite")
	private String satellite;

	@SerializedName("MarineStationGMTOffset")
	private Object marineStationGMTOffset;

	@SerializedName("PartnerID")
	private Object partnerID;

	@SerializedName("StationCode")
	private String stationCode;

	@SerializedName("CanonicalLocationKey")
	private String canonicalLocationKey;

	@SerializedName("Sources")
	private List<SourcesItem> sources;

	@SerializedName("CanonicalPostalCode")
	private String canonicalPostalCode;

	@SerializedName("LocationStem")
	private String locationStem;

	@SerializedName("PrimaryWarningCountyCode")
	private String primaryWarningCountyCode;

	@SerializedName("NXMetro")
	private String nXMetro;

	@SerializedName("StationGmtOffset")
	private double stationGmtOffset;

	@SerializedName("PrimaryWarningZoneCode")
	private String primaryWarningZoneCode;

	@SerializedName("VideoCode")
	private String videoCode;

	@SerializedName("BandMap")
	private String bandMap;

	@SerializedName("Population")
	private int population;

	@SerializedName("MediaRegion")
	private Object mediaRegion;

	@SerializedName("LocalRadar")
	private String localRadar;

	@SerializedName("Synoptic")
	private String synoptic;

	@SerializedName("Key")
	private String key;

	@SerializedName("NXState")
	private String nXState;

	public String getClimo(){
		return climo;
	}

	public String getMarineStation(){
		return marineStation;
	}

	public String getMetar(){
		return metar;
	}

	public String getSatellite(){
		return satellite;
	}

	public Object getMarineStationGMTOffset(){
		return marineStationGMTOffset;
	}

	public Object getPartnerID(){
		return partnerID;
	}

	public String getStationCode(){
		return stationCode;
	}

	public String getCanonicalLocationKey(){
		return canonicalLocationKey;
	}

	public List<SourcesItem> getSources(){
		return sources;
	}

	public String getCanonicalPostalCode(){
		return canonicalPostalCode;
	}

	public String getLocationStem(){
		return locationStem;
	}

	public String getPrimaryWarningCountyCode(){
		return primaryWarningCountyCode;
	}

	public String getNXMetro(){
		return nXMetro;
	}

	public double getStationGmtOffset(){
		return stationGmtOffset;
	}

	public String getPrimaryWarningZoneCode(){
		return primaryWarningZoneCode;
	}

	public String getVideoCode(){
		return videoCode;
	}

	public String getBandMap(){
		return bandMap;
	}

	public int getPopulation(){
		return population;
	}

	public Object getMediaRegion(){
		return mediaRegion;
	}

	public String getLocalRadar(){
		return localRadar;
	}

	public String getSynoptic(){
		return synoptic;
	}

	public String getKey(){
		return key;
	}

	public String getNXState(){
		return nXState;
	}
}