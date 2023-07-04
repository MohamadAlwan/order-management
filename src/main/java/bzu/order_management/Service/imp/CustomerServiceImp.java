package bzu.order_management.Service.imp;

import bzu.order_management.DTO.CustomerDto;
import bzu.order_management.Entity.Customer;
import bzu.order_management.Reposetory.CustomerRepository;
import bzu.order_management.Service.CustomerService;
import bzu.order_management.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImp implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;
    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer customer = mapToEntity(customerDto);
        customerRepository.save(customer);

        CustomerDto customerDto1 = mapToDto(customer);
        return customerDto1;
    }

    @Override
    public List<CustomerDto> getAllCustomer() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(customer -> mapToDto(customer)).collect(Collectors.toList());
    }

    @Override
    public CustomerDto getCustomerById(Integer id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));
        return mapToDto(customer);
    }

    @Override
    public CustomerDto updateCustomer(Integer id, CustomerDto customerDto) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setBornAt(customerDto.getBornAt());
        customer.setOrders(customerDto.getOrder());

        Customer customer1 = customerRepository.save(customer);
        return mapToDto(customer1);
    }

    @Override
    public void deleteCustomer(Integer id) {
        Customer customer=customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));
        customerRepository.delete(customer);
    }

    CustomerDto mapToDto (Customer customer){
     CustomerDto customerDto = new CustomerDto();
     customerDto.setId(customer.getId());
     customerDto.setFirstName(customer.getFirstName());
     customerDto.setLastName(customer.getLastName());
     customerDto.setBornAt(customer.getBornAt());
     customerDto.setOrder(customer.getOrders());
     return customerDto;
    }

    Customer mapToEntity(CustomerDto customerDto){
        Customer customer = new Customer();
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setBornAt(customerDto.getBornAt());
        customer.setOrders(customerDto.getOrder());
        return customer;
    }
}
