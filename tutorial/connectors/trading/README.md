To run the trading example, open a terminal in this directory and run the following commands:
```
mvn install
cd trading-eis
mvn exec:java
```
Launch http://localhost:8080/trading/

To stop the example, press `CTRL+C` in the terminal to close the `trading-eis` application, then undeploy the `trading-ear` application, run the following commands:
```
cd ..
mvn cargo:undeploy
```
