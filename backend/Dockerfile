## Runtime image
FROM maven:3.9.6-amazoncorretto-17 AS build

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests


## Runtime image
FROM amazoncorretto:17-alpine AS runtime

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

CMD ["java", "-jar", "app.jar"]
