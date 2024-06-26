/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.asconvertmvn.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tranm
 */
public class Cart {

    private List<Item> items;
    private int cartId;
    private int customerId;

    public Cart() {
        items = new ArrayList<>();
    }

    public Cart(List<Item> items) {
        this.items = items;
    }

    public Cart(int cartId, int customerId) {
        this.cartId = cartId;
        this.customerId = customerId;
        items = new ArrayList<>();
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public List<Item> getItems() {
        return items;
    }

    public Item getItemById(int id) {
        for (Item item : items) {
            if (item.getProduct().getProductId() == id) {
                return item;
            }
        }

        return null;
    }

    public int getQuantityById(int id) {
        Item item = getItemById(id);
        if (item != null) {
            return item.getQuantity();
        } else {
            return 0; // Or handle the case where the item is not found in the cart
        }
    }

    public void addItem(Item t) {
        //Exist item in cart 
        if (getItemById(t.getProduct().getProductId()) != null) {
            Item i = getItemById(t.getProduct().getProductId());
            i.setQuantity(i.getQuantity() + t.getQuantity());
        } else {
            //not exist in cart
//            t.setPrice(t.getProduct().getNewPrice());
            items.add(t);
        }
    }

    public void removeItem(int id) {
        if (getItemById(id) != null) {
            items.remove(getItemById(id));
        }
    }

    public int getTotalMoney() {
        int total = 0;
        for (Item item : items) {
            total += item.getQuantity() * item.getPrice();
        }
        return total;
    }

    public int getTotalMoneyDB() {
        int total = 0;
        for (Item item : items) {
            total += item.getPrice();
        }
        return total;
    }

    private Product getProductById(int id, List<Product> list) {
        for (Product product : list) {
            if (product.getProductId() == id) {
                return product;
            }
        }

        return null;
    }

    public Cart(String txt, List<Product> list) {
        items = new ArrayList<>();
        try {
            if (txt != null && txt.length() != 0) {
                String[] s = txt.split("\\.");
                for (String i : s) {
                    String[] n = i.split(":");
                    int id = Integer.parseInt(n[0]);
                    int quantity = Integer.parseInt(n[1]);
                    Product p = getProductById(id, list);
                    if (p != null) {
                        Item t = new Item();
                        t.setProduct(p);
                        t.setQuantity(quantity);
                        t.setPrice(p.getNewPrice());
                        addItem(t);
                    }
                }
            }
        } catch (NumberFormatException e) {
        }
    }

    @Override
    public String toString() {
        return "Cart{" + "items=" + items + ", cartId=" + cartId + ", customerId=" + customerId + '}';
    }

}
