package com.droidrank.tictactoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class playZoneActivity extends AppCompatActivity
{
    Button block1, block2, block3, block4, block5, block6, block7, block8, block9, restart;
    TextView result;
    Spinner level_select;
    int chance = 0;
    String p1="O",p2="X";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String mode = getIntent().getStringExtra("modeID");
        setContentView(R.layout.play_zone);

        level_select = (Spinner) findViewById(R.id.levelselect);
        block1 = (Button) findViewById(R.id.bt_block1);
        block2 = (Button) findViewById(R.id.bt_block2);
        block3 = (Button) findViewById(R.id.bt_block3);
        block4 = (Button) findViewById(R.id.bt_block4);
        block5 = (Button) findViewById(R.id.bt_block5);
        block6 = (Button) findViewById(R.id.bt_block6);
        block7 = (Button) findViewById(R.id.bt_block7);
        block8 = (Button) findViewById(R.id.bt_block8);
        block9 = (Button) findViewById(R.id.bt_block9);
        result = (TextView) findViewById(R.id.tv_show_result);
        restart = (Button) findViewById(R.id.bt_restart_game);

        if(mode.equals("single"))
        {
            level_select.setPrompt("Select Difficulty");
            level_select.setVisibility(View.VISIBLE);
        }
        else if(mode.equals("multi"))
            level_select.setVisibility(View.GONE);

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

    }
    public void pressed(View view)
    {
        final Button b = (Button)findViewById( view.getId() );

        if( chance%2 == 0 && b.getText().equals(""))
            b.setText(p1);
        else if( chance%2 == 1 && b.getText().equals(""))
            b.setText(p2);
        chance++;
    }

}
