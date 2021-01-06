package com.kpitb.zakattandusherr;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.kpitb.zakattandusherr.Adapter.MainPageAdapter;
import com.kpitb.zakattandusherr.Adapter.MainPageAdapterPashto;
import com.kpitb.zakattandusherr.Adapter.MainPageAdapterUrdu;
import com.kpitb.zakattandusherr.Modal.HomePageModel;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ZakatSchemes extends AppCompatActivity {
    private HomePageModel homePageData;
    private RecyclerView mRecyclerView;
    private ArrayList<HomePageModel> arrayList;

    private final String TAG = "ZAKAT_SCREEN";

    private FirebaseAnalytics firebaseAnalytics;

    String btnNAME, value;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zakat_scheme);

        firebaseAnalytics = FirebaseAnalytics.getInstance(this);
        mediaPlayer = MediaPlayer.create(this,R.raw.click_sound);

        Toolbar toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //get value from main activity adapter
        Intent intent = getIntent();
        if (intent.getStringExtra("LANG").equals("زکوٰۃ اسکیمیں"))
        {
            setUpRescyclerViewUrdu();
        }
        else if (intent.getStringExtra("LANG").equals("د زکات پلانونه"))
        {
            setUpRescyclerViewPashto();
        }
        else if (intent.getStringExtra("LANG").equals("Zakat Schemes")){
            setUpRescyclerView();
        }
        else {
            setUpRescyclerViewUrdu();
        }
        //show English language list view by default
        //setUpRescyclerViewUrdu();
    }

    private void setUpRescyclerViewPashto() {
        arrayList = new ArrayList<>();
        homePageData = new HomePageModel("ګزاره مرسته", R.color.orange,
                R.drawable.ic_children);
        arrayList.add(homePageData);
        homePageData = new HomePageModel("واده مرسته", R.color.yellow50,
                R.drawable.ic_basketball);
        arrayList.add(homePageData);
        homePageData = new HomePageModel("تعليمى ګامونه (عمومي)", R.color.blue700,
                R.drawable.ic_abc_block);
        arrayList.add(homePageData);
        homePageData = new HomePageModel("تعليمى ګامونه (هنري)", R.color.orange,
                R.drawable.ic_graduation_cap);
        arrayList.add(homePageData);
        homePageData = new HomePageModel("دينى مدرسې", R.color.yellow50,
                R.drawable.ic_mosque);
        arrayList.add(homePageData);
        homePageData = new HomePageModel("روغتیایی پاملرنه", R.color.blue700,
                R.drawable.ic_health);
        arrayList.add(homePageData);

        mRecyclerView = findViewById(R.id.resyclerView);
//        GridLayoutManager mGridLayoutManager = new GridLayoutManager(ZakatSchemes.this, 2);
//        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


//        mRecyclerView = findViewById(R.id.resyclerView);
////        GridLayoutManager mGridLayoutManager = new GridLayoutManager(MainActivity.this, 2);
////        mRecyclerView.setLayoutManager(mGridLayoutManager);
        MainPageAdapterPashto myAdapter3 = new MainPageAdapterPashto(ZakatSchemes.this, arrayList);
        mRecyclerView.setAdapter(myAdapter3);
    }

    private void setUpRescyclerViewUrdu() {
        arrayList = new ArrayList<>();
        homePageData = new HomePageModel("گزارا الاؤنس", R.color.orange,
                R.drawable.ic_children);
        arrayList.add(homePageData);
        homePageData = new HomePageModel("شادی الاؤنس", R.color.yellow50,
                R.drawable.ic_basketball);
        arrayList.add(homePageData);
        homePageData = new HomePageModel("تعلیمی وظائف (عام)", R.color.blue700,
                R.drawable.ic_abc_block);
        arrayList.add(homePageData);
        homePageData = new HomePageModel("تعلیمی وظیفہ (ٹیکنیکل)", R.color.orange,
                R.drawable.ic_graduation_cap);
        arrayList.add(homePageData);
        homePageData = new HomePageModel("دینی مدارس", R.color.yellow50,
                R.drawable.ic_mosque);
        arrayList.add(homePageData);
        homePageData = new HomePageModel("صحت کی دیکھ بال", R.color.blue700,
                R.drawable.ic_health);
        arrayList.add(homePageData);

        mRecyclerView = findViewById(R.id.resyclerView);
//        GridLayoutManager mGridLayoutManager = new GridLayoutManager(ZakatSchemes.this, 2);
//        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


//        mRecyclerView = findViewById(R.id.resyclerView);
////        GridLayoutManager mGridLayoutManager = new GridLayoutManager(MainActivity.this, 2);
////        mRecyclerView.setLayoutManager(mGridLayoutManager);
        MainPageAdapterUrdu myAdapter2 = new MainPageAdapterUrdu(ZakatSchemes.this, arrayList);
        mRecyclerView.setAdapter(myAdapter2);
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
        mediaPlayer.start();
        onBackPressed();
        return true;
    }

}
