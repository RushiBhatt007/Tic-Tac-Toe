package com.droidrank.tictactoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class statsActivity extends AppCompatActivity
{
    PieChart pieChart;
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

        float perX = ((float) winX/(winX+winO+draw))*100;
        float perO = ((float)winO/(winX+winO+draw))*100;
        float perDraw = ((float)draw/(winX+winO+draw))*100;

        pieChart = (PieChart) findViewById(R.id.graph);

        ArrayList<PieEntry> yVal =  new ArrayList<>();
        yVal.add(new PieEntry(perX,"Win of X"));
        yVal.add(new PieEntry(perO,"Win of Y"));
        yVal.add(new PieEntry(perDraw,"Draw"));

        PieDataSet pieDataSet = new PieDataSet(yVal, "Tic-Tac-Toe Game Results");

        PieData pieData = new PieData(pieDataSet);

        // In Percentage
        pieData.setValueFormatter(new PercentFormatter());
        // Default value
        //data.setValueFormatter(new DefaultValueFormatter(0));
        pieChart.setData(pieData);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setTransparentCircleRadius(58f);

        pieChart.setHoleRadius(58f);
        pieDataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        pieChart.invalidate();
    }
}
