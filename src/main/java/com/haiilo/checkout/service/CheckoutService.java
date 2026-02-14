package com.haiilo.checkout.service;

import com.haiilo.checkout.domain.Cart;
import com.haiilo.checkout.domain.Offer;

import java.math.BigDecimal;
import java.util.List;

public class CheckoutService {

    private final List<Offer> offers;

    public CheckoutService(List<Offer> offers) {
        this.offers = offers;
    }

    public BigDecimal checkout(Cart cart) {

        // Step 1: total price without discount
        BigDecimal totalPrice = cart.getTotalPrice();

        // Step 2: calculate total discount of all offers
        BigDecimal totalDiscount = offers.stream()
                .map(offer -> offer.calculateDiscount(cart))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Step 3: subtract discount from totalPrice
        return totalPrice.subtract(totalDiscount);
    }
}
