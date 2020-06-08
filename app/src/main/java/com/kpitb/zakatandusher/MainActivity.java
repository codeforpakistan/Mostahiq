package com.kpitb.zakatandusher;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.android.material.navigation.NavigationView;
import com.kpitb.zakatandusher.Adapter.MainAdapter;
import com.kpitb.zakatandusher.Modal.HomePageModel;
import com.kpitb.zakatandusher.utility.CustomTextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private NavigationView navigationView;
    private DrawerLayout drawer;
    private View navHeader;
    Toolbar mToolbar;
    private HomePageModel homePageData;
    private RecyclerView mRecyclerView;
    private ArrayList<HomePageModel> arrayList;
    int MENU_GUZARA_ALLOWNCE = Menu.FIRST;
    int MENU_MARRIAGE_ASSITANCE_GRANT = Menu.FIRST + 1;
    int MENU_EDUCATIONAL_GRANT = Menu.FIRST + 2;
    int MENU_EDUCATIONAL_GRANT_II = Menu.FIRST + 3;
    int MENU_DEENI_MADARIS = Menu.FIRST + 4;
    int MENU_HEALTH_CARE = Menu.FIRST + 5;
    Dialog IntroDialog;
    public static String MY_PREFS_NAME = "shaki_boy";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        Boolean introFlag = prefs.getBoolean("IntroFlag", true);//"No name defined" is the default value.


        if(introFlag){
            ShowDialog();
        }


        initViews();
        setUpRescyclerView();


//        setUpLocationRescyclerView();

        Menu menu = navigationView.getMenu();
        menu.add(0, MENU_GUZARA_ALLOWNCE, Menu.NONE, "Districts Information").setIcon(R.drawable.ic_children);
        menu.add(0, MENU_MARRIAGE_ASSITANCE_GRANT, Menu.NONE, "Provincial Hospitals Information").setIcon(R.drawable.ic_basketball);
        menu.add(0, MENU_EDUCATIONAL_GRANT, Menu.NONE, "Zakat Schemes Information").setIcon(R.drawable.ic_abc_block);

        setUpNavigationView();
    }

    private void setUpLocationRescyclerView() {
        arrayList = new ArrayList<>();
        homePageData = new HomePageModel("Abottabad", R.color.orange,
                R.drawable.ic_children);
        arrayList.add(homePageData);
        homePageData = new HomePageModel("Bannu", R.color.yellow50,
                R.drawable.ic_basketball);
        arrayList.add(homePageData);
        homePageData = new HomePageModel("Buner", R.color.blue700,
                R.drawable.ic_abc_block);
        arrayList.add(homePageData);
        homePageData = new HomePageModel("Battagram", R.color.greenA700,
                R.drawable.ic_graduation_cap);
        arrayList.add(homePageData);
        homePageData = new HomePageModel("Chitral", R.color.mosque,
                R.drawable.ic_mosque);
        arrayList.add(homePageData);
        homePageData = new HomePageModel("Charsadda", R.color.main_pending,R.drawable.ic_doctor);
        arrayList.add(homePageData);

        mRecyclerView = findViewById(R.id.location_resyclerView);
//         mGridLayoutManager = new GridLayoutManager(MainActivity.this, 2);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        MainAdapter myAdapter = new MainAdapter(MainActivity.this, arrayList);
        mRecyclerView.setAdapter(myAdapter);
    }

    private void initViews() {
        mToolbar = findViewById(R.id.app_bar);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view1);
        navHeader = navigationView.getHeaderView(0);
        mRecyclerView = findViewById(R.id.resyclerView);

    }

    private void setUpNavigationView() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                return true;
            }
        });

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, mToolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        drawer.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();
    }

    private void setUpRescyclerView() {

        arrayList = new ArrayList<>();
        homePageData = new HomePageModel("Districts Information", R.color.orange,
                R.drawable.ic_address);
        arrayList.add(homePageData);

        homePageData = new HomePageModel("Provincial Hospitals Information", R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(homePageData);

        homePageData = new HomePageModel("Zakat Schemes Information", R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(homePageData);

        mRecyclerView = findViewById(R.id.resyclerView);
//         mGridLayoutManager = new GridLayoutManager(MainActivity.this, 2);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        MainAdapter myAdapter = new MainAdapter(MainActivity.this, arrayList);
        mRecyclerView.setAdapter(myAdapter);

    }

    public void ShowDialog() {
        IntroDialog = new Dialog(MainActivity.this, android.R.style.Theme_Black_NoTitleBar);
        IntroDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(100, 0, 0, 0)));
        IntroDialog.setContentView(R.layout.introduction_dialog);
        IntroDialog.setCancelable(true);
        IntroDialog.show();


        ImageView closeDialog = IntroDialog.findViewById(R.id.dialog_close);
        RelativeLayout neverAsk = IntroDialog.findViewById(R.id.btn_never_ask);
        RelativeLayout next = IntroDialog.findViewById(R.id.btn_next);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntroDialog.dismiss();
                ShowDialogAppIntro();
            }
        });

        neverAsk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putBoolean("IntroFlag", false);
                editor.apply();
                IntroDialog.dismiss();
            }
        });

        closeDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntroDialog.dismiss();
            }
        });
    }

    private void ShowDialogAppIntro() {
        IntroDialog = new Dialog(MainActivity.this, android.R.style.Theme_Black_NoTitleBar);
        IntroDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(100, 0, 0, 0)));
        IntroDialog.setContentView(R.layout.introduction_dialog_app);
        IntroDialog.setCancelable(true);
        IntroDialog.show();


        ImageView closeDialog = IntroDialog.findViewById(R.id.dialog_close);
        RelativeLayout neverAsk = IntroDialog.findViewById(R.id.btn_never_ask);
        RelativeLayout next = IntroDialog.findViewById(R.id.btn_next);
        CustomTextView Content = IntroDialog.findViewById(R.id.content_text);
        CustomTextView labelButton = IntroDialog.findViewById(R.id.lbl_btn);

        labelButton.setText("Close");

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntroDialog.dismiss();
            }
        });

        neverAsk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putBoolean("IntroFlag", false);
                editor.apply();
                IntroDialog.dismiss();
            }
        });

        closeDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntroDialog.dismiss();
            }
        });
    }
}