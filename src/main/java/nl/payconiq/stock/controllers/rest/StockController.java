package nl.payconiq.stock.controllers.rest;

import io.swagger.annotations.ApiOperation;
import nl.payconiq.sotck.model.Stock;
import nl.payconiq.stock.dao.StockDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequestMapping(value = "/payconiq/api")
@RestController
public class StockController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StockController.class);
    @Autowired
    private StockDAO stockService;

    @ApiOperation(value = "View a list of available Stocks details", response = Iterable.class)
    @RequestMapping(value = "/stocks", method = RequestMethod.GET)
    public List<Map<String, Object>> findAll() {
        LOGGER.debug("Calling stocks list get ");
        return stockService.findAll();
    }

    @ApiOperation(value = "Create stock", response = Iterable.class)
    @RequestMapping(value = "/stocks", method = RequestMethod.POST)
    public ResponseEntity createStock(@RequestBody Stock stock) {
        LOGGER.debug("Creating a stock : " + stock);

        int response = stockService.createStock(stock);
        if (response == 1) {
            LOGGER.debug("Created stock:  " + stock);
            return new ResponseEntity("Stock is created successfully", HttpStatus.OK);
        }
        LOGGER.debug("Stock is not created. Something went wrong while crating :" + stock);

        return new ResponseEntity("Stock is NOT created successfully", HttpStatus.NOT_MODIFIED);

    }


    @ApiOperation(value = "Find stock by ID ", response = Iterable.class)
    @RequestMapping(value = "/stocks/{id}", method = RequestMethod.GET)
    public @ResponseBody
    String  findById(@RequestParam(value = "id") int id) {
        LOGGER.debug("Finding stock for : " + id);
        Stock stock = null;
        try {
            stock = stockService.findById(id);

        } catch (IncorrectResultSizeDataAccessException e) {
            LOGGER.error("Stock is not found with id " + id);
            return "Stock is not found with id " + id;
        }

        return "Stock is found with id " + id + stock.toString();

    }


    @ApiOperation(value = "Update stock  ", response = Iterable.class)
    @RequestMapping(value = "/stocks/{id}", method = RequestMethod.PUT)
    public @ResponseBody
    String update(@RequestParam long id, @RequestBody Stock stock) {
        LOGGER.debug(String.format("Updating stock for : %s stock %s:", id, stock));

        int response = stockService.updateStock(id, stock);
        if (response == 1) {
            LOGGER.debug(String.format("Updated stock for : %s - stock %s:", id, stock));

            return "Stock is updated successfully ID:" + id + stock;
        }
        return "Stock is NOT updated successfully ID:" + id + stock;

    }


}



