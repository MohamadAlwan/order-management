package bzu.order_management.Controller;

import bzu.order_management.DTO.OrderDto;
import bzu.order_management.Service.OrderService;
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

@Api(value = "Order controller exposes the CRUD operations in the REST APIs")
@RestController
@RequestMapping("order")
public class OrderController {
    private final Logger log = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @ApiOperation(value = "REST API to get all orders")
    @RolesAllowed("ROLE_ADMIN")
    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders(){
        return ResponseEntity.ok().body(orderService.getAllOrders());
    }

    @ApiOperation(value = "REST API to get orders by id")
    @RolesAllowed({"ROLE_USER","ROLE_ADMIN"})
    @GetMapping("{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable(name="id") Integer id){
        return ResponseEntity.ok().body(orderService.getOrderById(id));
    }

    @ApiOperation(value = "REST API to create orders")
    @RolesAllowed("ROLE_ADMIN")
    @PostMapping("customer/{id}")
    public ResponseEntity<OrderDto> createOrder(@Valid @RequestBody OrderDto orderDto, @PathVariable(name="id") Integer id){
        if(orderDto.getId()!=null){
            log.error("Cannot have an ID {}" , orderDto);
        }
        return new ResponseEntity(orderService.createOrder(orderDto,id), HttpStatus.CREATED);
    }

    @ApiOperation(value = "REST API to edit orders")
    @RolesAllowed("ROLE_ADMIN")
    @PutMapping("{id}")
    public ResponseEntity<OrderDto> updateOrder(@PathVariable(name="id") Integer id,@Valid @RequestBody OrderDto orderDto){
        return new ResponseEntity(orderService.updateOrder(id,orderDto),HttpStatus.OK);
    }

    @ApiOperation(value = "REST API to delete orders")
    @RolesAllowed("ROLE_ADMIN")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable(name="id") Integer id){
        orderService.deleteOrder(id);
        return new ResponseEntity<>("Deleted successfully",HttpStatus.OK);
    }
}
