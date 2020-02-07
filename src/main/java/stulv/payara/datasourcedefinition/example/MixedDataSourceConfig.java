package stulv.payara.datasourcedefinition.example;

import javax.annotation.sql.DataSourceDefinition;

@DataSourceDefinition(
    name = "java:app/jdbc/ds/mixed",
    className = "${ds.className}",
    serverName = "${MPCONFIG=ds.serverName}",
    databaseName = "${MPCONFIG=ds.databaseName}",
    user = "${MPCONFIG=ds.user}",
    password = "${MPCONFIG=ds.password}",
    properties = {"fish.payara.log-jdbc-calls=true"}
)
public class MixedDataSourceConfig {
}