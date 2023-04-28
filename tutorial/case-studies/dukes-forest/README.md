Troubleshooting
---

In case you are unable to redeploy **duke-store** and get the following error:

```
remote failure: Error occurred during deployment: Exception while deploying the app [dukes-store] : Application validation fails for given application [dukes-store] for jndi-name [java:global/jms/OrderQueue]. Please see server.log for more details.
[INFO] [talledLocalContainer] Command deploy failed.
```

Restart the domain and deploy again
```
$GF_HOME/glassfish/bin/asadmin restart-domain
mvn clean install
```
Cleanup
---
```
mvn cargo:undeploy
$GF_HOME/glassfish/bin/asadmin delete-auth-realm jdbcRealm
```