package com.bbot.darkweatherforecast.FifteenDays;

public class SevenDaysModel {

    String time3;
    int icon3;
    String date3;

    public String getTime3() {
        return this.time3;
    }

    public void setTime3( String time3) {
        this.time3 = time3;
    }

    public int getIcon3() {
        return this.icon3;
    }

    public void setIcon3( int icon3) {
        this.icon3 = icon3;
    }

    public String getDate3() {
        return this.date3;
    }

    public void setDate3( String date3) {
        this.date3 = date3;
    }

    public SevenDaysModel(){

    }


    public SevenDaysModel( String time3,  int icon3  ,String date3) {
        this.time3 = time3;
        this.icon3 = icon3;
        this.date3 = date3;
    }
}
