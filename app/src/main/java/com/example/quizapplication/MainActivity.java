package com.example.quizapplication;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;

public class MainActivity extends AppCompatActivity {

    private EditText userName;
    private EditText password;
    private Button loginButton;
    private TextView signup;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user= mAuth.getCurrentUser();

        if(user!=null){
            finish();
            startActivity(new Intent(MainActivity.this,Welcome2.class));
        }

        userName =(EditText) findViewById(R.id.username);
        password =(EditText) findViewById(R.id.password);
        loginButton = (Button)findViewById(R.id.btlogin);
        signup= findViewById(R.id.tvsignup);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(userName.getText().toString(),password.getText().toString());
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,SignUp.class));
            }
        });

    }

    private void validate(String username,String userpassword){

        mAuth.signInWithEmailAndPassword(username,userpassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Login Sucessful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this,Welcome2.class));
                }
                else{
                    Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }


}
