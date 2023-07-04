package bzu.order_management.Service.imp;

import bzu.order_management.DTO.StockDto;
import bzu.order_management.Entity.Product;
import bzu.order_management.Entity.Stock;
import bzu.order_management.Reposetory.ProductRepository;
import bzu.order_management.Reposetory.StockRepository;
import bzu.order_management.Service.StockService;
import bzu.order_management.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockServiceImp implements StockService {

    private Product product;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public StockDto createStock(StockDto stockDto,Integer productId) {
        Stock stock = mapToEntity(stockDto);
        Product product = productRepository.findById(productId).orElseThrow();
        stock.setProductID(product);

        Stock stock1 = stockRepository.save(stock);
        return mapToDto(stock1);
    }

    @Override
    public List<StockDto> getAllStock() {
        List<Stock> stocks = stockRepository.findAll();
        return stocks.stream().map(stock -> mapToDto(stock)).collect(Collectors.toList());
    }

    @Override
    public StockDto getStockById(Integer id) {
        Stock stock = stockRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Stock", "id", id));
        return mapToDto(stock);
    }

    @Override
    public StockDto updateStock(Integer id, StockDto stockDto) {
        Stock stock = stockRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Stock", "id", id));
        stock.setQuantity(stockDto.getQuantity());
        stock.setUpdatedAt(stockDto.getUpdatedAt());

        Stock stock1 = stockRepository.save(stock);
        return mapToDto(stock1);
    }

    @Override
    public void deleteStock(Integer id) {
        Stock stock = stockRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Stock", "id", id));
        stockRepository.delete(stock);
    }

    private StockDto mapToDto(Stock stock){
        StockDto stockDto = new StockDto();
        stockDto.setId(stock.getId());
        stockDto.setQuantity(stock.getQuantity());
        stockDto.setUpdatedAt(stock.getUpdatedAt());
        return stockDto;
    }

    private Stock mapToEntity(StockDto stockDto){
        Stock stock = new Stock();
        stock.setQuantity(stockDto.getQuantity());
        stock.setUpdatedAt(stockDto.getUpdatedAt());

        return stock;
    }
}
