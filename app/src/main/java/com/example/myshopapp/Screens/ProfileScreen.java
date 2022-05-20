package com.example.myshopapp.Screens;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.myshopapp.Database.AuthDatabase;
import com.example.myshopapp.Database.Database;
import com.example.myshopapp.Model.UserDataModel;
import com.example.myshopapp.R;

public class ProfileScreen extends AppCompatActivity {
    private EditText username, phone;
    private Database database = new Database(this);
    private AuthDatabase authDatabase = new AuthDatabase();
    private UserDataModel userDataModel = new UserDataModel();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_screen);
        username = findViewById(R.id.userNameProfile);
        phone = findViewById(R.id.PhoneNumberProfile);

       /* userDataModel=database.getUser(authDatabase.getUserId());
        username.setText(userDataModel.getUserName());
        phone.setText(userDataModel.getPhoneNumber());*/

        findViewById(R.id.saveButtonID).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.updateDataUser(new UserDataModel(authDatabase.getUserId(),username.getText().toString(),phone.getText().toString()));
                finish();
            }
        });
        findViewById(R.id.backButtonID).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}