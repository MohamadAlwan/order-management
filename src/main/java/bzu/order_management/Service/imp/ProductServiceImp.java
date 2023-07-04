package bzu.order_management.Service.imp;

import bzu.order_management.DTO.ProductDto;
import bzu.order_management.Entity.Product;
import bzu.order_management.Reposetory.ProductRepository;
import bzu.order_management.Service.ProductService;
import bzu.order_management.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product = mapToEntity(productDto);
        productRepository.save(product);

        ProductDto productDto1 = mapToDto(product);
        return productDto1;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(product -> mapToDto(product)).collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductById(Integer id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
        return mapToDto(product);
    }

    @Override
    public ProductDto updateProduct(Integer id, ProductDto productDto) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));

        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setReference(productDto.getReference());
        product.setSlug(productDto.getSlug());
        product.setStackable(productDto.getStackable());
        product.setVat(productDto.getVat());
        product.setStock(productDto.getStock());

        Product product1 = productRepository.save(product);

        return mapToDto(product1);
    }

    @Override
    public void deleteProduct(Integer id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
        productRepository.delete(product);
    }
    private ProductDto mapToDto(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setReference(product.getReference());
        productDto.setSlug(product.getSlug());
        productDto.setStackable(product.getStackable());
        productDto.setStock(product.getStock());
        productDto.setVat(product.getVat());
        return productDto;
    }

    private Product mapToEntity(ProductDto productDto){
        Product product=new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setReference(productDto.getReference());
        product.setSlug(productDto.getSlug());
        product.setStackable(productDto.getStackable());
        product.setVat(productDto.getVat());
        product.setStock(productDto.getStock());
        return product;
    }
}
