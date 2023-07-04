package bzu.order_management.DTO;


import bzu.order_management.Entity.Order;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Data
public class CustomerDto {
    private Integer id;

    private String firstName;

    private String LastName;

    private Date bornAt;

    private List<Order> order;

}
