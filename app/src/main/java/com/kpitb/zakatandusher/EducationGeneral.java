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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education_general);

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

    public void DownloadForm(View view){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://swkpk.gov.pk/wp-content/uploads/2018/01/Form-2%20Educational%20Stipends%20(Colleges,%20Universities%20etc).pdf"));
        startActivity(browserIntent);
    }

}