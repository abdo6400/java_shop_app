package com.example.myshopapp.Utils;

public class utils {
    //Database
    public static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME="ProductDB";
    //Tables->Name
    public static final String TABLE_NAME_UserData="userData";
    public static final String TABLE_NAME_Product="Product";
    public static final String TABLE_NAME_Cart="Cart";
    public static final String TABLE_NAME_Order="OrderTable";
    public static final String TABLE_NAME_Favorite="Favorite";
    public static final String TABLE_NAME_User_Order_Cart="User_Order_Cart";
    //************************************************************//
    //Table->UserData
    public static final String KEY_ID_U="id_u";
    public static final String KEY_IMAGE_U="imageUrl_U";
    public static final String KEY_USERNAME_U="username";
    public static final String KEY_PHONE_U="phoneNumber";
    //************************************************************//
    //Table->Product
    public static final String KEY_ID_P="id_p";
    public static final String KEY_IMAGE_P="imageUrl_P";
    public static final String KEY_TITLE_P="title_p";
    public static final String KEY_PRICE_P="price_p";
    public static final String KEY_DESCRIPTION_P="description_p";
    //************************************************************//
    //Table->Cart
    public static final String KEY_ID_C="id_c";
    public static final String KEY_IMAGE_C="imageUrl_C";
    public static final String KEY_TITLE_C="title_c";
    public static final String KEY_PRICE_C="price_c";
    public static final String KEY_QUANTITY_C="quantity_c";
    //************************************************************//
    //Table->Order
    public static final String KEY_ID_O="id_o";
    public static final String KEY_AMOUNT_O="amount_o";
    public static final String KEY_DATE_O="date_o";
    //************************************************************//
    //Table->Favorite
    public static final String KEY_PRODUCTID_F="productId";
    public static final String KEY_ISFAVORITE_F="isFavorite";
    //************************************************************//


}
