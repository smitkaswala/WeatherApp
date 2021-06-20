package com.bbot.darkweatherforecast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;

import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bbot.darkweatherforecast.SearchLocation.ResponseLite;
import com.bbot.darkweatherforecast.databinding.ActivitySearchBarBinding;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchBar extends AppCompatActivity {

    private ActivitySearchBarBinding binding;
    ArrayList<ResponseLite> responseLiteList;
    ArrayList<AddLocationClass> addLocationDataArrayList9;
    SearchAdapter searchAdapter;

    AutoSuggestAdapter autoSuggestAdapter;
    private static final int TRIGGER_AUTO_COMPLETE = 100;
    private static final long AUTO_COMPLETE_DELAY = 300;
    private Handler handler;
    SqliteDatabase sqliteDatabase;

    PrefManager prefManager;

    FusedLocationProviderClient fusedLocationProviderClient;
    TextView iv3;
    AutoCompleteTextView autoCompleteTextView;
    DBHelper DB;
    private ImageButton bck_btn;
    public Activity context_9;

    private AdView mAdView;


    RecyclerView listLocation;
    LinearLayoutManager mLayoutManager;
    Type type = new TypeToken<List<AddLocationData>>()
    { }.getType();

    private InterstitialAd interstitialAd;

    ArrayList<AddLocationData> addLocationDataArrayList;

    Toolbar toolbar;

    private com.facebook.ads.AdView adView2;

    Boolean isInternetPresent = false;
    ConnectionDetector cd;
    TextView edit1;

    ArrayList<String> list = new ArrayList<>();

    DBHelper dbHelper;
    LinearLayout current_location;

    private final String TAG = SearchBar.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_search_bar);

        bck_btn = (ImageButton) findViewById(R.id.bck_btn);
        iv3 = findViewById(R.id.iv3);
        autoCompleteTextView = findViewById(R.id.edit_1);
        current_location = findViewById(R.id.current_location);


//        mAdView = findViewById(R.id.adViewGoogle);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);
//        AdView adView = new AdView(this);
//        adView.setAdSize(AdSize.SMART_BANNER);



        adView2 = new com.facebook.ads.AdView(this,"433735587905021_433736307904949", com.facebook.ads.AdSize.BANNER_HEIGHT_50);
//        adView2 = new AdView(this, "IMG_16_9_APP_INSTALL#2822925034589011_2826374884244026", AdSize.BANNER_HEIGHT_90);

        // Find the Ad Container
//        LinearLayout adContainer = (LinearLayout) findViewById(R.id.banner_container);
        LinearLayout adContainer = (LinearLayout) findViewById(R.id.banner_container);

        // Add the ad view to your activity layout
        adContainer.addView(adView2);
//        adContainer2.addView(adView2);

        // Request an ad
        adView2.loadAd();

        bck_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });
        current_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getLocation();

            }
        });

        dbHelper = new DBHelper(this);

        //start activity

        sqliteDatabase = new SqliteDatabase(this);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        getLocation();

        sqliteDatabase = new SqliteDatabase(this);
        addLocationDataArrayList9 = sqliteDatabase.listContacts();
        binding.listLocation.setLayoutManager(new LinearLayoutManager(SearchBar.this));
        searchAdapter = new SearchAdapter(addLocationDataArrayList9,SearchBar.this);
        binding.listLocation.setAdapter(searchAdapter);

        autoSuggestAdapter = new AutoSuggestAdapter(SearchBar.this,R.layout.list_item_search_auto , R.id.text1, responseLiteList);
        binding.search.edit1.setThreshold(1);
        binding.search.edit1.setAdapter(autoSuggestAdapter);

        binding.search.edit1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                AddLocationClass addLocationClass = new AddLocationClass();
                addLocationClass.setCity(responseLiteList.get(position).getLocalizedName());
                addLocationClass.setCountry(responseLiteList.get(position).getCountry().getLocalizedName());
                addLocationClass.setStateloca(responseLiteList.get(position).getAdministrativeArea().getLocalizedName());
                addLocationClass.setKey(responseLiteList.get(position).getKey());
                sqliteDatabase.addbook(addLocationClass);


                InputMethodManager in = (InputMethodManager) getApplicationContext().getSystemService(getApplicationContext().INPUT_METHOD_SERVICE);
                in.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);


                Log.d("@@@@@@@", "onItemClick: "+responseLiteList.get(position).getKey());

                addLocationDataArrayList9.clear();
                addLocationDataArrayList9 = sqliteDatabase.listContacts();
                searchAdapter = new SearchAdapter(addLocationDataArrayList9,SearchBar.this);
                binding.listLocation.setAdapter(searchAdapter);

            }

        });

        binding.search.edit1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.length() > 2){

                    handler.removeMessages(TRIGGER_AUTO_COMPLETE);
                    handler.sendEmptyMessageDelayed(TRIGGER_AUTO_COMPLETE,AUTO_COMPLETE_DELAY);

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        });

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {

                if (msg.what == TRIGGER_AUTO_COMPLETE){

                    if (!TextUtils.isEmpty(binding.search.edit1.getText())){

                        searchLocation(binding.search.edit1.getText().toString());

                    }

                }

                return false;

            }

        });

    }

    private void searchLocation(CharSequence text) {

        Call<List<ResponseLite>> call4 = RetrofitClient.getInstance().getModel().getPost_5(
                "application/x-www-form-urlencoded", text,
                "srRLeAmTroxPinDG8Aus3Ikl6tLGJd94", "en-us", "value"

        );
        call4.enqueue(new Callback<List<ResponseLite>>() {
            @Override
            public void onResponse(Call<List<ResponseLite>> call, Response<List<ResponseLite>> response)

            {


                responseLiteList  = new ArrayList<>();
                List<ResponseLite> post5 = response.body();
                if (post5 != null)

                {
//                    prefManager.setKey();
                    responseLiteList.addAll(post5);
                    autoSuggestAdapter.setData(responseLiteList);
                    autoSuggestAdapter.notifyDataSetChanged();

                }

            }
            @Override
            public void onFailure(Call<List<ResponseLite>> call, Throwable t) {

                Log.e("SearchBar", "onError: " + t.getMessage());

            }

        });

    }

    private void getLocation() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;

        } else {

            fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {


                    Location location = task.getResult();
                    if (location != null) {
//                        Toast.makeText(SearchBar.this, "PERMISSION", Toast.LENGTH_SHORT).show();
                        try {
                            Geocoder geocoder = new Geocoder(SearchBar.this, Locale.getDefault());
                            List<Address> addresses = geocoder.getFromLocation(
                                    location.getLatitude(), location.getLongitude(), 1);


                            iv3.setText((addresses.get(0).getAddressLine(0)));
//                            tax_3.setText(addresses.get(0).getPostalCode());


                        } catch (IOException e) {
//                            Toast.makeText(SearchBar.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                }

            });

        }

    }

}











