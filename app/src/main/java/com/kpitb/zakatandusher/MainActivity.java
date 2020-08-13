package com.kpitb.zakatandusher;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.kpitb.zakatandusher.Adapter.MainAdapter;
import com.kpitb.zakatandusher.Modal.HomePageModel;
import com.kpitb.zakatandusher.Modal.MainPageModel;
import com.kpitb.zakatandusher.utility.CustomTextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private NavigationView navigationView;
    private DrawerLayout drawer;
    private View navHeader;
    Toolbar mToolbar;
    private MainPageModel mainPageModel;
    private RecyclerView mRecyclerView;
    private ArrayList<MainPageModel> arrayList;
    int MENU_GUZARA_ALLOWNCE = Menu.FIRST;
    int MENU_MARRIAGE_ASSITANCE_GRANT = Menu.FIRST + 1;
    int MENU_EDUCATIONAL_GRANT = Menu.FIRST + 2;
    int MENU_EDUCATIONAL_GRANT_II = Menu.FIRST + 3;
    int MENU_DEENI_MADARIS = Menu.FIRST + 4;
    int MENU_HEALTH_CARE = Menu.FIRST + 5;
    Dialog IntroDialog;
    public static String MY_PREFS_NAME = "shaki_boy";

    LinearLayout drawer_layout;
    View my_View;
    FrameLayout my_frame_view;

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

        mainPageModel = new MainPageModel("Zakat Schemes Information","زکوٰۃ سکیموں کی معلومات", R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(mainPageModel);

        mainPageModel = new MainPageModel("Provincial Hospitals Information","صوبائی اسپتالوں کی معلومات",
                R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(mainPageModel);

        mainPageModel = new MainPageModel("Districts Information","اضلاع سے متعلق معلومات",
                R.color.orange,
                R.drawable.ic_address);
        arrayList.add(mainPageModel);

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

        RelativeLayout neverAsk = IntroDialog.findViewById(R.id.btn_never_ask);
        final RelativeLayout next = IntroDialog.findViewById(R.id.btn_next);

        final LinearLayout middle_layout = IntroDialog.findViewById(R.id.middle_layout);
        final RelativeLayout relativeLayout = IntroDialog.findViewById(R.id.my_relative);
        final ScrollView field_text = IntroDialog.findViewById(R.id.field_text);
        //my_frame_view = IntroDialog.findViewById(R.id.my_frame_view);
        final TextView eng_lang, urdu_lang;
        eng_lang = IntroDialog.findViewById(R.id.eng);
        urdu_lang = IntroDialog.findViewById(R.id.urdu);

        urdu_lang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                eng_lang.setBackgroundResource(0);
                eng_lang.setTextColor(getResources().getColor(android.R.color.tab_indicator_text));

                urdu_lang.setBackgroundResource(R.drawable.language_bg);
                urdu_lang.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimaryDark));

                LayoutInflater inflater = (LayoutInflater) getApplicationContext()
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                my_View = inflater.inflate(R.layout.intro_in_urdu, null);
                relativeLayout.addView(my_View);
                relativeLayout.setVisibility(View.VISIBLE);
                field_text.setVisibility(View.GONE);
            }
        });

        eng_lang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (relativeLayout.getParent() != null)
                {
                    relativeLayout.removeView(my_View);
                }
                //drawer_layout.removeView(my_View);
                relativeLayout.setVisibility(View.GONE);
                field_text.setVisibility(View.VISIBLE);

                urdu_lang.setBackgroundResource(0);
                urdu_lang.setTextColor(getResources().getColor(android.R.color.tab_indicator_text));

                eng_lang.setBackgroundResource(R.drawable.language_bg);
                eng_lang.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimaryDark));
            }
        });

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
    }
}