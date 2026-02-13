package com.haiilo.checkout.domain;

import java.math.BigDecimal;

public class MultiBuyOffer implements Offer {
    private final String productName;
    private final int minQuantity;
    private final BigDecimal offerPrice;

    public MultiBuyOffer(String productName,
                      int minQuantity,
                      BigDecimal offerPrice) {
        this.productName = productName;
        this.minQuantity = minQuantity;
        this.offerPrice = offerPrice;
    }

    @Override
    public BigDecimal calculateDiscount(Cart cart) {

        CartItem item = cart.getItems().get(productName);

        // Check if item exists or not
        if (item == null) {
            return BigDecimal.ZERO;
        }

        int quantity = item.getQuantity();

        // Check if the quantity to buy applies to offer quantity rule
        if (quantity < minQuantity) {
            return BigDecimal.ZERO;
        }

        // how many times the offer is applied
        int offerApplications  = quantity / minQuantity;

        BigDecimal unitPrice = item.getProduct().getPrice();
        int discountedItems = offerApplications  * minQuantity;
        // final cart price without discount
        BigDecimal normalPrice = unitPrice.multiply(
                BigDecimal.valueOf(discountedItems)
        );

        // Offer price for those packets
        BigDecimal finalOfferPrice = offerPrice.multiply(
                BigDecimal.valueOf(offerApplications )
        );

        return normalPrice.subtract(finalOfferPrice);
    }

}
