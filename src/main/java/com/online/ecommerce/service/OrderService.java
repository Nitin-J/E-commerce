package com.online.ecommerce.service;

import com.online.ecommerce.dto.checkout.CheckoutItemDto;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;

import java.util.List;

public interface OrderService {
    Session createSession(List<CheckoutItemDto> checkoutItemDtoList) throws StripeException;
}
