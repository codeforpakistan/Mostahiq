package com.kpitb.zakatandusher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kpitb.zakatandusher.utility.CustomTextView;

public class GuzaraAllownce extends AppCompatActivity {

    TextView translate_btn;
    LinearLayout guzara_parent_layout;
    FrameLayout my_frame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guzara_allownce);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

//        //TRANSLATION
//        translate_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (translate_btn.getText().equals("Translate to Urdu"))
//                {
//                    LayoutInflater inflater = (LayoutInflater)getApplicationContext()
//                            .getSystemService (Context.LAYOUT_INFLATER_SERVICE);
//                    final View view = inflater.inflate(R.layout.guzara_allowance_urdu, null);
//                    my_frame.addView(view);
//                    my_frame.setVisibility(View.VISIBLE);
//
//
//                    final TextView translate_btn_eng = (TextView)view.findViewById(R.id.translate_btn_eng);
//                    CustomTextView download_form = (CustomTextView)view.findViewById(R.id.download_form);
//                    translate_btn_eng.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            if (translate_btn_eng.getText().equals("Translate to English"))
//                            {
//                                guzara_parent_layout.removeView(view);
//                                my_frame.setVisibility(View.GONE);
//                            }
//                        }
//                    });
//
//                    download_form.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            DownloadForm(my_frame);
//                        }
//                    });
//                }
//            }
//        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void DownloadForm(View view){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://swkpk.gov.pk/wp-content/uploads/2018/01/Guzara_Allow-Form.pdf"));
        startActivity(browserIntent);
    }
}
