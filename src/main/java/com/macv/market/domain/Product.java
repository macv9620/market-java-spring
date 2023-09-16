package com.macv.market.domain;

import com.macv.market.persistence.entity.Categoria;

import java.util.Objects;

public class Product {

    private int id;
    private String name;
    private int categoryId;
    private double price;
    private int stock;
    private boolean active;
    private Category category;
    private String barCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    @Override
    public String toString() {
        return "id: " + id + " name: " + name + " categoryId: " + categoryId +
                " price: " + price + " stock: " + stock + " active: " + active +
                " category: " + category + " barCode: " + barCode;
    }

    public boolean checkFields(){
        return Objects.equals(name, "") || categoryId < 1 ||
                Objects.equals(barCode, "") || price < 0 || stock < 0;
    }
}
