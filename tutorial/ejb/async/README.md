To run the async example, open a terminal in this directory and run the following commands:
```
mvn install
cd async-smtpd
mvn exec:java
```
Launch http://localhost:8080/async-war/

To stop the example, press `CTRL+C` in the terminal to close the `async-smtpd` application, then undeploy the `async-war` application, run the following commands:
```
cd ..
mvn cargo:undeploy
```