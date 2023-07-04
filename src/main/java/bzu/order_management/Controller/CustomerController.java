package bzu.order_management.Controller;

import bzu.order_management.DTO.CustomerDto;
import bzu.order_management.Service.CustomerService;
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

@Api(value = "Customer controller exposes the CRUD operations in the REST APIs")
@RestController
@RequestMapping("customer")
public class CustomerController {
    private final Logger log = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @ApiOperation(value = "REST API to get all the customers")
    @RolesAllowed("ROLE_ADMIN")
    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAllCustomers(){
        return ResponseEntity.ok().body(customerService.getAllCustomer());
    }

    @ApiOperation(value = "REST API to get all customer by id")
    @RolesAllowed({"ROLE_USER","ROLE_ADMIN"})
    @GetMapping("{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable(name="id") Integer id){
        return ResponseEntity.ok().body(customerService.getCustomerById(id));
    }

    @ApiOperation(value = "REST API to create customers")
    @RolesAllowed("ROLE_ADMIN")
    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@Valid @RequestBody CustomerDto customerDto){
        if (customerDto.getId()!=null){
            log.error("Cannot have an ID {}" , customerDto);
        }
        return new ResponseEntity(customerService.createCustomer(customerDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "REST API to edit in existing customers")
    @RolesAllowed("ROLE_ADMIN")
    @PutMapping("{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable(name="id") Integer id,@Valid @RequestBody CustomerDto customerDto){
        return new ResponseEntity(customerService.updateCustomer(id,customerDto),HttpStatus.OK);
    }

    @ApiOperation(value = "REST API to delete customers")
    @RolesAllowed("ROLE_ADMIN")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable(name="id") Integer id){
        customerService.deleteCustomer(id);
        return new ResponseEntity<>("Deleted successfully",HttpStatus.OK);
    }

}
