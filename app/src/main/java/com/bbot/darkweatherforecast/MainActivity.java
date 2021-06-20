package com.bbot.darkweatherforecast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.util.Calendar;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bbot.darkweatherforecast.FifteenDays.DailyForecastsItem;
import com.bbot.darkweatherforecast.FifteenDays.FifteenDaysAdapter;
import com.bbot.darkweatherforecast.FifteenDays.FifteenDaysModel;
import com.bbot.darkweatherforecast.FifteenDays.ResponseClasses;
import com.bbot.darkweatherforecast.FifteenDays.SevenDaysAdapter;
import com.bbot.darkweatherforecast.FifteenDays.SevenDaysModel;
import com.bbot.darkweatherforecast.FifteenDays.TemperatureAdapter;
import com.bbot.darkweatherforecast.FifteenDays.TemperatureModel;
import com.bbot.darkweatherforecast.FullDay.ResponseUsers;
import com.bbot.darkweatherforecast.TwenteenfourDay.ResponseCall;
import com.bbot.darkweatherforecast.TwenteenfourDay.TwentyFourHourAdapter;
import com.bbot.darkweatherforecast.TwenteenfourDay.TwentyFourModel;
import com.bbot.darkweatherforecast.databinding.ActivityMainSecondBinding;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdOptionsView;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.BuildConfig;
import com.facebook.ads.InterstitialAdListener;
import com.facebook.ads.MediaView;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.NativeAdListener;
import com.facebook.ads.NativeAdsManager;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAdView;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.reflect.TypeToken;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.onesignal.OneSignal;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NativeAdsManager.Listener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private ActivityMainSecondBinding binding;

    private FirebaseAnalytics firebaseAnalytics;


    FusedLocationProviderClient fusedLocationProviderClient;
    FusedLocationProviderClient fusedLocationProviderClient2;
    TextView tax_1, tax_2, tax_3, tax_9, text_1, text_2, text_3, text_4, text_5, text_6;
    String key;
    PrefManager prefManager;
    ProgressDialog progressDailog, progressDailog1;
    SaveState saveState;
    ImageView tempicon1, stn_btn, cur_btn, image_btn, icon_1;
    LinearLayout linearLayout, lcn_btn, linearLayout3;
    private static final long MINIMUNDIST = 1;
    private static final long MINTME = 1000;
    protected LocationManager locationMan;
    LinearLayout ivNointernetCoonection;
    private LocationRequest locationRequest;
    public static final int REQUEST_CHECK_SETTING = 1001;
    private static final int REQUEST_CHECK_SETTINGS = 100;
    Boolean isInternetPresent = false;
    ConnectionDetector cd;

    ReviewManager manager;
    ReviewInfo reviewInfo;

    Button yes,no;



    Type type = new TypeToken<List<AddLocationData>>() {
    }.getType();

    SharedPreferences sharedPreferences;
    Dialog dialog;

    // location last updated time
    private String mLastUpdateTime;
    FifteenDaysAdapter adapter_1;

    // location updates interval - 10sec
    private static final long UPDATE_INTERVAL_IN_MILLISECONDS = 10000;

    // fastest updates interval - 5 sec
    // location updates will be received if another app is requesting the locations
    // than your app can handle
    private static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = 5000;


    // bunch of location related apis
    private FusedLocationProviderClient mFusedLocationClient;
    private SettingsClient mSettingsClient;
    private LocationRequest mLocationRequest;
    private LocationSettingsRequest mLocationSettingsRequest;
    private LocationCallback mLocationCallback;
    private Location mCurrentLocation;

    // boolean flag to toggle the ui
    private Boolean mRequestingLocationUpdates;

    private static final int REQUEST_LOCATION = 1;
    LocationManager locationManager;
    String latitude, longitude;

    public static boolean apponcreate = true;

//    ReviewManager manager = ReviewManagerFactory.create(this);


    private com.facebook.ads.InterstitialAd interstitialAd,interstitialAd2;

//    private com.facebook.ads.AdView adView;
//    private com.facebook.ads.AdView adView2;

    private AdView mAdView;
    private UnifiedNativeAd firstUnifiedNative,secondUnifiedNative,thirdUnifiedNative,fourthUnifiedNative,fifthUnifiedNative;
    private InterstitialAd minterstitialAd,minterstitialAd2;

    private NativeAdLayout nativeAdLayout2;
    private NativeAdLayout nativeAdLayout3;
    private NativeAdLayout nativeAdLayout4;
    private NativeAdLayout nativeAdLayout5;

    private LinearLayout adView2;
    private LinearLayout adView3;
    private LinearLayout adView4;
    private LinearLayout adView5;

    private NativeAd nativeAd;
    private NativeAd nativeAd2;
    private NativeAd nativeAd3;
    private NativeAd nativeAd4;

    RecyclerView recyclerView_1, recyclerView_2, recyclerView_3, recyclerView_4;

    List<TwentyFourModel> twentyFourModelList = new ArrayList<>();
    List<Object> fifteenDaysModelList = new ArrayList<>();
    List<SevenDaysModel> sevenDaysModelList = new ArrayList<>();
    List<TemperatureModel> temperatureModelList = new ArrayList<>();


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main_second);


//        inti();
//        onBackPressed();
//        AudienceNetworkAds.initialize(this);
        AudienceNetworkAds.initialize(this);

        prefManager = new PrefManager(this);
        dialog = new Dialog(this);
        manager = ReviewManagerFactory.create(this);

//        minterstitialAd = new InterstitialAd(this);
//        minterstitialAd2 = new InterstitialAd(this);

        View v = LayoutInflater.from(this).inflate(R.layout.popup,null);
//        refreshAd5(v);

         yes = v.findViewById(R.id.yes);
         no = v.findViewById(R.id.no);

        dialog.setContentView(v);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });



//        dialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT,
//                WindowManager.LayoutParams.WRAP_CONTENT);

//        intiReviewInfo();
//        getReviewInfo();
//        startReviewFlow();

//        ReviewManager manager = ReviewManagerFactory.create(this);
//        com.google.android.play.core.tasks.Task<ReviewInfo> request = manager.requestReviewFlow();
//        request.addOnCompleteListener(task -> {
//            if (task.isSuccessful()) {
//                // We can get the ReviewInfo object
//                reviewInfo = task.getResult();
//
//            } else {
//                // There was some problem, log or handle the error code.
////                @ReviewErrorCode int reviewErrorCode = ((TaskException) task.getException()).getErrorCode();
//            }
//        });
//
//        com.google.android.play.core.tasks.Task<Void> flow = manager.launchReviewFlow(MainActivity.this, reviewInfo);
//        flow.addOnCompleteListener(task -> {
//            // The flow has finished. The API does not indicate whether the user
//            // reviewed or not, or even whether the review dialog was shown. Thus, no
//            // matter the result, we continue our app flow.
//        });

        //OneSignal initialization

        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();

        firebaseAnalytics = FirebaseAnalytics.getInstance(this);


//        minterstitialAd.setAdUnitId(getString(R.string.interstitial_admob));
//        AdRequest adRequest = new AdRequest.Builder().build();
//        minterstitialAd.loadAd(adRequest);
//        minterstitialAd.setAdListener(new AdListener(){
//
//
//            @Override
//            public void onAdClosed() {
//                super.onAdClosed();
//                openSearchBar();
//            }
//        });
//
//        minterstitialAd2.setAdUnitId(getString(R.string.interstitial_admob));
//        AdRequest adRequest2 = new AdRequest.Builder().build();
//        minterstitialAd2.loadAd(adRequest2);
//        minterstitialAd2.setAdListener(new AdListener(){
//
//
//            @Override
//            public void onAdClosed() {
//                super.onAdClosed();
//                onBackPressed();
//            }
//        });


//        adView = new AdView(this, "433735587905021_433736307904949", AdSize.BANNER_HEIGHT_50);

//        nativeAd = new NativeAd(this, "433735587905021_433736794571567");
//
//        adView2 = new AdView(this, "IMG_16_9_APP_INSTALL#2822925034589011_2826374884244026", AdSize.BANNER_HEIGHT_90);

        // Find the Ad Container
//        LinearLayout adContainer = (LinearLayout) findViewById(R.id.banner_container);
//        LinearLayout adContainer2 = (LinearLayout) findViewById(R.id.banner_container_2);

        // Add the ad view to your activity layout
//        adContainer.addView(adView);
//        adContainer2.addView(adView2);

        // Request an ad
//        adView.loadAd();
//        adView2.loadAd();

        // Native

        //Google Banner



//        refreshAd();
//        refreshAd2();
//        refreshAd3();
//        refreshAd4();

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adViewGoogle);
        AdRequest adRequests = new AdRequest.Builder().build();
        mAdView.loadAd(adRequests);
        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.SMART_BANNER);

