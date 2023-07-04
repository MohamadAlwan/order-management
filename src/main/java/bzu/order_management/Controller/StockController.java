package bzu.order_management.Controller;

import bzu.order_management.DTO.StockDto;
import bzu.order_management.Service.imp.StockServiceImp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

@Api(value = "Stock controller exposes the CRUD operations in the REST APIs")
@RolesAllowed("ROLE_ADMIN")
@RestController
@RequestMapping("stock")
public class StockController {

    private final Logger log = LoggerFactory.getLogger(StockController.class);

    @Autowired
    private StockServiceImp stockServiceImp;

    @ApiOperation(value = "REST API to get all stocks")
    @GetMapping
    public ResponseEntity<List<StockDto>> getAllStocks(){
        return ResponseEntity.ok().body(stockServiceImp.getAllStock());
    }

    @ApiOperation(value = "REST API to get stocks by id")
    @GetMapping("{id}")
    public ResponseEntity<StockDto> getStockById(@PathVariable(name="id") Integer id){

        return ResponseEntity.ok().body(stockServiceImp.getStockById(id));
    }

    @ApiOperation(value = "REST API to create stocks")
    @PostMapping("product/{id}")
    public ResponseEntity<StockDto> createStock(@PathVariable(name="id") Integer productId,@Valid @RequestBody StockDto stockDto){
        if(stockDto.getId()!=null){
            log.error("Cannot have an ID {}" , stockDto);
        }
        return new ResponseEntity(stockServiceImp.createStock(stockDto,productId), HttpStatus.CREATED);
    }

    @ApiOperation(value = "REST API to edit stocks")
    @PutMapping("{id}")
    public ResponseEntity<StockDto> updateStock(@PathVariable(name="id") Integer id, StockDto stockDto){
        return new ResponseEntity(stockServiceImp.updateStock(id,stockDto),HttpStatus.OK);
    }

    @ApiOperation(value = "REST API to delete stocks")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteStock(@PathVariable(name="id")Integer id){
        stockServiceImp.deleteStock(id);
        return new ResponseEntity("Deleted successfully",HttpStatus.OK);
    }
}
