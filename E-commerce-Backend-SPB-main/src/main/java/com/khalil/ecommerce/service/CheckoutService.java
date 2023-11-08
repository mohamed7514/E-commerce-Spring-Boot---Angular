package com.khalil.ecommerce.service;

import com.khalil.ecommerce.dto.Purchase;
import com.khalil.ecommerce.dto.PurchaseResponse;

public interface CheckoutService {
	
	PurchaseResponse placeOrder(Purchase purchase);

}
