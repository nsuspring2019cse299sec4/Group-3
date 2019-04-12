package com.example.khujo.data;

import com.example.khujo.models.Category;
import com.example.khujo.models.Item;
import com.example.khujo.models.PlacesDetailsResponse;
import com.example.khujo.models.PlacesResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface khujoApi {
    String BASE_URL = "https://khujo-2c69e.firebaseio.com/";


    @Headers("Content-Type: application/json")
    @GET("Users/{userId}/lists.json")
    Call<Map<String, Object>> getLists(@Path("userId") String userId);

    @Headers("Content-Type: application/json")
    @GET("Users/{userId}/lists/{listId}/items.json")
    Call<Map<String, Object>> getItems(@Path("userId") String userId, @Path("listId") String listId);

    @Headers("Content-Type: application/json")
    @GET("Users/{userId}/lists/{listId}/items/{itemId}.json")
    Call<Item> getItem(@Path("userId") String userId, @Path("listId") String listId, @Path("itemId") String itemId);

    @Headers("Content-Type: application/json")
    @GET("categories.json")
    Call<Map<String, Category>> getCategories();


}
