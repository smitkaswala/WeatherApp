package com.bbot.darkweatherforecast;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;

    public PrefManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("Mypref",Context.MODE_PRIVATE);
    }

    public void getKey( String key){
        editor = sharedPreferences.edit();
        editor.putString("key",key);
        editor.apply();
    }

    String setKey(){
        return sharedPreferences.getString("key","");
    }

    public void getLocation(String location){
        editor = sharedPreferences.edit();
        editor.putString("location",location);
        editor.apply();
    }

    public String setLocation(){
        return sharedPreferences.getString("location",null);
    }

    public void getUnit(int c){

        editor = sharedPreferences.edit();
        editor.putInt("unit",c);
        editor.apply();
    }

    public int setUnit() {

        return sharedPreferences.getInt("unit",0);

    }

    public void getMode(boolean t) {
        editor = sharedPreferences.edit();
        editor.putBoolean("mode",t);
        editor.apply();
    }

    public boolean setMode()

    {
        return sharedPreferences.getBoolean("mode",true);
    }

}
