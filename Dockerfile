FROM openjdk:17
VOLUME /tmp
EXPOSE 8080
ADD target/microservice1-0.0.1-SNAPSHOT.jar microservice1.jar

# Run the jar file
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/microservice1.jar"]