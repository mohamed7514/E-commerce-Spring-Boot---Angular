package com.khalil.ecommerce.dto;

import java.util.Set;

import com.khalil.ecommerce.entity.Address;
import com.khalil.ecommerce.entity.Customer;
import com.khalil.ecommerce.entity.Order;
import com.khalil.ecommerce.entity.OrderItem;

import lombok.Data;

@Data
public class Purchase {

    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;

}