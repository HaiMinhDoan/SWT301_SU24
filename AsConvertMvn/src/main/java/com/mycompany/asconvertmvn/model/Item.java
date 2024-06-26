/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.asconvertmvn.model;

/**
 *
 * @author tranm
 */
public class Item {

    private int cartId;
    private Product product;
    private int quantity;
    private double price;

    public Item() {
    }

    public Item( int cartId, Product product, int quantity, double price) {
        this.cartId = cartId;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }


    
    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item{"  + ", cartId=" + cartId + ", product=" + product + ", quantity=" + quantity + ", price=" + price + '}';
    }

   

}
