package com.example.myshopapp.Model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Base64;
import java.io.ByteArrayOutputStream;


public class HomeModel {
   private static final float PREFERRED_WIDTH = 250;
   private static final float PREFERRED_HEIGHT = 250;
   private int id;
   private String image;
   private String title;
   private String price;
   private String description;

   public HomeModel() {
   }

   public HomeModel(Bitmap image, String title, String price, String description) {
      this.image = bitmapToString(image);
      this.title = title;
      this.price = price;
      this.description = description;
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

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }


   public Bitmap getImage() {
      return stringToBitmap(this.image);
   }

   public String getImageAsString() {
      return this.image;
   }

   public void setImage(String image) {
      this.image = image;
   }

   private static String bitmapToString(Bitmap bitmap) {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
      byte[] b = baos.toByteArray();
      return Base64.encodeToString(b, Base64.DEFAULT);
   }

   private static Bitmap stringToBitmap(String encodedString) {
      try {
         byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
         return BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
      } catch (Exception e) {
         e.getMessage();
         return null;
      }
   }

   public static Bitmap resizeBitmap(Bitmap bitmap) {
      int width = bitmap.getWidth();
      int height = bitmap.getHeight();
      float scaleWidth = PREFERRED_WIDTH / width;
      float scaleHeight = PREFERRED_HEIGHT / height;

      Matrix matrix = new Matrix();
      matrix.postScale(scaleWidth, scaleHeight);
      Bitmap resizedBitmap = Bitmap.createBitmap(
              bitmap, 0, 0, width, height, matrix, false);
      bitmap.recycle();
      return resizedBitmap;
   }
}
