package com.example.news;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText emailid;
    EditText passwordTX;
    Button loginBtns;
    Button asksignuoBtn;
    FirebaseAuth firebaseAuth ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        emailid = findViewById(R.id.emailTxt);
        passwordTX = findViewById(R.id.passwordTxt);
        loginBtns = findViewById(R.id.loginBtn);
        asksignuoBtn = findViewById(R.id.askSignup);

        asksignuoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,SignUp.class ));
            }
        });

        loginBtns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = emailid.getText().toString();
                String Password = passwordTX.getText().toString();
                firebaseAuth = FirebaseAuth.getInstance();

                if(Email.isEmpty())
                {
                    emailid.setError("Please Enter Your Email ID");
                    emailid.requestFocus();
                }
                else if (Password.isEmpty())
                {
                    passwordTX.setError("Please Enter Your Password");
                    passwordTX.requestFocus();
                }
                else if(!Email.isEmpty() && !Password.isEmpty())
                {
                    firebaseAuth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(LoginActivity.this, "Login Sucessfull",Toast.LENGTH_SHORT).show();
                               startActivity(new Intent(LoginActivity.this,MainActivity.class));
                            }
                            else
                            {
                                Toast.makeText(LoginActivity.this, "Login Failed - Invalid Input", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }
        });
    }
}
