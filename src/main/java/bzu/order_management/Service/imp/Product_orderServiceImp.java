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
    public Product_orderDto getProduct_OrderById(Integer productId,Integer orderId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order", "id", orderId));
        Product_orderPK product_orderPK = new Product_orderPK(product,order);
        Product_order product_order = product_orderRepository.findById(product_orderPK).orElseThrow();
        return mapToDto(product_order);
    }

    @Override
    public Product_orderDto updateProduct_Order(Integer productId,Integer orderId, Product_orderDto product_orderDto) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order", "id", orderId));
        Product_orderPK product_orderPK = new Product_orderPK(product,order);
        Product_order product_order = product_orderRepository.findById(product_orderPK).orElseThrow();
        product_order.setVat(product_orderDto.getVat());
        product_order.setPrice(product_orderDto.getPrice());
        product_order.setQuantity(product_orderDto.getQuantity());
        return mapToDto(product_order);
    }

    @Override
    public void deleteProduct_Order(Integer productId,Integer orderId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order", "id", orderId));
        Product_orderPK product_orderPK = new Product_orderPK(product,order);
        Product_order product_order = product_orderRepository.findById(product_orderPK).orElseThrow();
        product_orderRepository.delete(product_order);
    }

    private Product_orderDto mapToDto(Product_order product_order){
        Product_orderDto product_orderDto  =  new Product_orderDto();
        Product_orderPK product_orderPK = new Product_orderPK(product_order.getProductId(),product_order.getOrderId());
        //product_orderDto.setProduct_orderPK(product_orderPK);
        product_orderDto.setPrice(product_order.getPrice());
        product_orderDto.setVat(product_order.getVat());
        product_orderDto.setQuantity(product_order.getQuantity());
        return product_orderDto;
    }

    private Product_order mapToEntity(Product_orderDto product_orderDto){
        Product_order product_order = new Product_order();
        product_order.setPrice(product_orderDto.getPrice());
        product_order.setQuantity(product_orderDto.getQuantity());
        product_order.setVat(product_orderDto.getVat());
        return product_order;
    }
}
