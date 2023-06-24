FROM adoptopenjdk/openjdk11:alpine-slim
EXPOSE 7000
ADD target/alumnowebflux-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar","/app.jar"]