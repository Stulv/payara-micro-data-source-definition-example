FROM maven:3.6-jdk-8 as builder

ENV MAVEN_OPTS="-Xmx512m"

COPY pom.xml .
RUN mvn dependency:go-offline
COPY src src
RUN mvn package

# Prepare the runtime image
FROM payara/micro:5.194
ENV APP_NAME=payara-dsd-example
ENV DEPENDENCY_DIR=target

# Default DB connection
ENV DS_CLASSNAME="org.postgresql.ds.PGSimpleDataSource"
ENV DS_SERVERNAME="database"
ENV DS_DATABASENAME="postgres"
ENV DS_USER="postgres"
ENV DS_PASSWORD="postgresdbpassword"

ENV JAVA_OPTS -Dds.className=${DS_CLASSNAME} \
              -Dds.serverName=${DS_SERVERNAME} \
              -Dds.databaseName=${DS_DATABASENAME} \
              -Dds.user=${DS_USER} \
              -Dds.password=${DS_PASSWORD}

USER payara

COPY --from=builder ${DEPENDENCY_DIR}/${APP_NAME} ${DEPLOY_DIR}/${APP_NAME}

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /opt/payara/payara-micro.jar --deploy ${DEPLOY_DIR}/${APP_NAME} --contextroot /"]