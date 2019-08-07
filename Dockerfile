FROM openjdk:8
RUN mkdir -p /opt/app
COPY target/vault.jar /opt/app
ENTRYPOINT ["java","-jar","/opt/app/vault.jar"]
EXPOSE 8080