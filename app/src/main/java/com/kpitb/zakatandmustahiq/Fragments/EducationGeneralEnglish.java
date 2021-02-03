package com.kpitb.zakatandmustahiq.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kpitb.zakatandmustahiq.R;

import androidx.fragment.app.Fragment;


public class EducationGeneralEnglish extends Fragment {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor spEditor;
    String Label;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_education_general, container, false);
        //initialize your UI
        return v;
    }

}
