FROM adoptopenjdk:11-jre-hotspot
COPY target/Authentication-Service-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]