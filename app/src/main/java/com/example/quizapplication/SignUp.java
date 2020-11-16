package com.example.quizapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {

    private EditText userName;
    private EditText password;
    private Button signupButton;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        userName = findViewById(R.id.etemailsignup);
        password = findViewById(R.id.etpasswordsignup);
        signupButton = findViewById(R.id.btnsignup);


        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( validate(userName.getText().toString(),password.getText().toString())){
                    createAccount(userName.getText().toString(),password.getText().toString());
                }
                else{
                    Toast.makeText(SignUp.this, "Email or Password cannot be Empty", Toast.LENGTH_SHORT).show();
                }
            }
        });




    }

    private Boolean validate(String userName, String password){

        if(userName.isEmpty() || password.isEmpty()){
            return false;
        }
        return true;
    }

    private void createAccount(String userName, String password){

        mAuth = FirebaseAuth.getInstance();

        mAuth.createUserWithEmailAndPassword(userName,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(SignUp.this, "Congraulations!", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(new Intent(SignUp.this,MainActivity.class));
                }
                else{
                    Toast.makeText(SignUp.this, "SignUp Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}