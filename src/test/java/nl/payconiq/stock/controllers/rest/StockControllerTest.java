package nl.payconiq.stock.controllers.rest;

import nl.payconiq.Application;
import nl.payconiq.sotck.model.Stock;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = "test")
public class StockControllerTest {

    @LocalServerPort
    private int port;

    private final String hostname = "http://localhost:";
    private String url = "/payconiq/api/stocks/";


    private TestRestTemplate restTemplate = new TestRestTemplate();

    private HttpHeaders headers = new HttpHeaders();

    @Test
    public void testGetStocks() {
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<ArrayList> response = restTemplate.exchange(hostname + port + url, HttpMethod.GET, entity, ArrayList.class);

        Assert.assertNotEquals(0, response.getBody().size());
    }


    @Test
    public void testGetStocksById() {

        int id = 1;
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<Stock> response = restTemplate.exchange(hostname + port + url + "/:id?id=" + id, HttpMethod.GET, entity, Stock.class);
        Stock expectedStock = new Stock();
        expectedStock.setId(1L);
        expectedStock.setName("Nederland 25");
        expectedStock.setCurrentPrice(BigDecimal.valueOf(526.26));
        expectedStock.setLastUpdated(new Date());

        Assert.assertEquals(expectedStock, response.getBody());
    }

    @Test
    public void testCreateStock() {
        headers.add("content-type", "application/json");
        Stock stock = new Stock();
        stock.setName("Test Stock Name 1");
        stock.setCurrentPrice(BigDecimal.valueOf(10.10));
        stock.setLastUpdated(new Date());

        HttpEntity<Stock> entity = new HttpEntity<>(stock, headers);
        ResponseEntity<String> response = restTemplate.exchange(hostname + port + url, HttpMethod.POST, entity, String.class);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testUpdateSuccessStock() {
        Stock stock = new Stock();
        stock.setCurrentPrice(new BigDecimal("1.01"));
        int id = 1;
        HttpEntity<Stock> entity = new HttpEntity<>(stock, headers);
        ResponseEntity<String> response = restTemplate.exchange(hostname + port + url + "/:id?id=" + id, HttpMethod.PUT, entity, String.class);
        String expected = "Stock is updated successfully ID:" + id + stock;

        Assert.assertEquals(expected, response.getBody());
    }


    @Test
    public void testUpdateFailedStock() {
        Stock stock = new Stock();
        stock.setCurrentPrice(new BigDecimal("1.01"));
        int id = 100;
        HttpEntity<Stock> entity = new HttpEntity<>(stock, headers);
        ResponseEntity<String> response = restTemplate.exchange(hostname + port + url + "/:id?id=" + id, HttpMethod.PUT, entity, String.class);
        String expectedResponse = "Stock is NOT updated successfully ID:" + id + stock;

        Assert.assertEquals(expectedResponse, response.getBody());
    }


}
