To run the traffic example, open a terminal in this directory and run the following commands:
```
cd traffic-eis
mvn install
mvn exec:java
```
Open another terminal in this directory and run the following command:
```
mvn install
```
Launch http://localhost:8080/traffic/

To stop the example, first undeploy the `traffic-ear` application from the second terminal, run the following command:
```
mvn cargo:undeploy
```
then press `CTRL+C` in the first terminal to close the `traffic-eis` application.