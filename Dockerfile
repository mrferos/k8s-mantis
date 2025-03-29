FROM eclipse-temurin:11-jre
COPY build/libs/k8s-mantis-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]