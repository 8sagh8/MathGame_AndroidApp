package com.example.mathquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //creating static final keys
    public static final String PLAYER_NAME = "MathQuiz.PLAYER_NAME";
    public static final String PLAYER_AGE = "MathQuiz.PLAYER_AGE";
    public static final String PLAYER_SCORE = "MathQuiz.PLAYER_SCORE";

    private Button start_Button;        // Button to Start Game
    private Button instruction_Button;  // Button to go open Instruction Page

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start_Button =findViewById(R.id.startButton);
        start_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer int_text_score = 0; // score will be calculating from next level

                EditText new_name = findViewById(R.id.playerName);
                String _new_name = new_name.getText().toString();

                //If name will be less than 2 or greater than 10 characters,
                // will pop-up error msg
                TextView display_error = findViewById(R.id.display_error);
                if (_new_name.length() < 2 || _new_name.length() > 10 ){
                    display_error.setText("");
                    display_error.append("Enter Name Between Characters\n[2 to 10]");
                    display_error.setVisibility(View.VISIBLE);
                }
                else {
                    display_error.setVisibility(View.INVISIBLE);
                    level_01(int_text_score);
                }
            }
        });

        instruction_Button = findViewById(R.id.instructionButton);
        instruction_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                instruction();
            }
        });
    }


    public void level_01(Integer int_text_score){
        // Fetching player info
        EditText p_name = findViewById(R.id.playerName);
        String text_name = p_name.getText().toString();

        EditText p_age = findViewById(R.id.playerAge);
        String text_age = p_age.getText().toString();

        // move to next page
        Intent intent = new Intent(this, Level01Activity.class);
        intent.putExtra(PLAYER_NAME, text_name);
        intent.putExtra(PLAYER_AGE, text_age);
        intent.putExtra(PLAYER_SCORE, int_text_score);
        startActivity(intent);
    }

    public void instruction(){
        // will handle the click on instruction button
        Intent intent = new Intent(this, InstructionActivity.class);
        startActivity(intent);
    }
}

