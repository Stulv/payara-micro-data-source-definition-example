# Payara Micro @DataSourceDefinition example

Shows different ways of looking up configuration values in Payara using variable
references in the @DataSourceDefinition annotation.

[Using variable references in Payara configuration](https://docs.payara.fish/documentation/payara-server/server-configuration/var-substitution/usage-of-variables.html)

## Build & run

Build the image:
```
docker-compose -f payara-dsd-example-stack.yml build
```
Start:
```
docker-compose -f payara-dsd-example-stack.yml up
```
The root endpoint in [DataSourceStatusController.java](src/main/java/stulv/payara/datasourcedefinition/example/DataSourceStatusController.java)
can be reached at:  
[http://localhost:8080/](http://localhost:8080/)