package bzu.order_management.Controller;

import bzu.order_management.DTO.Product_orderDto;
import bzu.order_management.Service.Product_orderService;
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

@Api(value = "product_order controller exposes the CRUD operations in the REST APIs")
@RolesAllowed("ROLE_ADMIN")
@RestController
@RequestMapping("product_order")
public class Product_orderController {

    private final Logger log = LoggerFactory.getLogger(Product_orderController.class);

    @Autowired
    Product_orderService product_orderService;

    @ApiOperation(value = "REST API to get all product orders")
    @RolesAllowed({"ROLE_ADMIN","ROLE_ADMIN"})
    @GetMapping
    public ResponseEntity<List<Product_orderDto>> getAllProduct_orders(){
        return ResponseEntity.ok().body(product_orderService.getAllProduct_Order());
    }

    @ApiOperation(value = "REST API to get product orders by id")
    @RolesAllowed({"ROLE_USER","ROLE_ADMIN"})
    @GetMapping("{id}")
    public ResponseEntity<Product_orderDto> getProduct_orderById(@PathVariable(name="id") Integer id){
        return  ResponseEntity.ok().body(product_orderService.getProduct_OrderById(id));
    }

    @ApiOperation(value = "REST API to create product orders")
    @RolesAllowed("ROLE_ADMIN")
    @PostMapping("product/{pid}/order/{oid}")
    public ResponseEntity<Product_orderDto> createProduct_order(@PathVariable(name="pid")Integer pid
            , @PathVariable(name="oid")Integer oid, @Valid @RequestBody Product_orderDto product_orderDto){
        return new ResponseEntity(product_orderService.createProduct_Order(product_orderDto,pid,oid), HttpStatus.CREATED);
    }

    @ApiOperation(value = "REST API to edit product orders")
    @RolesAllowed("ROLE_ADMIN")
    @PutMapping("{id}")
    public ResponseEntity<Product_orderDto> updateProduct_order(@PathVariable(name="id") Integer id, @Valid @RequestBody Product_orderDto product_orderDto){
        return new ResponseEntity(product_orderService.updateProduct_Order(id,product_orderDto),HttpStatus.OK);
    }

    @ApiOperation(value = "REST API to delete product orders")
    @RolesAllowed("ROLE_ADMIN")
    @DeleteMapping("{id}")
    public ResponseEntity<String> updateProduct_order(@PathVariable(name="id") Integer id){
        product_orderService.deleteProduct_Order(id);
        return new ResponseEntity("Deleted successfully",HttpStatus.OK);
    }
}
