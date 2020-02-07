package stulv.payara.datasourcedefinition.example;

import javax.annotation.sql.DataSourceDefinition;

@DataSourceDefinition(
    name = "java:app/jdbc/ds/env",
    className = "${ENV=DS_CLASSNAME}",
    serverName = "${ENV=DS_SERVERNAME}",
    databaseName = "${ENV=DS_DATABASENAME}",
    user = "${ENV=DS_USER}", 
    password = "${ENV=DS_PASSWORD}",
    properties = {"fish.payara.log-jdbc-calls=true"}
)
public class ENVDataSourceConfig {
}