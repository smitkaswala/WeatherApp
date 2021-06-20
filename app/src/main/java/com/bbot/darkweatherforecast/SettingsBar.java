package com.bbot.darkweatherforecast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdOptionsView;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.MediaView;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.NativeAdListener;
import com.google.android.datatransport.BuildConfig;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.location.FusedLocationProviderClient;

import java.util.ArrayList;
import java.util.List;

public class SettingsBar extends AppCompatActivity {

    private ImageButton bak_btn;
    private TextView btn_2,   about_1;
    LinearLayout btn_1,about_us,feed_1,shar_1,pri_1,rate_1;
    String[] Climate;
    PrefManager prefManager;
    SwitchCompat switch1,switch2;
    SharedPreferences sharedPreferences ;
    FusedLocationProviderClient fusedLocationProviderClient;
    String key;


//    private AdView mAdView;

    private com.facebook.ads.AdView adView2;

    private NativeAdLayout nativeAdLayout;
    private LinearLayout adView;
    private NativeAd nativeAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_bar);
        AudienceNetworkAds.initialize(this);

        bak_btn = (ImageButton) findViewById(R.id.bak_btn);
        btn_1 = (LinearLayout) findViewById(R.id.btn_1);
        btn_2 = (TextView) findViewById(R.id.btn_2);
        pri_1 =  findViewById(R.id.pri_1);
        shar_1 =  findViewById(R.id.shar_1);
        feed_1 =  findViewById(R.id.feed_1);
        rate_1 =  findViewById(R.id.rate_1);
        about_1 = (TextView) findViewById(R.id.about_1);
        switch1 = (SwitchCompat ) findViewById(R.id.switch1);
//        switch2 = (SwitchCompat) findViewById(R.id.switch2);
        about_us = findViewById(R.id.about_us);


        Climate = new String[]{"Celsius(°C)" , "Fahrenheit(°F)"};

        prefManager = new PrefManager(this);

        btn_2.setText(Climate[prefManager.setUnit()]);

        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(SettingsBar.this);
                mBuilder.setTitle("Select Unit");//set title of AlertDialog
                mBuilder.setSingleChoiceItems(Climate, prefManager.setUnit(), (dialog, which) -> {

                    prefManager.getUnit(which);
                    btn_2.setText(Climate[which]);
                    dialog.dismiss();

//                    loadNativeAd();

                });

                AlertDialog mDialog = mBuilder.create();
                mDialog.show();

            }
        });
        bak_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        shar_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(android.content.Intent.ACTION_SEND);
                i.setType("text/plain");
//                i.putExtra(android.content.Intent.EXTRA_SUBJECT,"Subject test");
//                i.putExtra(android.content.Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.bbotdev.weather");
                i.putExtra(android.content.Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID);
                startActivity(Intent.createChooser(i, "Share via"));
            }
        });
        about_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SettingsBar.this,AboutUs.class);
                startActivity(intent);
            }
        });
        rate_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {
                    Uri uri = Uri.parse("market://details?id=" + getPackageName() + "");
                    Intent goMarket = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(goMarket);
                } catch (ActivityNotFoundException e) {
                    Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName() + "");
                    Intent goMarket = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(goMarket);
                }


            }
        });
//        about_1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
        pri_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsBar.this, PrivacyPolicyActivity.class);
                startActivity(intent);
            }
        });

//        MobileAds.initialize(this, new OnInitializationCompleteListener() {
//            @Override
//            public void onInitializationComplete(InitializationStatus initializationStatus) {
//            }
//        });
//
//        mAdView = findViewById(R.id.adViewGoogle);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);
//        AdView mAdView = new AdView(this);
//        mAdView.setAdSize(AdSize.SMART_BANNER);

        adView2 = new com.facebook.ads.AdView(this,"433735587905021_433736307904949", com.facebook.ads.AdSize.BANNER_HEIGHT_50);
//        adView2 = new AdView(this, "IMG_16_9_APP_INSTALL#2822925034589011_2826374884244026", AdSize.BANNER_HEIGHT_90);

        // Find the Ad Container
//        LinearLayout adContainer = (LinearLayout) findViewById(R.id.banner_container);
        LinearLayout adContainer = (LinearLayout) findViewById(R.id.banner_container_2);

        // Add the ad view to your activity layout
        adContainer.addView(adView2);
//        adContainer2.addView(adView2);

        // Request an ad
        adView2.loadAd();
//        adView2.loadAd();

    }
    @Override
    protected void onDestroy() {

        if (adView2 != null ){

            adView2.destroy();

        }

        super.onDestroy();
    }
    
    
    private void loadNativeAd() {

        nativeAd = new NativeAd(this, "IMG_16_9_APP_INSTALL#2822925034589011_2830338053847709");

        NativeAdListener nativeAdListener = new NativeAdListener() {

            @Override
            public void onMediaDownloaded(Ad ad) {

            }

            @Override
            public void onError(Ad ad, AdError adError) {

            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Race condition, load() called again before last ad was displayed
                if (nativeAd == null || nativeAd != ad) {
                    return;
                }
                // Inflate Native Ad into Container
                inflateAd(nativeAd);
            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }

        };

        // Request an ad
        nativeAd.loadAd(
                nativeAd.buildLoadAdConfig()
                        .withAdListener(nativeAdListener)
                        .build());
    }

    private void inflateAd(NativeAd nativeAd) {

        nativeAd.unregisterView();

        // Add the Ad view into the ad container.
        nativeAdLayout = findViewById(R.id.native_ad_container);
        LayoutInflater inflater = LayoutInflater.from(SettingsBar.this);
        // Inflate the Ad view.  The layout referenced should be the one you created in the last step.
        adView = (LinearLayout) inflater.inflate(R.layout.native_ad_layout, nativeAdLayout, false);
        nativeAdLayout.addView(adView);

        // Add the AdOptionsView
        LinearLayout adChoicesContainer = findViewById(R.id.ad_choices_container);
        AdOptionsView adOptionsView = new AdOptionsView(SettingsBar.this, nativeAd, nativeAdLayout);
        adChoicesContainer.removeAllViews();
        adChoicesContainer.addView(adOptionsView, 0);

        // Create native UI using the ad metadata.
        MediaView nativeAdIcon = adView.findViewById(R.id.native_ad_icon);
        TextView nativeAdTitle = adView.findViewById(R.id.native_ad_title);
        MediaView nativeAdMedia = adView.findViewById(R.id.native_ad_media);
        TextView nativeAdSocialContext = adView.findViewById(R.id.native_ad_social_context);
        TextView nativeAdBody = adView.findViewById(R.id.native_ad_body);
        TextView sponsoredLabel = adView.findViewById(R.id.native_ad_sponsored_label);
        Button nativeAdCallToAction = adView.findViewById(R.id.native_ad_call_to_action);

        // Set the Text.
        nativeAdTitle.setText(nativeAd.getAdvertiserName());
        nativeAdBody.setText(nativeAd.getAdBodyText());
        nativeAdSocialContext.setText(nativeAd.getAdSocialContext());
        nativeAdCallToAction.setVisibility(nativeAd.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);
        nativeAdCallToAction.setText(nativeAd.getAdCallToAction());
        sponsoredLabel.setText(nativeAd.getSponsoredTranslation());

        // Create a list of clickable views
        List<View> clickableViews = new ArrayList<>();
        clickableViews.add(nativeAdTitle);
        clickableViews.add(nativeAdCallToAction);

        // Register the Title and CTA button to listen for clicks.
        nativeAd.registerViewForInteraction(
                adView, nativeAdMedia, nativeAdIcon, clickableViews);
    }
}