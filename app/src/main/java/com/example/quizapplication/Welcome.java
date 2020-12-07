package com.example.quizapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Welcome extends AppCompatActivity {
    private TextView name;
    private FirebaseAuth mAuth;
    private Button movieQuiz,computerQuiz,btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        mAuth=FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();

        name = findViewById(R.id.tvname);
        name.setText("Hi  "+user.getEmail());

        movieQuiz = findViewById(R.id.btnMovie);
        btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                finish();
                startActivity(new Intent(Welcome.this,MainActivity.class));
            }
        });
        movieQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(Welcome.this,QuestionActivity.class);
                intent.putExtra("quiz",1);
                startActivity(intent);
                finish();
            }
        });

        computerQuiz=findViewById(R.id.btnComputer);
        computerQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(Welcome.this,QuestionActivity.class);
                intent.putExtra("quiz",2);
                startActivity(intent);
                finish();
            }
        });

    }
}
