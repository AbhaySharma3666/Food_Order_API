package com.abhayproj.service;

import com.abhayproj.dto.OrderResponse;
import com.abhayproj.dto.OrderRequest;
import com.razorpay.RazorpayException;

import java.util.List;
import java.util.Map;

public interface OrderService {
    OrderResponse createOrderWithPayment(OrderRequest request) throws RazorpayException;

    void verifyPayment(Map<String, String> paymentData, String status);

    List<OrderResponse> getUserOrders();

    void removeOrder(String orderId);

    List<OrderResponse> getOrderOfAllUsers();

    void updateOrderStatus(String orderId, String status);
}
