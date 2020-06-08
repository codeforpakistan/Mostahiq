package com.kpitb.zakatandusher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.tabs.TabLayout;
import com.kpitb.zakatandusher.Adapter.ViewPagerAdapter;
import com.kpitb.zakatandusher.Fragments.EducationGeneralEnglish;
import com.kpitb.zakatandusher.Fragments.EducationGeneralUrdu;
import com.kpitb.zakatandusher.utility.CustomTextView;

public class EducationGeneral extends AppCompatActivity {
    ViewPager mProfileViewPager;
    TabLayout mOrderTabs;
    CustomTextView t_eng;
    CustomTextView t_urdu,btn_urdu,btn_eng;
    LinearLayout intro_eng,intro_urdu,eligibility_eng,eligibility_urdu,apply_eng,apply_urdu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education_general);

        initViews();

//        mProfileViewPager = findViewById(R.id.profile_view_pager);
//        mOrderTabs = findViewById(R.id.profile_tabs);
//        setUpViewPager();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void initViews() {
        t_urdu = findViewById(R.id.translate_urdu);
        t_eng = findViewById(R.id.translate_english);
        intro_eng = findViewById(R.id.engIntro);
        intro_urdu = findViewById(R.id.urdu_intro);
        eligibility_eng = findViewById(R.id.elgibelity_criteria_eng);
        eligibility_urdu = findViewById(R.id.elgibelity_criteria_Urdu);
        apply_eng = findViewById(R.id.how_to_apply_eng);
        apply_urdu = findViewById(R.id.how_to_apply_urdu);
        btn_eng = findViewById(R.id.btn_eng);
        btn_urdu = findViewById(R.id.btn_urdu);
    }

    public void setUpViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new EducationGeneralEnglish(), "English");
        adapter.addFragment(new EducationGeneralUrdu(), "اردو");
//        adapter.addFragment(new ExperienceFragment(), "Experiences");
        mProfileViewPager.setAdapter(adapter);
        mOrderTabs.setupWithViewPager(mProfileViewPager);
        mProfileViewPager.post(new Runnable() {
            @Override
            public void run() {
                mProfileViewPager.setCurrentItem(0);
            }
        });
    }

    public void DownloadForm(View view){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://swkpk.gov.pk/wp-content/uploads/2018/01/Form-2%20Educational%20Stipends%20(Colleges,%20Universities%20etc).pdf"));
        startActivity(browserIntent);
    }

    public void TranslateToEnglish(View view){
        intro_urdu.setVisibility(View.GONE);
        eligibility_urdu.setVisibility(View.GONE);
        apply_urdu.setVisibility(View.GONE);
        t_eng.setVisibility(View.GONE);
        btn_urdu.setVisibility(View.GONE);

        t_urdu.setVisibility(View.VISIBLE);
        intro_eng.setVisibility(View.VISIBLE);
        eligibility_eng.setVisibility(View.VISIBLE);
        apply_eng.setVisibility(View.VISIBLE);
        btn_eng.setVisibility(View.VISIBLE);
    }

    public void TranslateToUrdu(View view){
        t_urdu.setVisibility(View.GONE);
        intro_eng.setVisibility(View.GONE);
        eligibility_eng.setVisibility(View.GONE);
        apply_eng.setVisibility(View.GONE);
        btn_eng.setVisibility(View.GONE);

        intro_urdu.setVisibility(View.VISIBLE);
        eligibility_urdu.setVisibility(View.VISIBLE);
        apply_urdu.setVisibility(View.VISIBLE);
        t_eng.setVisibility(View.VISIBLE);
        btn_urdu.setVisibility(View.VISIBLE);
    }
}