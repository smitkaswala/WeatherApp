package com.bbot.darkweatherforecast;

import com.bbot.darkweatherforecast.GetCall.AdministrativeArea;
import com.bbot.darkweatherforecast.GetCall.Country;
import com.bbot.darkweatherforecast.GetCall.Details;
import com.bbot.darkweatherforecast.GetCall.GeoPosition;
import com.bbot.darkweatherforecast.GetCall.Region;
import com.bbot.darkweatherforecast.GetCall.SupplementalAdminAreasItem;
import com.bbot.darkweatherforecast.GetCall.TimeZone;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseClass {

	@SerializedName("AdministrativeArea")
	private AdministrativeArea administrativeArea;

	@SerializedName("LocalizedName")
	private String localizedName;

	@SerializedName("SupplementalAdminAreas")
	private List<SupplementalAdminAreasItem> supplementalAdminAreas;

	@SerializedName("DataSets")
	private List<String> dataSets;

	@SerializedName("Rank")
	private int rank;

	@SerializedName("IsAlias")
	private boolean isAlias;

	@SerializedName("Type")
	private String type;

	@SerializedName("TimeZone")
	private TimeZone timeZone;

	@SerializedName("Details")
	private Details details;

	@SerializedName("Version")
	private int version;

	@SerializedName("PrimaryPostalCode")
	private String primaryPostalCode;

	@SerializedName("Region")
	private Region region;

	@SerializedName("Country")
	private Country country;

	@SerializedName("GeoPosition")
	private GeoPosition geoPosition;

	@SerializedName("Key")
	private String key;

	@SerializedName("EnglishName")
	private String englishName;

	public AdministrativeArea getAdministrativeArea(){
		return administrativeArea;
	}

	public String getLocalizedName(){
		return localizedName;
	}

	public List<SupplementalAdminAreasItem> getSupplementalAdminAreas(){
		return supplementalAdminAreas;
	}

	public List<String> getDataSets(){
		return dataSets;
	}

	public int getRank(){
		return rank;
	}

	public boolean isIsAlias(){
		return isAlias;
	}

	public String getType(){
		return type;
	}

	public TimeZone getTimeZone(){
		return timeZone;
	}

	public Details getDetails(){
		return details;
	}

	public int getVersion(){
		return version;
	}

	public String getPrimaryPostalCode(){
		return primaryPostalCode;
	}

	public Region getRegion(){
		return region;
	}

	public Country getCountry(){
		return country;
	}

	public GeoPosition getGeoPosition(){
		return geoPosition;
	}

	public String getKey(){
		return key;
	}

	public String getEnglishName(){
		return englishName;
	}
}