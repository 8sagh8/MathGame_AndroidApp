package com.example.mathquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class Level02Activity extends AppCompatActivity {
    //creating static final keys
    public static final String PLAYER_NAME = "MathQuiz.PLAYER_NAME";
    public static final String PLAYER_AGE = "MathQuiz.PLAYER_AGE";
    public static final String PLAYER_SCORE = "MathQuiz.PLAYER_SCORE";

    private Button submit_Button;   // button to submit answer
    Integer count_seconds = 0;      // to count seconds
    Boolean _flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level02);

        // Fetch Player Name and Score
        Intent intent = getIntent();
        String text_name = intent.getStringExtra(Level01Activity.PLAYER_NAME);
        Integer text_score = getIntent().getExtras().getInt(Level01Activity.PLAYER_SCORE);

        // Set Player's Name and Score at top of screen
        TextView nameView = findViewById(R.id.details_taskBar_02);
        nameView.setText(text_name);
        TextView _p_score = findViewById(R.id.score_card);
        _p_score.setText(String.valueOf(text_score));

        // Displaying timer per second and calculating "count_seconds"
        Timer timer_Obj_02 = new Timer();
        timer_Obj_02.schedule(new TimerTask() {
            @Override
            public void run() {
                final TextView _timing_02 = findViewById(R.id.timing_02);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (count_seconds < 11){
                            _timing_02.setText(String.valueOf(count_seconds));
                            ++count_seconds;
                        }
                    }
                });
            }
        }, 0, 1000);

        // On click Submit Button
        submit_Button =findViewById(R.id.submit_02);
        submit_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer int_text_score = getIntent().getExtras().getInt(Level01Activity.PLAYER_SCORE);

                // On submitting wrong answer within time, wrong answer msg will pop-up
                // On submitting correct answer, calculate score and move to next level
                TextView display_error = findViewById(R.id.display_error);
                EditText _answer = findViewById(R.id.answer_02);
                String answer_ = _answer.getText().toString();
                Integer _ans = Integer.parseInt(answer_);

                if (_ans == 0){
                    display_error.setVisibility(View.INVISIBLE);
                    if (count_seconds < 6) {
                        int_text_score = int_text_score + 10;
                    }
                    else {
                        int_text_score = int_text_score + 5;
                    }
                    _flag = true;
                    level_03(int_text_score);
                }
                else {
                    display_error.setText("");
                    display_error.append("WRONG ANSWER!\nDon't Add Blank Spaces");
                    display_error.setVisibility(View.VISIBLE);
                }
            }
        });

        // Switch to Next Activity after 10 seconds of Interval by default
        Timer timer_Obj;
        timer_Obj = new Timer();
        timer_Obj.schedule(new TimerTask() {
            Integer int_text_score = getIntent().getExtras().getInt(Level01Activity.PLAYER_SCORE);
            @Override
            public void run() {
                if (_flag == false) {
                    _flag = true;
                    level_03(int_text_score);
                }
            }
        }, 10000);  // 10000 is equal to 10 seconds
    }

    public void level_03(Integer int_text_score){
        Intent intent = getIntent();
        String text_name = intent.getStringExtra(Level01Activity.PLAYER_NAME);
        String text_age = intent.getStringExtra(Level01Activity.PLAYER_AGE);

        // if all above conditions are okay.. then we will move on next page
        intent = new Intent(this, Level03Activity.class);
        intent.putExtra(PLAYER_NAME, text_name);
        intent.putExtra(PLAYER_AGE, text_age);
        intent.putExtra(PLAYER_SCORE, int_text_score);
        startActivity(intent);
    }
}