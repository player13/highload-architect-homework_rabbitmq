FROM openjdk:17 AS build
RUN microdnf install findutils
COPY src /project/src
COPY gradle /project/gradle
COPY build.gradle.kts settings.gradle.kts gradlew /project
WORKDIR /project
RUN ./gradlew build --no-daemon

FROM openjdk:17
COPY --from=build /project/build/libs/*-boot.jar /app.jar
EXPOSE 8080:8080
CMD ["java", "-jar", "/app.jar"]
