package com.example.numberguessinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView tv_number, tv_hint, tv_roundno;
    EditText et_number;
    Button b_submit;
    ImageButton b_home;

    int round_no = 1;

    int comp_no = getCompNumber();
    int user_no;

    String difficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_number = findViewById(R.id.tv_number);
        tv_hint = findViewById(R.id.tv_hint);
        tv_roundno = findViewById(R.id.tv_roundno);

        et_number = findViewById(R.id.et_number);

        b_submit = findViewById(R.id.b_submit);
        b_home = findViewById(R.id.b_home);

        //Get the bundle
        Bundle bundle = getIntent().getExtras();

        //Extract the data…
        difficulty = bundle.getString("stuff");

        // Button for Submitting the Number entered
        b_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Getting user number
                user_no = Integer.parseInt(et_number.getText().toString());
                play_turn(user_no);

            }
        });

        b_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),Choosing_Difficulty.class);
                startActivity(intent);

            }
        });

    }

    public void play_turn(int user_no) {

        // Checking for win
        boolean win = checkNumber(user_no);

        if (win) {
            displayWin();
            playAgain();
        }
        else {
            displayHint();
        }

        //Post Round Text Modifications
        displayPostRound();

    }

    public int getCompNumber() {

        // Random Number between 1-100
        Random r = new Random();
        return r.nextInt(100) + 1;

    }

    public boolean checkNumber(int user_no) {

        return user_no == comp_no;
    }

    public void displayHint() {

        String hint_text = "";

        if (difficulty.equals("Easy")) {
            switch (round_no) {

                // Which Half
                case 1:
                    if (comp_no >= 50) {
                        hint_text = "The number is in the upper half.";
                    }
                    else {
                        hint_text = "The number is in the lower half.";
                    }
                    break;

                // Range of 10
                case 2:
                    if (comp_no >= 1 && comp_no <= 10) {
                        hint_text = "The number is in between 1-10 (inclusive).";
                    }
                    else if (comp_no >= 90 && comp_no <= 100) {
                        hint_text = "The number is in between 90-100 (inclusive).";
                    }
                    else {
                        int lower_limit = comp_no - (comp_no % 10);
                        int upper_limit = lower_limit + 10;
                        hint_text = "The number is in between " + lower_limit + "-" + upper_limit + " (inclusive).";
                    }
                    break;

                // Difference
                case 3:
                    int difference = user_no > comp_no ? user_no - comp_no : comp_no - user_no;
                    hint_text = "You are " + difference + " numbers away!";
                    break;

                // Number reveal
                case 4:
                    hint_text = "The number is: " + comp_no;
                    break;

                default:
                    hint_text = "No More Hints!";
                    break;

            }
        }

        else if (difficulty.equals("Medium")) {
            switch (round_no) {

                // Which Half
                case 1:
                    if (comp_no >= 50) {
                        hint_text = "The number is in the upper half.";
                    }
                    else {
                        hint_text = "The number is in the lower half.";
                    }
                    break;

                // Which Quartile
                case 2:
                    if (comp_no <= 25) {
                        hint_text = "The number is in the first quartile";
                    }
                    else if (comp_no <= 50) {
                        hint_text = "The number is in the second quartile";
                    }
                    else if (comp_no <= 75) {
                        hint_text = "The number is in the third quartile";
                    }
                    else if (comp_no <= 100) {
                        hint_text = "The number is in the fourth quartile";
                    }
                    break;

                // Range of 10
                case 3:
                    if (comp_no >= 1 && comp_no <= 10) {
                        hint_text = "The number is in between 1-10 (inclusive).";
                    }
                    else if (comp_no >= 90 && comp_no <= 100) {
                        hint_text = "The number is in between 90-100 (inclusive).";
                    }
                    else {
                        int lower_limit = comp_no - (comp_no % 10);
                        int upper_limit = lower_limit + 10;
                        hint_text = "The number is in between " + lower_limit + "-" + upper_limit + " (inclusive).";
                    }
                    break;

                // Range of 5
                case 4:

                    if (comp_no >= 1 && comp_no <= 5) {
                        hint_text = "The number is in between 1-5 (inclusive).";
                    }
                    else if (comp_no >= 95 && comp_no <= 100) {
                        hint_text = "The number is in between 95-100 (inclusive).";
                    }
                    else {
                        int last_digit = comp_no % 10;
                        int lower_limit, upper_limit;
                        if (last_digit <= 5)
                            lower_limit = comp_no - last_digit;
                        else
                            lower_limit = comp_no - last_digit + 5;
                        upper_limit = lower_limit + 5;
                        hint_text = "The number is in between " + lower_limit + "-" + upper_limit + " (inclusive).";
                    }
                    break;

                // Difference
                case 5:

                    int difference = user_no > comp_no ? user_no - comp_no : comp_no - user_no;
                    hint_text = "You are " + difference + " numbers away!";
                    break;

                // Number reveal
                case 6:

                    hint_text = "The number is: " + comp_no;
                    break;

                default:

                    hint_text = "No More Hints!";
                    break;

            }
        }

        else if (difficulty.equals("Hard")) {
            switch (round_no) {

                // Which Half
                case 1:
                    if (comp_no >= 50) {
                        hint_text = "The number is in the upper half.";
                    } else {
                        hint_text = "The number is in the lower half.";
                    }
                    break;

                // Which Third
                case 2:
                    if (comp_no >= 66) {
                        hint_text = "The number is in the upper third.";
                    }
                    else if (comp_no >= 33) {
                        hint_text = "The number is in the middle third.";
                    }
                    else {
                        hint_text = "The number is in the lower third.";
                    }
                    break;

                // Which Quartile
                case 3:
                    if (comp_no <= 25) {
                        hint_text = "The number is in the first quartile";
                    } else if (comp_no <= 50) {
                        hint_text = "The number is in the second quartile";
                    } else if (comp_no <= 75) {
                        hint_text = "The number is in the third quartile";
                    } else if (comp_no <= 100) {
                        hint_text = "The number is in the fourth quartile";
                    }
                    break;

                // Which Fifth
                case 4:
                    if (comp_no <= 20) {
                        hint_text = "The number is in the first fifth.";
                    }
                    else if (comp_no <= 40) {
                        hint_text = "The number is in the second fifth.";
                    }
                    else if (comp_no <= 60) {
                        hint_text = "The number is in the third fifth.";
                    }
                    else if (comp_no <= 80) {
                        hint_text = "The number is in the fourth fifth.";
                    }
                    else {
                        hint_text = "The number is in the fifth fifth.";
                    }
                    break;

                // Range of 15
                case 5:
                    if (comp_no >= 1 && comp_no <= 15) {
                        hint_text = "The number is in between 1-15 (inclusive).";
                    }
                    else if (comp_no >= 85 && comp_no <= 100) {
                        hint_text = "The number is in between 85-100 (inclusive).";
                    }
                    else {
                        int lower_limit = comp_no - (comp_no % 10);
                        int upper_limit = lower_limit + 15;
                        hint_text = "The number is in between " + lower_limit + "-" + upper_limit + " (inclusive).";
                    }
                    break;

                // Range of 10
                case 6:
                    if (comp_no >= 1 && comp_no <= 10) {
                        hint_text = "The number is in between 1-10 (inclusive).";
                    }
                    else if (comp_no >= 90 && comp_no <= 100) {
                        hint_text = "The number is in between 90-100 (inclusive).";
                    }
                    else {
                        int lower_limit = comp_no - (comp_no % 10);
                        int upper_limit = lower_limit + 10;
                        hint_text = "The number is in between " + lower_limit + "-" + upper_limit + " (inclusive).";
                    }
                    break;

                // Range of 5
                case 7:
                    if (comp_no >= 1 && comp_no <= 5) {
                        hint_text = "The number is in between 1-5 (inclusive).";
                    }
                    else if (comp_no >= 95 && comp_no <= 100) {
                        hint_text = "The number is in between 95-100 (inclusive).";
                    }
                    else {
                        int last_digit = comp_no % 10;
                        int lower_limit, upper_limit;
                        if (last_digit <= 5)
                            lower_limit = comp_no - last_digit;
                        else
                            lower_limit = comp_no - last_digit + 5;
                        upper_limit = lower_limit + 5;
                        hint_text = "The number is in between " + lower_limit + "-" + upper_limit + " (inclusive).";
                    }
                    break;

                // Difference
                case 8:
                    int difference = user_no > comp_no ? user_no - comp_no : comp_no - user_no;
                    hint_text = "You are " + difference + " numbers away!";
                    break;

                // Number reveal
                case 9:
                    hint_text = "The number is: " + comp_no;
                    break;

                default:
                    hint_text = "No More Hints!";
                    break;

            }
        }

        tv_hint.setText(hint_text);

    }

    public void displayWin() {

        String win_text = "Congrats, you won!";
        tv_hint.setText(win_text);

    }

    public void playAgain() {

        //Resetting the parameters
        round_no = 0;
        comp_no = getCompNumber(); //New number for next round

    }

    public void displayPostRound() {

        // Updating Round No Text
        String roundno_text;
        if (round_no == 0) {
            roundno_text = "Prepare for the Next Number!";
        }
        else {
            roundno_text = "Round No: " + round_no;
        }
        tv_roundno.setText(roundno_text);
        round_no++;

        // Updating Number Text
        String number_text = String.valueOf(user_no);
        tv_number.setText(number_text);

    }

}