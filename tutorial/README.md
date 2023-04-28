## Prerequisites

- Maven
- JDK8
- GlassFish 6

## Build the examples

Unzip GlassFish on the predefined directory depending on your OS:

* Windows: `c:\glassfish6`
* Unix: `$HOME/glassfish6`

You can also customize this path via the `glassfish.home.prefix` Maven property. Make sure to start with a clean domain.

Start GlassFish:
```
$GF_HOME/bin/asadmin start-domain
$GF_HOME/bin/asadmin start-database
```

Add a user to the `file` realm with the `TutorialUser`
```
$GF_HOME/bin/asadmin create-file-user --groups TutorialUser admin
```

Clone this repository and build the examples using:

```
mvn install
```
