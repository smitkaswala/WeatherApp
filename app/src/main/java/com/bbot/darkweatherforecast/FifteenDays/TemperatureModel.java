package com.bbot.darkweatherforecast.FifteenDays;

public class TemperatureModel {
    int temp5;
    int val5;
    int icon5;
    int icon6;



    public int getTemp5(double value) {
        return this.temp5;
    }

    public void setTemp5(int temp5) {
        this.temp5 = temp5;
    }

    public int getVal5() {
        return this.val5;
    }

    public void setVal5(int val5) {
        this.val5 = val5;
    }

    public int getIcon5() {
        return this.icon5;
    }

    public void setIcon5(final int icon5) {
        this.icon5 = icon5;
    }
    public int getIcon6() {
        return this.icon6;
    }

    public void setIcon6(final int icon6) {
        this.icon6 = icon6;
    }



    public TemperatureModel(){

    }

    public TemperatureModel( int temp5, int val5, int icon5 , int icon6) {
        this.temp5 = temp5;
        this.val5 = val5;
        this.icon5 = icon5;
        this.icon6 = icon6;
    }

}
