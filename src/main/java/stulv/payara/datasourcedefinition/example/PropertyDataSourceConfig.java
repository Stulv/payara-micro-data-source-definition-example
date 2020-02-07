package stulv.payara.datasourcedefinition.example;

import javax.annotation.sql.DataSourceDefinition;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

@DataSourceDefinition(
    name = "java:app/jdbc/ds/property",
    className = "${ds.className}",
    serverName = "${ds.serverName}",
    databaseName = "${ds.databaseName}",
    user = "${ds.user}",
    password = "${ds.password}",
    properties = {"fish.payara.log-jdbc-calls=true"}
)
public class PropertyDataSourceConfig {
    @PersistenceUnit(unitName= "property")
    private EntityManagerFactory emf;
}