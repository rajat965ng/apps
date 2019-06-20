from java:openjdk-8
workdir /app
copy target/rbs-app-one.jar .
entrypoint ["java", "-jar", "rbs-app-one.jar"]
expose 8080