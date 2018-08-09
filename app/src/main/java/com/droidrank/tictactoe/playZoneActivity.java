package com.droidrank.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.HashMap;

public class playZoneActivity extends AppCompatActivity
{
    Button restart, stats;
    Button[] buttons = new Button[9];
    TextView result;
    Spinner level_select;

    HashMap<Integer, Integer> map = new HashMap<>();
    int currentBoardStatus = 0;
    int chance = 0, winX = 0, winO = 0, draw = 0;
    Boolean anyResult = false;
    String p1="O",p2="X";
    String choice[][] = new String[3][3];
    String mode;

    private int progressStatus=0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mode = getIntent().getStringExtra("modeID");
        setContentView(R.layout.play_zone);

        level_select = (Spinner) findViewById(R.id.levelselect);

        for(int i=0;i<9;i++)
        {
            int temp = 2131230740;
            buttons[i] = (Button) findViewById(temp + (i));
        }

        result = (TextView) findViewById(R.id.tv_show_result);
        restart = (Button) findViewById(R.id.bt_restart_game);
        stats = (Button) findViewById(R.id.see_graphs);

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
                currentBoardStatus = 0;
                chance=0;
                anyResult=false;
                result.setText("");

                for(int i=0;i<9;i++)
                {
                    buttons[i].setText("");
                    buttons[i].setTextColor(getResources().getColor(R.color.white));
                }

