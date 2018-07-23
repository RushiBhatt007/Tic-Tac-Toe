package com.droidrank.tictactoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

public class statsActivity extends AppCompatActivity
{
    LineChart lineChart;
    int winX,winO,draw;
    int colors[]= {R.color.myred, R.color.myblue, R.color.myyellow };
    @Override
    public void onCreate(Bundle savedInstance)
    {
        super.onCreate(savedInstance);
        String tempX = getIntent().getStringExtra("XwinID");
        String tempO = getIntent().getStringExtra("OwinID");
        String tempDraw = getIntent().getStringExtra("drawID");

        setContentView(R.layout.stats_screen);

        winX = Integer.parseInt(tempX);
        winO = Integer.parseInt(tempO);
        draw = Integer.parseInt(tempDraw);

        lineChart = (LineChart) findViewById(R.id.graph);

        List<Entry> entries = new ArrayList<Entry>();
        entries.add(new Entry(0f,winX));
        entries.add(new Entry(1f,winO));
        entries.add(new Entry(2f,draw));

        LineDataSet lineDataSet = new LineDataSet(entries,"Statistical representation of result");
        lineDataSet.setColors(colors, getApplicationContext());
        lineDataSet.setCubicIntensity(100);
        lineDataSet.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
        lineDataSet.setDrawFilled(true);

        LineData lineData = new LineData(lineDataSet);

        XAxis xAxis = new XAxis();
        YAxis yAxis = new YAxis();
        xAxis.setEnabled(false);
        yAxis.setEnabled(false);
        xAxis.setDrawAxisLine(false);
        yAxis.setDrawAxisLine(false);
        xAxis.setDrawGridLines(false);
        yAxis.setDrawGridLines(false);

        lineChart.getXAxis().setEnabled(false);
        lineChart.getAxisRight().setDrawGridLines(false);
        lineChart.getAxisLeft().setDrawAxisLine(false);
        lineChart.getXAxis().setDrawGridLines(false);
        lineChart.getXAxis().setDrawAxisLine(false);

        lineChart.setData(lineData);
        lineChart.animateXY(2000,2000);
        lineChart.invalidate(); // refresh
    }
}
