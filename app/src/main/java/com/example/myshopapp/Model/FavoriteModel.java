package com.example.myshopapp.Model;

public class FavoriteModel {

    private int id;
    private String isFavorite;

    public FavoriteModel() {
    }

    public FavoriteModel(int id, String isFavorite) {
        this.id = id;
        this.isFavorite = isFavorite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String isFavorite() {
        return isFavorite;
    }

    public void setFavorite(String favorite) {
        isFavorite = favorite;
    }
}
