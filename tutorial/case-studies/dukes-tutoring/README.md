Troubleshooting
---

In case you are unable to redeploy **dukes-tutoring-war** and get the following error:

```
[INFO] [talledLocalContainer] Command deploy failed.
[INFO] [talledLocalContainer] remote failure: Error occurred during deployment: Exception while deploying the app [dukes-tutoring-war] : Application validation fails for given application [dukes-tutoring-war] for jndi-name []. Please see server.log for more details.
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
$GF_HOME/glassfish/bin/asadmin delete-auth-realm tutoringRealm
```