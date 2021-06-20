package com.bbot.darkweatherforecast.GetCall;

import com.google.gson.annotations.SerializedName;

public class SourcesItem{

	@SerializedName("SourceId")
	private int sourceId;

	@SerializedName("DataType")
	private String dataType;

	@SerializedName("PartnerSourceUrl")
	private String partnerSourceUrl;

	@SerializedName("Source")
	private String source;

	public int getSourceId(){
		return sourceId;
	}

	public String getDataType(){
		return dataType;
	}

	public String getPartnerSourceUrl(){
		return partnerSourceUrl;
	}

	public String getSource(){
		return source;
	}
}