package com.example.myshopapp.Screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.myshopapp.Database.AuthDatabase;
import com.example.myshopapp.Database.Database;
import com.example.myshopapp.Model.UserDataModel;
import com.example.myshopapp.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.dialog.MaterialDialogs;

import java.util.ArrayList;
import java.util.List;

public class MyAccountScreen extends AppCompatActivity {
    private TextView userName;
    private Database db=new Database(this);
    AuthDatabase authDatabase=new AuthDatabase();
    UserDataModel userDataModel;
    private static final int SELECT_PHOTO =1;
    private static final int CAPTURE_PHOTO =2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account_screen);
      /*  userDataModel=db.getUser(authDatabase.getUserId());
        userName = findViewById(R.id.UserNameId);
        if(userDataModel.getUserName()!=null){
            userName.setText("Hello , "+userDataModel.getUserName());
        }*/
       findViewById(R.id.backButtonID).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               finish();
           }
       });
        findViewById(R.id.myOrderButtonID).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyAccountScreen.this,OrdersScreen.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.myFavoriteButtonID).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyAccountScreen.this,FavoriteScreen.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.myProfileButtonID).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyAccountScreen.this,ProfileScreen.class);
                startActivity(intent);
            }
        });
    }
}