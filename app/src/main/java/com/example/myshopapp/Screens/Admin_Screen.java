package com.example.myshopapp.Screens;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.MemoryFile;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.myshopapp.Database.Database;
import com.example.myshopapp.Model.HomeModel;
import com.example.myshopapp.R;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class Admin_Screen extends AppCompatActivity {
 private static final int GALLERY_REQUEST = 100;
   ImageView Image;
   Button save;
   EditText title,price,description;
   Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__screen);
        db=new Database(this);
        Image = findViewById(R.id.adminImage);
        save = findViewById(R.id.btnSave);
        title = findViewById(R.id.EditTitle);
        price = findViewById(R.id.EditPrice);
        description = findViewById(R.id.Editdescription);
        Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select picture"),GALLERY_REQUEST);
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap=((BitmapDrawable)Image.getDrawable()).getBitmap();
                db.addProduct(new HomeModel(bitmap,title.getText().toString(),
                        price.getText().toString(),description.getText().toString()));
                finish();
            }
        });
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GALLERY_REQUEST&&resultCode==RESULT_OK){
            try {
                Uri selectedImage = data.getData();
                InputStream imageStream = getContentResolver().openInputStream(selectedImage);
                Image.setImageBitmap(BitmapFactory.decodeStream(imageStream));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}