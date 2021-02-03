package com.kpitb.zakatandmustahiq.utility;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

public class CustomTextViewForMainPage extends AppCompatTextView {
    public CustomTextViewForMainPage(Context context) {
        super(context);
        setFont();
    }
    public CustomTextViewForMainPage(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFont();
    }
    public CustomTextViewForMainPage(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setFont();
    }

    private void setFont() {
        Typeface font = Typeface.createFromAsset(getContext().getAssets(), "fonts/Epilogue-Bold.ttf");
        setTypeface(font, Typeface.BOLD);
    }
}
