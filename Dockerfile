FROM gradle as build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle buildFatJar --no-daemon

FROM openjdk:17
EXPOSE 8080:8080

ENV MONGODB_CONNSTRING = "mongodb://mongodb/slaxnetwork"

RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar/ /app/ktor-app.jar/
ENTRYPOINT ["java", "-jar", "/app/ktor-app.jar"]