package com.example.quizapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class QuestionActivity extends AppCompatActivity {
    TextView question;
    TextView score;
    private FirebaseAuth mAuth;
    Button nextButton,quitButton;
    RadioGroup radioGroup;
    RadioButton rButton1,rButton2,rButton3;

    String questions[] ;
    String answers[] ;
    String opt[] ;


    int flag=0;
    public static int marks=0,correct=0,wrong=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        score = findViewById(R.id.tvScore);
        mAuth=FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();


        Intent intent =getIntent();
        int val = intent.getIntExtra("quiz",0);
        //Log.d("VALUE", "onCreate: "+val);
        if(val==1) {
            Movie m= new Movie();
            questions = m.questions;
            answers = m.answers;
            opt=m.opt;
        }
        else if (val==2){
            Computer s = new Computer();
            questions = s.questions;
            answers = s.answers;
            opt=s.opt;
        }


        //Log.d("VALUE :2 ", "onCreate: "+val );
        final TextView score = findViewById(R.id.tvScore);
        TextView Name = findViewById(R.id.tvUserEmail);
        Name.setText("Hello " +user.getEmail());

        nextButton = findViewById(R.id.btnNextQuestion);
        quitButton = findViewById(R.id.btnQuit);
        question = findViewById(R.id.tvQuestion);


        radioGroup =findViewById(R.id.radioGroup);

        rButton1 = findViewById(R.id.rbOptionA);
        rButton2 = findViewById(R.id.rbOptionB);
        rButton3 = findViewById(R.id.rbOptionC);

        question.setText(questions[flag]);
        rButton1.setText(opt[0]);
        rButton2.setText(opt[1]);
        rButton3.setText(opt[2]);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(radioGroup.getCheckedRadioButtonId()==-1){
                    Toast.makeText(QuestionActivity.this, "Please select one choice", Toast.LENGTH_SHORT).show();
                    return;
                }

                RadioButton answer = findViewById(radioGroup.getCheckedRadioButtonId());
                String ans =answer.getText().toString();

                if(ans.equals(answers[flag])){
                    correct++;
                    Toast.makeText(QuestionActivity.this, "Correct", Toast.LENGTH_SHORT).show();
                }
                else{
                    wrong++;
                    Toast.makeText(QuestionActivity.this, "Wrong", Toast.LENGTH_SHORT).show();
                }
                flag++;

                if(score!=null){
                    score.setText(" "+correct);
                }
                if(flag<questions.length)
                {
                    question.setText(questions[flag]);
                    rButton1.setText(opt[flag*3]);
                    rButton2.setText(opt[flag*3 +1]);
                    rButton3.setText(opt[flag*3 +2]);
                }
                else{
                    marks=correct;
                    startActivity(new Intent(QuestionActivity.this,Result.class));
                    finish();
                }
                radioGroup.clearCheck();
            }
        });

        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(QuestionActivity.this,Result.class));
                finish();
            }
        });
    }
}