FROM openjdk:17-jdk-alpine as builder

WORKDIR /app/msvc-negocio

COPY ./pom.xml /app
COPY ./msvc-negocio/.mvn ./.mvn
COPY ./msvc-negocio/mvnw .
COPY ./msvc-negocio/pom.xml .

RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/

COPY ./msvc-negocio/src ./src

RUN ./mvnw clean package -DskipTests

FROM openjdk:17-jdk-alpine

WORKDIR /app

RUN mkdir ./logs

COPY --from=builder /app/msvc-negocio/target/msvc-negocio-0.0.1-SNAPSHOT.jar .

EXPOSE 8005

CMD ["java","-jar","msvc-negocio-0.0.1-SNAPSHOT.jar"]