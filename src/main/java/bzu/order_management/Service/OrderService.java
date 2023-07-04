package bzu.order_management.Service;

import bzu.order_management.DTO.OrderDto;
import bzu.order_management.Entity.Customer;

import java.util.List;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDto, Integer customerId);

    List<OrderDto> getAllOrders();

    List<OrderDto> getAllOrdersForCustomer(Integer id);

    OrderDto getOrderById(Integer id);

    OrderDto updateOrder(Integer id, OrderDto orderDto);

    void deleteOrder(Integer id);
}
