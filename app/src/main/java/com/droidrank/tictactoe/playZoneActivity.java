package com.droidrank.tictactoe;

import android.os.Bundle;
import android.os.SystemClock;
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
    String choice[][] = new String[3][3];
    String mode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mode = getIntent().getStringExtra("modeID");
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

        //setting up default value
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                choice[i][j]="";

        if(mode.equals("single"))
        {
            level_select.setPrompt("Select Difficulty");
            level_select.setVisibility(View.VISIBLE);
        }
        else if(mode.equals("multi"))
            level_select.setVisibility(View.GONE);

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                chance=0;
                result.setText("");
                block1.setText("");
                block2.setText("");
                block3.setText("");
                block4.setText("");
                block5.setText("");
                block6.setText("");
                block7.setText("");
                block8.setText("");
                block9.setText("");

                for(int i=0;i<3;i++)
                    for(int j=0;j<3;j++)
                        choice[i][j]="";
            }
        });

    }
    public int checkWin()
    {
        //1- p1 wins-O
        //2- p2 wins-X
        //3- draw
        //4- continue playing
        //check rows
        for(int i=0;i<3;i++)
        {
            if(choice[i][0].equals(choice[i][1]) && choice[i][1].equals(choice[i][2]) && !choice[i][0].equals(""))
                if (choice[i][0].equals(p1))
                    return 1;
                else
                    return 2;
        }
        //check columns
        for(int i=0;i<3;i++)
        {
            if(choice[0][i].equals(choice[1][i]) && choice[1][i].equals(choice[2][i]) && !choice[0][i].equals(""))
                if (choice[0][i].equals(p1))
                    return 1;
                else
                    return 2;
        }
        //check left diagonal
        if(choice[0][0].equals(choice[1][1]) && choice[1][1].equals(choice[2][2]) && !choice[0][0].equals(""))
            if (choice[0][0].equals(p1))
                return 1;
            else
                return 2;

        //check right diagonal
        if(choice[0][2].equals(choice[1][1]) && choice[1][1].equals(choice[2][0]) && !choice[0][2].equals(""))
            if (choice[0][2].equals(p1))
                return 1;
            else
                return 2;

        if(chance>=9)
            return 3;

        return 4;
    }

    public int[] computerPlay()
    {
        int x,y;
        do {
            double posX = Math.random() * 3;
            double posY = Math.random() * 3;
            x = (int)Math.floor(posX);
            y = (int)Math.floor(posY);
        }while(!choice[x][y].equals(""));

        int arr[]={x,y};
        return arr;
    }

    public void moveMade()
    {
        int response = checkWin();
        if (response == 1)
            result.setText("O wins");
        else if (response == 2)
            result.setText("X wins");
        else if (response == 3)
            result.setText("It's a draw");
    }

    public void pressed(View view)
    {
        if(result.getText().equals("")) {
            if (mode.equals("multi")) {
                int temp = 2131230740;
                final Button b = (Button) findViewById(view.getId());
                int id = b.getId() - temp;
                int i = id / 3;
                int j = id % 3;
                if (chance % 2 == 0 && b.getText().equals("")) {
                    b.setText(p1);
                    choice[i][j] = p1;
                    chance++;
                } else if (chance % 2 == 1 && b.getText().equals("")) {
                    b.setText(p2);
                    choice[i][j] = p2;
                    chance++;
                }
                int response = checkWin();
                if (response == 1)
                    result.setText("O wins");
                else if (response == 2)
                    result.setText("X wins");
                else if (response == 3)
                    result.setText("It's a draw");
            } else if (mode.equals("single")) {
                int temp = 2131230740;
                final Button b = (Button) findViewById(view.getId());
                int id = b.getId() - temp;
                int i = id / 3;
                int j = id % 3;
                if (chance % 2 == 0 && b.getText().equals("")) {
                    b.setText(p1);
                    choice[i][j] = p1;
                    chance++;
                }

                moveMade();

                if (chance <= 8)
                {
                    SystemClock.sleep(200);
                    int[] arr = computerPlay();
                    final Button computer = (Button) findViewById(temp + arr[0] * 3 + arr[1]);
                    computer.setText(p2);
                    choice[arr[0]][arr[1]] = p2;
                    chance++;
                }
                moveMade();
            }
        }
    }

}
