package nl.payconiq.constants;

public final class AppConstants {
    private AppConstants() {
        // Prevent from initialization.
    }

    public static final String H2_SQL_FIND_ALL_STOCKS = "SELECT ID, Name , currentPrice  ,lastUpdated FROM STOCK";

    public static final String H2_SQL_FIND_STOCK_BY_ID = "SELECT * FROM STOCK WHERE ID = ?";

    public static final String H2_SQL_UPDATE_STOCK = "UPDATE STOCK SET NAME = ?, currentPrice = ?, lastUpdated = ? WHERE ID = ?";

    public static final String H2_SQL_INSERT_STOCK = "INSERT INTO STOCK (NAME, currentPrice, lastUpdated) VALUES (?,?,?) ";
}
