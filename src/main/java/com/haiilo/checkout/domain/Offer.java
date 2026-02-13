package com.haiilo.checkout.domain;

import java.math.BigDecimal;

public interface Offer {

    BigDecimal calculateDiscount(Cart cart);
}
