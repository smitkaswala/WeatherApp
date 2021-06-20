package com.bbot.darkweatherforecast;



import com.bbot.darkweatherforecast.FifteenDays.ResponseClasses;
import com.bbot.darkweatherforecast.FullDay.ResponseUsers;
import com.bbot.darkweatherforecast.SearchLocation.ResponseLite;
import com.bbot.darkweatherforecast.TwenteenfourDay.ResponseCall;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Model {

    @GET("locations/v1/cities/geoposition/search.json")
    Call<ResponseClass> getPosts_2(
            @Header("Content-Type") String header_1,
            @Query("apikey") String apikey_1,
            @Query("q") String q_1,
            @Query("language") String language_1,
            @Query("details") String details_1,
            @Query("toplevel") String toplevel_1);


    @GET("currentconditions/v1/{key}.json")
    Call<List<ResponseUsers>> getPosts(
            @Path("key") String key,
            @Header("Content-Type") String header,
            @Query("apikey") String apikey,
            @Query("language") String language,
            @Query("details") String details,
            @Query("getphotos") String getphotos);


    @GET("forecasts/v1/hourly/24hour/{key}")
    Call<List<ResponseCall>> getPost_3(
            @Path("key") String key,
            @Header("Content-Type") String header_3,
            @Query("apikey") String apikey_3,
            @Query("language") String language_3,
            @Query("details") String details_3,
            @Query("metric") String metric_3);


    @GET("forecasts/v1/daily/15day/{key}.json")
    Call<ResponseClasses> getPost_4(
            @Path("key") String key,
            @Header("Content-Type") String header_4,
            @Query("apikey") String apikey_4,
            @Query("language") String language_4,
            @Query("details") String details_4,
            @Query("metric") String metric_4);

    @GET("locations/v1/cities/autocomplete")
    Call<List<ResponseLite>> getPost_5
            (@Header("Content-Type") String header_5,
             @Query("q") CharSequence apikey_5,
             @Query("apikey") String language_5,
             @Query("language") String details_5,
             @Query("get_param") String metric_5);
}



