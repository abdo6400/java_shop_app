package com.example.myshopapp.Screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.myshopapp.Database.AuthDatabase;
import com.example.myshopapp.Database.Database;
import com.example.myshopapp.Model.UserDataModel;
import com.example.myshopapp.R;

public class Splash extends AppCompatActivity {
   TextView username;
   AuthDatabase authDatabase=new AuthDatabase();
   Database database=new Database(this);
   Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        bundle=getIntent().getExtras();
        if(bundle!=null)
        {
            username=findViewById(R.id.userNameID);
                username.setText(authDatabase.getUserId());
                Intent intent=new Intent(Splash.this,MainActivity.class);
                intent.putExtra("userName",bundle.getString("username"));
                intent.putExtra("PhoneNumber",bundle.getString("phoneNumber"));
                startActivity(intent);

        }

    }
}