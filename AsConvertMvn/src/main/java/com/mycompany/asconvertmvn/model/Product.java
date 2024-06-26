/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.asconvertmvn.model;

import lombok.Builder;

/**
 *
 * @author tranm
 */
@Builder
public class Product {

    private int productId;
    private String productName;
    private Category category;
    private int quantity;
    private Double sale;
    private Double price;
    private String img;
    private String description;

    public Product() {
    }

    public Product(int productId, String productName, Category category, int quantity, double sale, double price, String img, String description) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.quantity = quantity;
        this.sale = sale;
        this.price = price;
        this.img = img;
        this.description = description;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSale() {
        return sale;
    }

    public void setSale(double sale) {
        this.sale = sale;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
    public double getNewPrice() {
        if (sale > 0) {
            return price - (price * sale);
        } else {
            return price;
        }
    }
    
    public int getPercentSale() {
         if (sale > 0) {
            return (int)(sale * 100);
        } else {
            return sale.intValue();
        }
    }

    @Override
    public String toString() {
        return "Product{" + "productId=" + productId + ", productName=" + productName + ", category=" + category + ", quality=" + quantity + ", sale=" + sale + ", price=" + price + ", img=" + img + ", description=" + description + '}';
    }
    
    

}