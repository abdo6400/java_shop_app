package com.example.myshopapp.Database;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myshopapp.Model.UserDataModel;
import com.example.myshopapp.R;
import com.example.myshopapp.Screens.Auth;
import com.example.myshopapp.Screens.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jetbrains.annotations.NotNull;
public class AuthDatabase extends AppCompatActivity {
    public FirebaseAuth fire = FirebaseAuth.getInstance();
    public void signUp(String userName, String email, String phoneNumber, String password) {
        fire.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @org.jetbrains.annotations.NotNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                }
                else
                {
                }
            }
        });
    }
    public void signIn(String email, String password) {
        fire.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(AuthDatabase.this, "Successful login", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AuthDatabase.this, "Failed login", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void LogOut() {
        fire.signOut();
    }
    public String getUserId(){
        return fire.getUid();
    }
}



