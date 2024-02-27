FROM openjdk:17-jdk-alpine as builder

WORKDIR /app/msvc-venta

COPY ./pom.xml /app
COPY ./msvc-venta/.mvn ./.mvn
COPY ./msvc-venta/mvnw .
COPY ./msvc-venta/pom.xml .

RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/

COPY ./msvc-venta/src ./src

RUN ./mvnw clean package -DskipTests

FROM openjdk:17-jdk-alpine

WORKDIR /app
RUN mkdir ./logs

COPY --from=builder /app/msvc-venta/target/msvc-venta-0.0.1-SNAPSHOT.jar .

EXPOSE 8004

CMD ["java","-jar","msvc-venta-0.0.1-SNAPSHOT.jar"]