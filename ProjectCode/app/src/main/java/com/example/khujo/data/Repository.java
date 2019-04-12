package com.example.khujo.data;

import com.example.khujo.models.Category;
import com.example.khujo.models.Item;
import com.example.khujo.models.PlacesResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.khujo.data.khujoApi.BASE_URL;

public class Repository {

    private khujo api;
    private GooglePlacesApi placesApi;
    private static Repository sInstance;

    public static synchronized Repository getInstance() {
        if (sInstance == null) {
            sInstance = new Repository();
        }
        return sInstance;
    }
    private Repository() {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        api = retrofit.create(BazarmindApi.class);

        Retrofit retrofitMap = new Retrofit.Builder()
                .baseUrl(GooglePlacesApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        placesApi = retrofitMap.create(GooglePlacesApi.class);
    }

    public Call<Map<String, Object>> getLists(String userId) {
        return api.getLists(userId);
    }

    public Call<Map<String, Object>> getItems(String userId, String listId) {
        return api.getItems(userId, listId);
    }

    public Call<Item> getItem(String userId, String listId, String itemId) {
        return api.getItem(userId, listId, itemId);
    }

    public Call<PlacesResponse> getNearbyPlaces(String location, String types, String radius) {
        return placesApi.getPlaces(location, types, radius);
    }

    public Call<Map<String, Category>> getCategories() {
        return api.getCategories();
    }

    public Single<List<Item>> getItems(final String userId) {
        return Single.fromCallable(() -> getItemsFromFirebase(userId));
    }

    private List<Item> getItemsFromFirebase(String userId) throws IOException {
        List<Item> items = new ArrayList<>();
        Response<Map<String, Object>> listResponse = getLists(userId).execute();
        Map<String, Object> listMap = listResponse.body();

        for (Map.Entry<String, Object> entry : listMap.entrySet()) {
            Response<Map<String, Object>> itemResponse = getItems(userId, entry.getKey()).execute();
            Map<String, Object> itemMap = itemResponse.body();
            for (Map.Entry<String, Object> entryItem : itemMap.entrySet()) {
                Response<Item> itemResponse1 = getItem(userId, entry.getKey(), entryItem.getKey()).execute();
                Item item = itemResponse1.body();
                items.add(item);
            }
        }
        return items;
    }
}
