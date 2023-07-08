package bzu.order_management.Service;

import bzu.order_management.DTO.Product_orderDto;
import bzu.order_management.Entity.Product_orderPK;

import java.util.List;

public interface Product_orderService {

    Product_orderDto createProduct_Order(Product_orderDto product_orderDto,Integer productId,Integer orderId);

    List<Product_orderDto> getAllProduct_Order();

    Product_orderDto getProduct_OrderById(Integer id);

    Product_orderDto updateProduct_Order(Integer id, Product_orderDto product_orderDto);

    void deleteProduct_Order(Integer id);
}
