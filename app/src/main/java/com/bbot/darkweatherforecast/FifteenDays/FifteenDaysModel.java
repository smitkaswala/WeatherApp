package com.bbot.darkweatherforecast.FifteenDays;

public class FifteenDaysModel {
    int value1;
    int icon1;
    String time1;
    int temp1;
    int wind1;
    int wind2;
    String temp2;
    int icon3;
    private String unit;

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(final String unit) {
        this.unit = unit;
    }

    public int getValue1() {
        return this.value1;
    }

    public void setValue1( int value1) {
        this.value1 = value1;
    }

    public int getIcon1() {
        return this.icon1;
    }

    public void setIcon1( int icon1) {
        this.icon1 = icon1;
    }

    public String getTime1() {
        return this.time1;
    }

    public void setTime1( String time1) {
        this.time1 = time1;
    }

    public int getTemp1() {
        return this.temp1;
    }

    public void setTemp1( int temp1) {
        this.temp1 = temp1;
    }

    public int getWind1() {
        return this.wind1;
    }

    public void setWind1( int wind1) {
        this.wind1 = wind1;
    }

    public int getWind2() {
        return this.wind2;
    }

    public void setWind2( int wind2) {
        this.wind2 = wind2;
    }

    public String getTemp2() {
        return this.temp2;
    }

    public void setTemp2( String temp2) {
        this.temp2 = temp2;
    }

    public int getIcon3() {
        return this.icon3;
    }

    public void setIcon3( int icon3) {
        this.icon3 = icon3;
    }

    public FifteenDaysModel() {

    }

    public FifteenDaysModel(int value1, int icon1, String time1, int temp1, int wind1, int wind2, String temp2, int icon3) {

        this.value1 = value1;
        this.icon1 = icon1;
        this.time1 = time1;
        this.temp1 = temp1;
        this.wind1 = wind1;
        this.wind2 = wind2;
        this.temp2 = temp2;
        this.icon3 = icon3;
    }
}
