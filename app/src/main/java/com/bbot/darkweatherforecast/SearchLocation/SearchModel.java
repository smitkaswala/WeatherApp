	package com.bbot.darkweatherforecast.SearchLocation;

import com.google.gson.annotations.SerializedName;

    public class SearchModel {

        @SerializedName("AdministrativeArea")
        public AdministrativeArea administrativeArea;

        @SerializedName("Type")
        public String type;

        @SerializedName("Version")
        public int version;

        @SerializedName("LocalizedName")
        public String localizedName;

        @SerializedName("Country")
        public Country country;

        @SerializedName("Rank")
        public int rank;

        @SerializedName("Key")
        public String key;
    }