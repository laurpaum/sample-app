FROM quay.io/quarkus/centos-quarkus-maven:22.0-java11 AS builder

COPY . /project/
RUN mvn clean package

FROM fabric8/java-alpine-openjdk8-jre

ENV JAVA_OPTIONS="-Dquarkus.http.host=0.0.0.0 -Djava.util.logging.manager=org.jboss.logmanager.LogManager"
ENV AB_ENABLED=jmx_exporter

COPY --from=builder target/*.jar /deployments/app.jar

ENTRYPOINT [ "/deployments/run-java.sh" ]