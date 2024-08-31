FROM gradle:8.5-jdk21 AS build

WORKDIR /app
COPY . .
RUN gradle build --no-daemon

FROM openjdk:21-jdk

WORKDIR /app
COPY --from=BUILD /app/build/libs/*.jar .

#CMD ["ls", "-la"]
ENTRYPOINT ["java", "-jar", "app-1.0.SNAPSHOT.jar"]
