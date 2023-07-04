package bzu.order_management.DTO;


//import bzu.order_management.Entity.Product_orderPK;
import bzu.order_management.Entity.Product_orderPK;
import bzu.order_management.Entity.Stock;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class ProductDto {
    private Integer id;

    private String slug;

    private String name;

    private String reference;

    private Long price;

    private Long vat;

    private Boolean stackable;

    private List<Stock> stock;

    private List<Product_orderPK> product_order;
}
