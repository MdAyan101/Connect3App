package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;


public class MainActivity extends AppCompatActivity {
    // 1 : yellow  0 : red  2 : empty

   int activePlayer = 1;

   boolean gameActive = true;

   boolean gameWon = false;

   int[] gameState = {2,2,2,2,2,2,2,2,2};

   int[][] winningPositions = {{0,1,2} , {3,4,5} , {6,7,8}, {0,3,6} ,{1,4,7} ,{2,5,8}, {0,4,8} ,{2,4,6}};

   public static  int c=0;


    public  void fadeDown(View view)
    {
        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(c==9)
        {
            gameActive=false;
        }

        if(gameState[tappedCounter] == 2 && gameActive) {
            c++;

            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-2500);

            if (activePlayer == 1) {
                counter.setImageResource(R.drawable.yellow);

                activePlayer = 0;
            } else {
                counter.setImageResource(R.drawable.red);

                activePlayer = 1;
            }

            counter.animate().translationYBy(2500).rotation(3600).setDuration(700);

            String winner;

            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {

                    c=0;

                    gameActive = false;

                    gameWon = true;

                    if (gameState[winningPosition[0]] == 0)
                        winner = "Red";
                    else
                        winner = "Yellow";

                    TextView winnerTextView =  findViewById(R.id.winnerTextView);
                    Button playButton =  findViewById(R.id.playButton);

                    winnerTextView.setText(winner + " has won!");
                    playButton.setVisibility(View.VISIBLE);
                    winnerTextView.setVisibility(View.VISIBLE);
                }
            }
            if(!gameWon && c==9){

                winner = "Game has been drawn.";
                TextView winnerTextView =  findViewById(R.id.winnerTextView);
                Button playButton =  findViewById(R.id.playButton);

                winnerTextView.setText(winner);
                playButton.setVisibility(View.VISIBLE);
                winnerTextView.setVisibility(View.VISIBLE);
            }

        }
    }

    public void playAgain(View view){
        TextView winnerTextView =  findViewById(R.id.winnerTextView);
        Button playButton =  findViewById(R.id.playButton);

        playButton.setVisibility(View.INVISIBLE);
        winnerTextView.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = findViewById(R.id.gridLayout);

        for(int i=0 ; i <gridLayout.getChildCount(); i++)
        {
            ImageView counter = (ImageView) gridLayout.getChildAt(i);

            counter.setImageDrawable(null);
        }

        Arrays.fill(gameState, 2);

        activePlayer = 1;
        c=0;
        gameWon = false;
        gameActive = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}