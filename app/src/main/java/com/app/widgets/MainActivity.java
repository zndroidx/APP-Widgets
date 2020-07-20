package com.app.widgets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.app.widgets.impl.ExpandableTextViewActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void jump(Class c) {
        startActivity(new Intent(this, c));
    }

    public void ExpandableTextView(View view) {
        jump(ExpandableTextViewActivity.class);
    }
}
