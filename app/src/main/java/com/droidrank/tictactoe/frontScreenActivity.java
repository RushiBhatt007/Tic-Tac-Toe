package com.droidrank.tictactoe;

import android.support.v7.app.*;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class frontScreenActivity extends AppCompatActivity
{
    Button single,multi;
    String Single="single",Multi="multi";

    protected void onCreate(final Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.front_screen);

        single = (Button)findViewById(R.id.single_player);
        multi = (Button)findViewById(R.id.multi_player);

        multi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent nextScreen = new Intent(getApplicationContext() ,playZoneActivity.class);
                nextScreen.putExtra("modeID",Multi);
                startActivity(nextScreen);
            }
        });

        single.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent nextScreen = new Intent(getApplicationContext() ,playZoneActivity.class);
                nextScreen.putExtra("modeID",Single);
                startActivity(nextScreen);
            }
        });
    }
}
