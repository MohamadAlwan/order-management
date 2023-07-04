package bzu.order_management.Reposetory;

import bzu.order_management.Entity.Product_order;
import bzu.order_management.Entity.Product_orderPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Product_orderRepository extends JpaRepository<Product_order, Product_orderPK> {
}
