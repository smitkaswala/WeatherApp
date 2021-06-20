package com.bbot.darkweatherforecast.TwenteenfourDay;

public class TwentyFourModel {
    int value ;
    int icon ;
    String time;
    String time2;
    int icon2;


    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    public String getTime2() {
        return time2;
    }
    public void setTime2(String time2){
        this.time2 = time2;
    }

    public int getIcon2() {
        return icon2;
    }

    public void setIcon2(int icon2) {
        this.icon2 = icon2;
    }


    public TwentyFourModel() {
    }

    public TwentyFourModel(int value, int icon, String time ,String time2, int icon2) {
        this.value = value;
        this.icon = icon;
        this.time = time;
        this.time2 = time2;
        this.icon2 = icon2;
    }
}
