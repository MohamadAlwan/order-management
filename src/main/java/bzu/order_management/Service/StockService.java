package bzu.order_management.Service;

import bzu.order_management.DTO.StockDto;

import java.util.List;

public interface StockService {
    StockDto createStock(StockDto stockDto, Integer productId);

    List<StockDto> getAllStock();

    StockDto getStockById(Integer id);

    StockDto updateStock(Integer id,StockDto stockDto);

    void deleteStock(Integer id);
}
