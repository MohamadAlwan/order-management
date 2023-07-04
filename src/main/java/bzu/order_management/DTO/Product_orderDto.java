package bzu.order_management.DTO;

import bzu.order_management.Entity.Product_orderPK;
import lombok.Data;

@Data
public class Product_orderDto {

    private Product_orderPK product_orderPK;

    private Integer quantity;

    private Long price;

    private Long vat;
}
