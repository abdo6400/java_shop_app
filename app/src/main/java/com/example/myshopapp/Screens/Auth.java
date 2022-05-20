package com.example.myshopapp.Screens;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.myshopapp.Database.AuthDatabase;
import com.example.myshopapp.Database.Database;
import com.example.myshopapp.Model.UserDataModel;
import com.example.myshopapp.R;

import java.sql.Time;
import java.time.Duration;

enum AuthMode {
    SignUp, SignIn
}

public class Auth extends AppCompatActivity {
    AuthMode authMode = AuthMode.SignIn;
    EditText userName, email, phoneNumber, password, conPassword;
    Button sign;
    TextView title, textButton;
    AuthDatabase authDatabase=new AuthDatabase();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth_screen);
        userName = findViewById(R.id.UserName);
        email = findViewById(R.id.Email);
        phoneNumber = findViewById(R.id.PhoneNumber);
        password = findViewById(R.id.Password);
        conPassword = findViewById(R.id.ConfirmPassword);
        sign = findViewById(R.id.signUpButton);
        title = findViewById(R.id.titleID);
        textButton = findViewById(R.id.loginID);
        title.setText("Welcome to sign in page");
        userName.setVisibility(View.GONE);
        phoneNumber.setVisibility(View.GONE);
        conPassword.setVisibility(View.GONE);
        sign.setText("LogIn");
        textButton.setText("Or create new account ?");
        textButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authMode = changeAuthMode(authMode);
                if (authMode == AuthMode.SignIn) {
                    title.setText("Welcome to sign in page");
                    userName.setVisibility(View.GONE);
                    phoneNumber.setVisibility(View.GONE);
                    conPassword.setVisibility(View.GONE);
                    sign.setText("LogIn");
                    textButton.setText("Or create new account ?");
                } else {
                    title.setText("Welcome to sign up page");
                    sign.setText("SignUp");
                    textButton.setText("Or sign in ?");
                    userName.setVisibility(View.VISIBLE);
                    phoneNumber.setVisibility(View.VISIBLE);
                    conPassword.setVisibility(View.VISIBLE);
                }
            }
        });
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(authMode==AuthMode.SignIn)
                    {
                        if(email.getText().toString().isEmpty()||password.getText().toString().isEmpty())
                        {
                            Toast.makeText(Auth.this,"Invaild in sign in",Toast.LENGTH_LONG).show();
                        }else{
                             submit("SignIn");
                        }
                    }
                    else
                    {
                        if(email.getText().toString().isEmpty()||userName.getText().toString().isEmpty()||password.getText().toString().isEmpty()
                                ||phoneNumber.getText().toString().isEmpty()||conPassword.getText().toString().isEmpty())
                        {
                            Toast.makeText(Auth.this,"Invaild in create email",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            if(matchPassword(password.getText().toString(),conPassword.getText().toString()))
                            {

                                submit("SignUp");
                            }
                            else
                                Toast.makeText(Auth.this,"Password do not match",Toast.LENGTH_LONG).show();
                        }

                    }

                }catch (Exception e)
                {
                    Toast.makeText(Auth.this,"Invalid",Toast.LENGTH_LONG).show();
                }

            }
        });
    }
    private AuthMode changeAuthMode(AuthMode authMode) {
        if (authMode == AuthMode.SignIn)
            authMode = AuthMode.SignUp;
        else
            authMode = AuthMode.SignIn;
        return authMode;
    }
    private void submit(String authchoice) {
        if (authchoice=="SignIn") {
            signIn(email.getText().toString(),password.getText().toString());

        } else{
            signUp(userName.getText().toString(), email.getText().toString(), phoneNumber.getText().toString(), password.getText().toString());
        }
    }
    private void signIn(String email, String password)  {
            try {
               // authDatabase.signIn(email,password);
                Intent intent = new Intent(Auth.this, MainActivity.class);
                startActivity(intent);
                finish();
            } catch (Exception e) {
                e.printStackTrace();
            }

    }
    private void signUp(String userName, String email, String phoneNumber, String password) {
            //authDatabase.signUp(userName, email, phoneNumber, password);
            Intent intent = new Intent(Auth.this, Splash.class);
            intent.putExtra("username",userName);
            intent.putExtra("phoneNumber",phoneNumber);
            startActivity(intent);
    }
    public boolean matchPassword(String password,String ConformPassword){
        if (password.equals(ConformPassword))
            return true;
        else
            return false;
    }
    @Override
    protected void onStart() {
        super.onStart();
        if(authDatabase.fire.getCurrentUser()!=null)
        {
            Intent intent = new Intent(Auth.this, MainActivity.class);
            startActivity(intent);
        }
    }
}