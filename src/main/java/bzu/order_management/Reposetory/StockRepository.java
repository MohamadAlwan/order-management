package bzu.order_management.Reposetory;

import bzu.order_management.Entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock,Integer> {
}
