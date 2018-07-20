package com.droidrank.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{
    Button single,multi;

    protected void onCreate(final Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.front_screen);

        single = (Button)findViewById(R.id.single_player);
        multi = (Button)findViewById(R.id.multi_player);

        multi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent nextscreen = new Intent(getApplicationContext() ,play_zone.class);
                startActivity(nextscreen);
            }
        });
    }


}
