package bzu.order_management.DTO;

import bzu.order_management.Entity.Customer;
//import bzu.order_management.Entity.Product_orderPK;
import bzu.order_management.Entity.Order;
import bzu.order_management.Entity.Product_orderPK;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Data
public class OrderDto {

    private Integer id;

    private Date orderedAt;

    private List<Product_orderPK> product_order;
}
