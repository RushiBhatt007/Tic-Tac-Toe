package com.droidrank.tictactoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class statsActivity extends AppCompatActivity
{
    int winX,winO,draw;
    @Override
    public void onCreate(Bundle savedInstance)
    {
        super.onCreate(savedInstance);
        String tempX = getIntent().getStringExtra("XwinID");
        String tempO = getIntent().getStringExtra("OwinID");
        String tempDraw = getIntent().getStringExtra("drawID");

        setContentView(R.layout.stats_screen);

        if(tempX=="")
            tempX="0";
        if(tempO=="")
            tempO="0";
        if(tempDraw=="")
            tempDraw="0";

        winX = Integer.parseInt(tempX);
        winO = Integer.parseInt(tempO);
        draw = Integer.parseInt(tempDraw);

        TextView textView = (TextView) findViewById(R.id.textview);
        textView.setText("WIN OF X IS "+winX+"\n"+"WIN OF O IS "+winO+"\n"+"DRAW IS "+draw+"\n");
    }
}
