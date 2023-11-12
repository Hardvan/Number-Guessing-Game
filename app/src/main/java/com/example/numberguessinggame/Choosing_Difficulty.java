package com.example.numberguessinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

public class Choosing_Difficulty extends AppCompatActivity {

    Button b_easy, b_medium, b_hard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosing_difficulty);

        b_easy = findViewById(R.id.b_easy);
        b_medium = findViewById(R.id.b_medium);
        b_hard = findViewById(R.id.b_hard);

        b_easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Choosing_Difficulty.this, MainActivity.class);
                String difficulty = "Easy";

                //Create the bundle
                Bundle bundle = new Bundle();

                //Add your data to bundle
                bundle.putString("stuff", difficulty);

                //Add the bundle to the intent
                i.putExtras(bundle);

                //Fire that second activity
                startActivity(i);

            }
        });

        b_medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                String difficulty = "Medium";

                //Create the bundle
                Bundle bundle = new Bundle();

                //Add your data to bundle
                bundle.putString("stuff", difficulty);

                //Add the bundle to the intent
                i.putExtras(bundle);

                //Fire that second activity
                startActivity(i);

            }
        });

        b_hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                String difficulty = "Hard";

                //Create the bundle
                Bundle bundle = new Bundle();

                //Add your data to bundle
                bundle.putString("stuff", difficulty);

                //Add the bundle to the intent
                i.putExtras(bundle);

                //Fire that second activity
                startActivity(i);

            }
        });


    }
}