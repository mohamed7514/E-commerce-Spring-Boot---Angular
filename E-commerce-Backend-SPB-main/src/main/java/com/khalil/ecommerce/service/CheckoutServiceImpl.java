package com.khalil.ecommerce.service;

import java.util.Set;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khalil.ecommerce.dao.Customerrepository;
import com.khalil.ecommerce.dto.Purchase;
import com.khalil.ecommerce.dto.PurchaseResponse;
import com.khalil.ecommerce.entity.Customer;
import com.khalil.ecommerce.entity.Order;
import com.khalil.ecommerce.entity.OrderItem;

@Service
public class CheckoutServiceImpl implements CheckoutService{

	@Autowired
	private Customerrepository customerrepository;
	
	@Override
	@Transactional
	public PurchaseResponse placeOrder(Purchase purchase) {
		
		// retrieve the order info from dto 
		Order order = purchase.getOrder();
		// generate tracking number
		String orderTrackingNumber = generateOrderTrackingNumber();
		order.setOrderTrackingNumber(orderTrackingNumber);
		
		// populate order with orderItems
		Set<OrderItem> orderItems = purchase.getOrderItems();
		orderItems.forEach(item -> order.add(item));
		 
		// populate order with billingAddress and shippingAddress
		order.setBillingAddress(purchase.getBillingAddress());
		order.setShippingAddress(purchase.getShippingAddress());
		// populate costumer with order
		Customer customer= purchase.getCustomer();
		
		// check if this is an existing customer 
		String theEmail = customer.getEmail() ;
		Customer customerFromDB = customerrepository.findByEmail(theEmail);
		if(customerFromDB != null) {
			// assign the order to this cust 
			customer = customerFromDB;
			
		}
		
		customer.add(order);
		
		// save to data base
		customerrepository.save(customer);
		
		// return response 
		return new PurchaseResponse(orderTrackingNumber);
	}

	private String generateOrderTrackingNumber() {
		// generate random UUID number (Universally Unique IDentifier)
		
		return UUID.randomUUID().toString();
	}

}
