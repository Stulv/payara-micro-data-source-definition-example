package stulv.payara.datasourcedefinition.example;

import javax.annotation.sql.DataSourceDefinition;

@DataSourceDefinition(
    name = "java:app/jdbc/ds/hardcoded",
    className = "org.postgresql.ds.PGConnectionPoolDataSource",
    serverName = "database",
    databaseName = "postgres",
    user = "postgres",
    password = "postgresdbpassword",
    properties = {"fish.payara.log-jdbc-calls=true"}
)
public class HardCodedDataSourceConfig {
}