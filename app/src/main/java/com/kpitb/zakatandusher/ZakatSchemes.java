package com.kpitb.zakatandusher;

import android.os.Bundle;

import com.kpitb.zakatandusher.Adapter.MainPageAdapter;
import com.kpitb.zakatandusher.Modal.HomePageModel;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ZakatSchemes extends AppCompatActivity {
    private HomePageModel homePageData;
    private RecyclerView mRecyclerView;
    private ArrayList<HomePageModel> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setUpRescyclerView();
    }
    private void setUpRescyclerView() {

        arrayList = new ArrayList<>();
        homePageData = new HomePageModel("Guzzara Allowance", R.color.orange,
                R.drawable.ic_children);
        arrayList.add(homePageData);
        homePageData = new HomePageModel("Marriage Assistance", R.color.yellow50,
                R.drawable.ic_basketball);
        arrayList.add(homePageData);
        homePageData = new HomePageModel("Educational Stipends (General)", R.color.blue700,
                R.drawable.ic_abc_block);
        arrayList.add(homePageData);
        homePageData = new HomePageModel("Educational Stipends (Technical)", R.color.orange,
                R.drawable.ic_graduation_cap);
        arrayList.add(homePageData);
        homePageData = new HomePageModel("Deeni Madaris", R.color.yellow50,
                R.drawable.ic_mosque);
        arrayList.add(homePageData);
        homePageData = new HomePageModel("Health Care", R.color.blue700,
                R.drawable.ic_health);
        arrayList.add(homePageData);

        mRecyclerView = findViewById(R.id.resyclerView);
//        GridLayoutManager mGridLayoutManager = new GridLayoutManager(ZakatSchemes.this, 2);
//        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


//        mRecyclerView = findViewById(R.id.resyclerView);
////        GridLayoutManager mGridLayoutManager = new GridLayoutManager(MainActivity.this, 2);
////        mRecyclerView.setLayoutManager(mGridLayoutManager);
        MainPageAdapter myAdapter = new MainPageAdapter(ZakatSchemes.this, arrayList);
        mRecyclerView.setAdapter(myAdapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
