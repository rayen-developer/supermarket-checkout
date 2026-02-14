package com.haiilo.checkout.domain;

import lombok.Getter;
import java.util.HashMap;
import java.util.Map;
import java.math.BigDecimal;

@Getter
public class Cart {
    // key: product name, value: CartItem
    private final Map<String, CartItem> items = new HashMap<>();

    // Add product with quantity
    public void addProduct(Product product, int quantity) {
        if (items.containsKey(product.getName())) {
            CartItem current = items.get(product.getName());
            current.setQuantity(current.getQuantity() + quantity);
        } else {
            items.put(product.getName(), new CartItem(product, quantity));
        }
    }

    // Remove a product by name
    public void removeProduct(String productName) {
        items.remove(productName);
    }

    // Get total price for this cart
    public BigDecimal getTotalPrice() {
        return items.values().stream()
                .map(CartItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
