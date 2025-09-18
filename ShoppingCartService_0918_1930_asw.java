// 代码生成时间: 2025-09-18 19:30:34
package com.example.demo.service;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class ShoppingCartService {
    // In-memory shopping cart storage
    private Map<String, ShoppingCart> shoppingCarts = new HashMap<>();

    public ShoppingCart createCart() {
        String cartId = UUID.randomUUID().toString();
        ShoppingCart newCart = new ShoppingCart(cartId);
        shoppingCarts.put(cartId, newCart);
        return newCart;
    }

    public ShoppingCart getCart(String cartId) {
        if (!shoppingCarts.containsKey(cartId)) {
            throw new IllegalArgumentException("Cart with ID: " + cartId + " does not exist");
        }
        return shoppingCarts.get(cartId);
    }

    public void addItem(String cartId, Item item) {
        ShoppingCart cart = getCart(cartId);
        cart.addItem(item);
    }

    public void removeItem(String cartId, String itemId) {
        ShoppingCart cart = getCart(cartId);
        cart.removeItem(itemId);
    }

    public void clearCart(String cartId) {
        ShoppingCart cart = getCart(cartId);
        cart.clear();
    }

    public Map<String, Double> getCartTotals(String cartId) {
        ShoppingCart cart = getCart(cartId);
        return cart.getTotals();
    }
}

// Represents a shopping cart
class ShoppingCart {
    private String id;
    private Map<String, Item> items = new HashMap<>();

    public ShoppingCart(String id) {
        this.id = id;
    }

    public void addItem(Item item) {
        items.put(item.getId(), item);
    }

    public void removeItem(String itemId) {
        if (items.containsKey(itemId)) {
            items.remove(itemId);
        }
    }

    public void clear() {
        items.clear();
    }

    public Map<String, Double> getTotals() {
        Map<String, Double> totals = new HashMap<>();
        items.forEach((itemId, item) -> totals.put(itemId, item.getPrice() * item.getQuantity()));
        return totals;
    }
}

// Represents an item in the shopping cart
class Item {
    private String id;
    private String name;
    private double price;
    private int quantity;

    public Item(String id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}
