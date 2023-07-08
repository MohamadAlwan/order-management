package bzu.order_management.DTO;

import bzu.order_management.Entity.Product_orderPK;
import lombok.Data;

@Data
public class Product_orderDto {
    private Integer id;
    private Integer productId;
    private Integer orderId;
    private Integer quantity;
    private Long price;
    private Long vat;
}