package com.abhayproj.service;

import com.abhayproj.io.OrderResponse;
import com.abhayproj.io.OrderRequest;
import com.razorpay.RazorpayException;

import java.util.List;
import java.util.Map;

public interface OrderService {
    OrderResponse createOrderWithPayment(OrderRequest request) throws RazorpayException;

    void verifyPayment(Map<String, String> paymentData, String status);

    List<OrderResponse> getUserOrders();

    void removeOrder(String OrderId);

    List<OrderResponse> getOrderOfAllUsers();

    void updateOrderStatus(String orderId, String status);
}
