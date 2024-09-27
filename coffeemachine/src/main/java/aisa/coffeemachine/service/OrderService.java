package aisa.coffeemachine.service;

import aisa.coffeemachine.dto.order.OrderRequest;
import aisa.coffeemachine.dto.order.OrderResponse;

import java.text.ParseException;
import java.util.List;
public interface OrderService {
    List<OrderResponse> getAllOrders();
    List<OrderResponse> getOrderByDrink(String drinkName);
    List<OrderResponse> getAllOrdersByDate(String date) throws ParseException;
    void createOrder(OrderRequest orderRequest);
}

