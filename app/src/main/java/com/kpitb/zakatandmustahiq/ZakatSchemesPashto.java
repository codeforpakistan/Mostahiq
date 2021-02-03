package com.kpitb.zakatandmustahiq;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kpitb.zakatandmustahiq.Adapter.MainPageAdapter;
import com.kpitb.zakatandmustahiq.Adapter.MainPageAdapterPashto;
import com.kpitb.zakatandmustahiq.Adapter.MainPageAdapterUrdu;
import com.kpitb.zakatandmustahiq.Modal.HomePageModel;

import java.util.ArrayList;

public class ZakatSchemesPashto extends AppCompatActivity {
    private HomePageModel homePageData;
    private RecyclerView mRecyclerView;
    private ArrayList<HomePageModel> arrayList;

    private ImageView language_chnge;

    private boolean flagmale = false;
    private boolean flagfemale = false;
    private boolean flagfemale3 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zakat_scheme);

        Toolbar toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

 /*       language_chnge = findViewById(R.id.change_language);
        language_chnge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setUpRescyclerViewUrdu();

                DisplayMetrics metrics = getResources().getDisplayMetrics();
                int width = metrics.widthPixels;
                int height = metrics.heightPixels;

                final Dialog dialog = new Dialog(ZakatSchemesPashto.this);
                dialog.setContentView(R.layout.custom_dialog);
                final RadioButton radioUrdu = (RadioButton)dialog.findViewById(R.id.lang1);
                final RadioButton radioPashto = (RadioButton)dialog.findViewById(R.id.lang2);
                final RadioButton radioEng = (RadioButton)dialog.findViewById(R.id.lang3);
                dialog.getWindow().setLayout((6 * width)/7, ActionBar.LayoutParams.WRAP_CONTENT);

                radioUrdu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
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
                        if (radioUrdu.isChecked() == true)
                        {
                            setUpRescyclerViewUrdu();
                            dialog.dismiss();
                        }
                        else if (radioPashto.isChecked() == true)
                        {
                            setUpRescyclerViewPashto();
                            dialog.dismiss();
                        }
                        else {
                            setUpRescyclerView();
                            dialog.dismiss();
                        }

                    }
                });

                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });*/

        //show English language list view by default
        setUpRescyclerViewPashto();
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
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        MainPageAdapterPashto myAdapter3 = new MainPageAdapterPashto(ZakatSchemesPashto.this, arrayList);
        mRecyclerView.setAdapter(myAdapter3);
    }

    private void setUpRescyclerViewUrdu() {
        arrayList = new ArrayList<>();
        homePageData = new HomePageModel("گزارا الاؤنس", R.color.orange,
                R.drawable.ic_children);
        arrayList.add(homePageData);
        homePageData = new HomePageModel("شادی کی مدد", R.color.yellow50,
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
        MainPageAdapterUrdu myAdapter2 = new MainPageAdapterUrdu(ZakatSchemesPashto.this, arrayList);
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
        MainPageAdapter myAdapter = new MainPageAdapter(ZakatSchemesPashto.this, arrayList);
        mRecyclerView.setAdapter(myAdapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