//        MobileAds.initialize(this, new OnInitializationCompleteListener() {
//            @Override
//            public void onInitializationComplete(InitializationStatus initializationStatus) {
//            }
//        });

        interstitialAd = new com.facebook.ads.InterstitialAd(MainActivity.this, "433735587905021_466368787975034");


        InterstitialAdListener interstitialAdListener = new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {
                // Interstitial ad displayed callback
                Log.e(TAG, "Interstitial ad displayed.");
            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                // Interstitial dismissed callback
                Log.e(TAG, "Interstitial ad dismissed.");
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                // Ad error callback
                Log.e(TAG, "Interstitial ad failed to load: " + adError.getErrorMessage());
            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Interstitial ad is loaded and ready to be displayed
                Log.d(TAG, "Interstitial ad is loaded and ready to be displayed!");
                // Show the ad
//                interstitialAd.show();

            }

            @Override
            public void onAdClicked(Ad ad) {
                // Ad clicked callback
                Log.d(TAG, "Interstitial ad clicked!");
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                // Ad impression logged callback
                Log.d(TAG, "Interstitial ad impression logged!");
            }
        };

        // For auto play video ads, it's recommended to load the ad
        // at least 30 seconds before it is shown
        interstitialAd.loadAd(
                interstitialAd.buildLoadAdConfig()
                        .withAdListener(interstitialAdListener)
                        .build());


//        interstitialAd2 = new com.facebook.ads.InterstitialAd(MainActivity.this, "433735587905021_433736307904949");
//
//        InterstitialAdListener interstitialAdListener2 = new InterstitialAdListener() {
//            @Override
//            public void onInterstitialDisplayed(Ad ad) {
//                // Interstitial ad displayed callback
//                Log.e(TAG, "Interstitial ad displayed.");
//            }
//
//            @Override
//            public void onInterstitialDismissed(Ad ad) {
//                // Interstitial dismissed callback
//                Log.e(TAG, "Interstitial ad dismissed.");
//            }
//
//            @Override
//            public void onError(Ad ad, AdError adError) {
//                // Ad error callback
//                Log.e(TAG, "Interstitial ad failed to load: " + adError.getErrorMessage());
//            }
//
//            @Override
//            public void onAdLoaded(Ad ad) {
//                // Interstitial ad is loaded and ready to be displayed
//                Log.d(TAG, "Interstitial ad is loaded and ready to be displayed!");
//                // Show the ad
////                interstitialAd2.show();
//
//            }
//
//            @Override
//            public void onAdClicked(Ad ad) {
//                // Ad clicked callback
//                Log.d(TAG, "Interstitial ad clicked!");
//            }
//
//            @Override
//            public void onLoggingImpression(Ad ad) {
//                // Ad impression logged callback
//                Log.d(TAG, "Interstitial ad impression logged!");
//            }
//        };
//
//        // For auto play video ads, it's recommended to load the ad
//        // at least 30 seconds before it is shown
//        interstitialAd2.loadAd(
//                interstitialAd2.buildLoadAdConfig()
//                        .withAdListener(interstitialAdListener2)
//                        .build());



        nativeAd = new NativeAd(this, "433735587905021_433736794571567");


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
//
                // Inflate Native Ad into Container
                inflateAd(nativeAd);
//
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
//
//
        nativeAd2 = new NativeAd(this, "433735587905021_433736794571567");


        NativeAdListener nativeAdListener2 = new NativeAdListener() {

            @Override
            public void onMediaDownloaded(Ad ad) {

            }

            @Override
            public void onError(Ad ad, AdError adError) {

            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Race condition, load() called again before last ad was displayed
                if (nativeAd2 == null || nativeAd2 != ad) {
                    return;
                }
//
                // Inflate Native Ad into Container
                inflateAd2(nativeAd2);
//
            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }

        };

        // Request an ad
        nativeAd2.loadAd(
                nativeAd2.buildLoadAdConfig()
                        .withAdListener(nativeAdListener2)
                        .build());
//
        nativeAd3 = new NativeAd(this, "433735587905021_433736794571567");


        NativeAdListener nativeAdListener3 = new NativeAdListener() {

            @Override
            public void onMediaDownloaded(Ad ad) {

            }

            @Override
            public void onError(Ad ad, AdError adError) {

            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Race condition, load() called again before last ad was displayed
                if (nativeAd3 == null || nativeAd3 != ad) {
                    return;
                }
//
                // Inflate Native Ad into Container
                inflateAd3(nativeAd3);
//
            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }

        };

        // Request an ad
        nativeAd3.loadAd(
                nativeAd3.buildLoadAdConfig()
                        .withAdListener(nativeAdListener3)
                        .build());

        nativeAd4 = new NativeAd(this, "433735587905021_433736794571567");

        NativeAdListener nativeAdListener4 = new NativeAdListener() {

            @Override
            public void onMediaDownloaded(Ad ad) {

            }

            @Override
            public void onError(Ad ad, AdError adError) {

            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Race condition, load() called again before last ad was displayed
                if (nativeAd4 == null || nativeAd4 != ad) {
                    return;
                }

                // Inflate Native Ad into Container
                inflateAd4(nativeAd4);

            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }

        };

        // Request an ad
        nativeAd4.loadAd(
                nativeAd4.buildLoadAdConfig()
                        .withAdListener(nativeAdListener4)
                        .build());



        // initialize the necessary libraries

        init();

        // restore the values from saved instance state
        restoreValuesFromBundle(savedInstanceState);

        progressDailog = new ProgressDialog(MainActivity.this);

        //Show Dailog
        progressDailog.show();

        //set Content View
        progressDailog.setContentView(R.layout.progress_dialog);
        progressDailog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT);

        //run time
        //set Transparent Background
        progressDailog.getWindow().setBackgroundDrawableResource(
                android.R.color.transparent);

        //run time
        progressDailog.onStart();


        runtimePermission();


    }

    private void refreshAd() {
    AdLoader.Builder builder = new AdLoader.Builder(this,getString(R.string.native_advanced_ad_unit_id));
    builder.forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
        @Override
        public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
            if (firstUnifiedNative != null) {
                firstUnifiedNative.destroy();
            }
            firstUnifiedNative = unifiedNativeAd;
            FrameLayout frameLayout = findViewById(R.id.frame_layout);
            UnifiedNativeAdView adView = (UnifiedNativeAdView) getLayoutInflater().inflate(R.layout.nativead_layout, null);
            populateUnifiedNativeAdView(unifiedNativeAd, adView);
            frameLayout.removeAllViews();
            frameLayout.addView(adView);
        }
    });
    NativeAdOptions adOptions = new NativeAdOptions.Builder().build();
    builder.withNativeAdOptions(adOptions);
    AdLoader adLoader = builder.withAdListener (new AdListener(){

        @Override
        public void onAdFailedToLoad(LoadAdError loadAdError) {
            super.onAdFailedToLoad(loadAdError);
        }


    }).build();
    adLoader.loadAd(new AdRequest.Builder().build());
}

    private void refreshAd2() {
        AdLoader.Builder builder = new AdLoader.Builder(this,getString(R.string.native_advanced_ad_unit_id));
        builder.forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
            @Override
            public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                if (secondUnifiedNative != null) {
                    secondUnifiedNative.destroy();
                }
                secondUnifiedNative = unifiedNativeAd;
                FrameLayout frameLayout = findViewById(R.id.frame_layout_second);
                UnifiedNativeAdView adView = (UnifiedNativeAdView) getLayoutInflater().inflate(R.layout.nativead_layout, null);
                populateUnifiedNativeAdView(unifiedNativeAd, adView);
                frameLayout.removeAllViews();
                frameLayout.addView(adView);
            }
        });
        NativeAdOptions adOptions = new NativeAdOptions.Builder().build();
        builder.withNativeAdOptions(adOptions);
        AdLoader adLoader = builder.withAdListener (new AdListener(){

            @Override
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
            }


        }).build();
        adLoader.loadAd(new AdRequest.Builder().build());
    }

    private void refreshAd3() {
        AdLoader.Builder builder = new AdLoader.Builder(this,getString(R.string.native_advanced_ad_unit_id));
        builder.forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
            @Override
            public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                if (thirdUnifiedNative != null) {
                    thirdUnifiedNative.destroy();
                }
                thirdUnifiedNative = unifiedNativeAd;
                FrameLayout frameLayout = findViewById(R.id.frame_layout_third);
                UnifiedNativeAdView adView = (UnifiedNativeAdView) getLayoutInflater().inflate(R.layout.nativead_layout, null);
                populateUnifiedNativeAdView(unifiedNativeAd, adView);
                frameLayout.removeAllViews();
                frameLayout.addView(adView);
            }
        });
        NativeAdOptions adOptions = new NativeAdOptions.Builder().build();
        builder.withNativeAdOptions(adOptions);
        AdLoader adLoader = builder.withAdListener (new AdListener(){

            @Override
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
            }


        }).build();
        adLoader.loadAd(new AdRequest.Builder().build());
    }

    private void refreshAd4() {
        AdLoader.Builder builder = new AdLoader.Builder(this,getString(R.string.native_advanced_ad_unit_id));
        builder.forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
            @Override
            public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                if (fourthUnifiedNative != null) {
                    fourthUnifiedNative.destroy();
                }
               fourthUnifiedNative = unifiedNativeAd;
                FrameLayout frameLayout = findViewById(R.id.frame_layout_fourth);
                UnifiedNativeAdView adView = (UnifiedNativeAdView) getLayoutInflater().inflate(R.layout.nativead_layout, null);
                populateUnifiedNativeAdView(unifiedNativeAd, adView);
                frameLayout.removeAllViews();
                frameLayout.addView(adView);
            }
        });
        NativeAdOptions adOptions = new NativeAdOptions.Builder().build();
        builder.withNativeAdOptions(adOptions);
        AdLoader adLoader = builder.withAdListener (new AdListener(){

            @Override
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
            }


        }).build();
        adLoader.loadAd(new AdRequest.Builder().build());
    }

