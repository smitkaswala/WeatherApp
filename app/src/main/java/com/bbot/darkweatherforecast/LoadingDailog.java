package com.bbot.darkweatherforecast;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Handler;
import android.view.LayoutInflater;

public class LoadingDailog {
    Activity activity;
    AlertDialog alertDialog;

    LoadingDailog (Activity myActivity){
        activity = myActivity;
    }
    void startLoadingDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.progress_dialog,null));
        builder.setCancelable(false);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                alertDialog = builder.create();
                alertDialog.show();
            }

        },2000);

    }
    void dismissDialog(){
        alertDialog.dismiss();
    }
}
