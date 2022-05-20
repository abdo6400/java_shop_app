package com.example.myshopapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ListView;

import com.example.myshopapp.Model.CartModel;
import com.example.myshopapp.Model.FavoriteModel;
import com.example.myshopapp.Model.HomeModel;
import com.example.myshopapp.Model.OrderModel;
import com.example.myshopapp.Model.UserDataModel;
import com.example.myshopapp.Utils.utils;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {

    String create_table = " CREATE TABLE ";
    String sign1 = " (";
    String sign2 =" )";
    String drop = " DROP TABLE IF EXISTS ";

    public Database(Context context) {
        super(context, utils.DATABASE_NAME, null, utils.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //product_Table
        String Table_Product = create_table + utils.TABLE_NAME_Product + sign1 + utils.KEY_ID_P + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + utils.KEY_IMAGE_P + " TEXT," + utils.KEY_TITLE_P + " TEXT," + utils.KEY_PRICE_P + " TEXT," + utils.KEY_DESCRIPTION_P + " TEXT" + sign2;
        //user_Table
         String Table_USER=create_table+ utils.TABLE_NAME_UserData+sign1+utils.KEY_ID_U+" TEXT PRIMARY KEY ,"
         +utils.KEY_IMAGE_U+" TEXT,"+ utils.KEY_USERNAME_U+" TEXT,"+utils.KEY_PHONE_U+" TEXT"+" )";
        //Cart_Table
        String Table_Cart = create_table + utils.TABLE_NAME_Cart + sign1 + utils.KEY_ID_C
                + " INTEGER PRIMARY KEY AUTOINCREMENT ," + utils.KEY_IMAGE_C
                + " TEXT," + utils.KEY_TITLE_C + " TEXT," + utils.KEY_PRICE_C + " TEXT," + utils.KEY_QUANTITY_C + " TEXT" + sign2;
        //Order_Table
      /*  String Table_Order = create_table + utils.TABLE_NAME_Order + sign1 + utils.KEY_ID_O
                + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + utils.KEY_AMOUNT_O + " TEXT," + utils.KEY_DATE_O + " TEXT " + sign2;*/
        //Favorite_Table
        String Table_Favorite = create_table + utils.TABLE_NAME_Favorite + sign1 +
                utils.KEY_PRODUCTID_F + " INTEGER PRIMARY KEY ," + utils.KEY_ISFAVORITE_F + " TEXT " + sign2;
        //exec_SQL_Tables
          db.execSQL(Table_USER);
        db.execSQL(Table_Product);
        db.execSQL(Table_Cart);
        //db.execSQL(Table_Order);
        db.execSQL(Table_Favorite);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(drop + utils.TABLE_NAME_Product);
        db.execSQL(drop + utils.TABLE_NAME_UserData);
        db.execSQL(drop + utils.TABLE_NAME_Cart);
        db.execSQL(drop + utils.TABLE_NAME_Favorite);
        db.execSQL(drop + utils.TABLE_NAME_Order);
        onCreate(db);

    }

    public void addProduct(HomeModel product) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(utils.KEY_IMAGE_P, product.getImageAsString());
        values.put(utils.KEY_TITLE_P, product.getTitle());
        values.put(utils.KEY_PRICE_P, product.getPrice());
        values.put(utils.KEY_DESCRIPTION_P, product.getDescription());
        sqLiteDatabase.insert(utils.TABLE_NAME_Product, null, values);
    }

    public List<HomeModel> getAllProduct() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String getALL = " SELECT * FROM " + utils.TABLE_NAME_Product;
        List<HomeModel> productList = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(getALL, null);
        if (cursor.moveToFirst())
            do {
                HomeModel model = new HomeModel();
                model.setId(cursor.getInt(cursor.getColumnIndex(utils.KEY_ID_P)));
                model.setImage(cursor.getString(cursor.getColumnIndex(utils.KEY_IMAGE_P)));
                model.setTitle(cursor.getString(cursor.getColumnIndex(utils.KEY_TITLE_P)));
                model.setPrice(cursor.getString(cursor.getColumnIndex(utils.KEY_PRICE_P)));
                model.setDescription(cursor.getString(cursor.getColumnIndex(utils.KEY_DESCRIPTION_P)));
                productList.add(model);
            } while (cursor.moveToNext());
        cursor.close();
        return productList;
    }

    public HomeModel searchData(int id) {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query(utils.TABLE_NAME_Product, new String[]{utils.KEY_ID_P, utils.KEY_IMAGE_P
                        , utils.KEY_TITLE_P, utils.KEY_PRICE_P, utils.KEY_DESCRIPTION_P}, utils.KEY_ID_P + "=?"
                , new String[]{String.valueOf(id)}, null, null, null, null);
        HomeModel data = new HomeModel();
        if (cursor != null)
            cursor.moveToFirst();
        data.setImage(cursor.getString(cursor.getColumnIndex(utils.KEY_IMAGE_P)));
        data.setTitle(cursor.getString(cursor.getColumnIndex(utils.KEY_TITLE_P)));
        data.setPrice(cursor.getString(cursor.getColumnIndex(utils.KEY_PRICE_P)));
        data.setDescription(cursor.getString(cursor.getColumnIndex(utils.KEY_DESCRIPTION_P)));

        cursor.close();
        return data;
    }

    public void deleteData(HomeModel data) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(utils.TABLE_NAME_Product, utils.KEY_ID_P
                + " =?", new String[]{String.valueOf(data.getId())});
    }
    public void addUser(UserDataModel userDataModel) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(utils.KEY_ID_U, userDataModel.getUserID());
        values.put(utils.KEY_IMAGE_U, userDataModel.getImageUrl());
        values.put(utils.KEY_USERNAME_U, userDataModel.getUserName());
        values.put(utils.KEY_PHONE_U, userDataModel.getPhoneNumber());
        sqLiteDatabase.insert(utils.TABLE_NAME_UserData, null, values);
        sqLiteDatabase.close();
    }

    public UserDataModel getUser(String id) {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query(utils.TABLE_NAME_UserData, new String[]{utils.KEY_ID_U, utils.KEY_IMAGE_U
                        , utils.KEY_USERNAME_U, utils.KEY_PHONE_U}, utils.KEY_ID_U + "=?"
                , new String[]{id}, null, null, null, null);
        UserDataModel data = new UserDataModel();
        if (cursor != null)
            cursor.moveToFirst();
        data.setImageUrl(cursor.getString(cursor.getColumnIndex(utils.KEY_IMAGE_U)));
        data.setUserID(cursor.getString(cursor.getColumnIndex(utils.KEY_ID_U)));
        data.setPhoneNumber(cursor.getString(cursor.getColumnIndex(utils.KEY_PHONE_U)));
        data.setUserName(cursor.getString(cursor.getColumnIndex(utils.KEY_USERNAME_U)));
        cursor.close();
        return data;
    }
    public int updateDataUser(UserDataModel data) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(utils.KEY_USERNAME_U, data.getUserName());
        contentValues.put(utils.KEY_PHONE_U, data.getPhoneNumber());
        return sqLiteDatabase.update(utils.TABLE_NAME_UserData, contentValues, utils.KEY_ID_U + " =?", new String[]{data.getUserID()});
    }

    public void addProductToCart(CartModel cartModel) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(utils.KEY_IMAGE_C, cartModel.getImage());
        values.put(utils.KEY_TITLE_C, cartModel.getTitle());
        values.put(utils.KEY_PRICE_C, cartModel.getPrice());
        values.put(utils.KEY_QUANTITY_C, cartModel.getQuantity());
        sqLiteDatabase.insert(utils.TABLE_NAME_Cart, null, values);
        sqLiteDatabase.close();
    }

    public CartModel searchProductInCart(int id) {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query(utils.TABLE_NAME_Cart, new String[]{utils.KEY_ID_C, utils.KEY_IMAGE_C
                        , utils.KEY_PRICE_C, utils.KEY_QUANTITY_C}, utils.KEY_ID_C + "=?"
                , new String[]{String.valueOf(id)}, null, null, null, null);
        CartModel data = new CartModel();
        if (cursor != null)
            cursor.moveToFirst();
        data.setImage(cursor.getString(cursor.getColumnIndex(utils.KEY_IMAGE_C)));
        data.setPrice(cursor.getString(cursor.getColumnIndex(utils.KEY_PRICE_C)));
        data.setQuantity(cursor.getString(cursor.getColumnIndex(utils.KEY_QUANTITY_C)));
        data.setTitle(cursor.getString(cursor.getColumnIndex(utils.KEY_TITLE_C)));

        cursor.close();
        return data;
    }

    public List<CartModel> getAllProductInCart() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String getALL = " SELECT * FROM " + utils.TABLE_NAME_Cart;
        List<CartModel> productList = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(getALL, null);
        if (cursor.moveToFirst())
            do {
                CartModel data = new CartModel();
                data.setId(cursor.getInt(cursor.getColumnIndex(utils.KEY_ID_C)));
                data.setImage(cursor.getString(cursor.getColumnIndex(utils.KEY_IMAGE_C)));
                data.setTitle(cursor.getString(cursor.getColumnIndex(utils.KEY_TITLE_C)));
                data.setPrice(cursor.getString(cursor.getColumnIndex(utils.KEY_PRICE_C)));
                data.setQuantity(cursor.getString(cursor.getColumnIndex(utils.KEY_QUANTITY_C)));
                productList.add(data);
            } while (cursor.moveToNext());
        cursor.close();
        return productList;
    }
   public void deleteDataFromCart(int id) {
       SQLiteDatabase db = this.getWritableDatabase();
       db.delete(utils.TABLE_NAME_Cart, utils.KEY_ID_C
               + " =?", new String[]{String.valueOf(id)});
   }

    public void addToFavorite(FavoriteModel favoriteModel) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(utils.KEY_PRODUCTID_F, favoriteModel.getId());
        values.put(utils.KEY_ISFAVORITE_F, favoriteModel.isFavorite());
        sqLiteDatabase.insert(utils.TABLE_NAME_Favorite, null, values);
        sqLiteDatabase.close();
    }

    public List<FavoriteModel> getProductInFavorite() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String getALL = " SELECT * FROM " + utils.TABLE_NAME_Favorite;
        List<FavoriteModel> favoriteModels = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(getALL, null);
        if (cursor.moveToFirst())
            do {
                FavoriteModel favoriteModel = new FavoriteModel();
                favoriteModel.setId(cursor.getInt(cursor.getColumnIndex(utils.KEY_PRODUCTID_F)));
                favoriteModel.setFavorite(cursor.getString(cursor.getColumnIndex(utils.KEY_ISFAVORITE_F)));
                favoriteModels.add(favoriteModel);
            } while (cursor.moveToNext());
        cursor.close();
        return favoriteModels;
    }

    public void deleteDataFromFavorite(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(utils.TABLE_NAME_Favorite, utils.KEY_PRODUCTID_F
                + " =?", new String[]{String.valueOf(id)});
    }
    public void addOrder(OrderModel orderModel) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(utils.KEY_AMOUNT_O, orderModel.getAmount());
        values.put(utils.KEY_DATE_O, orderModel.getDateTime());
        sqLiteDatabase.insert(utils.TABLE_NAME_Order, null, values);
        sqLiteDatabase.close();
    }

    public List<OrderModel> getAllOrder() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String getAll =  " SELECT * FROM " + utils.TABLE_NAME_Order;
        List<OrderModel> orderModelList = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(getAll,null);
        if (cursor.moveToFirst())
            do {
                OrderModel orderModel = new OrderModel();
                orderModel.setId(cursor.getInt(cursor.getColumnIndex(utils.KEY_ID_O)));
                orderModel.setAmount(cursor.getString(cursor.getColumnIndex(utils.KEY_AMOUNT_O)));
                orderModel.setDateTime(cursor.getString(cursor.getColumnIndex(utils.KEY_DATE_O)));
                orderModelList.add(orderModel);
            } while (cursor.moveToNext());
        cursor.close();
        return orderModelList;
    }
    public OrderModel searchDataInOrder(int id) {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query(utils.TABLE_NAME_Order, new String[]{utils.KEY_ID_O, utils.KEY_AMOUNT_O
                        , utils.KEY_DATE_O}, utils.KEY_ID_O + "=?"
                , new String[]{String.valueOf(id)}, null, null, null, null);
        OrderModel data = new OrderModel();
        if (cursor != null)
            cursor.moveToFirst();
        data.setId(cursor.getInt(cursor.getColumnIndex(utils.KEY_ID_O)));
        data.setAmount(cursor.getString(cursor.getColumnIndex(utils.KEY_AMOUNT_O)));
        data.setDateTime(cursor.getString(cursor.getColumnIndex(utils.KEY_DATE_O)));

        cursor.close();
        return data;
    }

}
