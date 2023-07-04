package bzu.order_management.DTO;

import bzu.order_management.Entity.Product;
import lombok.Data;

import java.util.Date;
@Data
public class StockDto {
    private Integer id;

    private Integer quantity;

    private Date updatedAt;
}
