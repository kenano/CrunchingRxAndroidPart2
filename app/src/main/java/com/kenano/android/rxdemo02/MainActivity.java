package com.kenano.android.rxdemo02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.kenano.android.rxdemo02.part1.Part1Activity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
    }

    @Override
    public void onClick(View view) {
        startActivity(getLaunchIntentFor(view.getId()));
    }

    private void initUI() {
        initTapListener(R.id.btnPart1);
    }

    private void initTapListener(int viewId) {
        this.findViewById(viewId).setOnClickListener(this);
    }

    private Intent getLaunchIntentFor(int viewId){
        Intent launchIntent = null;
        switch (viewId){
            case R.id.btnPart1:
                launchIntent = new Intent(this, Part1Activity.class);
                break;
        }

        return launchIntent;
    }
}
