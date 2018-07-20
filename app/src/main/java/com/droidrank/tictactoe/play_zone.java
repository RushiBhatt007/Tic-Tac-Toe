package com.droidrank.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class play_zone extends AppCompatActivity
{
    Button block1, block2, block3, block4, block5, block6, block7, block8, block9, restart;
    TextView result;

    int chance = 0;
    String p1="O",p2="X";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        

        /**
         * Restarts the game
         */
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

    }
    public void pressed(View view)
    {
        Button b = (Button)findViewById( view.getId() );
        Intent i = new Intent(this,MainActivity.class);

        //Toast.makeText(getApplicationContext(),view.getId()+"",Toast.LENGTH_SHORT).show();
        if( chance%2 == 0 )
            b.setText(p1);
        else
            b.setText(p2);
        chance++;
    }

}
