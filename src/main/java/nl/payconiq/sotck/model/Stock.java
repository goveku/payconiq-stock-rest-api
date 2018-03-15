package nl.payconiq.sotck.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

public class Stock implements Serializable {


    private Long id;
    private String name;
    private Date lastUpdated;
    private BigDecimal currentPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Stock)) {
            return false;
        }

        Stock stock = (Stock) obj;
        if (!id.equals(stock.id)) {
            return false;
        }

        if (!name.equals(stock.name)) {
            return false;
        }

        return stock.currentPrice.equals(currentPrice);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(new Object[]{
                id,
                name,
                currentPrice
        });
    }

    @Override
    public String toString() {
        return "[Sock name : " + this.getName() + ", Current Price :" + this.getCurrentPrice() + ", Last updated: " + this.getLastUpdated() + "]";
    }
}
