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
    }

    public void onChosen(View view)
    {
        final Button button = (Button) findViewById(view.getId());
        Intent nextScreen = new Intent(getApplicationContext() ,playZoneActivity.class);
        if(button.getId() == R.id.single_player)
            nextScreen.putExtra("modeID",Single);

        else if(button.getId() == R.id.multi_player)
            nextScreen.putExtra("modeID",Multi);
        startActivity(nextScreen);
    }
}
