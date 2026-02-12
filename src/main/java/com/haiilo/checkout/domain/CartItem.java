package com.haiilo.checkout.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CartItem {
    private Product product;
    private int quantity;

    // get total price for this item
    public java.math.BigDecimal getTotalPrice() {
        return product.getPrice().multiply(java.math.BigDecimal.valueOf(quantity));
    }
}
