package com.example.mathquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        String text_name = intent.getStringExtra(Level05Activity.PLAYER_NAME);
        String text_age = intent.getStringExtra(Level05Activity.PLAYER_AGE);
        Integer int_text_score = getIntent().getExtras().getInt(Level05Activity.PLAYER_SCORE);

        TextView nameView = findViewById(R.id.playerNameSaved);
        nameView.setText(text_name);

        TextView ageView = findViewById(R.id.playerAgeSaved);
        ageView.setText(text_age);

        TextView scoreView = findViewById(R.id.playerAgeScore);
        scoreView.setText(String.valueOf(int_text_score));
    }
}
