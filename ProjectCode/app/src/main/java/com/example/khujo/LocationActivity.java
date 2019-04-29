package com.example.play.bazarmind;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.play.bazarmind.adapters.ResultAdapter;
import com.example.play.bazarmind.models.Item;
import com.example.play.bazarmind.models.Result;
import com.example.play.bazarmind.services.LocationUpdatesIntentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LocationActivity extends AppCompatActivity {
    private static final String TAG = "LocationActivity";
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        mRecyclerView = findViewById(R.id.al_location_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (getIntent() != null) {
            Map<Result, List<Item>> resultListMap = LocationUpdatesIntentService.convertToMap(getIntent().getByteArrayExtra(LocationUpdatesIntentService.LOCATION_MAP_KEY));
            if (resultListMap != null) {
                List<Result> results = new ArrayList<>();
                for (Map.Entry<Result, List<Item>> entry : resultListMap.entrySet()) {
                    results.add(entry.getKey());
                }
                ResultAdapter adapter = new ResultAdapter(results, resultListMap);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                adapter.bindToRecyclerView(mRecyclerView);
                adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                        Result item = (Result) adapter.getItem(position);
                        String location = item.getGeometry().getLocation().getLat()+","+item.getGeometry().getLocation().getLng();
                        Uri gmmIntentUri = Uri.parse("geo:0,0?q="+location+" (" + Uri.encode(item.getName()) + ")");
                        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                        mapIntent.setPackage("com.google.android.apps.maps");
                        if (mapIntent.resolveActivity(getPackageManager()) != null) {
                            startActivity(mapIntent);
                        }
                    }
                });
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
