package bzu.order_management.Controller;

import bzu.order_management.DTO.ProductDto;
import bzu.order_management.Service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

@Api(value = "Product controller exposes the CRUD operations in the REST APIs")
@RolesAllowed("ROLE_ADMIN")
@RestController
@RequestMapping("product")
public class ProductController {

    private final Logger log = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @ApiOperation(value = "REST API to get all products")
    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        return ResponseEntity.ok().body(productService.getAllProducts());
    }

    @ApiOperation(value = "REST API to get products by id")
    @GetMapping("{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable(name="id") Integer id){
        return ResponseEntity.ok().body(productService.getProductById(id));
    }

    @ApiOperation(value = "REST API to create products")
    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productDto){
        return new ResponseEntity(productService.createProduct(productDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "REST API to edit products")
    @PutMapping("{id}")
    public ResponseEntity<ProductDto> updateProduct(@Valid @RequestBody ProductDto productDto,@PathVariable(name="id") Integer id){
        return new ResponseEntity(productService.updateProduct(id,productDto),HttpStatus.OK);
    }

    @ApiOperation(value = "REST API to delete products")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable(name="id") Integer id){
        return new ResponseEntity("Deleted successfully",HttpStatus.OK);
    }

}