//    private void refreshAd5(View v) {
//        AdLoader.Builder builder = new AdLoader.Builder(this,getString(R.string.native_advanced_ad_unit_id));
//        builder.forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
//            @Override
//            public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
//                if (fifthUnifiedNative != null) {
//                    fifthUnifiedNative.destroy();
//                }
//                fifthUnifiedNative = unifiedNativeAd;
//                FrameLayout frameLayout = v.findViewById(R.id.frame_layout_fifth);
//                UnifiedNativeAdView adView = (UnifiedNativeAdView) getLayoutInflater().inflate(R.layout.nativead_layout, null);
//                populateUnifiedNativeAdView(unifiedNativeAd, adView);
//                frameLayout.removeAllViews();
//                frameLayout.addView(adView);
//            }
//        });
//        NativeAdOptions adOptions = new NativeAdOptions.Builder().build();
//        builder.withNativeAdOptions(adOptions);
//        AdLoader adLoader = builder.withAdListener (new AdListener(){
//
//            @Override
//            public void onAdFailedToLoad(LoadAdError loadAdError) {
//                super.onAdFailedToLoad(loadAdError);
//            }
//
//
//        }).build();
//        adLoader.loadAd(new AdRequest.Builder().build());
//    }

    private void populateUnifiedNativeAdView(UnifiedNativeAd nativeAd, UnifiedNativeAdView adView) {
        adView.setMediaView(adView.findViewById(R.id.ad_media));
        adView.setHeadlineView(adView.findViewById(R.id.ad_headline));
        adView.setBodyView(adView.findViewById(R.id.ad_body));
        adView.setCallToActionView(adView.findViewById(R.id.ad_call_to_action));
        adView.setIconView(adView.findViewById(R.id.ad_app_icon));
        adView.setPriceView(adView.findViewById(R.id.ad_price));
        adView.setAdvertiserView(adView.findViewById(R.id.ad_advertiser));
        adView.setStoreView(adView.findViewById(R.id.ad_store));
        adView.setStarRatingView(adView.findViewById(R.id.ad_stars));
        ((TextView)adView.getHeadlineView()).setText(nativeAd.getHeadline());
        adView.getMediaView().setMediaContent(nativeAd.getMediaContent());
        if (nativeAd.getBody() == null) {
            adView.getBodyView().setVisibility(View.INVISIBLE);
        } else {
            adView.getBodyView().setVisibility(View.VISIBLE);
            ((TextView) adView.getBodyView()).setText(nativeAd.getBody());
        }
        if (nativeAd.getCallToAction() == null) {
            adView.getCallToActionView().setVisibility(View.INVISIBLE);
        } else {
            adView.getCallToActionView().setVisibility(View.VISIBLE);
            ((Button) adView.getCallToActionView()).setText(nativeAd.getCallToAction());
        }
        if (nativeAd.getIcon() == null) {
            adView.getIconView().setVisibility(View.GONE);
        } else {
            ((ImageView) adView.getIconView()).setImageDrawable(nativeAd.getIcon().getDrawable());
            adView.getIconView().setVisibility(View.VISIBLE);
        }
        if (nativeAd.getPrice() == null) {
            adView.getPriceView().setVisibility(View.INVISIBLE);
        } else {
            adView.getPriceView().setVisibility(View.VISIBLE);
            ((TextView) adView.getPriceView()).setText(nativeAd.getPrice());
        }
        if (nativeAd.getStore() == null) {
            adView.getStoreView().setVisibility(View.INVISIBLE);
        } else {
            adView.getStoreView().setVisibility(View.VISIBLE);
            ((TextView) adView.getStoreView()).setText(nativeAd.getStore());
        }
        if (nativeAd.getStarRating() == null) {
            adView.getStarRatingView().setVisibility(View.INVISIBLE);
        } else {

            ((RatingBar) adView.getStarRatingView()).setRating(nativeAd.getStarRating().floatValue());
            adView.getStarRatingView().setVisibility(View.VISIBLE);
        }
        if (nativeAd.getAdvertiser() == null) {
            adView.getAdvertiserView().setVisibility(View.INVISIBLE);
        } else {
            ((TextView) adView.getAdvertiserView()).setText(nativeAd.getAdvertiser());
            adView.getAdvertiserView().setVisibility(View.VISIBLE);
        }
        adView.setNativeAd(nativeAd);
    }

    private void inflateAd(NativeAd nativeAd) {

        nativeAd.unregisterView();

        // Add the Ad view into the ad container.
        nativeAdLayout2 = findViewById(R.id.native_ad_container_2);
        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        // Inflate the Ad view.  The layout referenced should be the one you created in the last step.
        adView2 = (LinearLayout) inflater.inflate(R.layout.native_ad_layout2, nativeAdLayout2, false);
        nativeAdLayout2.addView(adView2);

        // Add the AdOptionsView
        LinearLayout adChoicesContainer = findViewById(R.id.ad_choices_container_2);
        AdOptionsView adOptionsView = new AdOptionsView(MainActivity.this, nativeAd, nativeAdLayout2);
        adChoicesContainer.removeAllViews();
        adChoicesContainer.addView(adOptionsView, 0);

        // Create native UI using the ad metadata.
        MediaView nativeAdIcon = adView2.findViewById(R.id.native_ad_icon);
        TextView nativeAdTitle = adView2.findViewById(R.id.native_ad_title);
        MediaView nativeAdMedia = adView2.findViewById(R.id.native_ad_media);
        TextView nativeAdSocialContext = adView2.findViewById(R.id.native_ad_social_context);
        TextView nativeAdBody = adView2.findViewById(R.id.native_ad_body);
        TextView sponsoredLabel = adView2.findViewById(R.id.native_ad_sponsored_label);
        Button nativeAdCallToAction = adView2.findViewById(R.id.native_ad_call_to_action);

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
                adView2, nativeAdMedia, nativeAdIcon, clickableViews);
    }
