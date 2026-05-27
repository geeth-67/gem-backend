FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY src ./src
COPY pom.xml .
RUN apt-get update && apt-get install -y maven && mvn clean package
EXPOSE 8080
ENTRYPOINT ["java" , "-jar" , "target/gem-backend-0.0.1-SNAPSHOT.jar"]