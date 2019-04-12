package com.example.khujo.data;

import com.example.khujo.PlacesDetailsResponse;
import com.example.khujo.models.PlacesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface GooglePlacesApi {
    String BASE_URL = "https://maps.googleapis.com/maps/api/place/";
    String API_KEY = "AIzaSyBoL2ZzJl033fH5CFTEevcGgoicq8sqg3c";

    @Headers("Content-Type: application/json")
    @GET("nearbysearch/json?key="+API_KEY)
    Call<PlacesResponse> getPlaces(@Query("location") String location, @Query("types") String types, @Query("radius") String radius);

    @Headers("Content-Type: application/json")
    @GET("details/json?key="+API_KEY)
    Call<PlacesDetailsResponse> getPlaceDetails(@Query("placeid") String placeId);
}
