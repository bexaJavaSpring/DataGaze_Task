# Stage 1: Build the application
FROM maven:3.9-eclipse-temurin-21 AS build
ENV APP_HOME=/DataGaze_Task

WORKDIR $APP_HOME

# Copy source code and pom.xml
COPY pom.xml .
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Stage 2: Create a minimal runtime image
FROM eclipse-temurin:21-jre AS runtime
ENV APP_HOME=/DataGaze_Task

WORKDIR $APP_HOME

# Copy the JAR from the build stage
COPY --from=build $APP_HOME/target/DataGaze_Task-0.0.1-SNAPSHOT.jar $APP_HOME/app.jar

# Expose the application port
EXPOSE 7777

# Run the application
CMD ["java", "-jar", "app.jar"]
