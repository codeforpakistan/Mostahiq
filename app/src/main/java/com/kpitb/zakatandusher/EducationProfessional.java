package com.kpitb.zakatandusher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.kpitb.zakatandusher.utility.CustomTextView;

public class EducationProfessional extends AppCompatActivity {
    CustomTextView t_eng;
    CustomTextView t_urdu,btn_urdu,btn_eng;
    LinearLayout intro_eng,intro_urdu,eligibility_eng,eligibility_urdu,apply_eng,apply_urdu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education_professional);

        initViews();
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
        intro_eng = findViewById(R.id.intro_eng);
        intro_urdu = findViewById(R.id.intro_urdu);
        eligibility_eng = findViewById(R.id.eligility_eng);
        eligibility_urdu = findViewById(R.id.eligibility_urdu);
        apply_eng = findViewById(R.id.h_t_apply_eng);
        apply_urdu = findViewById(R.id.how_to_apply_urdu);
        btn_eng = findViewById(R.id.btn_eng);
        btn_urdu = findViewById(R.id.btn_urdu);
    }

    public void DownloadForm(View view){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://swkpk.gov.pk/wp-content/uploads/2018/01/Form-4%20Educational%20Stipends%20(Technical).pdf"));
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
