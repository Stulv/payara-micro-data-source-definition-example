# Payara Micro @DataSourceDefinition example

Shows different ways of looking up configuration values in Payara using variable
references in the @DataSourceDefinition annotation.

[Config variable references in Payara.](https://docs.payara.fish/documentation/payara-server/server-configuration/var-substitution/usage-of-variables.html)

## Build & run using Docker

Build and run, this will also start a postgres container:
```
docker-compose -f payara-dsd-example-stack.yml up --build
```
The root endpoint in [DataSourceStatusController.java](src/main/java/stulv/payara/datasourcedefinition/example/DataSourceStatusController.java)
can be reached at [http://localhost:8080/](http://localhost:8080/).
It lists each data source configuration and if it loaded successfully or not.
```
{
    "HardCodedDataSourceConfig.java" :  "SUCCESS",
    PropertyDataSourceConfig.java" :    "SUCCESS",
    "MPCONFIGDataSourceConfig.java" :   "FAILURE",
    "ENVDataSourceConfig.java" :        "FAILURE",
    "MixedDataSourceConfig.java" :      "SUCCESS"
}
```

## [HardCodedDataSourceConfig.java](src/main/java/stulv/payara/datasourcedefinition/example/HardCodedDataSourceConfig.java)

``` java
@DataSourceDefinition(
    name = "java:app/jdbc/ds/hardcoded",
    className = "org.postgresql.ds.PGConnectionPoolDataSource",
    serverName = "database",
    databaseName = "postgres",
    user = "postgres",
    password = "postgresdbpassword",
    properties = {"fish.payara.log-jdbc-calls=true"}
)
```

## [PropertyDataSourceConfig.java](src/main/java/stulv/payara/datasourcedefinition/example/PropertyDataSourceConfig.java)

``` java
@DataSourceDefinition(
    name = "java:app/jdbc/ds/property",
    className = "${ds.className}",
    serverName = "${ds.serverName}",
    databaseName = "${ds.databaseName}",
    user = "${ds.user}",
    password = "${ds.password}",
    properties = {"fish.payara.log-jdbc-calls=true"}
)
```

## [MPCONFIGDataSourceConfig.java](src/main/java/stulv/payara/datasourcedefinition/example/MPCONFIGDataSourceConfig.java)

``` java
@DataSourceDefinition(
    name = "java:app/jdbc/ds/mpconfig",
    className = "${MPCONFIG=ds.className}",
    serverName = "${MPCONFIG=ds.serverName}",
    databaseName = "${MPCONFIG=ds.databaseName}",
    user = "${MPCONFIG=ds.user}",
    password = "${MPCONFIG=ds.password}",
    properties = {"fish.payara.log-jdbc-calls=true"}
)
```

## [ENVDataSourceConfig.java](src/main/java/stulv/payara/datasourcedefinition/example/ENVDataSourceConfig.java)

``` java
@DataSourceDefinition(
    name = "java:app/jdbc/ds/env",
    className = "${ENV=DS_CLASSNAME}",
    serverName = "${ENV=DS_SERVERNAME}",
    databaseName = "${ENV=DS_DATABASENAME}",
    user = "${ENV=DS_USER}", 
    password = "${ENV=DS_PASSWORD}",
    properties = {"fish.payara.log-jdbc-calls=true"}
)
```

## [MixedDataSourceConfig.java](src/main/java/stulv/payara/datasourcedefinition/example/MixedDataSourceConfig.java)

``` java
@DataSourceDefinition(
    name = "java:app/jdbc/ds/mixed",
    className = "${ds.className}",
    serverName = "${MPCONFIG=ds.serverName}",
    databaseName = "${MPCONFIG=ds.databaseName}",
    user = "${MPCONFIG=ds.user}",
    password = "${MPCONFIG=ds.password}",
    properties = {"fish.payara.log-jdbc-calls=true"}
)
```
