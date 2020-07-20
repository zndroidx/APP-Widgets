package com.app.widgets.impl;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.app.widgets.R;
import com.zndroid.widget.ExpandableTextView;

public class ExpandableTextViewActivity extends AppCompatActivity {

    ExpandableTextView expandableTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_text_view);

        expandableTextView = findViewById(R.id.expand_text_view);
        expandableTextView.setText(getString(R.string.dummy_text));
    }
}