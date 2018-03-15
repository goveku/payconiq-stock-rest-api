package nl.payconiq.stock.dao;

import nl.payconiq.constants.AppConstants;
import nl.payconiq.sotck.model.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;

@Repository
public class StockServiceDAOImpl implements StockDAO<Stock> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Map<String, Object>> findAll() {
        return jdbcTemplate.queryForList(AppConstants.H2_SQL_FIND_ALL_STOCKS);
    }

    @Override
    public Stock findById(long id) {
        Object[] params = new Object[]{id};
        int[] types = new int[]{Types.BIGINT};
        return jdbcTemplate.queryForObject(AppConstants.H2_SQL_FIND_STOCK_BY_ID, params, types, (rs, rowNum) -> {
            Stock stock = new Stock();
            stock.setId(rs.getLong("ID"));
            stock.setName(rs.getString("Name"));
            stock.setCurrentPrice(rs.getBigDecimal("currentPrice"));
            stock.setLastUpdated(rs.getTimestamp("lastUpdated"));
            return stock;
        });
    }


    @Override
    public int createStock(Stock stock) {
        Object[] params = new Object[]{stock.getName(), stock.getCurrentPrice(), stock.getLastUpdated()};
        int[] types = new int[]{Types.VARCHAR, Types.DECIMAL, Types.TIMESTAMP};
        return jdbcTemplate.update(AppConstants.H2_SQL_INSERT_STOCK, params, types);
    }

    @Override
    public int updateStock(long id, Stock stock) {
        Object[] params = new Object[]{stock.getName(), stock.getCurrentPrice(), stock.getLastUpdated(), id};
        int[] types = new int[]{Types.VARCHAR, Types.DECIMAL, Types.TIMESTAMP, Types.BIGINT};
        return jdbcTemplate.update(AppConstants.H2_SQL_UPDATE_STOCK, params, types);
    }
}
