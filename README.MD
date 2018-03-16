**Payconiq Stock Rest Service API**

     Payconiq Stock API service to create/update/find/view stock details.

**Approached solution.**

   Implemented Stock Rest API service with below operations to perform find, view all , create or update stocks.

    GET  /payconiq/api/stocks/ (get a list of stocks)

    GET  /payconiq/api/stocks/1 (get one stock from the list)

    PUT  /payconiq/api/stocks/1 (update the price of a single stock)

    POST /payconiq/api/stocks (create a stock)

This application uses in-memory database H2DB with STOCK table for storing stocks details.
STOCK table contains ID primary key with AUTOINCREAMENT its sequence number.

UI Page is available pointing to rest endpoint /payconiq/api/stocks/view. UI page displays the list of stocks stored in in-memory database(H2DB) STOCK table.


**Configuration details:**

    1. application.yml file contains server port number and H2DB database datasouce details.
    2. schema-h2.sql --  H2DB DDL script for creating STOCK table in JVM memory.
    3. data-h2.sql   --  Initial data set insert script.
    4. Use http://localhost:8086/swagger-ui link for testing Rest API operations.
    5. http://localhost:8086//payconiq/api/stocks/view   --- endpoint to display all stocks available in JVM memory.

** Software Used:**
   1. Java 8
   2. Spring Boot v2.0.0.RELEASE
   3. H2DB
   4. Spring JDBC
   5. Maven
   6. Swaggar
   7. IntelliJ
