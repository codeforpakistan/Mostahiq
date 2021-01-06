package com.kpitb.zakattandusherr;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.Animator;
import android.app.ActionBar;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.kpitb.zakattandusherr.Adapter.MainAdapterEnglish;
import com.kpitb.zakattandusherr.Adapter.MainAdapterPashto;
import com.kpitb.zakattandusherr.Adapter.MainAdapterUrdu;
import com.kpitb.zakattandusherr.Modal.MainPageModelEnglish;
import com.kpitb.zakattandusherr.Modal.MainPageModelPashto;
import com.kpitb.zakattandusherr.Modal.MainPageModelUrdu;
import com.kpitb.zakattandusherr.utility.CustomTextView;
import com.kpitb.zakattandusherr.utility.CustomTextViewForMainPage;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MAIN_DASHBOARD";

    private NavigationView navigationView;
    private DrawerLayout drawer;
    private View navHeader;
    Toolbar mToolbar;
    private MainPageModelUrdu mainPageModelUrdu;
    private RecyclerView mRecyclerView;
    private ArrayList<MainPageModelUrdu> arrayList;

    public static final int MENU_GUZARA_ALLOWNCE = Menu.FIRST;
    public static final int MENU_MARRIAGE_ASSITANCE_GRANT = Menu.FIRST + 1;
    public static final int MENU_EDUCATIONAL_GRANT = Menu.FIRST + 2;
    public static final int MENU_EDUCATIONAL_GRANT_II = Menu.FIRST + 3;
    public static final int MENU_DEENI_MADARIS = Menu.FIRST + 4;
    public static final int MENU_HEALTH_CARE = Menu.FIRST + 5;
    public static final int LANGUAGE = Menu.FIRST + 6;
    public static final int CREDIT = Menu.FIRST + 7;
    public static final int EXIT = Menu.FIRST + 8;

    Dialog IntroDialog;
    public static String MY_PREFS_NAME = "shaki_boy";

    LinearLayout drawer_layout;
    View my_View;
    FrameLayout my_frame_view;

    private ImageView language_chnge;
    private boolean flagmale = false;
    private boolean flagfemale = false;
    private boolean flagfemale3 = false;

    private MainPageModelPashto mainPageModelPashto;
    private ArrayList<MainPageModelPashto> arrayListPashto;

    private MainPageModelEnglish mainPageModelEnglish;
    private ArrayList<MainPageModelEnglish> arrayListEnglish;

    private AlphaAnimation buttonClickAnimation = new AlphaAnimation(1F, 0.8F);

    ImageView img_close_drawer, change_lang;

    private FirebaseAnalytics firebaseAnalytics;

    String btnNAME, value;

    private boolean flagUrdu = false;
    private boolean flagEng = false;
    private boolean flagPashto = false;

    MediaPlayer mediaPlayer;

    private CustomTextViewForMainPage tvTitle,txtComplaint,txtComplaintUrdu,txtComplaintPashto;
    private CustomTextViewForMainPage tvTitleUrd, tvTitlePst;
    private RelativeLayout view;
    private View separator,separator2,separator3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAnalytics = FirebaseAnalytics.getInstance(this);

        mediaPlayer = MediaPlayer.create(this,R.raw.click_sound);

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        Boolean introFlag = prefs.getBoolean("IntroFlag", true);//"No name defined" is the default value.

        if(introFlag){
            ShowDialog();
        }

        initViews();

        //BY DEFAULT SHOW LIST IN URDU
        setUpRescyclerViewUrdu();
        txtComplaintUrdu.setVisibility(View.VISIBLE);

        Menu menu = navigationView.getMenu();
        menu.add(0, MENU_EDUCATIONAL_GRANT, Menu.NONE, "زکوٰۃ اسکیمیں").setIcon(R.drawable.zakat_schemz_icon);
        menu.add(0, MENU_MARRIAGE_ASSITANCE_GRANT, Menu.NONE, "صوبائی ہسپتال").setIcon(R.drawable.hspital_icon);
        menu.add(0, MENU_GUZARA_ALLOWNCE, Menu.NONE, "ضلعی زکوٰۃ دفاتر").setIcon(R.drawable.izla_icon);
        menu.add(0, LANGUAGE, Menu.NONE,"ایپ کی زبان تبدیل کریں").setIcon(R.drawable.translate_icon);
        menu.add(0, CREDIT, Menu.NONE,"کریڈٹ").setIcon(R.drawable.credits);
        menu.add(0, EXIT, Menu.NONE,"باہر نکلیں").setIcon(R.drawable.exit_icon);

        setUpNavigationView();

        language_chnge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setUpRescyclerViewUrdu();
                mediaPlayer.start();
                YoYo.with(Techniques.Landing)
                        .duration(200)
                        .repeat(0)
                        .playOn(language_chnge);
                DisplayMetrics metrics = getResources().getDisplayMetrics();
                int width = metrics.widthPixels;
                int height = metrics.heightPixels;

                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.language_dialog_two);
                final RadioButton radioUrdu = (RadioButton)dialog.findViewById(R.id.lang1);
                final RadioButton radioPashto = (RadioButton)dialog.findViewById(R.id.lang2);
                final RadioButton radioEng = (RadioButton)dialog.findViewById(R.id.lang3);
                dialog.getWindow().setLayout((6 * width)/7, ActionBar.LayoutParams.WRAP_CONTENT);

                radioUrdu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mediaPlayer.start();
                        if (radioUrdu.isChecked())
                        {
                            if (!flagmale)
                            {
                                radioUrdu.setChecked(true);
                                radioPashto.setChecked(false);
                                radioEng.setChecked(false);
                                flagmale = true;
                                flagfemale = false;
                                flagfemale3 = false;
                            }
                            else {
                                flagmale = false;
                                radioUrdu.setChecked(false);
                                radioPashto.setChecked(false);
                                radioEng.setChecked(false);
                            }
                        }
                    }
                });

                radioPashto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mediaPlayer.start();
                        if (radioPashto.isChecked())
                        {
                            if (!flagfemale)
                            {
                                radioPashto.setChecked(true);
                                radioUrdu.setChecked(false);
                                radioEng.setChecked(false);
                                flagmale = false;
                                flagfemale = true;
                                flagfemale3 = false;
                            }
                            else {
                                flagfemale = false;
                                radioPashto.setChecked(false);
                                radioUrdu.setChecked(false);
                                radioEng.setChecked(false);
                            }
                        }
                    }
                });

                radioEng.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mediaPlayer.start();
                        if (radioEng.isChecked())
                        {
                            if (!flagfemale3)
                            {
                                radioEng.setChecked(true);
                                radioUrdu.setChecked(false);
                                radioPashto.setChecked(false);
                                flagmale = false;
                                flagfemale = false;
                                flagfemale3 = true;
                            }
                            else {
                                flagfemale3 = false;
                                radioEng.setChecked(false);
                                radioUrdu.setChecked(false);
                                radioPashto.setChecked(false);
                            }
                        }
                    }
                });

                Button btnOK = (Button)dialog.findViewById(R.id.btn_yes);
                Button btnCancel = (Button)dialog.findViewById(R.id.btn_no);

                btnOK.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mediaPlayer.start();
                        if (radioUrdu.isChecked() == true)
                        {
                            txtComplaintUrdu.setVisibility(View.VISIBLE);
                            tvTitleUrd.setVisibility(View.VISIBLE);
                            tvTitle.setVisibility(View.INVISIBLE);
                            tvTitlePst.setVisibility(View.INVISIBLE);
                            txtComplaint.setVisibility(View.INVISIBLE);
                            txtComplaintPashto.setVisibility(View.INVISIBLE);
                            separator.setVisibility(View.VISIBLE);
                            separator2.setVisibility(View.INVISIBLE);
                            separator3.setVisibility(View.INVISIBLE);
                            setUpRescyclerViewUrdu();
                            navigationView.getMenu().clear();
                            Menu menu = navigationView.getMenu();
                            menu.add(0, MENU_EDUCATIONAL_GRANT, Menu.NONE, "زکوٰۃ اسکیمیں").setIcon(R.drawable.zakat_schemz_icon);
                            menu.add(0, MENU_MARRIAGE_ASSITANCE_GRANT, Menu.NONE, "صوبائی ہسپتال").setIcon(R.drawable.hspital_icon);
                            menu.add(0, MENU_GUZARA_ALLOWNCE, Menu.NONE, "ضلعی زکوٰۃ دفاتر").setIcon(R.drawable.izla_icon);
                            menu.add(0, LANGUAGE, Menu.NONE,"ایپ کی زبان تبدیل کریں").setIcon(R.drawable.translate_icon);
                            menu.add(0, CREDIT, Menu.NONE,"کریڈٹ").setIcon(R.drawable.credits);
                            menu.add(0, EXIT, Menu.NONE,"باہر نکلیں").setIcon(R.drawable.exit_icon);
                            dialog.dismiss();
                        }
                        else if (radioPashto.isChecked() == true)
                        {
                            txtComplaintUrdu.setVisibility(View.INVISIBLE);
                            txtComplaint.setVisibility(View.INVISIBLE);
                            txtComplaintPashto.setVisibility(View.VISIBLE);
                            tvTitleUrd.setVisibility(View.INVISIBLE);
                            tvTitle.setVisibility(View.INVISIBLE);
                            tvTitlePst.setVisibility(View.VISIBLE);
                            separator.setVisibility(View.INVISIBLE);
                            separator2.setVisibility(View.VISIBLE);
                            separator3.setVisibility(View.INVISIBLE);
                            setUpRescyclerViewPashto();
                            navigationView.getMenu().clear();
                            Menu menu = navigationView.getMenu();
                            menu.add(0, MENU_EDUCATIONAL_GRANT, Menu.NONE, "د زکات پلانونه").setIcon(R.drawable.zakat_schemz_icon);
                            menu.add(0, MENU_MARRIAGE_ASSITANCE_GRANT, Menu.NONE, "ولايتي روغتون").setIcon(R.drawable.hspital_icon);
                            menu.add(0, MENU_GUZARA_ALLOWNCE, Menu.NONE, "د زکات دفترونه").setIcon(R.drawable.izla_icon);
                            menu.add(0, LANGUAGE, Menu.NONE,"د ایپ ژبه بدل کړئ").setIcon(R.drawable.translate_icon);
                            menu.add(0, CREDIT, Menu.NONE,"کریډیټونه").setIcon(R.drawable.credits);
                            menu.add(0, EXIT, Menu.NONE,"وتون").setIcon(R.drawable.exit_icon);
                            dialog.dismiss();
                        }
                        else {
                            txtComplaintUrdu.setVisibility(View.INVISIBLE);
                            txtComplaint.setVisibility(View.VISIBLE);
                            txtComplaintPashto.setVisibility(View.INVISIBLE);
                            tvTitleUrd.setVisibility(View.INVISIBLE);
                            tvTitle.setVisibility(View.VISIBLE);
                            tvTitlePst.setVisibility(View.INVISIBLE);
                            separator.setVisibility(View.INVISIBLE);
                            separator2.setVisibility(View.INVISIBLE);
                            separator3.setVisibility(View.VISIBLE);
                            setUpRescyclerViewEnglish();
                            navigationView.getMenu().clear();
                            Menu menu = navigationView.getMenu();
                            menu.add(0, MENU_EDUCATIONAL_GRANT, Menu.NONE, "Zakat Schemes").setIcon(R.drawable.zakat_schemz_icon);
                            menu.add(0, MENU_MARRIAGE_ASSITANCE_GRANT, Menu.NONE, "Provincial Hospitals").setIcon(R.drawable.hspital_icon);
                            menu.add(0, MENU_GUZARA_ALLOWNCE, Menu.NONE, "District Offices").setIcon(R.drawable.izla_icon);
                            menu.add(0, LANGUAGE, Menu.NONE,"Change App Language").setIcon(R.drawable.translate_icon);
                            menu.add(0, CREDIT, Menu.NONE,"Credits").setIcon(R.drawable.credits);
                            menu.add(0, EXIT, Menu.NONE,"Exit").setIcon(R.drawable.exit_icon);
                            dialog.dismiss();
                        }

                    }
                });

                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mediaPlayer.start();
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
    }

    private void initViews() {
        mToolbar = findViewById(R.id.app_bar);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view1);
        navHeader = navigationView.getHeaderView(0);
        mRecyclerView = findViewById(R.id.resyclerView);
        language_chnge = findViewById(R.id.change_language);
        img_close_drawer = (ImageView)navHeader.findViewById(R.id.close_drawer_btn);
        change_lang = (ImageView)navHeader.findViewById(R.id.change_lang);
        txtComplaint = findViewById(R.id.txtComplaint);
        txtComplaintUrdu = findViewById(R.id.txtComplaintUrdu);
        txtComplaintPashto = findViewById(R.id.txtComplaintPashto);
        view = findViewById(R.id.view);
        tvTitle = findViewById(R.id.tvTitle);
        tvTitleUrd = findViewById(R.id.tvTitleUrdu);
        tvTitlePst = findViewById(R.id.tvTitlePashto);
        separator = findViewById(R.id.separator);
        separator2 = findViewById(R.id.separator2);
        separator3 = findViewById(R.id.separator3);

        img_close_drawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                drawer.closeDrawers();
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String appPackageName = "com.govpk.citizensportal";
                try {
                    Intent appStoreIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName));
                    appStoreIntent.setPackage("com.android.vending");
                    startActivity(appStoreIntent);
                } catch (android.content.ActivityNotFoundException exception) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                }
            }
        });

     /*   tvTitleUrdu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String appPackageName = "com.govpk.citizensportal";
                try {
                    Intent appStoreIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName));
                    appStoreIntent.setPackage("com.android.vending");
                    startActivity(appStoreIntent);
                } catch (android.content.ActivityNotFoundException exception) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                }
            }
        });

        tvTitlePashto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String appPackageName = "com.govpk.citizensportal";
                try {
                    Intent appStoreIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName));
                    appStoreIntent.setPackage("com.android.vending");
                    startActivity(appStoreIntent);
                } catch (android.content.ActivityNotFoundException exception) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                }
            }
        });*/
    }

    private void setUpNavigationView() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case MENU_GUZARA_ALLOWNCE:
                        mediaPlayer.start();
                        Bundle params = new Bundle();
                        params.putInt("ButtonID",menuItem.getItemId());
                        btnNAME = "Districts";
                        setStatus("District_Info");
                        Log.d(TAG, "LOGZZZ: " + btnNAME);
                        firebaseAnalytics.logEvent(btnNAME,params);
                        if (menuItem.getTitle().equals("District Offices"))
                        {
                            startActivity(new Intent(MainActivity.this, DistrictsActivity.class));
                        } else if (menuItem.getTitle().equals("د زکات دفترونه"))
                        {
                            startActivity(new Intent(MainActivity.this, DistrictsActivityPashto.class));
                        } else if (menuItem.getTitle().equals("ضلعی زکوٰۃ دفاتر"))
                        {
                            startActivity(new Intent(MainActivity.this, DistrictsActivityUrdu.class));
                        }
                        break;
                    case MENU_MARRIAGE_ASSITANCE_GRANT:
                        mediaPlayer.start();
                        Bundle params2 = new Bundle();
                        params2.putInt("ButtonID",menuItem.getItemId());
                        btnNAME = "Hospitals";
                        setStatus("Hospitals_Info");
                        Log.d(TAG, "LOGZZZ: " + btnNAME);
                        firebaseAnalytics.logEvent(btnNAME,params2);
                        if (menuItem.getTitle().equals("Provincial Hospitals"))
                        {
                            startActivity(new Intent(MainActivity.this, ProvincialActivity.class));
                        } else if (menuItem.getTitle().equals("ولايتي روغتون"))
                        {
                            startActivity(new Intent(MainActivity.this, ProvincialActivityPashto.class));
                        } else if (menuItem.getTitle().equals("صوبائی ہسپتال"))
                        {
                            startActivity(new Intent(MainActivity.this, ProvincialActivityUrdu.class));
                        }
                        break;
                    case MENU_EDUCATIONAL_GRANT:
                        mediaPlayer.start();
                        Bundle params3 = new Bundle();
                        params3.putInt("ButtonID",menuItem.getItemId());
                        btnNAME = "Schemes";
                        setStatus("Schemes_Info");
                        Log.d(TAG, "LOGZZZ: " + btnNAME);
                        firebaseAnalytics.logEvent(btnNAME,params3);
                        if (menuItem.getTitle().equals("Zakat Schemes"))
                        {
                            Intent intent = new Intent(MainActivity.this,ZakatSchemes.class);
                            intent.putExtra("LANG","Zakat Schemes");
                            startActivity(intent);
                        } else if (menuItem.getTitle().equals("د زکات پلانونه"))
                        {
                            Intent intent = new Intent(MainActivity.this,ZakatSchemes.class);
                            intent.putExtra("LANG","د زکات پلانونه");
                            startActivity(intent);
                        } else if (menuItem.getTitle().equals("زکوٰۃ اسکیمیں"))
                        {
                            Intent intent = new Intent(MainActivity.this,ZakatSchemes.class);
                            intent.putExtra("LANG","زکوٰۃ اسکیمیں");
                            startActivity(intent);
                        }
                        break;
                    case CREDIT:
                        mediaPlayer.start();
                        Intent intent1 = new Intent(MainActivity.this,Credit_Activity.class);
                        startActivity(intent1);
                        break;
                    case EXIT:
                        finishAffinity();
                        System.exit(0);
                        break;
                    case LANGUAGE:
                        mediaPlayer.start();
                        DisplayMetrics metrics = getResources().getDisplayMetrics();
                        int width = metrics.widthPixels;
                        int height = metrics.heightPixels;

                        final Dialog dialog = new Dialog(MainActivity.this);
                        dialog.setContentView(R.layout.language_dialog_two);
                        final RadioButton radioUrdu = (RadioButton)dialog.findViewById(R.id.lang1);
                        final RadioButton radioPashto = (RadioButton)dialog.findViewById(R.id.lang2);
                        final RadioButton radioEng = (RadioButton)dialog.findViewById(R.id.lang3);
                        dialog.getWindow().setLayout((6 * width)/7, ActionBar.LayoutParams.WRAP_CONTENT);

                        radioUrdu.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mediaPlayer.start();
                                if (radioUrdu.isChecked())
                                {
                                    if (!flagUrdu)
                                    {
                                        radioUrdu.setChecked(true);
                                        radioPashto.setChecked(false);
                                        radioEng.setChecked(false);
                                        flagUrdu = true;
                                        flagEng = false;
                                        flagPashto = false;
                                    }
                                    else {
                                        flagUrdu = false;
                                        radioUrdu.setChecked(false);
                                        radioPashto.setChecked(false);
                                        radioEng.setChecked(false);
                                    }
                                }
                            }
                        });

                        radioPashto.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mediaPlayer.start();
                                if (radioPashto.isChecked())
                                {
                                    if (!flagPashto)
                                    {
                                        radioPashto.setChecked(true);
                                        radioUrdu.setChecked(false);
                                        radioEng.setChecked(false);
                                        flagUrdu = false;
                                        flagPashto = true;
                                        flagEng = false;
                                    }
                                    else {
                                        flagPashto = false;
                                        radioPashto.setChecked(false);
                                        radioUrdu.setChecked(false);
                                        radioEng.setChecked(false);
                                    }
                                }
                            }
                        });

                        radioEng.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mediaPlayer.start();
                                if (radioEng.isChecked())
                                {
                                    if (!flagEng)
                                    {
                                        radioEng.setChecked(true);
                                        radioUrdu.setChecked(false);
                                        radioPashto.setChecked(false);
                                        flagUrdu = false;
                                        flagPashto = false;
                                        flagEng = true;
                                    }
                                    else {
                                        flagEng = false;
                                        radioEng.setChecked(false);
                                        radioUrdu.setChecked(false);
                                        radioPashto.setChecked(false);
                                    }
                                }
                            }
                        });

                        Button btnOK = (Button)dialog.findViewById(R.id.btn_yes);
                        Button btnCancel = (Button)dialog.findViewById(R.id.btn_no);

                        btnOK.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mediaPlayer.start();
                                if (radioUrdu.isChecked() == true) {
                                    txtComplaintUrdu.setVisibility(View.VISIBLE);
                                    txtComplaint.setVisibility(View.INVISIBLE);
                                    txtComplaintPashto.setVisibility(View.INVISIBLE);
                                    tvTitleUrd.setVisibility(View.VISIBLE);
                                    tvTitle.setVisibility(View.INVISIBLE);
                                    tvTitlePst.setVisibility(View.INVISIBLE);
                                    separator.setVisibility(View.VISIBLE);
                                    separator2.setVisibility(View.INVISIBLE);
                                    separator3.setVisibility(View.INVISIBLE);
                                    navigationView.getMenu().clear();
                                    Menu menu = navigationView.getMenu();
                                    menu.add(0, MENU_EDUCATIONAL_GRANT, Menu.NONE, "زکوٰۃ اسکیمیں").setIcon(R.drawable.zakat_schemz_icon);
                                    menu.add(0, MENU_MARRIAGE_ASSITANCE_GRANT, Menu.NONE, "صوبائی ہسپتال").setIcon(R.drawable.hspital_icon);
                                    menu.add(0, MENU_GUZARA_ALLOWNCE, Menu.NONE, "ضلعی زکوٰۃ دفاتر").setIcon(R.drawable.izla_icon);
                                    menu.add(0, LANGUAGE, Menu.NONE,"ایپ کی زبان تبدیل کریں").setIcon(R.drawable.translate_icon);
                                    menu.add(0, CREDIT, Menu.NONE,"کریڈٹ").setIcon(R.drawable.credits);
                                    menu.add(0, EXIT, Menu.NONE,"باہر نکلیں").setIcon(R.drawable.exit_icon);
                                    setUpRescyclerViewUrdu();
                                    dialog.dismiss();
                                }
                                else if (radioPashto.isChecked() == true)
                                {
                                    txtComplaintUrdu.setVisibility(View.INVISIBLE);
                                    txtComplaint.setVisibility(View.INVISIBLE);
                                    txtComplaintPashto.setVisibility(View.VISIBLE);
                                    tvTitleUrd.setVisibility(View.INVISIBLE);
                                    tvTitle.setVisibility(View.INVISIBLE);
                                    tvTitlePst.setVisibility(View.VISIBLE);
                                    separator.setVisibility(View.INVISIBLE);
                                    separator2.setVisibility(View.VISIBLE);
                                    separator3.setVisibility(View.INVISIBLE);
                                    navigationView.getMenu().clear();
                                    Menu menu = navigationView.getMenu();
                                    menu.add(0, MENU_EDUCATIONAL_GRANT, Menu.NONE, "د زکات پلانونه").setIcon(R.drawable.zakat_schemz_icon);
                                    menu.add(0, MENU_MARRIAGE_ASSITANCE_GRANT, Menu.NONE, "ولايتي روغتون").setIcon(R.drawable.hspital_icon);
                                    menu.add(0, MENU_GUZARA_ALLOWNCE, Menu.NONE, "د زکات دفترونه").setIcon(R.drawable.izla_icon);
                                    menu.add(0, LANGUAGE, Menu.NONE,"د ایپ ژبه بدل کړئ").setIcon(R.drawable.translate_icon);
                                    menu.add(0, CREDIT, Menu.NONE,"کریډیټونه").setIcon(R.drawable.credits);
                                    menu.add(0, EXIT, Menu.NONE,"وتون").setIcon(R.drawable.exit_icon);
                                    setUpRescyclerViewPashto();
                                    dialog.dismiss();
                                }
                                else {
                                    txtComplaintUrdu.setVisibility(View.INVISIBLE);
                                    txtComplaint.setVisibility(View.VISIBLE);
                                    txtComplaintPashto.setVisibility(View.INVISIBLE);
                                    tvTitleUrd.setVisibility(View.INVISIBLE);
                                    tvTitle.setVisibility(View.VISIBLE);
                                    tvTitlePst.setVisibility(View.INVISIBLE);
                                    separator.setVisibility(View.INVISIBLE);
                                    separator2.setVisibility(View.INVISIBLE);
                                    separator3.setVisibility(View.VISIBLE);
                                    navigationView.getMenu().clear();
                                    Menu menu = navigationView.getMenu();
                                    menu.add(0, MENU_EDUCATIONAL_GRANT, Menu.NONE, "Zakat Schemes").setIcon(R.drawable.zakat_schemz_icon);
                                    menu.add(0, MENU_MARRIAGE_ASSITANCE_GRANT, Menu.NONE, "Provincial Hospitals").setIcon(R.drawable.hspital_icon);
                                    menu.add(0, MENU_GUZARA_ALLOWNCE, Menu.NONE, "District Offices").setIcon(R.drawable.izla_icon);
                                    menu.add(0, LANGUAGE, Menu.NONE,"Change App Language").setIcon(R.drawable.translate_icon);
                                    menu.add(0, CREDIT, Menu.NONE,"Credits").setIcon(R.drawable.credits);
                                    menu.add(0, EXIT, Menu.NONE,"Exit").setIcon(R.drawable.exit_icon);
                                    setUpRescyclerViewEnglish();
                                    dialog.dismiss();
                                }
                            }
                        });

                        btnCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mediaPlayer.start();
                                dialog.dismiss();
                            }
                        });
                        dialog.show();
                        break;
                }
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

    private void setStatus(String text) {
        value = text;
    }


    private void setUpRescyclerViewUrdu() {

        arrayList = new ArrayList<>();

        mainPageModelUrdu = new MainPageModelUrdu("زکوٰۃ اسکیمیں", R.color.blue700,
                R.drawable.zakat_schemz_icon);
        arrayList.add(mainPageModelUrdu);

        mainPageModelUrdu = new MainPageModelUrdu("صوبائی ہسپتال",
                R.color.yellow50,
                R.drawable.hspital_icon);
        arrayList.add(mainPageModelUrdu);

        mainPageModelUrdu = new MainPageModelUrdu("ضلعی زکوٰۃ دفاتر",
                R.color.orange,
                R.drawable.izla_icon);
        arrayList.add(mainPageModelUrdu);

        mRecyclerView = findViewById(R.id.resyclerView);
//         mGridLayoutManager = new GridLayoutManager(MainActivity.this, 2);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        MainAdapterUrdu myAdapter = new MainAdapterUrdu(MainActivity.this, arrayList);
        mRecyclerView.setAdapter(myAdapter);

    }

    private void setUpRescyclerViewPashto() {

        arrayListPashto = new ArrayList<>();

        mainPageModelPashto = new MainPageModelPashto("د زکات پلانونه", R.color.blue700,
                R.drawable.zakat_schemz_icon);
        arrayListPashto.add(mainPageModelPashto);

        mainPageModelPashto = new MainPageModelPashto("ولايتي روغتون",
                R.color.yellow50,
                R.drawable.hspital_icon);
        arrayListPashto.add(mainPageModelPashto);

        mainPageModelPashto = new MainPageModelPashto("د زکات دفترونه",
                R.color.orange,
                R.drawable.izla_icon);
        arrayListPashto.add(mainPageModelPashto);

        mRecyclerView = findViewById(R.id.resyclerView);
//         mGridLayoutManager = new GridLayoutManager(MainActivity.this, 2);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        MainAdapterPashto mainAdapterPashto = new MainAdapterPashto(MainActivity.this, arrayListPashto);
        mRecyclerView.setAdapter(mainAdapterPashto);

    }

    private void setUpRescyclerViewEnglish() {
        arrayListEnglish = new ArrayList<>();

        mainPageModelEnglish = new MainPageModelEnglish("Zakat Schemes", R.color.blue700,
                R.drawable.zakat_schemz_icon);
        arrayListEnglish.add(mainPageModelEnglish);

        mainPageModelEnglish = new MainPageModelEnglish("Provincial Hospitals",
                R.color.yellow50,
                R.drawable.hspital_icon);
        arrayListEnglish.add(mainPageModelEnglish);

        mainPageModelEnglish = new MainPageModelEnglish("District Offices",
                R.color.orange,
                R.drawable.izla_icon);
        arrayListEnglish.add(mainPageModelEnglish);

        mRecyclerView = findViewById(R.id.resyclerView);
//         mGridLayoutManager = new GridLayoutManager(MainActivity.this, 2);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        MainAdapterEnglish mainAdapterEnglish = new MainAdapterEnglish(MainActivity.this, arrayListEnglish);
        mRecyclerView.setAdapter(mainAdapterEnglish);

    }

    public void ShowDialog() {
        IntroDialog = new Dialog(MainActivity.this, android.R.style.Theme_Black_NoTitleBar);
        IntroDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(100, 0, 0, 0)));
        IntroDialog.setContentView(R.layout.introduction_dialog);
        IntroDialog.setCancelable(true);
        IntroDialog.show();

        RelativeLayout neverAsk = IntroDialog.findViewById(R.id.btn_never_ask);
        final RelativeLayout next = IntroDialog.findViewById(R.id.btn_next);

        final RelativeLayout relativeLayout = IntroDialog.findViewById(R.id.my_relative);
        final ScrollView field_text = IntroDialog.findViewById(R.id.field_text);
        final TextView eng_lang, urdu_lang;
        eng_lang = IntroDialog.findViewById(R.id.eng);
        urdu_lang = IntroDialog.findViewById(R.id.urdu);

        urdu_lang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                YoYo.with(Techniques.Landing)
                        .duration(200)
                        .repeat(0)
                        .playOn(urdu_lang);

                if (relativeLayout.getParent() != null)
                {
                    relativeLayout.removeView(my_View);
                }

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
                mediaPlayer.start();
                YoYo.with(Techniques.Landing)
                        .duration(200)
                        .repeat(0)
                        .playOn(eng_lang);
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
                //view.startAnimation(buttonClickAnimation);
                //IntroDialog.dismiss();
           mediaPlayer.start();
                YoYo.with(Techniques.Landing)
                        .duration(200)
                        .interpolate(new AccelerateDecelerateInterpolator())
                        .withListener(new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animator animation) {
                                IntroDialog.dismiss();
                            }

                            @Override
                            public void onAnimationCancel(Animator animation) {

                            }

                            @Override
                            public void onAnimationRepeat(Animator animation) {

                            }
                        }).playOn(next);
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