package bzu.order_management.Service;

import bzu.order_management.DTO.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto createProduct(ProductDto productDto);

    List<ProductDto> getAllProducts();

    ProductDto getProductById(Integer id);

    ProductDto updateProduct(Integer id,ProductDto productDto);

    void deleteProduct(Integer id);

}
