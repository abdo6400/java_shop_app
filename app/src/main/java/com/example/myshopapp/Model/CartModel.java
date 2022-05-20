package com.example.myshopapp.Model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class CartModel {
   private static final float PREFERRED_WIDTH = 50;
   private static final float PREFERRED_HEIGHT = 50;
   private int id;
   private String quantity;
   private String price;
   private String title;
   private String image;

   public CartModel() {
   }

   public CartModel(String image, String title, String price, String quantity) {
      this.image = image;
      this.title = title;
      this.price = price;
      this.quantity = quantity;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getPrice() {
      return price;
   }

   public void setPrice(String price) {
      this.price = price;
   }

   public String getQuantity() {
      return quantity;
   }

   public void setQuantity(String quantity) {
      this.quantity = quantity;
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }


   public String getImage() {
      return this.image;
   }


   public void setImage(String image) {
      this.image = image;
   }



}

