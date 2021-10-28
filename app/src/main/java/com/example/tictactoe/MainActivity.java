package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity
{
    private Button[] BUTTONS;
    private Button buttonTL;
    private Button buttonTM;
    private Button buttonTR;
    private Button buttonML;
    private Button buttonMM;
    private Button buttonMR;
    private Button buttonBL;
    private Button buttonBM;
    private Button buttonBR;
    private Button newGameButton;

    private String player;
    private TextView currTurn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        buttonTL = findViewById(R.id.buttonTL);
        buttonTM = findViewById(R.id.buttonTM);
        buttonTR = findViewById(R.id.buttonTR);
        buttonML = findViewById(R.id.buttonML);
        buttonMM = findViewById(R.id.buttonMM);
        buttonMR = findViewById(R.id.buttonMR);
        buttonBL = findViewById(R.id.buttonBL);
        buttonBM = findViewById(R.id.buttonBM);
        buttonBR = findViewById(R.id.buttonBR);
        BUTTONS = new Button[] {buttonTL,buttonTM,buttonTR,buttonML,buttonMM,buttonMR,buttonBL,buttonBM,buttonBR};
        newGameButton = findViewById(R.id.newGameButton);

        player = "X";
        currTurn = findViewById(R.id.playerTurnTextView);

        resetGame();
    }

    private void resetGame()
    {
        //Reduced down redundant code and made it into a for loop.
        for(int i = 0; i < BUTTONS.length; i++)
        {
            BUTTONS[i].setText("");
            BUTTONS[i].setEnabled(true);
        }

        player = "X";
        currTurn.setText("Player X's turn");
    }

    public void onClick(View v)
    {
        //Created an array of buttons like you had mentioned towards the end of class.
        Button[] buttonArray = {buttonTL,buttonTM,buttonTR,buttonML,buttonMM,buttonMR,buttonBL,buttonBM,buttonBR};
        //Tried to make it just a new button constructor of new Button() but for some reason it didn't like that.
        Button tempButton = buttonTL;
        //Runs through the array and finds whatever button was clicked on and sets temp to be that button.
        for(int i = 0; i < buttonArray.length; i++)
        {
            if(v.equals(buttonArray[i]))
            {
                tempButton = buttonArray[i];
            }
        }

        if(tempButton.getText() == (""))
        {
            if(player == ("X"))
            {
                tempButton.setText("X");
                player = "O";
            }
            else
            {
                tempButton.setText("O");
                player = "X";
            }
            //tempButton.setEnabled(false);

            currTurn.setText("Player " + player + "'s turn");
        }

        if (v == newGameButton)
        {
            resetGame();
        }
    }
}