//
    private void inflateAd2(NativeAd nativeAd2) {

        nativeAd2.unregisterView();

        // Add the Ad view into the ad container.
        nativeAdLayout3 = findViewById(R.id.native_ad_container_3);
        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        // Inflate the Ad view.  The layout referenced should be the one you created in the last step.
        adView3 = (LinearLayout) inflater.inflate(R.layout.native_ad_layout3, nativeAdLayout3, false);
        nativeAdLayout3.addView(adView3);

        // Add the AdOptionsView
        LinearLayout adChoicesContainer = findViewById(R.id.ad_choices_container_3);
        AdOptionsView adOptionsView = new AdOptionsView(MainActivity.this, nativeAd2, nativeAdLayout3);
        adChoicesContainer.removeAllViews();
        adChoicesContainer.addView(adOptionsView, 0);

        // Create native UI using the ad metadata.
        MediaView nativeAdIcon = adView3.findViewById(R.id.native_ad_icon);
        TextView nativeAdTitle = adView3.findViewById(R.id.native_ad_title);
        MediaView nativeAdMedia = adView3.findViewById(R.id.native_ad_media);
        TextView nativeAdSocialContext = adView3.findViewById(R.id.native_ad_social_context);
        TextView nativeAdBody = adView3.findViewById(R.id.native_ad_body);
        TextView sponsoredLabel = adView3.findViewById(R.id.native_ad_sponsored_label);
        Button nativeAdCallToAction = adView3.findViewById(R.id.native_ad_call_to_action);

        // Set the Text.
        nativeAdTitle.setText(nativeAd2.getAdvertiserName());
        nativeAdBody.setText(nativeAd2.getAdBodyText());
        nativeAdSocialContext.setText(nativeAd2.getAdSocialContext());
        nativeAdCallToAction.setVisibility(nativeAd2.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);
        nativeAdCallToAction.setText(nativeAd2.getAdCallToAction());
        sponsoredLabel.setText(nativeAd2.getSponsoredTranslation());

        // Create a list of clickable views
        List<View> clickableViews = new ArrayList<>();
        clickableViews.add(nativeAdTitle);
        clickableViews.add(nativeAdCallToAction);

        // Register the Title and CTA button to listen for clicks.
        nativeAd2.registerViewForInteraction(
                adView3, nativeAdMedia, nativeAdIcon, clickableViews);
    }

    private void inflateAd3(NativeAd nativeAd3) {

        nativeAd3.unregisterView();

        // Add the Ad view into the ad container.
        nativeAdLayout4 = findViewById(R.id.native_ad_container_4);
        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        // Inflate the Ad view.  The layout referenced should be the one you created in the last step.
        adView4 = (LinearLayout) inflater.inflate(R.layout.native_ad_layout4, nativeAdLayout4, false);
        nativeAdLayout4.addView(adView4);

        // Add the AdOptionsView
        LinearLayout adChoicesContainer = findViewById(R.id.ad_choices_container_4);
        AdOptionsView adOptionsView = new AdOptionsView(MainActivity.this, nativeAd3, nativeAdLayout4);
        adChoicesContainer.removeAllViews();
        adChoicesContainer.addView(adOptionsView, 0);

        // Create native UI using the ad metadata.
        MediaView nativeAdIcon = adView4.findViewById(R.id.native_ad_icon);
        TextView nativeAdTitle = adView4.findViewById(R.id.native_ad_title);
        MediaView nativeAdMedia = adView4.findViewById(R.id.native_ad_media);
        TextView nativeAdSocialContext = adView4.findViewById(R.id.native_ad_social_context);
        TextView nativeAdBody = adView4.findViewById(R.id.native_ad_body);
        TextView sponsoredLabel = adView4.findViewById(R.id.native_ad_sponsored_label);
        Button nativeAdCallToAction = adView4.findViewById(R.id.native_ad_call_to_action);

        // Set the Text.
        nativeAdTitle.setText(nativeAd3.getAdvertiserName());
        nativeAdBody.setText(nativeAd3.getAdBodyText());
        nativeAdSocialContext.setText(nativeAd3.getAdSocialContext());
        nativeAdCallToAction.setVisibility(nativeAd3.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);
        nativeAdCallToAction.setText(nativeAd3.getAdCallToAction());
        sponsoredLabel.setText(nativeAd3.getSponsoredTranslation());

        // Create a list of clickable views
        List<View> clickableViews = new ArrayList<>();
        clickableViews.add(nativeAdTitle);
        clickableViews.add(nativeAdCallToAction);

        // Register the Title and CTA button to listen for clicks.
        nativeAd3.registerViewForInteraction(
                adView4, nativeAdMedia, nativeAdIcon, clickableViews);
    }

    private void inflateAd4(NativeAd nativeAd4) {

        nativeAd4.unregisterView();

        // Add the Ad view into the ad container.
        nativeAdLayout5 = findViewById(R.id.native_ad_container_5);
        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        // Inflate the Ad view.  The layout referenced should be the one you created in the last step.
        adView5 = (LinearLayout) inflater.inflate(R.layout.native_ad_layout5, nativeAdLayout5, false);
        nativeAdLayout5.addView(adView5);

        // Add the AdOptionsView
        LinearLayout adChoicesContainer = findViewById(R.id.ad_choices_container_5);
        AdOptionsView adOptionsView = new AdOptionsView(MainActivity.this, nativeAd4, nativeAdLayout5);
        adChoicesContainer.removeAllViews();
        adChoicesContainer.addView(adOptionsView, 0);

        // Create native UI using the ad metadata.
        MediaView nativeAdIcon = adView5.findViewById(R.id.native_ad_icon);
        TextView nativeAdTitle = adView5.findViewById(R.id.native_ad_title);
        MediaView nativeAdMedia = adView5.findViewById(R.id.native_ad_media);
        TextView nativeAdSocialContext = adView5.findViewById(R.id.native_ad_social_context);
        TextView nativeAdBody = adView5.findViewById(R.id.native_ad_body);
        TextView sponsoredLabel = adView5.findViewById(R.id.native_ad_sponsored_label);
        Button nativeAdCallToAction = adView5.findViewById(R.id.native_ad_call_to_action);

        // Set the Text.
        nativeAdTitle.setText(nativeAd4.getAdvertiserName());
        nativeAdBody.setText(nativeAd4.getAdBodyText());
        nativeAdSocialContext.setText(nativeAd4.getAdSocialContext());
        nativeAdCallToAction.setVisibility(nativeAd4.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);
        nativeAdCallToAction.setText(nativeAd4.getAdCallToAction());
        sponsoredLabel.setText(nativeAd4.getSponsoredTranslation());

        // Create a list of clickable views
        List<View> clickableViews = new ArrayList<>();
        clickableViews.add(nativeAdTitle);
        clickableViews.add(nativeAdCallToAction);

        // Register the Title and CTA button to listen for clicks.
        nativeAd4.registerViewForInteraction(
                adView5, nativeAdMedia, nativeAdIcon, clickableViews);
    }

    @Override
    protected void onDestroy() {
        if (firstUnifiedNative != null)
            firstUnifiedNative.destroy();

//        if (interstitialAd != null) {
//            interstitialAd.destroy();
//        }
//        if (interstitialAd2 != null) {
//            interstitialAd2.destroy();
//        }


        super.onDestroy();
    }

    private void inti(){

        manager = ReviewManagerFactory.create(this);

        com.google.android.play.core.tasks.Task<ReviewInfo> request = manager.requestReviewFlow();

        request.addOnCompleteListener(new com.google.android.play.core.tasks.OnCompleteListener<ReviewInfo>() {
            @Override
            public void onComplete(@NonNull com.google.android.play.core.tasks.Task<ReviewInfo> task) {

                if (task.isSuccessful()){

                    reviewInfo = task.getResult();
                    openReview();

                }else {
                    Toast.makeText(MainActivity.this,"ERROR",Toast.LENGTH_SHORT);
                }
            }
        });


    }

    @Override
    public void onBackPressed() {

//        if (minterstitialAd2.isLoaded()) {
//            minterstitialAd2.show();
//        }

//        if (interstitialAd2.isAdLoaded()) {
//            interstitialAd2.show();
//        }

//        if (minterstitialAd2.isLoaded()) {
//            minterstitialAd2.show();
//        }

//        dialog.getWindow().getAttributes().windowAnimations =
//                android.R.style.Animation_Dialog;

        dialog.show();



    }


    public void openReview(){
        if (reviewInfo != null){
            com.google.android.play.core.tasks.Task<Void> flow = manager.launchReviewFlow(this,reviewInfo);
            flow.addOnCompleteListener(new com.google.android.play.core.tasks.OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull com.google.android.play.core.tasks.Task<Void> task) {

                }
            });

        }
    }



    private void checkConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if (wifi.isConnected()) {
            dialog.dismiss();
            startApp();

        } else if (mobile.isConnected()) {
//            apiVideoRequest();
            dialog.dismiss();
            startApp();

        } else {


            dialog.setContentView(R.layout.alert_dailog);
            //Set outside touch
            dialog.setCanceledOnTouchOutside(false);
            //Set dialog width and height
            dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.MATCH_PARENT);
            //Set Transparent background
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            //Set animation
            dialog.getWindow().getAttributes().windowAnimations =
                    android.R.style.Animation_Dialog;

            Button bt_try = dialog.findViewById(R.id.bt_try);
            //Perform onClick listener
            bt_try.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Call recreate method
                    checkConnection();
                }
            });
            dialog.show();


        }
    }

    @SuppressLint("RestrictedApi")
    private void init() {

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mSettingsClient = LocationServices.getSettingsClient(this);

        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                // location is received
                mCurrentLocation = locationResult.getLastLocation();
                mLastUpdateTime = DateFormat.getTimeInstance().format(new Date());

                updateLocationUI();
            }
        };

        mRequestingLocationUpdates = false;

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);
        mLocationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(mLocationRequest);
        mLocationSettingsRequest = builder.build();

        if (Build.VERSION.SDK_INT >= 23) {
            Dexter.withActivity(this)
                    .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                    .withListener(new PermissionListener() {
                        @Override
                        public void onPermissionGranted(PermissionGrantedResponse response) {
                            mRequestingLocationUpdates = true;
                            startLocationUpdates();
                        }

                        @Override
                        public void onPermissionDenied(PermissionDeniedResponse response) {
                            if (response.isPermanentlyDenied()) {
                                // open device settings when the permission is
                                // denied permanently
                                openSettings();
                            }
                        }

                        @Override
                        public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                            token.continuePermissionRequest();
                        }

                    }).check();
        } else {
            mRequestingLocationUpdates = true;
            startLocationUpdates();
        }
    }

    /**
     * Restoring values from saved instance state
     */
    private void restoreValuesFromBundle(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("is_requesting_updates")) {
                mRequestingLocationUpdates = savedInstanceState.getBoolean("is_requesting_updates");
            }

            if (savedInstanceState.containsKey("last_known_location")) {
                mCurrentLocation = savedInstanceState.getParcelable("last_known_location");
            }

            if (savedInstanceState.containsKey("last_updated_on")) {
                mLastUpdateTime = savedInstanceState.getString("last_updated_on");
            }
        }

        updateLocationUI();
    }

    /**
     * Update the UI displaying the location data
     * and toggling the buttons
     */

    private void updateLocationUI() {
        if (mCurrentLocation != null) {
//
//            );
            if (apponcreate) {
                apponcreate = false;
                startApp();
            } else {

            }

        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("is_requesting_updates", mRequestingLocationUpdates);
        outState.putParcelable("last_known_location", mCurrentLocation);
        outState.putString("last_updated_on", mLastUpdateTime);

    }

    /**
     * Starting location updates
     * Check whether location settings are satisfied and then
     * location updates will be requested
     */

    private void startLocationUpdates() {
        mSettingsClient
                .checkLocationSettings(mLocationSettingsRequest)
                .addOnSuccessListener(this, new OnSuccessListener<LocationSettingsResponse>() {
                    @SuppressLint("MissingPermission")
                    @Override
                    public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                        Log.i(TAG, "All location settings are satisfied.");

//                        Toast.makeText(getApplicationContext(), "Started location updates!", Toast.LENGTH_SHORT).show();

                        //noinspection MissingPermission
                        mFusedLocationClient.requestLocationUpdates(mLocationRequest,
                                mLocationCallback, Looper.myLooper());

                        updateLocationUI();
                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        int statusCode = ((ApiException) e).getStatusCode();
                        switch (statusCode) {
                            case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                                Log.i(TAG, "Location settings are not satisfied. Attempting to upgrade " +
                                        "location settings ");
                                try {
                                    // Show the dialog by calling startResolutionForResult(), and check the
                                    // result in onActivityResult().
                                    ResolvableApiException rae = (ResolvableApiException) e;
                                    rae.startResolutionForResult(MainActivity.this, REQUEST_CHECK_SETTINGS);
                                } catch (IntentSender.SendIntentException sie) {
                                    Log.i(TAG, "PendingIntent unable to execute request.");
                                }
                                break;
                            case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                                String errorMessage = "Location settings are inadequate, and cannot be " +
                                        "fixed here. Fix in Settings.";
                                Log.e(TAG, errorMessage);

//                                Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_LONG).show();
                        }

                        updateLocationUI();
                    }
                });
    }

    public void stopLocationUpdates() {
        // Removing location updates
        mFusedLocationClient
                .removeLocationUpdates(mLocationCallback)
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
//                        Toast.makeText(getApplicationContext(), "Location updates stopped!", Toast.LENGTH_SHORT).show();

                    }

                });

    }

    private void openSettings() {
        Intent intent = new Intent();
        intent.setAction(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package",
                BuildConfig.APPLICATION_ID, null);
        intent.setData(uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();

//        loadNativeAd();

        // Resuming location updates depending on button state and
        // allowed permissions
        if (mRequestingLocationUpdates && checkPermissions()) {
            startLocationUpdates();
        }

        updateLocationUI();
    }

    private boolean checkPermissions() {
        int permissionState = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        return permissionState == PackageManager.PERMISSION_GRANTED;
    }
    
    @Override
    protected void onPause() {
        super.onPause();

        if (mRequestingLocationUpdates) {
            // pausing location updates
            stopLocationUpdates();
        }
    }

    private void startApp() {


        recyclerView_1 = findViewById(R.id.rce_1);
//        recyclerView_2 = findViewById(R.id.rce_2);
        recyclerView_3 = findViewById(R.id.rce_3);
        recyclerView_4 = findViewById(R.id.rce_4);

        lcn_btn = findViewById(R.id.lcn_btn);
        stn_btn = findViewById(R.id.stn_btn);
        cur_btn = findViewById(R.id.cur_btn);
        image_btn = findViewById(R.id.image_btn);
        icon_1 = findViewById(R.id.icon_1);

        tax_1 = findViewById(R.id.tax_1);
        tax_2 = findViewById(R.id.tax_2);
        tax_3 = findViewById(R.id.tax_3);
        tax_9 = findViewById(R.id.tax_9);

        linearLayout3 = findViewById(R.id.linear9);

        text_1 = findViewById(R.id.text_1);
        text_2 = findViewById(R.id.text_2);
        text_3 = findViewById(R.id.text_3);
        text_4 = findViewById(R.id.text_4);
        text_5 = findViewById(R.id.text_5);
        text_6 = findViewById(R.id.text_6);


        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        fusedLocationProviderClient2 = LocationServices.getFusedLocationProviderClient(this);


        getLocation();

//        loadNativeAd();



        lcn_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (interstitialAd.isAdLoaded()) {
                    interstitialAd.show();
                } else if (interstitialAd != null){
                    openSearchBar();
                }else {
                    openSearchBar();
                }

//                if (minterstitialAd.isLoaded()) {
//                    minterstitialAd.show();
//                } else {
//                    openSearchBar();
//                }

//                if (interstitialAd.isAdLoaded()) {
//                    interstitialAd.show();
//
//
//                }else
//                    {
//                        openSearchBar();
//                }

//                if (minterstitialAd.isLoaded()) {
//                    minterstitialAd.show();
//                } else {
//                    openSearchBar();
////                }


            }
        });
        stn_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSettingsBar();
            }
        });
        cur_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Initialize Progress Dialog
                progressDailog = new ProgressDialog(MainActivity.this);
                //Show Dailog
                progressDailog.show();
                //set Content View
                progressDailog.setContentView(R.layout.progress_dialog);
                progressDailog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                        WindowManager.LayoutParams.MATCH_PARENT);
                //run time
                //set Transparent Background
                progressDailog.getWindow().setBackgroundDrawableResource(
                        android.R.color.transparent);
                //run time
                progressDailog.onStart();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        getLocation();

                        progressDailog.dismiss();
                    }

                }, 2000);

            }

        });

    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, @Nullable final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case 11:


                if (requestCode == 11) {

                    if (data != null) {

                        String key = data.getStringExtra("key");
                        String name = data.getStringExtra("name");


                        currentData(key);
                        tax_2.setText(name);

                    }
                }
                break;


            case REQUEST_CHECK_SETTINGS:

                switch (resultCode) {
                    case Activity.RESULT_OK:


                        getKey(prefManager.setLocation());

//                        Toast.makeText(this, "GPS ON", Toast.LENGTH_SHORT).show();

                        Log.e(TAG, "User agreed to make required location settings changes.");
                        // Nothing to do. startLocationupdates() gets called in onResume again.
                        break;
                    case Activity.RESULT_CANCELED:


                        Log.e(TAG, "User chose not to make required location settings changes.");
                        mRequestingLocationUpdates = false;
                        break;
                }

                break;

        }

    }

    private void runtimePermission() {
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);

        } else {

            checkConnection();
//            startApp();

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//            startApp();
            checkConnection();
        } else {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
            getLocation();
        }

    }

    public void openSettingsBar() {
        Intent intent = new Intent(MainActivity.this, SettingsBar.class);
        startActivity(intent);
    }

    public void openSearchBar() {

        Intent intent = new Intent(MainActivity.this, SearchBar.class);
        startActivityForResult(intent, 11);

    }

    private void getLocation() {


//        Toast.makeText(this, "location====", Toast.LENGTH_SHORT).show();


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {


            return;

        } else {

            fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {


                    task.addOnCompleteListener(new OnCompleteListener<Location>() {
                        @Override
                        public void onComplete(@NonNull Task<Location> task) {

                            if (task.isComplete()) {
                                Location location = task.getResult();
//                                Toast.makeText(MainActivity.this, "3" + task.getResult(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    Location location = task.getResult();
                    if (location != null) {

                        try {
                            Geocoder geocoder = new Geocoder(MainActivity.this, Locale.getDefault());
                            List<Address> addresses = geocoder.getFromLocation(
                                    location.getLatitude(), location.getLongitude(), 1);


                            prefManager.getLocation(addresses.get(0).getLatitude() + "," + addresses.get(0).getLongitude());

                            tax_2.setText((addresses.get(0).getLocality() + " , " + addresses.get(0).getCountryName()));

                            getKey(addresses.get(0).getLatitude() + "," + addresses.get(0).getLongitude());

//                            Toast.makeText(MainActivity.this, addresses.get(0).getLatitude() + "," + addresses.get(0).getLongitude(), Toast.LENGTH_SHORT).show();


                        } catch (IOException e) {

                        }

                    } else {
//                        Toast.makeText(MainActivity.this, "location nul", Toast.LENGTH_SHORT).show();
                    }

                }

            });

        }

    }

    private void getKey(String location) {

        Call<ResponseClass> call1 = RetrofitClient.getInstance().getModel().getPosts_2(
                "application/x-www-form-urlencoded", "d7e795ae6a0d44aaa8abb1a0a7ac19e4",
                location, "en-gb", "true", "false");

        call1.enqueue(new Callback<ResponseClass>() {
            @Override
            public void onResponse(Call<ResponseClass> call, Response<ResponseClass> response) {
                Log.d("+++", "On Response: " + response.toString());
                if (response.code() == 200) {
                    ResponseClass responseClass = response.body();

                    prefManager.getKey(responseClass.getKey());

                    currentData();

                    currentData(responseClass.getKey());

                }

            }

            @Override
            public void onFailure(Call<ResponseClass> call, Throwable t) {
                Log.e("+++++", "onError: " + t.getMessage());
//                Toast.makeText(MainActivity.this, "No Internet connection!", Toast.LENGTH_SHORT).show();
            }

        });

    }

    private void currentData() {

        Call<List<ResponseUsers>> call = RetrofitClient.getInstance().getModel().getPosts(

                prefManager.setKey(),
                "application/x-www-form-urlencoded",
                "srRLeAmTroxPinDG8Aus3Ikl6tLGJd94",
                "en-gb", "true", "true");

        call.enqueue(new Callback<List<ResponseUsers>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<ResponseUsers>> call, Response<List<ResponseUsers>> response) {

                Log.d("TAG", "onResponse: " + response.toString());

                List<ResponseUsers> posts = response.body();

                if (response.code() == 200) {

                    Log.d("TAG", "onResponse: " + response.toString());

                    for (ResponseUsers post : posts) {
                        final int same = Math.round((float) post.getTemperature().getMetric().getValue());
                        final int same2 = Math.round((float) post.getTemperature().getImperial().getValue());


                        String date = String.valueOf(post.getLocalObservationDateTime());
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

                        Date iconID_9 = null;
                        try {
                            iconID_9 = dateFormat.parse(date);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        dateFormat = new SimpleDateFormat("EEE, MMM dd");

                        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
                        Date iconID_10 = null;
                        try {
                            iconID_10 = dateFormat1.parse(date);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        dateFormat1 = new SimpleDateFormat("HH:mm:ss");

                        Log.d("TAG", "*********" + dateFormat1.format(iconID_10));


                        try {
                            String string1 = "07:00:00";
                            Date time1 = new SimpleDateFormat("HH:mm:ss").parse(string1);
                            Calendar calendar1 = Calendar.getInstance();
                            calendar1.setTime(time1);
                            calendar1.add(Calendar.DATE, 1);

                            String string2 = "17:00:00";
                            Date time2 = new SimpleDateFormat("HH:mm:ss").parse(string2);
                            Calendar calendar2 = Calendar.getInstance();
                            calendar2.setTime(time2);
                            calendar2.add(Calendar.DATE, 1);
                            Log.d("TAG", "*********" + time1);

                            String string4 = "17:00:00";
                            Date time4 = new SimpleDateFormat("HH:mm:ss").parse(string4);
                            Calendar calendar4 = Calendar.getInstance();
                            calendar4.setTime(time4);
                            calendar4.add(Calendar.DATE, 1);

                            String string5 = "19:00:00";
                            Date time5 = new SimpleDateFormat("HH:mm:ss").parse(string5);
                            Calendar calendar5 = Calendar.getInstance();
                            calendar5.setTime(time5);
                            calendar5.add(Calendar.DATE, 1);

                            final String format = dateFormat1.format(iconID_10);

                            String someRandomTime = format;
                            Date d = new SimpleDateFormat("HH:mm:ss").parse(someRandomTime);
                            Calendar calendar3 = Calendar.getInstance();
                            calendar3.setTime(d);
                            calendar3.add(Calendar.DATE, 1);

                            Date x = calendar3.getTime();
                            if (x.after(calendar1.getTime()) && x.before(calendar2.getTime())) {

//                                System.out.println(true);
                                linearLayout3.setBackgroundResource(R.drawable.ic_group_2934);

                                Log.d("TAG", "*****1****");

                            } else if (x.after(calendar4.getTime()) && x.before(calendar5.getTime())) {
                                Log.d("TAG", "****2*****");
                                linearLayout3.setBackgroundResource(R.drawable.ic_group_2934_2);
                            } else {
                                Log.d("TAG", "****3*****");
                                linearLayout3.setBackgroundResource(R.drawable.group_2934_2);
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                            Log.d("TAG", "********" + e.getMessage());
                        }


                        tax_9.setText(dateFormat.format(iconID_9));

                        if (prefManager.setUnit() == 0) {
                            text_1.setText(post.getTemperatureSummary().getPast6HourRange().getMinimum().getMetric().getValue() + " ⁰| " +
                                    post.getTemperatureSummary().getPast6HourRange().getMaximum().getMetric().getValue() + " ⁰");
                            text_5.setText(post.getDewPoint().getMetric().getValue() + "⁰" + post.getDewPoint().getMetric().getUnit());
                            tax_1.setText(same + "");
                            icon_1.setImageResource(R.drawable.ic_vector__2);

                        } else {
                            text_1.setText(post.getTemperatureSummary().getPast6HourRange().getMinimum().getImperial().getValue() + " ⁰| " +
                                    post.getTemperatureSummary().getPast6HourRange().getMaximum().getImperial().getValue() + " ⁰");
                            text_5.setText(post.getDewPoint().getImperial().getValue() + "⁰" + post.getDewPoint().getImperial().getUnit());
                            tax_1.setText(same2 + "");
                            icon_1.setImageResource(R.drawable.ic_farenhit);

                        }

                        text_2.setText(post.getPressure().getMetric().getValue() + " " + post.getPressure().getMetric().getUnit());
                        text_3.setText(post.getRelativeHumidity() + " %");
                        text_4.setText(post.getWind().getSpeed().getMetric().getValue() + " " + post.getWind().getSpeed().getMetric().getUnit());
                        text_6.setText(post.getCloudCover() + " %");
                    }

                    next24HourData(prefManager.setKey());

                }

            }

            @Override
            public void onFailure(Call<List<ResponseUsers>> call, Throwable t) {

                Log.e("Current Data", "onError: " + t.getMessage());

            }
        });

    }

    private void currentData(String key) {

        Call<List<ResponseUsers>> call = RetrofitClient.getInstance().getModel().getPosts(
                key,
                "application/x-www-form-urlencoded",
                "srRLeAmTroxPinDG8Aus3Ikl6tLGJd94",
                "en-gb", "true", "true");

        call.enqueue(new Callback<List<ResponseUsers>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<List<ResponseUsers>> call, Response<List<ResponseUsers>> response) {

                Log.d("TAG", "onResponse: " + response.toString());

                List<ResponseUsers> posts = response.body();

                if (response.code() == 200) {

                    Log.d("TAG", "onResponse: " + response.toString());

                    for (ResponseUsers post : posts) {
                        final int same = Math.round((float) post.getTemperature().getMetric().getValue());
                        final int same2 = Math.round((float) post.getTemperature().getImperial().getValue());

                        String date = String.valueOf(post.getLocalObservationDateTime());
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

                        Date iconID_9 = null;
                        try {
                            iconID_9 = dateFormat.parse(date);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        dateFormat = new SimpleDateFormat("EEE, MMM dd");

                        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
                        Date iconID_10 = null;
                        try {
                            iconID_10 = dateFormat1.parse(date);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        dateFormat1 = new SimpleDateFormat("HH:mm:ss");

                        Log.d("TAG", "*********" + dateFormat1.format(iconID_10));

                        try {
                            String string1 = "07:00:00";
                            Date time1 = new SimpleDateFormat("HH:mm:ss").parse(string1);
                            Calendar calendar1 = Calendar.getInstance();
                            calendar1.setTime(time1);
                            calendar1.add(Calendar.DATE, 1);

                            String string2 = "17:00:00";
                            Date time2 = new SimpleDateFormat("HH:mm:ss").parse(string2);
                            Calendar calendar2 = Calendar.getInstance();
                            calendar2.setTime(time2);
                            calendar2.add(Calendar.DATE, 1);
                            Log.d("TAG", "*********" + time1);


                            String string4 = "17:00:00";
                            Date time4 = new SimpleDateFormat("HH:mm:ss").parse(string4);
                            Calendar calendar4 = Calendar.getInstance();
                            calendar4.setTime(time4);
                            calendar4.add(Calendar.DATE, 1);

                            String string5 = "19:00:00";
                            Date time5 = new SimpleDateFormat("HH:mm:ss").parse(string5);
                            Calendar calendar5 = Calendar.getInstance();
                            calendar5.setTime(time5);
                            calendar5.add(Calendar.DATE, 1);

                            final String format = dateFormat1.format(iconID_10);

                            String someRandomTime = format;
                            Date d = new SimpleDateFormat("HH:mm:ss").parse(someRandomTime);
                            Calendar calendar3 = Calendar.getInstance();
                            calendar3.setTime(d);
                            calendar3.add(Calendar.DATE, 1);

                            Date x = calendar3.getTime();
                            if (x.after(calendar1.getTime()) && x.before(calendar2.getTime())) {

//                                System.out.println(true);
                                linearLayout3.setBackgroundResource(R.drawable.ic_group_2934);

                                Log.d("TAG", "*****1****");
                            } else if (x.after(calendar4.getTime()) && x.before(calendar5.getTime())) {
                                linearLayout3.setBackgroundResource(R.drawable.ic_group_2934_2);

                                Log.d("TAG", "****2*****");
                            } else {
                                linearLayout3.setBackgroundResource(R.drawable.group_2934_2);

                                Log.d("TAG", "****3*****");
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                            Log.d("TAG", "********" + e.getMessage());
                        }


                        tax_9.setText(dateFormat.format(iconID_9));

                        if (prefManager.setUnit() == 0) {
                            text_1.setText(post.getTemperatureSummary().getPast6HourRange().getMinimum().getMetric().getValue() + " ⁰| " +
                                    post.getTemperatureSummary().getPast6HourRange().getMaximum().getMetric().getValue() + " ⁰");
                            text_5.setText(post.getDewPoint().getMetric().getValue() + "⁰" + post.getDewPoint().getMetric().getUnit());
                            tax_1.setText(same + "");
                            icon_1.setImageResource(R.drawable.ic_vector__2);

                        } else {
                            text_1.setText(post.getTemperatureSummary().getPast6HourRange().getMinimum().getImperial().getValue() + " ⁰| " +
                                    post.getTemperatureSummary().getPast6HourRange().getMaximum().getImperial().getValue() + " ⁰");
                            text_5.setText(post.getDewPoint().getImperial().getValue() + "⁰" + post.getDewPoint().getImperial().getUnit());
                            tax_1.setText(same2 + "");
                            icon_1.setImageResource(R.drawable.ic_farenhit);

                        }

                        text_2.setText(post.getPressure().getMetric().getValue() + " " + post.getPressure().getMetric().getUnit());
                        text_3.setText(post.getRelativeHumidity() + " %");
                        text_4.setText(post.getWind().getSpeed().getMetric().getValue() + " " + post.getWind().getSpeed().getMetric().getUnit());
                        text_6.setText(post.getCloudCover() + " %");

                    }

                    next24HourData(key);

                }
            }

            @Override
            public void onFailure(Call<List<ResponseUsers>> call, Throwable t) {

                Log.e("Current Data", "onError: " + t.getMessage());

            }
        });

    }

    private void next24HourData(String key) {

        Call<List<ResponseCall>> call2 = RetrofitClient.getInstance().getModel().getPost_3(
                key,
                "application/x-www-form-urlencoded", "srRLeAmTroxPinDG8Aus3Ikl6tLGJd94",
                "en-GB", "true", prefManager.setUnit() == 0 ? "true" : "false");

        call2.enqueue(new Callback<List<ResponseCall>>() {
            @Override
            public void onResponse(Call<List<ResponseCall>> call, Response<List<ResponseCall>> response) {
                Log.d("TAG", "On Response: " + response.toString());

                List<ResponseCall> posts1 = response.body();

                if (response.code() == 200) {
                    twentyFourModelList.clear();

                    Log.d("TAG", "On Response: " + response.toString());

                    for (ResponseCall post_1 : posts1) {

                        final int round = Math.round((float) post_1.getRealFeelTemperature().getValue());
                        final int roun2 = Math.round((float) post_1.getTemperature().getValue());

                        String i = String.valueOf(post_1.getWeatherIcon());

                        int j = Integer.parseInt(i);
                        int image_btn4 = getResources().getIdentifier("b" + i, "drawable", getPackageName());


                        tax_3.setText(post_1.getIconPhrase());


                        image_btn.setImageResource(image_btn4);
                        TwentyFourModel twentyFourModel = new TwentyFourModel();
                        twentyFourModel.setTime(post_1.getDateTime());
                        twentyFourModel.setValue(round);
                        twentyFourModel.setIcon(post_1.getWeatherIcon());
                        twentyFourModelList.add(twentyFourModel);

                        Log.d("TAG", "onResponse: " + round + "\n" + post_1.getRealFeelTemperature().getValue());

                    }

                    recyclerView_1.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
                    TwentyFourHourAdapter adapter = new TwentyFourHourAdapter(MainActivity.this, twentyFourModelList);
                    recyclerView_1.setAdapter(adapter);


                    next15DayData(key);

                }

            }

            @Override
            public void onFailure(Call<List<ResponseCall>> call, Throwable t) {
                Log.e("TAG", "onError: " + t.getMessage());
            }

        });

    }

    private void next15DayData(String key) {

        Call<ResponseClasses> call3 = RetrofitClient.getInstance().getModel().getPost_4(
                key,
                "application/x-www-form-urlencoded", "srRLeAmTroxPinDG8Aus3Ikl6tLGJd94",
                "en-gb", "true", prefManager.setUnit() == 0 ? "true" : "false");

        call3.enqueue(new Callback<ResponseClasses>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<ResponseClasses> call, Response<ResponseClasses> response) {
                Log.d("TAG", "On Responseee: " + response.toString());


                ResponseClasses posts_1 = response.body();


                if (response.code() == 200) {
                    sevenDaysModelList.clear();
                    temperatureModelList.clear();
                    fifteenDaysModelList.clear();


                    List<DailyForecastsItem> list = posts_1.getDailyForecasts();

                    for (int i = 0; i < 7; i++) {
                        SevenDaysModel sevenDaysModel = new SevenDaysModel();
                        sevenDaysModel.setTime3(list.get(i).getDate());
                        sevenDaysModel.setIcon3(list.get(i).getDay().getIcon());
                        sevenDaysModel.setDate3(list.get(i).getDate());
                        sevenDaysModelList.add(sevenDaysModel);

                    }

                    for (int k = 0; k < 7; k++) {
                        DailyForecastsItem post_7 = list.get(k);
                        int round_5 = Math.round((float) post_7.getRealFeelTemperatureShade().getMinimum().getValue());
                        int round_6 = Math.round((float) post_7.getRealFeelTemperature().getMaximum().getValue());


                        TemperatureModel temperatureModel = new TemperatureModel();
                        temperatureModel.setTemp5(round_6);
                        temperatureModel.setVal5(round_5);
                        temperatureModel.setIcon6(list.get(k).getDay().getIcon());

                        temperatureModelList.add(temperatureModel);

                    }

                    for (DailyForecastsItem post_2 : list) {


                        final int round_1 = Math.round((float) post_2.getTemperature().getMaximum().getValue());
                        final int round7 = Math.round((float) post_2.getTemperature().getMaximum().getValue());
                        final int round8 = Math.round((float) post_2.getTemperature().getMinimum().getValue());


                        String i = String.valueOf(post_2.getDay().getIcon());
                        int j = Integer.parseInt(i);

                        int image_btn3 = getResources().getIdentifier("b" + i, "drawable", getPackageName());


                        FifteenDaysModel fifteenDaysModel = new FifteenDaysModel();
                        fifteenDaysModel.setTime1(post_2.getDate());
                        fifteenDaysModel.setIcon1(post_2.getDay().getIcon());
                        fifteenDaysModel.setValue1(post_2.getDay().getRainProbability());
                        fifteenDaysModel.setWind1(post_2.getDay().getCloudCover());
                        fifteenDaysModel.setTemp2(post_2.getDay().getIconPhrase());
                        fifteenDaysModel.setTemp1(round_1);
                        fifteenDaysModel.setWind2((int) post_2.getDay().getWind().getSpeed().getValue());
                        fifteenDaysModel.setIcon3(post_2.getDay().getIcon());
                        fifteenDaysModel.setUnit(post_2.getTemperature().getMaximum().getUnit());

                        fifteenDaysModelList.add(fifteenDaysModel);

                    }

                    for (int i = 0; i < fifteenDaysModelList.size(); i++) {
                        FifteenDaysModel fifteenDaysModel = (FifteenDaysModel) fifteenDaysModelList.get(i);

                        if (i == 0) {
                            binding.tvDate.setText(getDate(fifteenDaysModel.getTime1()));
                            binding.ivIcon.setImageResource(geticon(String.valueOf(fifteenDaysModel.getIcon1())));
                            binding.tvTemp.setText(fifteenDaysModel.getTemp1() + "⁰");
                            binding.tvCloud.setText(String.valueOf(fifteenDaysModel.getWind1()) + "%");
                            binding.tvWind.setText(String.valueOf(fifteenDaysModel.getWind2()) + ".0KM/H");
                            binding.tvIcStatus.setText(String.valueOf(fifteenDaysModel.getTemp2()));
                            binding.tvRain.setText(String.valueOf(fifteenDaysModel.getValue1()) + "%");
                        }else if (i == 1){
                            binding.tvDate2.setText(getDate(fifteenDaysModel.getTime1()));
                            binding.ivIcon2.setImageResource(geticon(String.valueOf(fifteenDaysModel.getIcon1())));
                            binding.tvTemp2.setText(fifteenDaysModel.getTemp1() + "⁰");
                            binding.tvCloud2.setText(String.valueOf(fifteenDaysModel.getWind1()) + "%");
                            binding.tvWind2.setText(String.valueOf(fifteenDaysModel.getWind2()) + ".0KM/H");
                            binding.tvIcStatus2.setText(String.valueOf(fifteenDaysModel.getTemp2()));
                            binding.tvRain2.setText(String.valueOf(fifteenDaysModel.getValue1()) + "%");
                        }else if (i == 2){
                            binding.tvDate3.setText(getDate(fifteenDaysModel.getTime1()));
                            binding.ivIcon3.setImageResource(geticon(String.valueOf(fifteenDaysModel.getIcon1())));
                            binding.tvTemp3.setText(fifteenDaysModel.getTemp1() + "⁰");
                            binding.tvCloud3.setText(String.valueOf(fifteenDaysModel.getWind1()) + "%");
                            binding.tvWind3.setText(String.valueOf(fifteenDaysModel.getWind2()) + ".0KM/H");
                            binding.tvIcStatus3.setText(String.valueOf(fifteenDaysModel.getTemp2()));
                            binding.tvRain3.setText(String.valueOf(fifteenDaysModel.getValue1()) + "%");
                        }else if (i == 3){
                            binding.tvDate4.setText(getDate(fifteenDaysModel.getTime1()));
                            binding.ivIcon4.setImageResource(geticon(String.valueOf(fifteenDaysModel.getIcon1())));
                            binding.tvTemp4.setText(fifteenDaysModel.getTemp1() + "⁰");
                            binding.tvCloud4.setText(String.valueOf(fifteenDaysModel.getWind1()) + "%");
                            binding.tvWind4.setText(String.valueOf(fifteenDaysModel.getWind2()) + ".0KM/H");
                            binding.tvIcStatus4.setText(String.valueOf(fifteenDaysModel.getTemp2()));
                            binding.tvRain4.setText(String.valueOf(fifteenDaysModel.getValue1()) + "%");
                        }else if (i == 4){
                            binding.tvDate5.setText(getDate(fifteenDaysModel.getTime1()));
                            binding.ivIcon5.setImageResource(geticon(String.valueOf(fifteenDaysModel.getIcon1())));
                            binding.tvTemp5.setText(fifteenDaysModel.getTemp1() + "⁰");
                            binding.tvCloud5.setText(String.valueOf(fifteenDaysModel.getWind1()) + "%");
                            binding.tvWind5.setText(String.valueOf(fifteenDaysModel.getWind2()) + ".0KM/H");
                            binding.tvIcStatus5.setText(String.valueOf(fifteenDaysModel.getTemp2()));
                            binding.tvRain5.setText(String.valueOf(fifteenDaysModel.getValue1()) + "%");
                        }else if (i == 5){
                            binding.tvDate6.setText(getDate(fifteenDaysModel.getTime1()));
                            binding.ivIcon6.setImageResource(geticon(String.valueOf(fifteenDaysModel.getIcon1())));
                            binding.tvTemp6.setText(fifteenDaysModel.getTemp1() + "⁰");
                            binding.tvCloud6.setText(String.valueOf(fifteenDaysModel.getWind1()) + "%");
                            binding.tvWind6.setText(String.valueOf(fifteenDaysModel.getWind2()) + ".0KM/H");
                            binding.tvIcStatus6.setText(String.valueOf(fifteenDaysModel.getTemp2()));
                            binding.tvRain6.setText(String.valueOf(fifteenDaysModel.getValue1()) + "%");
                        }else if (i == 6){
                            binding.tvDate7.setText(getDate(fifteenDaysModel.getTime1()));
                            binding.ivIcon7.setImageResource(geticon(String.valueOf(fifteenDaysModel.getIcon1())));
                            binding.tvTemp7.setText(fifteenDaysModel.getTemp1() + "⁰");
                            binding.tvCloud7.setText(String.valueOf(fifteenDaysModel.getWind1()) + "%");
                            binding.tvWind7.setText(String.valueOf(fifteenDaysModel.getWind2()) + ".0KM/H");
                            binding.tvIcStatus7.setText(String.valueOf(fifteenDaysModel.getTemp2()));
                            binding.tvRain7.setText(String.valueOf(fifteenDaysModel.getValue1()) + "%");
                        }else if (i == 7){
                            binding.tvDate8.setText(getDate(fifteenDaysModel.getTime1()));
                            binding.ivIcon8.setImageResource(geticon(String.valueOf(fifteenDaysModel.getIcon1())));
                            binding.tvTemp8.setText(fifteenDaysModel.getTemp1() + "⁰");
                            binding.tvCloud8.setText(String.valueOf(fifteenDaysModel.getWind1()) + "%");
                            binding.tvWind8.setText(String.valueOf(fifteenDaysModel.getWind2()) + ".0KM/H");
                            binding.tvIcStatus8.setText(String.valueOf(fifteenDaysModel.getTemp2()));
                            binding.tvRain8.setText(String.valueOf(fifteenDaysModel.getValue1()) + "%");
                        }else if (i == 8){
                            binding.tvDate9.setText(getDate(fifteenDaysModel.getTime1()));
                            binding.ivIcon9.setImageResource(geticon(String.valueOf(fifteenDaysModel.getIcon1())));
                            binding.tvTemp9.setText(fifteenDaysModel.getTemp1() + "⁰");
                            binding.tvCloud9.setText(String.valueOf(fifteenDaysModel.getWind1()) + "%");
                            binding.tvWind9.setText(String.valueOf(fifteenDaysModel.getWind2()) + ".0KM/H");
                            binding.tvIcStatus9.setText(String.valueOf(fifteenDaysModel.getTemp2()));
                            binding.tvRain9.setText(String.valueOf(fifteenDaysModel.getValue1()) + "%");
                        }else if (i == 9){
                            binding.tvDate10.setText(getDate(fifteenDaysModel.getTime1()));
                            binding.ivIcon10.setImageResource(geticon(String.valueOf(fifteenDaysModel.getIcon1())));
                            binding.tvTemp10.setText(fifteenDaysModel.getTemp1() + "⁰");
                            binding.tvCloud10.setText(String.valueOf(fifteenDaysModel.getWind1()) + "%");
                            binding.tvWind10.setText(String.valueOf(fifteenDaysModel.getWind2()) + ".0KM/H");
                            binding.tvIcStatus10.setText(String.valueOf(fifteenDaysModel.getTemp2()));
                            binding.tvRain10.setText(String.valueOf(fifteenDaysModel.getValue1()) + "%");
                        }else if (i == 10){
                            binding.tvDate11.setText(getDate(fifteenDaysModel.getTime1()));
                            binding.ivIcon11.setImageResource(geticon(String.valueOf(fifteenDaysModel.getIcon1())));
                            binding.tvTemp11.setText(fifteenDaysModel.getTemp1() + "⁰");
                            binding.tvCloud11.setText(String.valueOf(fifteenDaysModel.getWind1()) + "%");
                            binding.tvWind11.setText(String.valueOf(fifteenDaysModel.getWind2()) + ".0KM/H");
                            binding.tvIcStatus11.setText(String.valueOf(fifteenDaysModel.getTemp2()));
                            binding.tvRain11.setText(String.valueOf(fifteenDaysModel.getValue1()) + "%");
                        }else if (i == 11){
                            binding.tvDate12.setText(getDate(fifteenDaysModel.getTime1()));
                            binding.ivIcon12.setImageResource(geticon(String.valueOf(fifteenDaysModel.getIcon1())));
                            binding.tvTemp12.setText(fifteenDaysModel.getTemp1() + "⁰");
                            binding.tvCloud12.setText(String.valueOf(fifteenDaysModel.getWind1()) + "%");
                            binding.tvWind12.setText(String.valueOf(fifteenDaysModel.getWind2()) + ".0KM/H");
                            binding.tvIcStatus12.setText(String.valueOf(fifteenDaysModel.getTemp2()));
                            binding.tvRain12.setText(String.valueOf(fifteenDaysModel.getValue1()) + "%");
                        }else if (i == 12){
                            binding.tvDate13.setText(getDate(fifteenDaysModel.getTime1()));
                            binding.ivIcon13.setImageResource(geticon(String.valueOf(fifteenDaysModel.getIcon1())));
                            binding.tvTemp13.setText(fifteenDaysModel.getTemp1() + "⁰");
                            binding.tvCloud13.setText(String.valueOf(fifteenDaysModel.getWind1()) + "%");
                            binding.tvWind13.setText(String.valueOf(fifteenDaysModel.getWind2()) + ".0KM/H");
                            binding.tvIcStatus13.setText(String.valueOf(fifteenDaysModel.getTemp2()));
                            binding.tvRain13.setText(String.valueOf(fifteenDaysModel.getValue1()) + "%");
                        }else if (i == 13){
                            binding.tvDate14.setText(getDate(fifteenDaysModel.getTime1()));
                            binding.ivIcon14.setImageResource(geticon(String.valueOf(fifteenDaysModel.getIcon1())));
                            binding.tvTemp14.setText(fifteenDaysModel.getTemp1() + "⁰");
                            binding.tvCloud14.setText(String.valueOf(fifteenDaysModel.getWind1()) + "%");
                            binding.tvWind14.setText(String.valueOf(fifteenDaysModel.getWind2()) + ".0KM/H");
                            binding.tvIcStatus14.setText(String.valueOf(fifteenDaysModel.getTemp2()));
                            binding.tvRain14.setText(String.valueOf(fifteenDaysModel.getValue1()) + "%");
                        }else if (i == 14){
                            binding.tvDate15.setText(getDate(fifteenDaysModel.getTime1()));
                            binding.ivIcon15.setImageResource(geticon(String.valueOf(fifteenDaysModel.getIcon1())));
                            binding.tvTemp15.setText(fifteenDaysModel.getTemp1() + "⁰");
                            binding.tvCloud15.setText(String.valueOf(fifteenDaysModel.getWind1()) + "%");
                            binding.tvWind15.setText(String.valueOf(fifteenDaysModel.getWind2()) + ".0KM/H");
                            binding.tvIcStatus15.setText(String.valueOf(fifteenDaysModel.getTemp2()));
                            binding.tvRain15.setText(String.valueOf(fifteenDaysModel.getValue1()) + "%");


                        }

                        Log.d("TAG++++++++++", "onResponse: " + fifteenDaysModel.getValue1());

                    }


//                    recyclerView_2.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
//                    adapter_1 = new FifteenDaysAdapter(MainActivity.this, fifteenDaysModelList, fifteenDaysModelList.size());
//                    recyclerView_2.setAdapter(adapter_1);
//                    adapter_1.notifyDataSetChanged();
//                    adapter_1.initNativeAds();

                    recyclerView_3.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
                    SevenDaysAdapter adapter_2 = new SevenDaysAdapter(MainActivity.this, sevenDaysModelList);
                    recyclerView_3.setAdapter(adapter_2);


                    recyclerView_4.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
                    TemperatureAdapter adapter_3 = new TemperatureAdapter(MainActivity.this, temperatureModelList);
                    recyclerView_4.setAdapter(adapter_3);


                }

                progressDailog.dismiss();

            }

            @Override
            public void onFailure(Call<ResponseClasses> call, Throwable t) {
                Log.e("TAG", "onError 15 days : " + t.getMessage());

            }

        });


    }

    private String getDate(String date) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date iconID_2 = null;
        try {
            iconID_2 = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        dateFormat = new SimpleDateFormat("E dd, yyyy ");

        return dateFormat.format(iconID_2);
    }

    private int geticon (String i){
        return getResources().getIdentifier("a" + i, "drawable",getPackageName());
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        AdRequest adRequest = new AdRequest.Builder().build();
//        minterstitialAd.loadAd(adRequest);
//        minterstitialAd2.loadAd(adRequest);

//        minterstitialAd.loadAd(adRequest);
//        minterstitialAd2.loadAd(adRequest);
//        interstitialAd.loadAd();
//        interstitialAd2.loadAd();


        currentData(prefManager.setKey());

    }

    @Override
    public void onAdsLoaded() {


        adapter_1.notifyDataSetChanged();

    }

    @Override
    public void onAdError(AdError adError) {

    }

}

