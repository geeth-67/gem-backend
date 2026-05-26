FROM eclipe-temurin:21-jdk
WORKDIR /app
COPY src ./src
COPY pom.xml .
RUN apt-get update && apt-get install -y maven && mvn clean package
EXPOSE 8080
ENTRYPOINT ["java" , "-jar" , ""]