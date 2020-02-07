package stulv.payara.datasourcedefinition.example;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("")
public class DataSourceStatusController {

    @GET
    public Map<String, String> getStatuses() {
        Map<String, String> dataSourceStatuses = new HashMap<String, String>();

        dataSourceStatuses.put("HardCodedDataSourceConfig.java"
                              , tryLoadingPersistenceUnit("hardcoded").toString());
        dataSourceStatuses.put("PropertyDataSourceConfig.java"
                              , tryLoadingPersistenceUnit("property").toString());
        dataSourceStatuses.put("ENVDataSourceConfig.java"
                              , tryLoadingPersistenceUnit("env").toString());
        dataSourceStatuses.put("MPCONFIGDataSourceConfig.java"
                              , tryLoadingPersistenceUnit("mpconfig").toString());
        dataSourceStatuses.put("MixedDataSourceConfig.java"
                              , tryLoadingPersistenceUnit("mixed").toString());

        return dataSourceStatuses;
    }

    public Status tryLoadingPersistenceUnit(String puName) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory(puName);
            emf.createEntityManager();
            return Status.SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return Status.FAILURE;
        }
    }

    public enum Status {
        SUCCESS,
        FAILURE;
    }
}