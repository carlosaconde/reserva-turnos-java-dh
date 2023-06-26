FROM maven:3.8.4-openjdk-17 as builder

WORKDIR /app

COPY src ./src
COPY pom.xml .

RUN mvn clean package -DskipTests

FROM openjdk:17-jdk

WORKDIR /app

COPY --from=builder /app/target/reservaDeTurnos-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]