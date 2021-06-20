package com.bbot.darkweatherforecast;

public class DataModel {

    String citykey;
    String city;
    String country;

    public DataModel(){

    }

    public DataModel(String citykey, String city, String country){
        this.citykey = citykey;
        this.city = city;
        this.country = country;
    }

    public String getCitykey() {
        return this.citykey;
    }

    public void setCitykey(final String citykey) {
        this.citykey = citykey;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(final String country) {
        this.country = country;
    }


}
