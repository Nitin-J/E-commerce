package com.online.ecommerce.service.impl;

import com.online.ecommerce.dto.checkout.CheckoutItemDto;
import com.online.ecommerce.service.OrderService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Value("${BASE_URL}")
    private String baseUrl;

    @Value("${STRIPE_SECRET_KEY}")
    private String apiKey;

    @Override
    public Session createSession(List<CheckoutItemDto> checkoutItemDtoList) throws StripeException {
        //Success and fail URL

        String successUrl = baseUrl+"payment/success";

        String failureUrl = baseUrl+"payment/failed";

        Stripe.apiKey = apiKey;

        List<SessionCreateParams.LineItem> sessionItemList = new ArrayList<>();
        for(CheckoutItemDto checkoutItemDto: checkoutItemDtoList){
            sessionItemList.add(createSessionLineItem(checkoutItemDto));
        }
        SessionCreateParams params = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setCancelUrl(failureUrl)
                .addAllLineItem(sessionItemList)
                .setSuccessUrl(successUrl)
                .build();

        return Session.create(params);
    }

    private SessionCreateParams.LineItem createSessionLineItem(CheckoutItemDto checkoutItemDto) {
        return SessionCreateParams.LineItem.builder().
                setPriceData(createPriceDate(checkoutItemDto)).
                setQuantity(Long.parseLong(String.valueOf(checkoutItemDto.getQuantity()))).
                build();
    }

    private SessionCreateParams.LineItem.PriceData createPriceDate(CheckoutItemDto checkoutItemDto) {
        return SessionCreateParams.LineItem.PriceData.builder().
                setCurrency("usd").
                setUnitAmount((long)(checkoutItemDto.getPrice()*100)).
                setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
                        .setName(checkoutItemDto.getProductName())
                        .build()
                ).build();

    }
}
