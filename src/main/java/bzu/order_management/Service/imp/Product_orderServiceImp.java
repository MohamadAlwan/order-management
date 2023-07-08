package bzu.order_management.Service.imp;

import bzu.order_management.DTO.Product_orderDto;
import bzu.order_management.Entity.Order;
import bzu.order_management.Entity.Product;
import bzu.order_management.Entity.Product_order;
import bzu.order_management.Entity.Product_orderPK;
import bzu.order_management.Reposetory.OrderRepository;
import bzu.order_management.Reposetory.ProductRepository;
import bzu.order_management.Reposetory.Product_orderRepository;
import bzu.order_management.Service.Product_orderService;
import bzu.order_management.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class Product_orderServiceImp implements Product_orderService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    Product_orderRepository product_orderRepository;

    @Autowired
    OrderRepository orderRepository;

    @Override
    public Product_orderDto createProduct_Order(Product_orderDto product_orderDto, Integer productId, Integer orderId) {

        Product_order product_order = mapToEntity(product_orderDto);
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order", "id", orderId));
        product_order.setProductId(product);
        product_order.setOrderId(order);

        Product_order product_order1 = product_orderRepository.save(product_order);
        return mapToDto(product_order1);

    }

    @Override
    public List<Product_orderDto> getAllProduct_Order() {
        List<Product_order> product_orders = product_orderRepository.findAll();
        return product_orders.stream().map(product_order -> mapToDto(product_order)).collect(Collectors.toList());
    }

    @Override
    public Product_orderDto getProduct_OrderById(Integer id) {
        Product_order product_order = product_orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product_order", "id", id));
        return mapToDto(product_order);
    }

    @Override
    public Product_orderDto updateProduct_Order(Integer id, Product_orderDto product_orderDto) {
        Product_order product_order = product_orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product_order", "id", id));
        product_order.setQuantity(product_orderDto.getQuantity());
        product_order.setPrice(product_orderDto.getPrice());
        product_order.setVat(product_orderDto.getVat());

        Product_order updatedProduct_order = product_orderRepository.save(product_order);
        return mapToDto(updatedProduct_order);
    }

    @Override
    public void deleteProduct_Order(Integer id) {
        Product_order product_order = product_orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product_order", "id", id));
        product_orderRepository.delete(product_order);
    }

    private Product_orderDto mapToDto(Product_order product_order) {
        Product_orderDto product_orderDto = new Product_orderDto();
        product_orderDto.setId(product_order.getId());
        product_orderDto.setQuantity(product_order.getQuantity());
        product_orderDto.setPrice(product_order.getPrice());
        product_orderDto.setVat(product_order.getVat());
        return product_orderDto;
    }

    private Product_order mapToEntity(Product_orderDto product_orderDto) {
        Product_order product_order = new Product_order();
        product_order.setQuantity(product_orderDto.getQuantity());
        product_order.setPrice(product_orderDto.getPrice());
        product_order.setVat(product_orderDto.getVat());
        return product_order;
    }
}
