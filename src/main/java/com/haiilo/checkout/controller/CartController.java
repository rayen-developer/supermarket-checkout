package com.haiilo.checkout.controller;

import com.haiilo.checkout.domain.Cart;
import com.haiilo.checkout.domain.MultiBuyOffer;
import com.haiilo.checkout.domain.Offer;
import com.haiilo.checkout.domain.Product;
import com.haiilo.checkout.service.CheckoutService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final Cart cart = new Cart();
    // available offers as example
    private final List<Offer> offers = List.of(
            new MultiBuyOffer("Apple", 2, new BigDecimal("0.45")),
            new MultiBuyOffer("Banana", 3, new BigDecimal("1.00"))
    );

    private final CheckoutService checkoutService = new CheckoutService(offers);

    // Add product to cart
    @PostMapping("/add")
    public String addProduct(@RequestParam String name,
                             @RequestParam double price,
                             @RequestParam int quantity) {
        Product product = new Product(name, BigDecimal.valueOf(price));
        cart.addProduct(product, quantity);
        return quantity + " x " + name + " added to cart.";
    }

    // Get cart normal total price
    @GetMapping("/totalPrice")
    public BigDecimal getTotalPrice() {
        return cart.getTotalPrice();
    }

    // Checkout and get final price with discount
    @GetMapping("/checkout")
    public BigDecimal checkout() {
        return checkoutService.checkout(cart);
    }
}