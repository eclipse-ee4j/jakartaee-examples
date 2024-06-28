Jakarta EE kickoff app
===================

Basic project template to kickoff a new Jakarta EE or Java EE web application.

## Usage

Clone the branch matching your target environment into a new project. There is currently one branche available.

- [Jakarta EE 10 with Faces 4.0]


Fow WildFly 25 and higher Jakarta Authentication once again needs to be activated before the application can be deployed (it's a recurring theme for WilfFly it seems). See also https://stackoverflow.com/a/70240973/472792

## Database

The default app uses [H2](http://www.h2database.com) with `drop-and-create`. In other words, the default app uses an embedded database and is configured to drop and create all tables on startup. If you want to stop dropping all tables on startup, edit `jakarta.persistence.schema-generation.database.action` property of `/META-INF/persistence.xml` to `create`. If you want to change the database, install the desired JDBC driver in your target server and edit the JDBC driver configuration in `/META-INF/conf/datasource-settings.xml`. It's not necessary to manually create a data source in the target server as that's already done via `<data-source>` in `web.xml`.
