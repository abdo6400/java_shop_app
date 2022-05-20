package com.example.myshopapp.Model;

public class OrderModel {
    private int id;
    private String amount;
    private String dateTime;

    public OrderModel() {
    }

    public OrderModel(String amount, String dateTime) {
        this.amount = amount;
        this.dateTime = dateTime;
    }

    public OrderModel(int id, String amount, String dateTime) {
        this.id = id;
        this.amount = amount;
        this.dateTime = dateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

}
