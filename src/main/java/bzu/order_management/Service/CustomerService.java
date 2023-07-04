package bzu.order_management.Service;

import bzu.order_management.DTO.CustomerDto;

import java.util.List;

public interface CustomerService {
    CustomerDto createCustomer(CustomerDto customerDto);

    List<CustomerDto> getAllCustomer();

    CustomerDto getCustomerById(Integer id);

    CustomerDto updateCustomer(Integer id, CustomerDto customerDto);

    void deleteCustomer(Integer id);


}
