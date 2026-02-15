package com.haiilo.checkout;

import com.haiilo.checkout.domain.Cart;
import com.haiilo.checkout.domain.MultiBuyOffer;
import com.haiilo.checkout.domain.Offer;
import com.haiilo.checkout.domain.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MultiBuyOfferTest {

    @Test
    void shouldApplyDiscountWhenMinimumQuantityReached() {

        Product apple = new Product("Apple", new BigDecimal("0.30"));
        Cart cart = new Cart();
        cart.addProduct(apple, 5);

        Offer offer = new MultiBuyOffer("Apple", 2, new BigDecimal("0.45"));

        BigDecimal discount = offer.calculateDiscount(cart);

        assertEquals(new BigDecimal("0.30"), discount);
    }

    @Test
    void shouldReturnZeroDiscountWhenMinimumQuantityNotReached() {

        Product apple = new Product("Apple", new BigDecimal("0.30"));
        Cart cart = new Cart();
        cart.addProduct(apple, 1);

        Offer offer = new MultiBuyOffer("Apple", 2, new BigDecimal("0.45"));

        BigDecimal discount = offer.calculateDiscount(cart);

        assertEquals(BigDecimal.ZERO, discount);
    }
}
