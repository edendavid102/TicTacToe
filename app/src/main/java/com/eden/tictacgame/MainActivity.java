package com.eden.tictacgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView title;
    String status;
    ImageButton[] btn = new ImageButton[10];
    Button startBtn;
    String[] squares = new String[10];
    int stepNumber = 0;

   // class Board implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = findViewById(R.id.mainActivity_title);
        startBtn = findViewById(R.id.mainActivity_start_btn);
        btn[1] = findViewById(R.id.mainActivity_btn1);
        btn[2] = findViewById(R.id.mainActivity_btn2);
        btn[3] = findViewById(R.id.mainActivity_btn3);
        btn[4] = findViewById(R.id.mainActivity_btn4);
        btn[5] = findViewById(R.id.mainActivity_btn5);
        btn[6] = findViewById(R.id.mainActivity_btn6);
        btn[7] = findViewById(R.id.mainActivity_btn7);
        btn[8] = findViewById(R.id.mainActivity_btn8);
        btn[9] = findViewById(R.id.mainActivity_btn9);

        for(int i=1; i < 10 ; i++){
            btn[i].setOnClickListener(this);
            btn[i].setClickable(false);
        }


       startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=1;i<10;i++){
                    btn[i].setImageResource(R.drawable.empty);
                    squares[i]=null;
                    stepNumber=0;
                    btn[i].setClickable(true);
                    startBtn.setText("Play Again");
                    title.setText("X Play");

                }
            }
        });

    }
    @Override
    public void onClick(View v) {
        int tag = Integer.parseInt(v.getTag().toString());

        if (stepNumber%2==0) {
            btn[tag].setImageResource(R.drawable.x);
            squares[tag] = "X";
            status = "O Play";

        }
        else {
            btn[tag].setImageResource(R.drawable.o);
            squares[tag] = "O";
            status = "X Play";
        }
        if(stepNumber==8){
            status = "No Winner";
        }

        if(calculateWinner(squares)!= null) {
            status = "The Winner is : " + calculateWinner(squares).toString();
            //title.setTextSize(40);
            startBtn.setText("play again");
            for(int i=1;i<10;i++){
                btn[i].setClickable(false);
            }
        }
        title.setText(status);
        btn[tag].setClickable(false);
        stepNumber++;

    }

    public String calculateWinner(String[] squares) {
        int[][] lines = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
                {1, 4, 7},
                {2, 5, 8},
                {3, 6, 9},
                {1, 5, 9},
                {3, 5, 7},
        };

        for ( int i = 0; i < lines.length; i++) {
            int a = lines[i][0];
            int b= lines[i][1];
            int c = lines[i][2];
            if (squares[a]!=null && squares[a] == squares[b] && squares[a] == squares[c]) {
                return squares[a];
            }
        }
        return null;
    }

    }


