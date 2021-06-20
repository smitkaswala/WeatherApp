package com.bbot.darkweatherforecast;

public class AddLocationClass {

    public String id;
    public String city;
    public String country;
    public String stateloca;
    public String key;

    public AddLocationClass(String id, String city, String country, String stateloca, String key) {
        this.id = id;
        this.city = city;
        this.country = country;
        this.stateloca = stateloca;
        this.key = key;

    }

    public AddLocationClass() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStateloca() {
        return stateloca;
    }

    public void setStateloca(String stateloca) {
        this.stateloca = stateloca;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}

