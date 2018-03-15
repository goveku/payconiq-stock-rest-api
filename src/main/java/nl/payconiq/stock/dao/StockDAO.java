package nl.payconiq.stock.dao;

import nl.payconiq.sotck.model.Stock;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface StockDAO<E extends Serializable> {
    List<Map<String, Object>> findAll();

    Stock findById(long id);

    int createStock(E e);

    int updateStock(long id, E e);

}
