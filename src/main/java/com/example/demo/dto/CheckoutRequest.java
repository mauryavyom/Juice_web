package com.example.demo.dto;

import java.util.List;

public class CheckoutRequest {
    private List<CheckoutItem> items;
    private double totalAmount;

    public List<CheckoutItem> getItems() {
        return items;
    }
    public void setItems(List<CheckoutItem> items) {
        this.items = items;
    }
    public double getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}