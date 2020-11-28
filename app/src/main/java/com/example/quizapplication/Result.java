package com.example.quizapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Result extends AppCompatActivity {
    TextView tv, tv2, tv3;
    Button btnRestart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tv = findViewById(R.id.tvresult);
        tv2 = findViewById(R.id.tvcorrect);
        tv3 = findViewById(R.id.tvwrong);
        btnRestart =  findViewById(R.id.btnreset);

        StringBuffer sb = new StringBuffer();
        sb.append("Correct answers: " + QuestionActivity.correct + "\n");
        StringBuffer sb2 = new StringBuffer();
        sb2.append("Wrong Answers: " + QuestionActivity.wrong + "\n");
        StringBuffer sb3 = new StringBuffer();
        sb3.append("Final Score: " + QuestionActivity.correct + "\n");
        tv.setText(sb);
        tv2.setText(sb2);
        tv3.setText(sb3);

        QuestionActivity.correct=0;
        QuestionActivity.wrong=0;
        // QuestionActivity.

        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Result.this,Welcome.class);
                startActivity(in);
            }
        });
    }
}