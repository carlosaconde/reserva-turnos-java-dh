FROM openjdk:17.0.2

WORKDIR /app

COPY ./target/reservaDeTurnos-0.0.1-SNAPSHOT.jar /app

EXPOSE 8080

CMD ["java", "-jar", "reservaDeTurnos-0.0.1-SNAPSHOT.jar"]