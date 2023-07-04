package bzu.order_management.Service.imp;


import bzu.order_management.DTO.OrderDto;
import bzu.order_management.Entity.Customer;
import bzu.order_management.Entity.Order;
import bzu.order_management.Reposetory.CustomerRepository;
import bzu.order_management.Reposetory.OrderRepository;
import bzu.order_management.Service.OrderService;
import bzu.order_management.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImp implements OrderService {


    private Customer customer;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public OrderDto createOrder(OrderDto orderDto, Integer customerId) {
        Order order = mapToEntity(orderDto);
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("Customer", "id", customerId));
        order.setCustomerId(customer);

        Order order1 =  orderRepository.save(order);
        return mapToDto(order1);
    }

    @Override
    public List<OrderDto> getAllOrders() {
        List<Order> orderDto = orderRepository.findAll();
        return orderDto.stream().map(order -> mapToDto(order)).collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getAllOrdersForCustomer(Integer id) {
        return null;
    }

    @Override
    public OrderDto getOrderById(Integer id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order", "id", id));
        return mapToDto(order);
    }

    @Override
    public OrderDto updateOrder(Integer id, OrderDto orderDto) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order", "id", id));
        order.setOrderedAt(orderDto.getOrderedAt());
       //order.setProduct_order(orderDto.getProduct_order());

        Order order1 = orderRepository.save(order);
        return mapToDto(order1);
    }

    @Override
    public void deleteOrder(Integer id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order", "id", id));
        orderRepository.delete(order);
    }

    private OrderDto mapToDto(Order order){
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        //orderDto.setProduct_order(order.getProduct_order());
        orderDto.setOrderedAt(order.getOrderedAt());
        return orderDto;
    }

    private Order mapToEntity(OrderDto orderDto){
        Order order = new Order();
       // order.setProduct_order(orderDto.getProduct_order());
        order.setOrderedAt(orderDto.getOrderedAt());
        return order;
    }
}