                for(int i=0;i<3;i++)
                    for(int j=0;j<3;j++)
                        choice[i][j]="";
            }
        });

        stats.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent nextScreen = new Intent(getApplicationContext() ,statsActivity.class);
                nextScreen.putExtra("XwinID",winX+"");
                nextScreen.putExtra("OwinID",winO+"");
                nextScreen.putExtra("drawID",draw+"");
                startActivity(nextScreen);
            }
        });

    }
    public int[] checkWin()
    {
        boolean hasWon = false;
        int[] resultArray = new int[3];
        resultArray[0] = 4; //default value for continue play

        //index 0
        //1- p1 wins-O
        //2- p2 wins-X
        //3- draw
        //4- continue playing

        //index 1
        //1- row
        //2- column
        //3- left-diagonal
        //4- right-diagonal
        //-1 for draw, continue play

        //index 2
        //value of row or column
        //-1 for diagonal, draw, continue play

        if(!hasWon)
        {
            //check rows
            for (int i = 0; i < 3; i++) {
                if (choice[i][0].equals(choice[i][1]) && choice[i][1].equals(choice[i][2]) && !choice[i][0].equals(""))
                    if (choice[i][0].equals(p1)) {
                        int tempArray[] = {1, 1, i};
                        resultArray = tempArray;
                        hasWon = true;
                    } else {
                        int tempArray[] = {2, 1, i};
                        resultArray = tempArray;
                        hasWon = true;
                    }
            }
        }

        if(!hasWon) {
            //check columns
            for (int i = 0; i < 3; i++) {
                if (choice[0][i].equals(choice[1][i]) && choice[1][i].equals(choice[2][i]) && !choice[0][i].equals(""))
                    if (choice[0][i].equals(p1)) {
                        int tempArray[] = {1, 2, i};
                        resultArray = tempArray;
                        hasWon = true;
                    } else {
                        int tempArray[] = {2, 2, i};
                        resultArray = tempArray;
                        hasWon = true;
                    }
            }
        }

        if(!hasWon) {
            //check left diagonal
            if (choice[0][0].equals(choice[1][1]) && choice[1][1].equals(choice[2][2]) && !choice[0][0].equals(""))
                if (choice[0][0].equals(p1)) {
                    int tempArray[] = {1, 3, -1};
                    resultArray = tempArray;
                    hasWon = true;
                } else {
                    int tempArray[] = {2, 3, -1};
                    resultArray = tempArray;
                    hasWon = true;
                }
        }

        if(!hasWon) {
            //check right diagonal
            if (choice[0][2].equals(choice[1][1]) && choice[1][1].equals(choice[2][0]) && !choice[0][2].equals(""))
                if (choice[0][2].equals(p1)) {
                    int tempArray[] = {1, 4, -1};
                    resultArray = tempArray;
                    hasWon = true;
                } else {
                    int tempArray[] = {2, 4, -1};
                    resultArray = tempArray;
                    hasWon = true;
                }
        }

        // draw
        if(chance>=9 && !hasWon)
        {
            int tempArray[] = {3, -1, -1};
            resultArray = tempArray;
        }

        return resultArray;
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

    public void highlightWin(int[] response)
    {
        //row
        if(response[1]==1)
        {
            int rowVal = response[2];
            for(int i=0;i<3;i++)
                buttons[3*rowVal + i].setTextColor(getResources().getColor(R.color.myred));
        }

        //column
        else if(response[1]==2)
        {
            int colVal = response[2];
            for(int i=0;i<3;i++)
                buttons[colVal + 3*i].setTextColor(getResources().getColor(R.color.myred));
        }

        //left-diagonal
        else if(response[1]==3)
        {
            buttons[0].setTextColor(getResources().getColor(R.color.myred));
            buttons[4].setTextColor(getResources().getColor(R.color.myred));
            buttons[8].setTextColor(getResources().getColor(R.color.myred));
        }

        //right-diagonal
        else if(response[1]==4)
        {
            buttons[2].setTextColor(getResources().getColor(R.color.myred));
            buttons[4].setTextColor(getResources().getColor(R.color.myred));
            buttons[6].setTextColor(getResources().getColor(R.color.myred));
        }
    }

    public void moveMade()
    {
        int response[] = checkWin();
        if (response[0] == 1)
        {
            map.put(currentBoardStatus, 0);
            result.setText("O wins");
            winO++;
            anyResult=true;
            highlightWin(response);
        }
        else if (response[0] == 2)
        {
            map.put(currentBoardStatus, 1);
            result.setText("X wins");
            winX++;
            anyResult=true;
            highlightWin(response);
        }
        else if (response[0] == 3)
        {
            map.put(currentBoardStatus, 2);
            result.setText("It's a draw");
            draw++;
            anyResult=true;
        }
    }

    /*
    00 - 000000001
    01 - 000000010
    02 - 000000100
    10 - 000001000
    11 - 000010000
    12 - 000100000
    20 - 001000000
    21 - 010000000
    22 - 100000000
     */

    public void pressed(View view)
    {
        final Button b = (Button) findViewById(view.getId());
        int temp = 2131230740;
        int id = b.getId() - temp;
        int i = id / 3;
        int j = id % 3;
        if(result.getText().equals("") && b.getText().equals(""))
        {
            if (mode.equals("multi") && anyResult==false)
            {
                if (chance % 2 == 0 && b.getText().equals(""))
                {
                    //2 for 0
                    currentBoardStatus+= 2*Math.pow(10,j)*Math.pow(10,3*i);
                    b.setText(p1);
                    choice[i][j] = p1;
                    chance++;
                }
                else if (chance % 2 == 1 && b.getText().equals(""))
                {
                    //1 for X
                    currentBoardStatus+= 1*Math.pow(10,j)*Math.pow(10,3*i);
                    b.setText(p2);
                    choice[i][j] = p2;
                    chance++;
                }
                moveMade();
            }
            else if (mode.equals("single") && anyResult==false)
            {
                if (chance % 2 == 0 && b.getText().equals(""))
                {
                    //2 for 0
                    currentBoardStatus+= 2*Math.pow(10,j)*Math.pow(10,3*i);
                    b.setText(p1);
                    choice[i][j] = p1;
                    chance++;
                    moveMade();
                }

                if (chance <= 8 && anyResult==false)
                {
                    int[] arr = computerPlay();
                    final Button computer = (Button) findViewById(temp + arr[0] * 3 + arr[1]);
                    computer.setText(p2);
                    choice[arr[0]][arr[1]] = p2;
                    //1 for X
                    currentBoardStatus+= 1*Math.pow(10,arr[1])*Math.pow(10,3*arr[0]);
                    chance++;
                    moveMade();
                }
            }
        }
    }

}
