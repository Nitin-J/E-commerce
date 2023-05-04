package com.online.ecommerce.controller;

import com.online.ecommerce.dto.checkout.CheckoutItemDto;
import com.online.ecommerce.dto.checkout.StripeResponse;
import com.online.ecommerce.service.AuthenticationService;
import com.online.ecommerce.service.OrderService;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private OrderService orderService;

    @Operation(summary = "stripe session checkout api")
    @PostMapping("/createCheckoutSession")
    public ResponseEntity<StripeResponse> checkoutList(@RequestBody List<CheckoutItemDto> checkoutItemDtoList)
    throws StripeException {
        Session session = orderService.createSession(checkoutItemDtoList);
        StripeResponse stripeResponse = new StripeResponse(session.getId());
        return ResponseEntity.status(HttpStatus.OK).body(stripeResponse);
    }

}
