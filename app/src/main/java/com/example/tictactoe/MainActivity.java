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
    //Helps keep track of when a win condition should be triggered.
    private int count;
    //Removes the magical == 9 from down below
    //Basically if the count ever hits 9 that means a player has 5 and the other has 4 without
    //any win conditions coming through.
    private final  int tie = 9;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Establishing all of the buttons and the button array that I use later.
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

        //Setting default values of player count and the starting players turn.
        player = "X";
        count = 0;
        currTurn = findViewById(R.id.playerTurnTextView);

        resetGame();
    }

    private void resetGame()
    {
        //Reduced down redundant code and made it into a for loop.
        //That sets all the buttons to being re-enabled and blank text boxes
        //when the NEW GAME button is pushed.
        for(int i = 0; i < BUTTONS.length; i++)
        {
            BUTTONS[i].setText("");
            BUTTONS[i].setEnabled(true);
        }

        player = "X";
        count = 0;
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

        //Makes sure that whatever button was clicked on is an actual empty box and doesn't have
        //any symbols in it.
        if(tempButton.getText() == (""))
        {
            //Checks what players turn it is and if the player has set a win condition yet.
            if(player == ("X"))
            {
                tempButton.setText("X");
                if(!winCond())
                {
                    player = "O";
                }
            }
            //Same as above but its for player O
            else
            {
                tempButton.setText("O");
                if(!winCond())
                {
                    player = "X";
                }
            }
            //Removed the button disable line here.
            //tempButton.setEnabled(false);
            count++;
            //The count 5 is because 5 is the minimum amount of turns in order to win.
            //X O X O X -> X wins for example.
            if(count >= 5 && winCond())
            {
                currTurn.setText("Player " + player + " wins!");
            }
            //If no win then its other players turn.
            else
            {
                currTurn.setText("Player " + player + "'s turn");
            }
        }



        //If tie is reached then the game is a deadlock and it will say tie game and prevent players
        //from changing any values in the buttons.
        if(count == tie)
        {
            currTurn.setText("Tie game!");
        }

        //If the view ever becomes newGame then it will reset all tiles to blank and become
        //x's turn.
        if (v == newGameButton)
        {
            resetGame();
        }
    }

    //Simple method to determine if a winner has been selected yet or not.
    private boolean winCond()
    {
        //Made a string array to help clean up the big if statement below.
        String[] buttonArray = {buttonTL.getText().toString(),buttonTM.getText().toString(),buttonTR.getText().toString(),buttonML.getText().toString(),buttonMM.getText().toString(),
                buttonMR.getText().toString(),buttonBL.getText().toString(),buttonBM.getText().toString(),buttonBR.getText().toString()};
        Button[] buttons = {buttonTL,buttonTM,buttonTR,buttonML,buttonMM,buttonMR,buttonBL,buttonBM,buttonBR};
        //A very ugly if statement that I was thinking of making a switch that basically runs through each of the 8 win conditions and then also checks
        //that the spaces aren't blank for the win condition otherwise X would always win from the start.
        if((buttonArray[0]== buttonArray[1] && buttonArray[1] == buttonArray[2]) && (buttonArray[0] != "" || buttonArray[1] != "" || buttonArray[2] != "") ||
                (buttonArray[3] == buttonArray[4] && buttonArray[4] == buttonArray[5]) && (buttonArray[3] != "" || buttonArray[4] != "" || buttonArray[5] != "") ||
                (buttonArray[6] == buttonArray[7] && buttonArray[7] == buttonArray[8]) && (buttonArray[6] != "" || buttonArray[7] != "" || buttonArray[8] != "") ||
                (buttonArray[0] == buttonArray[3] && buttonArray[3] == buttonArray[6]) && (buttonArray[0] != "" || buttonArray[3] != "" || buttonArray[6] != "") ||
                (buttonArray[1] == buttonArray[4] && buttonArray[4] == buttonArray[7]) && (buttonArray[1] != "" || buttonArray[4] != "" || buttonArray[7] != "") ||
                (buttonArray[2] == buttonArray[5] && buttonArray[5] == buttonArray[8]) && (buttonArray[2] != "" || buttonArray[5] != "" || buttonArray[8] != "") ||
                (buttonArray[0] == buttonArray[4] && buttonArray[4] == buttonArray[8]) && (buttonArray[0] != "" || buttonArray[4] != "" || buttonArray[8] != "") ||
                (buttonArray[2] == buttonArray[4] && buttonArray[4] == buttonArray[6]) && (buttonArray[2] != "" || buttonArray[4] != "" || buttonArray[6] != ""))
        {
            //I disable the buttons here and not in the tie because if I don't the player who won is still allowed
            //to fill out the board so this prevents that from happening.
            for(int i = 0; i < buttons.length; i++)
            {
                buttons[i].setEnabled(false);
            }
            return true;
        }
        return false;
    }
}