FROM openjdk:17-jdk-alpine as builder

WORKDIR /app/msvc-cliente

COPY ./pom.xml /app
COPY ./msvc-cliente/.mvn ./.mvn
COPY ./msvc-cliente/mvnw .
COPY ./msvc-cliente/pom.xml .

RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/

COPY msvc-cliente/src ./src

RUN ./mvnw clean package -DskipTests

FROM openjdk:17-jdk-alpine

WORKDIR /app
RUN mkdir ./logs

COPY --from=builder /app/msvc-cliente/target/msvc-cliente-0.0.1-SNAPSHOT.jar .

EXPOSE 8002

CMD ["java","-jar","msvc-cliente-0.0.1-SNAPSHOT.jar"]