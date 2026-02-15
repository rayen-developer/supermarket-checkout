package com.haiilo.checkout;

import com.haiilo.checkout.domain.Cart;
import com.haiilo.checkout.domain.MultiBuyOffer;
import com.haiilo.checkout.domain.Offer;
import com.haiilo.checkout.domain.Product;
import com.haiilo.checkout.service.CheckoutService;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckoutServiceTest {

    @Test
    void shouldCalculateFinalPriceWithDiscount() {

        Product apple = new Product("Apple", new BigDecimal("0.30"));

        Cart cart = new Cart();
        cart.addProduct(apple, 5);

        Offer offer = new MultiBuyOffer("Apple", 2, new BigDecimal("0.45"));

        CheckoutService checkoutService =
                new CheckoutService(List.of(offer));

        BigDecimal finalPrice = checkoutService.checkout(cart);

        assertEquals(new BigDecimal("1.20"), finalPrice);
    }

    @Test
    void shouldReturnFinalPriceWhenNoOffers() {

        Product apple = new Product("Apple", new BigDecimal("0.30"));

        Cart cart = new Cart();
        cart.addProduct(apple, 3);

        CheckoutService checkoutService =
                new CheckoutService(List.of());

        BigDecimal finalPrice = checkoutService.checkout(cart);

        assertEquals(new BigDecimal("0.90"), finalPrice);
    }
}
