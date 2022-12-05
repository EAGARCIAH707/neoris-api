# https://hub.docker.com/_/maven
FROM maven:3.6.3-jdk-11 as builder

# Copy local code to the container image.
WORKDIR /app
COPY pom.xml .
COPY src ./src

# Build a release artifact.
RUN mvn package

FROM openjdk:11.0.7-slim-buster


# Copy the jar to the production image from the builder stage.
COPY --from=builder /app/target/api-*.jar /api.jar

# Run the web service on container startup.
CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/api.jar"]